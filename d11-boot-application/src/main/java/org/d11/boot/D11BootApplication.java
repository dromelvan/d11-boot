package org.d11.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the D11 Boot application.
 */
@SpringBootApplication
public class D11BootApplication {

    /**
     * Starts the D11 Boot application.
     *
     * @param args Program arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(D11BootApplication.class, args);
    }

}
