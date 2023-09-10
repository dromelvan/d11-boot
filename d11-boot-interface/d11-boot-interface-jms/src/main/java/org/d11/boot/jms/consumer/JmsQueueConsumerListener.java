package org.d11.boot.jms.consumer;

import org.d11.boot.jms.message.JmsMessage;

/**
 * Listens for message consumed events from a JmsQueueConsumerListener. This is mostly intended for testing.
 *
 * @param <T> Type of the message the consumer consumes.
 */
public interface JmsQueueConsumerListener<T extends JmsMessage> {

    /**
     * Notifies the listener that the consumer has consumed a JMS message.
     *
     * @param jmsMessage The JMS message that was consumed.
     */
    void jmsMessageConsumed(T jmsMessage);

}
