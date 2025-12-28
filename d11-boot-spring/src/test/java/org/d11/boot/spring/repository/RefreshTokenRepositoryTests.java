package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.RefreshToken;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Refresh token repository tests.
 */
class RefreshTokenRepositoryTests extends AbstractRepositoryTests<RefreshToken, RefreshTokenRepository> {

    /**
     * Tests RefreshTokenRepository::findByUuid.
     */
    @Test
    void testFindByUuid() {
        getEntities().forEach(refreshToken -> {
            final RefreshToken result = getRepository().findByUuid(refreshToken.getUuid()).orElse(null);
            assertNotNull(result);
            assertEquals(refreshToken, result);
        });
    }

    /**
     * Tests RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull.
     */
    @Test
    void testFindByUuidAndExpiresAtIsAfterOrExpiresAtIsNull() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime tomorrow = now.plusSeconds(60 * 60 * 24);

        int persistentCount = 0;
        int nonExpiredCount = 0;
        int expiredCount = 0;

        for (final RefreshToken refreshToken : getEntities()) {
            final RefreshToken result =
                    getRepository().findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(refreshToken.getUuid(), now)
                            .orElse(null);

            if (refreshToken.getExpiresAt().isAfter(tomorrow)) {
                assertNotNull(result);
                assertEquals(refreshToken, result);
                assertNotNull(refreshToken.getExpiresAt());
                ++persistentCount;
            } else if (refreshToken.getExpiresAt().isAfter(now)) {
                assertNotNull(result);
                assertEquals(refreshToken, result);
                assertNotNull(refreshToken.getExpiresAt());
                assertTrue(refreshToken.getExpiresAt().isAfter(now));
                ++nonExpiredCount;
            } else {
                assertNull(result);
                ++expiredCount;
            }
        }

        assertTrue(persistentCount > 0 && nonExpiredCount > 0 && expiredCount > 0);
    }

}
