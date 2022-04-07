package org.d11.boot.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main class of the D11 CLI application.
 */
@SpringBootApplication(scanBasePackages = "org.d11.boot")
@EnableFeignClients
@SuppressWarnings("PMD.UseUtilityClass")
public class D11BootCliApplication {

    /**
     * Starts the D11 command line application.
     *
     * @param args Application parameters.
     */
    public static void main(final String[] args) {
        SpringApplication.run(D11BootCliApplication.class, args);
    }

}
