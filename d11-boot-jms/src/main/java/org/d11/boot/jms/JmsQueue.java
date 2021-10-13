package org.d11.boot.jms;

import org.d11.boot.jms.message.DownloadWhoscoredMatchMessage;
import org.d11.boot.jms.message.TestMessage;
import org.d11.boot.jms.message.UpdateMatchMessage;
import org.d11.boot.jms.message.UpdateMatchRequestMessage;

/**
 * JMS queue definitions.
 */
public enum JmsQueue {

    /**
     * Test queue for test messages.
     */
    TEST(TestMessage.class),
    /**
     * Download WhoScored match queue to trigger match page downloads.
     */
    DOWNLOAD_WHOSCORED_MATCH(DownloadWhoscoredMatchMessage.class),
    /**
     * Update match queue to trigger match updates.
     */
    UPDATE_MATCH(UpdateMatchMessage.class),
    /**
     * Update match requests to trigger match updates when we only know the D11 id of the match.
     */
    UPDATE_MATCH_REQUEST(UpdateMatchRequestMessage.class);

    /**
     * Prefix for all JMS queues.
     */
    @SuppressWarnings("PMD.AvoidUsingHardCodedIP")
    private static final String PREFIX = "D11::";

    /**
     * Class of the message body for a route.
     */
    private final Class<?> bodyClass;

    /**
     * Creates a new JMS queue definition.
     *
     * @param bodyClass The class the queue expects the message body to have.
     */
    JmsQueue(final Class<?> bodyClass) {
        this.bodyClass = bodyClass;
    }

    /**
     * Gets the class the queue expects the message body to have.
     *
     * @return The class the queue expects the message body to have.
     */
    public Class<?> getBodyClass() {
        return this.bodyClass;
    }

    /**
     * Gets the name of the queue.
     *
     * @return The name of the queue
     */
    public String getName() {
        return PREFIX + toString();
    }

}
