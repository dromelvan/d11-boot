package org.d11.boot.jms.producer;

import org.d11.boot.jms.JmsComponentIntegrationTests;
import org.d11.boot.jms.JmsQueueComponent;
import org.d11.boot.jms.message.TextMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test queue producer tests.
 */
class TestQueueProducerIntegrationTests extends JmsComponentIntegrationTests {

    /**
     * Test queue producer bean.
     */
    @Autowired
    private TestQueueProducer testQueueProducer;

    /**
     * JMS template for consuming messages.
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Stop listeners to prevent the TestQueueConsumer bean from consuming test messages.
     */
    @BeforeEach
    public void beforeEach() {
        stopListeners();
    }

    /**
     * Start all listeners again.
     */
    @AfterEach
    public void afterEach() {
        startListeners();
    }

    /**
     * Tests TestQueueProducer.
     */
    @Test
    void testTestQueueProducer() {
        final TextMessage sent = new TextMessage("TextMessage", LocalDateTime.now());
        this.testQueueProducer.produce(sent);

        final TextMessage received =
                (TextMessage) this.jmsTemplate.receiveAndConvert(JmsQueueComponent.TEST_QUEUE_DESTINATION);

        assertNotNull(received, "TestQueueProducer received not null");
        assertEquals(sent.getText(), received.getText(), "TestQueueProducer received text equals");
        assertEquals(sent.getDatetime(), received.getDatetime(), "TestQueueProducer received datetime equals");
    }

}
