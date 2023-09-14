package org.d11.boot.camel.route;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.bean.InsertTransferWindow;
import org.springframework.stereotype.Component;

/**
 * Builds an insert transfer window route.
 */
@Component
public class InsertTransferWindowRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() {
        configureOnException();

        final Route route = Route.INSERT_TRANSFER_WINDOW;

        // Direct route that inserts a new transfer window
        from(route.getEndpoint())
                // Set route id
                .id(route.getId())
                .log(LoggingLevel.INFO, "Starting transfer window insert...")
                .bean(InsertTransferWindow.class)
                .log(LoggingLevel.INFO, "Transfer window insert finished.");
    }

}
