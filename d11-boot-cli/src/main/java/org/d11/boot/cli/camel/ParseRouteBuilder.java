package org.d11.boot.cli.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.AbstractRouteBuilder;
import org.d11.boot.camel.processor.MatchDataProcessor;
import org.d11.boot.camel.processor.MatchFileProcessor;
import org.d11.boot.jms.message.UpdateMatchMessage;
import org.d11.boot.parser.match.MatchParser;
import org.springframework.stereotype.Component;

/**
 * Builds a route to parse a WhoScored match file and save the json output to a new file.
 */
@Component
public class ParseRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() {
        from("{{app.route.parse}}")
                .routeId("PARSE_MATCH")
                // Body is File. Processor sets 'matchWeekDirectory' and 'finish' properties.
                .process(new MatchFileProcessor())
                .bean(MatchParser.class, "parse(${body})")
                // Body is MatchData
                // Processor sets 'dataFileName' property and creates UpdateMatchMessage from MatchData body and the
                // 'finish' exchange property
                .process(new MatchDataProcessor())
                .marshal(getDataFormat(UpdateMatchMessage.class, true))
                // Body is JSON String representation of the match update data
                .log(LoggingLevel.INFO,
                        "Saving file ${exchangeProperty.dataFileName} to ${exchangeProperty.matchWeekDirectory}.")
                .toD("file://${exchangeProperty.matchWeekDirectory}?fileName=${exchangeProperty.dataFileName}");
    }

}
