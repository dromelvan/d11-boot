package org.d11.boot.application.camel;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.camel.LoggingLevel;
import org.d11.boot.application.service.camel.InsertTransferWindowService;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a route that inserts a transfer window.
 */
@Component
public class InsertTransferWindowRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new insert transfer window route builder.
     */
    public InsertTransferWindowRouteBuilder() {
        super(JmsQueue.INSERT_TRANSFER_WINDOW);
    }

    @Override
    public void configure() {
        from(getSource())
                .routeId(getRouteId())
                .doTry()
                    .log(LoggingLevel.DEBUG, "Processing message: ${body}")
                    .log(LoggingLevel.INFO, "Starting transfer window insert...")
                    .unmarshal(getSourceDataFormat())
                    // Body is InsertTransferWindowMessage
                    .bean(InsertTransferWindowService.class)
                    .log(LoggingLevel.INFO, "Transfer window insert finished.")
                .doCatch(JsonParseException.class)
                    .log(LoggingLevel.ERROR, "Could not unmarshal message: ${body}.")
                .end();
    }

}
