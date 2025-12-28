package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Refresh token tests.
 */
class RefreshTokenTests extends EasyRandomTests {

    /**
     * Tests RefreshToken::isValid.
     */
    @Test
    void testIsValid() {
        final RefreshToken refreshToken = generate(RefreshToken.class);

        assertTrue(refreshToken.isValid());

        assertFalse(new RefreshToken(null, LocalDateTime.now()).isValid());
        assertFalse(new RefreshToken(new User(), null).isValid());
    }

}
