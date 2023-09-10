package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

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

        assertTrue(refreshToken.isValid(), "RefreshToken::isValid");
    }

}
