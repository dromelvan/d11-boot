package org.d11.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the D11 Boot application.
 */
@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class D11BootApplication {

    /**
     * Common serialVersionUID for serializable classes.
     */
    public static final long VERSION = 1L;

    /**
     * Starts the D11 Boot application.
     *
     * @param args Application parameters. Not used.
     */
    public static void main(final String[] args) {
        SpringApplication.run(D11BootApplication.class, args);
    }

}
