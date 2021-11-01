package org.d11.boot.cli.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Processor that stops the Camel context and shuts down the application.
 */
public class ShutdownProcessor implements Processor {

    @Override
    @SuppressWarnings({ "PMD.DoNotCallSystemExit", "PMD.DoNotUseThreads" })
    public void process(final Exchange exchange) {
        // If we try to stop and shut down in the exchange thread we have to wait for inflight stuff for 30 seconds.
        // If we do it in a separate thread it will stop immediately.
        final Thread shutdownThread = new Thread(() -> {
            Thread.currentThread().setName("ShutdownThread");
            exchange.getContext().stop();
            System.exit(0);
        });
        shutdownThread.start();
    }

}
