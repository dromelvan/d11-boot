package org.d11.boot.camel.bean;

import org.d11.boot.camel.body.InsertTransferWindowExchangeBody;
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
 * Tests the insert transfer window bean.
 */
@ExtendWith(MockitoExtension.class)
class InsertTransferWindowTests {

    /**
     * Mocked transfer window service.
     */
    @Mock
    private TransferWindowService transferWindowService;

    /**
     * Tests InsertTransferWindow::handle.
     */
    @Test
    void testInsertTransferWindow() {
        final InsertTransferWindowExchangeBody body = new InsertTransferWindowExchangeBody();
        body.setDatetime(LocalDateTime.now().plusDays(1));
        body.setTransferDayDelay(1);

        final TransferWindow transferWindow = new TransferWindow();

        when(this.transferWindowService.insertTransferWindow(eq(body.getDatetime()), eq(body.getTransferDayDelay())))
                .thenReturn(transferWindow);

        final InsertTransferWindow insertTransferWindow = new InsertTransferWindow(this.transferWindowService);
        insertTransferWindow.handle(body);

        assertNotNull(body.getTransferWindow(), "InsertTransferWindow::handle not null");
        assertEquals(transferWindow, body.getTransferWindow(), "InsertTransferWindow::handle equals");
    }

}
