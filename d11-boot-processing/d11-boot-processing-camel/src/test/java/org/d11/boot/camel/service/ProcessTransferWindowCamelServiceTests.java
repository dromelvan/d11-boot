package org.d11.boot.camel.service;

import org.apache.camel.ProducerTemplate;
import org.d11.boot.camel.Route;
import org.d11.boot.camel.body.CreateTransferWindowExchangeBody;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.service.BaseD11BootServiceTests;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Process transfer window service tests.
 */
class ProcessTransferWindowCamelServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked producer template.
     */
    @Mock
    private ProducerTemplate producerTemplate;

    /**
     * Process transfer window service.
     */
    @InjectMocks
    private ProcessTransferWindowCamelService processTransferWindowCamelService;

    /**
     * Tests ProcessTransferWindowCamelService::createTransferWindow.
     */
    @Test
    void testCreateTransferWindow() {
        final TransferWindow transferWindow = generate(TransferWindow.class);
        final CreateTransferWindowExchangeBody body = new CreateTransferWindowExchangeBody();
        body.setTransferWindow(transferWindow);

        when(this.producerTemplate.requestBody(eq(Route.CREATE_TRANSFER_WINDOW.getEndpoint()),
                                               any(CreateTransferWindowExchangeBody.class),
                                               eq(CreateTransferWindowExchangeBody.class)))
                .thenReturn(body);

        final TransferWindow result =
                this.processTransferWindowCamelService.createTransferWindow(LocalDateTime.now(), 1);

        assertNotNull(result, "ProcessTransferWindowCamelService::createTransferWindow not null");
        assertEquals(transferWindow, result, "ProcessTransferWindowCamelService::createTransferWindow equals");
    }

}
