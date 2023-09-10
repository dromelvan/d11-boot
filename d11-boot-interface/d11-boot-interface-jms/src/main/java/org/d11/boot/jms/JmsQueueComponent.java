package org.d11.boot.jms;

/**
 * Base class for components that interact with JMS queues. Provides constants for JMS queue destinations.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class JmsQueueComponent {

    /**
     * Address for all JMS queues.
     */
    private static final String ADDRESS = "D11";

    /**
     * Test queue destination name.
     */
    public static final String TEST_QUEUE_DESTINATION = ADDRESS + "::" + "TEST";

}
