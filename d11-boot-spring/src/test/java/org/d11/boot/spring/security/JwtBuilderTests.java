package org.d11.boot.spring.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JwtBuilder tests.
 */
class JwtBuilderTests {

    /**
     * Pattern for matching am encoded JWT.
     */
    private static final String JWT_REGEX = "^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$";

    /**
     * Token time to live in seconds.
     */
    private static final int TIME_TO_LIVE = 300;

    /**
     * Tests JwtBuilder::build and JwtBuilder::decode.
     *
     * @throws NoSuchAlgorithmException This won't happen
     */
    @Test
    void testBuildAndDecode() throws NoSuchAlgorithmException {
        final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        final KeyPair keyPair = generator.generateKeyPair();
        final JwtBuilder jwtBuilder = new JwtBuilder((RSAPrivateKey) keyPair.getPrivate(),
                                                     (RSAPublicKey) keyPair.getPublic(),
                                                     TIME_TO_LIVE);

        final String username = "username";
        final JwtBuildResult result = jwtBuilder.build(username);
        final LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(TIME_TO_LIVE).truncatedTo(ChronoUnit.SECONDS);

        assertNotNull(result, "JwtBuilder::build not null");
        assertTrue(result.jwt().matches(JWT_REGEX), "JwtBuilder::build jwt matches");
        assertEquals(expiresAt, result.expiresAt(), "JwtBuilder::build expiresAt equals");

        final DecodedJWT decodedJWT = jwtBuilder.decode(result.jwt());

        assertNotNull(decodedJWT, "JwtBuilder::decode not null");
        assertEquals(username, decodedJWT.getClaim("username").asString(), "JwtBuilder::decode username equals");
        assertEquals(result.expiresAt().atZone(ZoneId.systemDefault()).toInstant(),
                     decodedJWT.getExpiresAt().toInstant(),
                     "JwtBuilder::decode expiresAt equals");
    }

}
