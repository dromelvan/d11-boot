package org.d11.boot.application.security;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Encryption configuration tests.
 */
public class EncryptionConfigurationTests {

    /**
     * Tests encryption configuration.
     */
    @Test
    public void encryptionConfiguration() {
        final String message = "Hello World!";
        final String notPassword = "not_password";
        ApplicationArguments applicationArguments = mock(ApplicationArguments.class);

        // Encryption password = "password", no decryption password provided.
        String encryptedMessage = getEncryptor("password").encrypt(message);
        EncryptionConfiguration encryptionConfiguration = new EncryptionConfiguration(applicationArguments);
        assertEquals(message, encryptionConfiguration.stringEncryptor().decrypt(encryptedMessage),
                     "Default password should decrypt encrypted message.");

        // Encryption password = "not_password", no decryption password provided.
        final String notPasswordEncryptedMessage = getEncryptor(notPassword).encrypt(message);
        final EncryptionConfiguration badEncryptionConfiguration = new EncryptionConfiguration(applicationArguments);
        assertThrows(EncryptionOperationNotPossibleException.class, () -> {
            badEncryptionConfiguration.stringEncryptor().decrypt(notPasswordEncryptedMessage);
        });

        // Encryption password = "not_password", decryption password provided in ApplicationArguments
        when(applicationArguments.containsOption(EncryptionConfiguration.ENCRYPTION_PASSWORD))
                .thenReturn(true);
        when(applicationArguments.getOptionValues(EncryptionConfiguration.ENCRYPTION_PASSWORD))
                .thenReturn(Collections.singletonList(notPassword));

        encryptedMessage = getEncryptor(notPassword).encrypt(message);
        encryptionConfiguration = new EncryptionConfiguration(applicationArguments);
        assertEquals(message, encryptionConfiguration.stringEncryptor().decrypt(encryptedMessage),
                     "Application arguments password should decrypt encrypted message.");

        // Encryption password = "not_password", decryption password provided in system properties
        applicationArguments = mock(ApplicationArguments.class);
        System.setProperty(EncryptionConfiguration.ENCRYPTION_PASSWORD, notPassword);

        encryptedMessage = getEncryptor(notPassword).encrypt(message);
        encryptionConfiguration = new EncryptionConfiguration(applicationArguments);
        assertEquals(message, encryptionConfiguration.stringEncryptor().decrypt(encryptedMessage),
                     "System property password should decrypt encrypted message.");
        System.setProperty(EncryptionConfiguration.ENCRYPTION_PASSWORD, "");
    }

    private PooledPBEStringEncryptor getEncryptor(final String password) {
        final SimpleStringPBEConfig simpleStringPBEConfig = new SimpleStringPBEConfig();
        simpleStringPBEConfig.setPassword(password);
        simpleStringPBEConfig.setPoolSize("1");
        final PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
        pooledPBEStringEncryptor.setConfig(simpleStringPBEConfig);
        return pooledPBEStringEncryptor;
    }

}
