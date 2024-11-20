package org.d11.boot.camel.route;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spi.Registry;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.bean.HandleTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests routes defined in TestRouteBuilder.
 */
@ExtendWith(MockitoExtension.class)
class TestRouteBuilderTests extends RouteBuilderTests {

    /**
     * TestRouteBuilder that will be tested.
     */
    @InjectMocks
    private TestRouteBuilder testRouteBuilder;

    /**
     * Mocked HandleTest bean.
     */
    @Mock
    private HandleTest handleTest;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        final Registry registry = this.context.getRegistry();
        registry.bind("handleTest", HandleTest.class, this.handleTest);

        return this.testRouteBuilder;
    }

    /**
     * Tests Route.RECEIVE_TEST.
     */
    @Test
    void testReceiveTest() throws Exception {
        try (MockEndpoint mockEndpoint = mockEndpoint(Route.RECEIVE_TEST, "log:test")) {
            final String body = Route.RECEIVE_TEST.getEndpoint();
            mockEndpoint.expectedMessageCount(1);
            mockEndpoint.expectedBodiesReceived(body);
            template.sendBody(Route.RECEIVE_TEST.getEndpoint(), body);
            mockEndpoint.assertIsSatisfied();
        }
    }

    /**
     * Tests Route.HANDLE_TEST.
     */
    @Test
    void testHandleTest() {
        final String body = Route.HANDLE_TEST.getEndpoint();
        template.sendBody(Route.HANDLE_TEST.getEndpoint(), body);

        verify(this.handleTest, times(1)).handle(eq(body));
    }

}
