package org.d11.boot.application.camel;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.camel.LoggingLevel;
import org.d11.boot.application.service.camel.UpdateMatchService;
import org.d11.boot.application.service.camel.UpdateStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Builds a route that performs a match update.
 */
@Component
public class UpdateMatchRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new route builder.
     */
    @Autowired
    public UpdateMatchRouteBuilder() {
        super(JmsQueue.UPDATE_MATCH, null);
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                .doTry()
                    .log(LoggingLevel.DEBUG, "Processing message: ${body}")
                    .unmarshal(getSourceDataFormat())
                    // Body is UpdateMatchMessage
                    .log(LoggingLevel.INFO,
                            "Updating match ${body.matchData.homeTeamName} vs ${body.matchData.awayTeamName} (${body.matchData.matchId}).")
                    .bean(UpdateMatchService.class, "updateMatchData(${body.matchData}, ${body.finish})")
                    .bean(UpdateStatsService.class, "updateStats(${body.matchData})")
                    .log(LoggingLevel.INFO,
                            "Match ${body.matchData.homeTeamName} vs ${body.matchData.awayTeamName} (${body.matchData.matchId}) update complete.")
                .doCatch(JsonParseException.class)
                    .log(LoggingLevel.ERROR, "Could not unmarshal message: ${body}.")
                .end();
    }

}
