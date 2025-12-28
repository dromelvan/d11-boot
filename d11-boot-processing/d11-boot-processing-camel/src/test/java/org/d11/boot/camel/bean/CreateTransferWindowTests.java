package org.d11.boot.camel.bean;

import org.d11.boot.camel.body.CreateTransferWindowExchangeBody;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.service.TransferWindowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Tests the create transfer window bean.
 */
@ExtendWith(MockitoExtension.class)
class CreateTransferWindowTests {

    /**
     * Mocked transfer window service.
     */
    @Mock
    private TransferWindowService transferWindowService;

    /**
     * Tests CreateTransferWindow::handle.
     */
    @Test
    void testCreateTransferWindow() {
        final CreateTransferWindowExchangeBody body = new CreateTransferWindowExchangeBody();
        body.setDatetime(LocalDateTime.now().plusDays(1));
        body.setTransferDayDelay(1);

        final TransferWindow transferWindow = new TransferWindow();

        when(this.transferWindowService.createTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenReturn(transferWindow);

        final CreateTransferWindow createTransferWindow = new CreateTransferWindow(this.transferWindowService);
        createTransferWindow.handle(body);

        assertNotNull(body.getTransferWindow());
        assertEquals(transferWindow, body.getTransferWindow());
    }

}
