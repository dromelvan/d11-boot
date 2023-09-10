package org.d11.boot.jms.consumer;

import org.apache.camel.ProducerTemplate;
import org.d11.boot.camel.Route;
import org.d11.boot.jms.message.TextMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test queue consumer tests.
 */
@ExtendWith(MockitoExtension.class)
class TestQueueConsumerTests {

    /**
     * Mocked producer template.
     */
    @Mock
    private ProducerTemplate producerTemplate;

    /**
     * Tests TestQueueConsumer::consume.
     */
    @Test
    void testConsume() {
        final TestQueueConsumer testQueueConsumer = new TestQueueConsumer(this.producerTemplate);
        final TextMessage textMessage = new TextMessage("Text", LocalDateTime.now());

        testQueueConsumer.consume(textMessage);

        verify(this.producerTemplate, times(1)).sendBody(eq(Route.RECEIVE_TEST.getEndpoint()), eq(textMessage));
    }

}
