package org.d11.boot.application.security;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jasypt encryption configuration.
 */
@Configuration
public class EncryptionConfiguration {

    /**
     * The password used when none is available in the application argument or system properties.
     */
    public static final String DEFAULT_ENCRYPTION_PASSWORD = "password";
    /**
     * The option in the application arguments or system properties where we expect the password to be provided.
     */
    public static final String ENCRYPTION_PASSWORD = "encryption.configuration.password";
    /**
     * PBE config for the decrypting.
     */
    private final SimpleStringPBEConfig simpleStringPBEConfig;

    /**
     * Creates a new encryption configuration.
     *
     * @param applicationArguments The application arguments the application was started with.
     */
    @Autowired
    public EncryptionConfiguration(final ApplicationArguments applicationArguments) {
        this.simpleStringPBEConfig = new SimpleStringPBEConfig();
        if (applicationArguments.containsOption(ENCRYPTION_PASSWORD)) {
            final String password = applicationArguments.getOptionValues(ENCRYPTION_PASSWORD).get(0);
            this.simpleStringPBEConfig.setPassword(password);
        } else if (System.getProperty(ENCRYPTION_PASSWORD) == null) {
            this.simpleStringPBEConfig.setPassword(DEFAULT_ENCRYPTION_PASSWORD);
        } else {
            this.simpleStringPBEConfig.setPassword(System.getProperty(ENCRYPTION_PASSWORD));
        }
        this.simpleStringPBEConfig.setPoolSize("1");
    }

    /**
     * Provides the encryptor to actually perform the decryption.
     *
     * @return StringEncryptor that does the encrypting of Jasyp encrypted strings.
     */
    @Bean(name = "encryptor")
    public StringEncryptor stringEncryptor() {
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(this.simpleStringPBEConfig);
        return encryptor;
    }

}
