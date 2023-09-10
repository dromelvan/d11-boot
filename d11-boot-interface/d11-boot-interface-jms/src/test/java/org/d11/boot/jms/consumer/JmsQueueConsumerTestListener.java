package org.d11.boot.jms.consumer;

import lombok.Getter;
import org.d11.boot.jms.message.JmsMessage;

/**
 * JMS queue consumer listener that stores the last JMS message that was consumed.
 *
 * @param <T> Type of the JMS message the consumer consumes.
 */
public class JmsQueueConsumerTestListener<T extends JmsMessage> implements JmsQueueConsumerListener<T> {

    /**
     * The last JMS message that was consumed by the consumer this listener is listening to.
     */
    @Getter
    private T jmsMessage;

    @Override
    public void jmsMessageConsumed(final T consumedJmsMessage) {
        this.jmsMessage = consumedJmsMessage;
    }

}
