package org.d11.boot.jms.consumer;

import org.d11.boot.jms.JmsComponentIntegrationTests;
import org.d11.boot.jms.JmsQueueComponent;
import org.d11.boot.jms.message.TextMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test queue consumer tests.
 */
class TestQueueConsumerIntegrationTests extends JmsComponentIntegrationTests {

    /**
     * JMS template for producing messages.
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Test queue consumer bean.
     */
    @Autowired
    private TestQueueConsumer testQueueConsumer;

    /**
     * Tests TestQueueConsumer.
     */
    @Test
    void testTestQueueConsumer() {
        final TextMessage sent = new TextMessage("TextMessage", LocalDateTime.now());
        final JmsQueueConsumerTestListener<TextMessage> listener = new JmsQueueConsumerTestListener<>();
        this.testQueueConsumer.addJmsQueueConsumerListener(listener);

        this.jmsTemplate.convertAndSend(JmsQueueComponent.TEST_QUEUE_DESTINATION, sent);

        awaitUntil(() -> listener.getJmsMessage() != null);

        final TextMessage received = listener.getJmsMessage();

        assertNotNull(received);
        assertEquals(sent.getText(), received.getText());
        assertEquals(sent.getDatetime(), received.getDatetime());
    }

}
