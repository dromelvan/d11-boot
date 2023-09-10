package org.d11.boot.jms.producer;

import org.d11.boot.camel.bean.HandleTest;
import org.d11.boot.jms.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Producer of messages to the test JMS queue.
 */
@Component
public class TestQueueProducer extends JmsQueueProducer<TextMessage> implements HandleTest {

    /**
     * Creates a new test queue producer.
     *
     * @param jmsTemplate The JMS template.
     */
    @Autowired
    public TestQueueProducer(final JmsTemplate jmsTemplate) {
        super(jmsTemplate, TEST_QUEUE_DESTINATION);
    }

    @Override
    public void handle(final Object bar) {
        produce((TextMessage) bar);
    }

}
