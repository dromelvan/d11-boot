package org.d11.boot.application;

import org.d11.boot.application.security.EncryptionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.util.Arrays;

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
     * Starts the D11 Boot application. Encryption password is provided by console or the
     * -Dencryption.configuration.password=password system property.
     *
     * @param args Application parameters. Not used.
     */
    public static void main(final String[] args) {
        final Console console = System.console();
        // Console is not available if we're running in IDE or Gradle bootRun.
        if (console == null) {
            SpringApplication.run(D11BootApplication.class, args);
        } else {
            final String key = new String(console.readPassword("Secret Encryption Key: "));
            String[] enrichedArgs = Arrays.copyOf(args, args.length + 1);
            enrichedArgs[enrichedArgs.length - 1] = String.format("--%s=%s", EncryptionConfiguration.ENCRYPTION_PASSWORD, key);
            SpringApplication.run(D11BootApplication.class, enrichedArgs);
        }
    }

}
