package org.d11.boot.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the JMS module integration test application.
 */
@SpringBootApplication
public class JmsApplication {

    /**
     * Starts the JMS application.
     *
     * @param args Program arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(JmsApplication.class, args);
    }

}
