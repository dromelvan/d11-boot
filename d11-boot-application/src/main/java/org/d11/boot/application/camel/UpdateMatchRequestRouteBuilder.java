package org.d11.boot.application.camel;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.camel.LoggingLevel;
import org.d11.boot.application.service.camel.UpdateMatchService;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.jms.JmsQueue;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Builds a route that reads from the update match request JMS queue, finds the match and posts to the download
 * WhoScored match queue. This route mostly exists so we can trigger match updates externally using only the D11 id
 * instead of having to look up the WhoScored id.
 */
//@Component -- Disable this since we are switching to Fotmob with the new Python updater
public class UpdateMatchRequestRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new route builder.
     */
    @Autowired
    public UpdateMatchRequestRouteBuilder() {
        super(JmsQueue.UPDATE_MATCH_REQUEST, JmsQueue.DOWNLOAD_WHOSCORED_MATCH);
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                .doTry()
                    .log(LoggingLevel.DEBUG, "Processing message: ${body}")
                    .unmarshal(getSourceDataFormat())
                    .log(LoggingLevel.DEBUG, "Finding match ids for match ${body.matchId}.")
                    .bean(UpdateMatchService.class, "getDownloadWhoscoredMatchMessage(${body.matchId}, ${body.finish})")
                    .marshal(getDestinationDataFormat())
                    .log(LoggingLevel.DEBUG, "Forwarding message ${body} to " + getDestination() + ".")
                    .to(getDestination())
                    .log(LoggingLevel.DEBUG, "Message processing finished with result: ${body}")
                .doCatch(JsonParseException.class)
                    .log(LoggingLevel.ERROR, "Could not unmarshal message: ${body}")
                .doCatch(NotFoundException.class)
                    .log(LoggingLevel.ERROR, "Match ${body.matchId} not found.")
                .end();
    }

}
