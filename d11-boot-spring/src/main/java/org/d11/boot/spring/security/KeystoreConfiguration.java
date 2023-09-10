package org.d11.boot.spring.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Provides private and public RSA keys from a keystore resource.
 */
@Data
@Configuration
@Slf4j
public class KeystoreConfiguration {

    /**
     * Keystore resource.
     */
    @Value("classpath:${d11.security.keystore.location}")
    private Resource keyStoreResource;

    /**
     * Password for the keystore.
     */
    @Value("${d11.security.keystore.password}")
    private String password;

    /**
     * Key alias the keys are associated with.
     */
    @Value("${d11.security.keystore.keyAlias}")
    private String keyAlias;

    /**
     * Passphrase for the private key.
     */
    @Value("${d11.security.keystore.privateKeyPassphrase}")
    private String privateKeyPassphrase;

    /**
     * Provides a keystore from the provided location.
     *
     * @return Keystore containing private and public RSA keys.
     */
    @Bean
    public KeyStore keyStore() {
        try (InputStream inputStream = this.keyStoreResource.getInputStream()) {
            final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(inputStream, this.password.toCharArray());
            return keyStore;
        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new IllegalArgumentException("KeystoreConfiguration unable to load keystore.", e);
        }
    }

    /**
     * Provides a private RSA key for signing JWTs.
     *
     * @param keyStore The keystore the private key will be taken from.
     * @return Private RSA key for signing JWTs.
     */
    @Bean
    public RSAPrivateKey rsaPrivateKey(final KeyStore keyStore) {
        try {
            final Key key = keyStore.getKey(this.keyAlias, this.privateKeyPassphrase.toCharArray());
            if (key instanceof RSAPrivateKey) {
                return (RSAPrivateKey) key;
            }
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new IllegalArgumentException("KeystoreConfiguration unable to get private key.", e);
        }
        throw new IllegalArgumentException("KeystoreConfiguration did not find private key.");
    }

    /**
     * Provides a public RSA key for validating JWTs.
     *
     * @param keyStore The keystore they public key will be taken from.
     * @return Public RSA key for validating JWTs.
     */
    @Bean
    public RSAPublicKey rsaPublicKey(final KeyStore keyStore) {
        try {
            final Certificate certificate = keyStore.getCertificate(this.keyAlias);
            final PublicKey publicKey = certificate.getPublicKey();

            if (publicKey instanceof RSAPublicKey) {
                return (RSAPublicKey) publicKey;
            }
        } catch (KeyStoreException e) {
            throw new IllegalArgumentException("KeystoreConfiguration unable to get public key.", e);
        }
        throw new IllegalArgumentException("KeystoreConfiguration did not find public key.");
    }

}
