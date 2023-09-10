package org.d11.boot.jms;

import org.apache.camel.builder.RouteBuilder;
import org.d11.boot.camel.Route;
import org.springframework.stereotype.Component;

/**
 * Implements dummy Camel routes for JMS component integration tests.
 */
@Component
public class IntegrationRouteBuilder extends RouteBuilder {

    /**
     * Endpoint for sending message to nowhere.
     */
    private static final String STUB_NOWHERE = "stub:nowhere";

    @Override
    public void configure() {
        from(Route.RECEIVE_TEST.getEndpoint())
                .to(STUB_NOWHERE);

        from(Route.HANDLE_TEST.getEndpoint())
                .to(STUB_NOWHERE);
    }

}
