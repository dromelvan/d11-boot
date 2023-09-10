package org.d11.boot.jms.consumer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.activemq.artemis.utils.collections.ConcurrentHashSet;
import org.apache.camel.ProducerTemplate;
import org.d11.boot.jms.JmsQueueComponent;
import org.d11.boot.jms.message.JmsMessage;

import java.util.Set;

/**
 * Base class for JMS queue consumers.
 *
 * @param <T> Type of the JMS message the consumer consumes.
 */
@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
                    justification = "Can't inject an immutable ProducerTemplate")
public class JmsQueueConsumer<T extends JmsMessage> extends JmsQueueComponent {

    /**
     * Set of registered JMS queue consumer listeners.
     */
    private final Set<JmsQueueConsumerListener<T>> jmsQueueConsumerListeners = new ConcurrentHashSet<>();

    /**
     * Producer template for triggering Camel routes.
     */
    private final ProducerTemplate producerTemplate;

    /**
     * Creates a new JMS queue consumer.
     *
     * @param producerTemplate Producer template for triggering Camel routes.
     */
    public JmsQueueConsumer(final ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    /**
     * Adds a JMS queue consumer listener.
     *
     * @param jmsQueueConsumerListener The listener.
     */
    public void addJmsQueueConsumerListener(final JmsQueueConsumerListener<T> jmsQueueConsumerListener) {
        this.jmsQueueConsumerListeners.add(jmsQueueConsumerListener);
    }

    /**
     * Fires a JMS message consumed event to all registered listeners.
     *
     * @param jmsMessage The JMS message.
     */
    protected void jmsMessageConsumed(final T jmsMessage) {
        for (final JmsQueueConsumerListener<T> jmsQueueConsumerListener : this.jmsQueueConsumerListeners) {
            jmsQueueConsumerListener.jmsMessageConsumed(jmsMessage);
        }
    }

    /**
     * Sends a body to a Camel queue.
     *
     * @param endpoint Camel endpoint to send the message to.
     * @param body     Body to send.
     */
    protected void sendBody(final String endpoint, final Object body) {
        this.producerTemplate.sendBody(endpoint, body);
    }

}
