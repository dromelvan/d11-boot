package org.d11.boot.cli.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.cli.camel.processor.ShutdownProcessor;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a route that downloads Premier League squads and sends the data for each team to an ActiveMQ update squad queue.
 */
@Component
public class UpdateSquadsRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new update squads route builder.
     */
    public UpdateSquadsRouteBuilder() {
        super(null, JmsQueue.UPDATE_SQUADS_REQUEST);
    }

    @Override
    public void configure() {
        from("{{app.route.updateSquads}}")
                .routeId("UPDATE_SQUADS")
                .log(LoggingLevel.INFO, "Starting team squad updates...!")
                .marshal(getDestinationDataFormat())
                .to(getDestination())
                .log(LoggingLevel.INFO, "Team squad updates started.")
                .process(new ShutdownProcessor());
    }

}
