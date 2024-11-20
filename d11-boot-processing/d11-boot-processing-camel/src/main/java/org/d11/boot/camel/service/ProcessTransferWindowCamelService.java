package org.d11.boot.camel.service;

import org.apache.camel.ProducerTemplate;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.body.CreateTransferWindowExchangeBody;
import org.d11.boot.processing.service.ProcessTransferWindowService;
import org.d11.boot.spring.model.TransferWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Process transfer window service.
 */
@Service
public class ProcessTransferWindowCamelService implements ProcessTransferWindowService {

    /**
     * Producer template.
     */
    private final ProducerTemplate producerTemplate;

    /**
     * Creates a new process transfer window service.
     *
     * @param producerTemplate Producer template.
     */
    @Autowired
    public ProcessTransferWindowCamelService(final ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public TransferWindow createTransferWindow(final LocalDateTime datetime, final int transferDayDelay) {
        final CreateTransferWindowExchangeBody body = new CreateTransferWindowExchangeBody();
        body.setDatetime(datetime);
        body.setTransferDayDelay(transferDayDelay);

        return this.producerTemplate
                .requestBody(Route.CREATE_TRANSFER_WINDOW.getEndpoint(), body, CreateTransferWindowExchangeBody.class)
                .getTransferWindow();
    }

}
