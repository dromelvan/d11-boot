package org.d11.boot.camel.route;

import lombok.SneakyThrows;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.d11.boot.camel.Route;

/**
 * Base class for route builders.
 */
public class RouteBuilderTests extends CamelTestSupport {

    /**
     * Mocked endpoint prefix.
     */
    protected static final String MOCK = "mock:";

    /**
     * Mocks an endpoint for a route.
     *
     * @param route    The route definition.
     * @param endpoint The endpoint URI.
     * @return Mocked endpoint for the route and the endpoint.
     */
    @SneakyThrows
    protected MockEndpoint mockEndpoint(final Route route, final String endpoint) {
        final MockEndpoint mockEndpoint = getMockEndpoint(MOCK + endpoint);

        AdviceWith.adviceWith(this.context, route.getId(), advice ->
                advice.interceptSendToEndpoint(endpoint)
                        .skipSendToOriginalEndpoint()
                        .to(MOCK + endpoint)
        );

        return mockEndpoint;
    }

}
