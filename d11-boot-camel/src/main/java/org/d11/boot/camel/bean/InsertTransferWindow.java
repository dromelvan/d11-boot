package org.d11.boot.camel.bean;

import org.apache.camel.Handler;
import org.d11.boot.camel.body.InsertTransferWindowExchangeBody;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.service.TransferWindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Camel bean that inserts a new transfer window.
 */
@Component
public class InsertTransferWindow {

    /**
     * Service used to insert transfer windows.
     */
    private final TransferWindowService transferWindowService;

    /**
     * Creates a new bean.
     *
     * @param transferWindowService Service used to insert transfer windows.
     */
    @Autowired
    public InsertTransferWindow(final TransferWindowService transferWindowService) {
        this.transferWindowService = transferWindowService;
    }

    /**
     * Inserts a transfer window.
     *
     * @param insertTransferWindowExchangeBody Body with transfer window properties.
     */
    @Handler
    public void handle(final InsertTransferWindowExchangeBody insertTransferWindowExchangeBody) {
        final TransferWindow transferWindow =
                this.transferWindowService.insertTransferWindow(insertTransferWindowExchangeBody.getDatetime(),
                                                                insertTransferWindowExchangeBody.getTransferDayDelay());

        insertTransferWindowExchangeBody.setTransferWindow(transferWindow);
    }

}
