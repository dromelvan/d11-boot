package org.d11.boot.application.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.application.service.camel.UpdatePlayerService;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a route to update player info.
 */
@Component
public class UpdatePlayersRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new route builder.
     */
    public UpdatePlayersRouteBuilder() {
        super(JmsQueue.UPDATE_PLAYERS);
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                .log(LoggingLevel.DEBUG, "Processing message: ${body}")
                .unmarshal(getSourceDataFormat())
                .bean(UpdatePlayerService.class, "updatePlayers(${body.players})");
    }

}
