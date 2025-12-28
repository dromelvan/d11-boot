package org.d11.boot.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.spi.Registry;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.bean.CreateTransferWindow;
import org.d11.boot.camel.body.CreateTransferWindowExchangeBody;
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
 * Tests routes defined in CreateTransferWindowRouteBuilder.
 */
@ExtendWith(MockitoExtension.class)
class CreateTransferWindowRouteBuilderTests extends RouteBuilderTests {

    /**
     * CreateTransferWindowRouteBuilder that will be tested.
     */
    @InjectMocks
    private CreateTransferWindowRouteBuilder createTransferWindowRouteBuilder;

    /**
     * Mocked transfer window service.
     */
    @Mock
    private TransferWindowService transferWindowService;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        final Registry registry = this.context.getRegistry();
        registry.bind("createTransferWindow", CreateTransferWindow.class,
                      new CreateTransferWindow(this.transferWindowService));

        return this.createTransferWindowRouteBuilder;
    }

    /**
     * Tests Route.CREATE_TRANSFER_WINDOW bean validation.
     */
    @Test
    void testCreateTransferWindowBeanValidation() {
        final CreateTransferWindowExchangeBody body = new CreateTransferWindowExchangeBody();
        body.setDatetime(LocalDateTime.now().minusDays(1L));
        body.setTransferDayDelay(1);

        final BadRequestException badRequestException = new BadRequestException("", "");
        when(this.transferWindowService.createTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenThrow(badRequestException);

        Exchange exchange = send(Route.CREATE_TRANSFER_WINDOW, body);

        assertTrue(exchange.isFailed());
        assertEquals(badRequestException, exchange.getException());

        final ConflictException conflictException = new ConflictException("");
        when(this.transferWindowService.createTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenThrow(conflictException);

        exchange = send(Route.CREATE_TRANSFER_WINDOW, body);

        assertTrue(exchange.isFailed());
        assertEquals(conflictException, exchange.getException());

        final TransferWindow transferWindow = new TransferWindow();
        when(this.transferWindowService.createTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenReturn(transferWindow);

        exchange = send(Route.CREATE_TRANSFER_WINDOW, body);

        assertFalse(exchange.isFailed());
        assertEquals(transferWindow,
                     exchange.getIn().getBody(CreateTransferWindowExchangeBody.class).getTransferWindow());
    }

}
