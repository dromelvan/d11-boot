package org.d11.boot.camel.route;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.bean.CreateTransferWindow;
import org.springframework.stereotype.Component;

/**
 * Builds a create transfer window route.
 */
@Component
public class CreateTransferWindowRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() {
        configureOnException();

        final Route route = Route.CREATE_TRANSFER_WINDOW;

        // Direct route that creates a new transfer window
        from(route.getEndpoint())
                // Set route id
                .id(route.getId())
                .log(LoggingLevel.INFO, "Create transfer window starting...")
                .bean(CreateTransferWindow.class)
                .log(LoggingLevel.INFO, "Create transfer window finished.");
    }

}
