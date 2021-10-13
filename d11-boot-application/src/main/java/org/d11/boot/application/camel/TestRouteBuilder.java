package org.d11.boot.application.camel;

import org.d11.boot.camel.AbstractJmsRouteBuilder;
import org.d11.boot.jms.JmsQueue;
import org.springframework.stereotype.Component;

/**
 * Builds a test route.
 */
@Component
public class TestRouteBuilder extends AbstractJmsRouteBuilder {

    /**
     * Creates a new route builder.
     */
    public TestRouteBuilder() {
        super(JmsQueue.TEST);
    }

    @Override
    public void configure() {
        from(getSource())
                .unmarshal(getSourceDataFormat())
                .log("${body.datetime}");
    }

}
