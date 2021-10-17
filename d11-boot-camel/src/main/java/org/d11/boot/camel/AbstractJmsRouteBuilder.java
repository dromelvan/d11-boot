package org.d11.boot.camel;

import lombok.Getter;
import lombok.Setter;
import org.apache.camel.spi.DataFormat;
import org.d11.boot.jms.JmsQueue;
import org.springframework.beans.factory.annotation.Value;

/**
 * Base class for JMS route builders.
 */
public abstract class AbstractJmsRouteBuilder extends AbstractRouteBuilder {

    /**
     * Endpoint for the ActiveMQ the route listens and posts to.
     */
    @Getter
    @Setter
    @Value("${app.camel.endpoint.activemq}")
    private String activeMQEndpoint;

    /**
     * The queue the route listens to.
     */
    private final JmsQueue sourceJmsQueue;
    /**
     * The queue the route posts to.
     */
    private final JmsQueue destinationJmsQueue;

    /**
     * Creates a new JMS route builder.
     *
     * @param sourceJmsQueue The queue the route listens to.
     */
    protected AbstractJmsRouteBuilder(final JmsQueue sourceJmsQueue) {
        this(sourceJmsQueue, null);
    }

    /**
     * Creates a new JMS route builder.
     *
     * @param sourceJmsQueue      The queue the route listens to.
     * @param destinationJmsQueue The queue the route posts to.
     */
    protected AbstractJmsRouteBuilder(final JmsQueue sourceJmsQueue, final JmsQueue destinationJmsQueue) {
        this.sourceJmsQueue = sourceJmsQueue;
        this.destinationJmsQueue = destinationJmsQueue;
    }

    /**
     * Gets the route id from the source JMS queue.
     *
     * @return The route id.
     */
    protected String getRouteId() {
        return this.sourceJmsQueue.getName();
    }

    /**
     * URL of the ActiveMQ queue the route listens and posts to.
     *
     * @param queue Name of the queue.
     * @return URL of the ActiveMQ queue the route listens and posts to.
     */
    protected String getActiveMQEndpoint(final String queue) {
        return String.format(this.activeMQEndpoint, queue);
    }

    /**
     * Gets the URL of the queue the route listens to.
     *
     * @return The URL of the queue the route listens to.
     */
    protected String getSource() {
        return getActiveMQEndpoint(this.sourceJmsQueue.getName());
    }

    /**
     * Gets the URL of the queue the route posts to.
     *
     * @return The URL of the queue the route posts to.
     */
    protected String getDestination() {
        return getActiveMQEndpoint(this.destinationJmsQueue.getName());
    }

    /**
     * Gets data format for messages on the source JMS queue.
     *
     * @return Data format for messages on the source JMS queue.
     */
    protected DataFormat getSourceDataFormat() {
        return getDataFormat(this.sourceJmsQueue.getBodyClass(), false);
    }

    /**
     * Gets data format for messages on the destination JMS queue.
     *
     * @return Data format for messages on the destination JMS queue.
     */
    protected DataFormat getDestinationDataFormat() {
        return getDataFormat(this.destinationJmsQueue.getBodyClass(), true);
    }

}
