package org.d11.boot.jms.producer;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.jms.JmsQueueComponent;
import org.d11.boot.jms.message.JmsMessage;
import org.springframework.jms.core.JmsTemplate;

/**
 * Base class for JMS queue producers.
 *
 * @param <T> Type of the JMS message the producer produces.
 */
public class JmsQueueProducer<T extends JmsMessage> extends JmsQueueComponent {

    /**
     * JMS template that sends messages to Artemis.
     */
    @Getter(AccessLevel.PROTECTED)
    private final JmsTemplate jmsTemplate;

    /**
     * Name of the JMS queue the message will be sent to.
     */
    @Getter(AccessLevel.PROTECTED)
    private final String queue;

    /**
     * Creates a new JMS queue producer.
     *
     * @param jmsTemplate The JMS template.
     * @param queue       The JMS queue name.
     */
    protected JmsQueueProducer(final JmsTemplate jmsTemplate, final String queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    /**
     * Sends a message to the queue.
     *
     * @param message Message that will be sent.
     */
    public void produce(final T message) {
        this.jmsTemplate.convertAndSend(this.queue, message);
    }

}
