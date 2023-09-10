package org.d11.boot.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Provides a Spring Boot configuration for @DataJpaTests.
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
