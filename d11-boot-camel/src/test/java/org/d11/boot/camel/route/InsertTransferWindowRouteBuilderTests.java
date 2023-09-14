package org.d11.boot.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.spi.Registry;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.bean.InsertTransferWindow;
import org.d11.boot.camel.body.InsertTransferWindowExchangeBody;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.service.TransferWindowService;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Tests routes defined in InsertTransferWindowRouteBuilder.
 */
@ExtendWith(MockitoExtension.class)
class InsertTransferWindowRouteBuilderTests extends RouteBuilderTests {

    /**
     * InsertTransferWindowRouteBuilder that will be tested.
     */
    @InjectMocks
    private InsertTransferWindowRouteBuilder insertTransferWindowRouteBuilder;

    /**
     * Mocked transfer window service.
     */
    @Mock
    private TransferWindowService transferWindowService;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        final Registry registry = this.context.getRegistry();
        registry.bind("insertTransferWindow", InsertTransferWindow.class,
                      new InsertTransferWindow(this.transferWindowService));

        return this.insertTransferWindowRouteBuilder;
    }

    /**
     * Tests Route.INSERT_TRANSFER_WINDOW bean validation.
     */
    @Test
    void testInsertTransferWindowBeanValidation() {
        final InsertTransferWindowExchangeBody body = new InsertTransferWindowExchangeBody();
        body.setDatetime(LocalDateTime.now().minusDays(1L));
        body.setTransferDayDelay(1);

        final BadRequestException badRequestException = new BadRequestException("", "");
        when(this.transferWindowService.insertTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenThrow(badRequestException);

        Exchange exchange = send(Route.INSERT_TRANSFER_WINDOW, body);

        assertTrue(exchange.isFailed(), "Route.INSERT_TRANSFER_WINDOW isFailed BadRequestException");
        assertEquals(badRequestException, exchange.getException(),
                     "Route.INSERT_TRANSFER_WINDOW exception BadRequestException");

        final ConflictException conflictException = new ConflictException("");
        when(this.transferWindowService.insertTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenThrow(conflictException);

        exchange = send(Route.INSERT_TRANSFER_WINDOW, body);

        assertTrue(exchange.isFailed(), "Route.INSERT_TRANSFER_WINDOW isFailed ConflictException");
        assertEquals(conflictException, exchange.getException(),
                     "Route.INSERT_TRANSFER_WINDOW exception ConflictException");

        final TransferWindow transferWindow = new TransferWindow();
        when(this.transferWindowService.insertTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenReturn(transferWindow);

        exchange = send(Route.INSERT_TRANSFER_WINDOW, body);

        assertFalse(exchange.isFailed(), "Route.INSERT_TRANSFER_WINDOW isFailed");
        assertEquals(transferWindow,
                     exchange.getIn().getBody(InsertTransferWindowExchangeBody.class).getTransferWindow(),
                     "Route.INSERT_TRANSFER_WINDOW transferWindow equals");
    }

}
