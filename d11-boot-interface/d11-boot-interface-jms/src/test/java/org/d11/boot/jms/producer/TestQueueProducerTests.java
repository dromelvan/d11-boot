package org.d11.boot.jms.producer;

import org.d11.boot.jms.JmsQueueComponent;
import org.d11.boot.jms.message.TextMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test queue producer tests.
 */
@ExtendWith(MockitoExtension.class)
class TestQueueProducerTests {

    /**
     * Text message for testing.
     */
    private static final TextMessage TEXT_MESSAGE = new TextMessage("Text", LocalDateTime.now());

    /**
     * Mocked JMS template.
     */
    @Mock
    private JmsTemplate jmsTemplate;

    /**
     * Tests TestQueueProducer::produce.
     */
    @Test
    void testProduce() {
        final TestQueueProducer testQueueProducer = new TestQueueProducer(this.jmsTemplate);

        testQueueProducer.produce(TEXT_MESSAGE);

        verify(this.jmsTemplate, times(1)).convertAndSend(eq(JmsQueueComponent.TEST_QUEUE_DESTINATION),
                                                          eq(TEXT_MESSAGE));
    }

    /**
     * Tests TestQueueProducer::handle.
     */
    @Test
    void testHandle() {
        final TestQueueProducer testQueueProducer = new TestQueueProducer(this.jmsTemplate);

        testQueueProducer.handle(TEXT_MESSAGE);

        verify(this.jmsTemplate, times(1)).convertAndSend(eq(JmsQueueComponent.TEST_QUEUE_DESTINATION),
                                                          eq(TEXT_MESSAGE));
    }

}
