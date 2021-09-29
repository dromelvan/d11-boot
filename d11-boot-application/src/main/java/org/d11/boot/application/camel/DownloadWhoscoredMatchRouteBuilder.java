package org.d11.boot.application.camel;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.camel.LoggingLevel;
import org.d11.boot.application.camel.processor.DownloadWhoscoredMatchProcessor;
import org.d11.boot.application.camel.processor.MatchDataProcessor;
import org.d11.boot.application.camel.processor.WebPageProcessor;
import org.d11.boot.download.whoscored.WhoscoredMatchDownloader;
import org.d11.boot.parser.match.MatchParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Builds a route to download WhoScored match pages.
 */
@Component
public class DownloadWhoscoredMatchRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Route throttle delay. Move this to config file when we can be bothered.
     */
    private static final int THROTTLE_DELAY = 10_000;

    /**
     * Creates a new route builder.
     */
    @Autowired
    public DownloadWhoscoredMatchRouteBuilder() {
        super(JmsQueue.DOWNLOAD_WHOSCORED_MATCH, JmsQueue.UPDATE_MATCH);
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                // Throttle the route to avoid triggering Whoscored flood protection.
                .throttle(1).timePeriodMillis(THROTTLE_DELAY)
                .doTry()
                    .log(LoggingLevel.DEBUG, "Processing message: ${body}")
                    .unmarshal(getSourceDataFormat())
                    // Body is DownloadMatchRequestMessage. Processor sets 'matchWeekDirectory', 'matchId' and 'finish' properties
                    .process(new DownloadWhoscoredMatchProcessor())
                    .log(LoggingLevel.INFO, "Downloading match ${body.id}, WhoScored id ${body.whoscoredId}.")
                    .bean(WhoscoredMatchDownloader.class, "downloadMatch(${body.whoscoredId})")
                    // Body is WebPage
                    .log(LoggingLevel.INFO, "Downloaded web page ${body.title}.")
                    // Processor sets 'downloadFileName' property and sets body to WebPage.pageSource
                    .process(new WebPageProcessor())
                    // Body is String containing web page source
                    .log(LoggingLevel.INFO,
                            "Saving file ${exchangeProperty.downloadFileName} to /files/download/${exchangeProperty.matchWeekDirectory}.")
                    .toD("file://files/download/${exchangeProperty.matchWeekDirectory}?fileName=${exchangeProperty.downloadFileName}")
                    .bean(MatchParser.class, "parse(${body})")
                    // Body is MatchData
                    // Processor sets 'dataFileName' property and creates UpdateMatch from MatchData body and the 'finish' exchange property
                    .process(new MatchDataProcessor())
                    // Body is UpdateMatchMessage
                    .marshal(getDestinationDataFormat())
                    // Body is JSON String representation of the match update data
                    .log(LoggingLevel.INFO,
                            "Saving file ${exchangeProperty.dataFileName} to /files/data/${exchangeProperty.matchWeekDirectory}.")
                    .toD("file://files/data/${exchangeProperty.matchWeekDirectory}?fileName=${exchangeProperty.dataFileName}")
                    .log(LoggingLevel.DEBUG, "Forwarding message ${body} to " + getDestination() + ".")
                    .to(getDestination())
                    .log(LoggingLevel.DEBUG, "Message processing finished with result: ${body}")
                .doCatch(JsonParseException.class)
                    .log(LoggingLevel.ERROR, "Could not unmarshal message: ${body}.")
                .end();

    }

}
