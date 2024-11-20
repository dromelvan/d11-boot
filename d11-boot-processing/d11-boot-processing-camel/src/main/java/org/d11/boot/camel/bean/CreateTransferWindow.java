package org.d11.boot.camel.bean;

import org.apache.camel.Handler;
import org.d11.boot.camel.body.CreateTransferWindowExchangeBody;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.service.TransferWindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Camel bean that creates a new transfer window.
 */
@Component
public class CreateTransferWindow {

    /**
     * Service used to create transfer windows.
     */
    private final TransferWindowService transferWindowService;

    /**
     * Creates a new bean.
     *
     * @param transferWindowService Service used to create transfer windows.
     */
    @Autowired
    public CreateTransferWindow(final TransferWindowService transferWindowService) {
        this.transferWindowService = transferWindowService;
    }

    /**
     * Creates a transfer window.
     *
     * @param createTransferWindowExchangeBody Body with transfer window properties.
     */
    @Handler
    public void handle(final CreateTransferWindowExchangeBody createTransferWindowExchangeBody) {
        final TransferWindow transferWindow =
                this.transferWindowService.createTransferWindow(createTransferWindowExchangeBody.getDatetime(),
                                                                createTransferWindowExchangeBody.getTransferDayDelay());

        createTransferWindowExchangeBody.setTransferWindow(transferWindow);
    }

}
