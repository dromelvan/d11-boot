package org.d11.boot.application.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.application.service.camel.UpdateSquadService;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a route to update team squads.
 */
@Component
public class UpdateSquadRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new route builder.
     */
    public UpdateSquadRouteBuilder() {
        super(JmsQueue.UPDATE_SQUAD);
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                .log(LoggingLevel.DEBUG, "Processing message: ${body}")
                .unmarshal(getSourceDataFormat())
                .bean(UpdateSquadService.class, "updateSquad(${body.teamData})");
    }

}
