package org.d11.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provides a Spring Boot configuration for @SpringBootTests.
 */
@SpringBootApplication
public class SpringBootTestApplication {

    /**
     * Starts the Spring Boot test application.
     *
     * @param args Program arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootTestApplication.class, args);
    }

}
