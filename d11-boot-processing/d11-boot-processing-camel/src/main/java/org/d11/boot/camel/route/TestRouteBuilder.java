package org.d11.boot.camel.route;

import org.d11.boot.camel.bean.HandleTest;
import org.d11.boot.camel.Route;
import org.springframework.stereotype.Component;

/**
 * Builds a test route.
 */
@Component
public class TestRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() {
        // Direct route that logs the input
        from(Route.RECEIVE_TEST.getEndpoint())
                .id(Route.RECEIVE_TEST.getId())
                .to("log:test");

        // Direct route that handles a test route body. How that is done is decided by the implementation of
        // HandleTest.
        from(Route.HANDLE_TEST.getEndpoint())
                .id(Route.HANDLE_TEST.getId())
                .bean(HandleTest.class);
    }

}
