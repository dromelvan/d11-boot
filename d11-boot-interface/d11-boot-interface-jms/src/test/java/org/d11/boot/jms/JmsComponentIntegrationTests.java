package org.d11.boot.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.listener.MessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.Callable;

import static org.awaitility.Awaitility.await;

/**
 * Base class for JMS component integration tests.
 */
@SpringBootTest
public class JmsComponentIntegrationTests {

    /**
     * Max wait duration. If the callable is not true after this time the test will fail.
     */
    private static final int AT_MOST_DURATION_SECONDS = 5;

    /**
     * Interval for callable status polling.
     */
    private static final long POLL_INTERVAL = 100;

    /**
     * Used to stop and start JMS listeners.
     */
    @Autowired
    private JmsListenerEndpointRegistry jmsListenerEndpointRegistry;


    /**
     * Pauses execution of a test method until the callable returns true.
     *
     * @param callable Callable that provides the status of whatever task the test waits for.
     */
    protected void awaitUntil(final Callable<Boolean> callable) {
        await()
                .atMost(Duration.ofSeconds(AT_MOST_DURATION_SECONDS))
                .with()
                .pollInterval(Duration.ofMillis(POLL_INTERVAL))
                .until(callable);
    }

    /**
     * Stops all @JmsListeners. This method can be used before tests where we want to consume a message in some other
     * way than letting the defined JmsQueueConsumers do it.
     */
    protected void stopListeners() {
        for (final MessageListenerContainer messageListenerContainer
                : this.jmsListenerEndpointRegistry.getListenerContainers()) {
            messageListenerContainer.stop();
        }
    }

    /**
     * Starts all @JmsListeners. This method can be used after tests that have been performed while the listeners are
     * stopped have completed.
     */
    protected void startListeners() {
        for (final MessageListenerContainer messageListenerContainer
                : this.jmsListenerEndpointRegistry.getListenerContainers()) {
            messageListenerContainer.start();
        }
    }

}
