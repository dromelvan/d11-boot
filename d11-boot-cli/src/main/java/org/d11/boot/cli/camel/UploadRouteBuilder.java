package org.d11.boot.cli.camel;

import org.apache.camel.LoggingLevel;
import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.cli.camel.processor.ShutdownProcessor;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a route that reads a json file and sends the content to an ActiveMQ update match stats queue.
 */
@Component
public class UploadRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new upload route builder.
     */
    protected UploadRouteBuilder() {
        super(null, JmsQueue.UPDATE_MATCH);
    }

    @Override
    public void configure() {
        from("{{app.route.upload}}")
                .routeId("UPLOAD_MATCH")
                // Body is File.
                .log(LoggingLevel.INFO, "Uploading file ${body.name}")
                .convertBodyTo(String.class)
                // Body is JSON String representation of the match update data.
                .to(getDestination())
                .log(LoggingLevel.INFO, "File uploaded to " + getDestination() + " at {{spring.activemq.broker-url}}.")
                .process(new ShutdownProcessor());
    }
}
