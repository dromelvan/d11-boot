package org.d11.boot.jms.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.d11.boot.camel.Route;
import org.d11.boot.jms.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Consumer of messages from the test JMS queue.
 */
@Slf4j
@Component
public class TestQueueConsumer extends JmsQueueConsumer<TextMessage> {

    /**
     * Creates a new test queue consumer.
     *
     * @param producerTemplate Camel producer template.
     */
    @Autowired
    public TestQueueConsumer(final ProducerTemplate producerTemplate) {
        super(producerTemplate);
    }

    /**
     * Consumes a message from the test JMS queue by sending it to the Camel test route.
     *
     * @param textMessage The text message.
     */
    @JmsListener(destination = TEST_QUEUE_DESTINATION)
    public void consume(final TextMessage textMessage) {
        LOGGER.info("Consumed text message: {}", textMessage);
        sendBody(Route.RECEIVE_TEST.getEndpoint(), textMessage);
        jmsMessageConsumed(textMessage);
    }

}
