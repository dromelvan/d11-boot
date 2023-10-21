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
            assertNotNull(result, "RefreshTokenRepository::findByUuid not null");
            assertEquals(refreshToken, result, "RefreshTokenRepository::findByUuid");
        });
    }

    /**
     * Tests RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull.
     */
    @Test
    void testFindByUuidAndExpiresAtIsAfterOrExpiresAtIsNull() {
        final LocalDateTime now = LocalDateTime.now();

        int persistentCount = 0;
        int nonExpiredCount = 0;
        int expiredCount = 0;

        for (final RefreshToken refreshToken : getEntities()) {
            final RefreshToken result =
                    getRepository().findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(refreshToken.getUuid(), now)
                            .orElse(null);

            if (refreshToken.getExpiresAt() == null) {
                assertNotNull(result,
                              "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull pers not null");
                assertEquals(refreshToken, result,
                             "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull pers equals");
                assertNull(refreshToken.getExpiresAt(),
                           "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull pers expiresAt");
                ++persistentCount;
            } else if (refreshToken.getExpiresAt().isAfter(now)) {
                assertNotNull(result,
                              "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull noexp not null");
                assertEquals(refreshToken, result,
                             "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull noexp equals");
                assertNotNull(refreshToken.getExpiresAt(),
                              "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull noexp expiresAt");
                assertTrue(refreshToken.getExpiresAt().isAfter(now),
                              "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull noexp isAfter");
                ++nonExpiredCount;
            } else {
                assertNull(result,
                           "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull exp null");
                ++expiredCount;
            }
        }

        assertTrue(persistentCount > 0 && nonExpiredCount > 0 && expiredCount > 0,
                   "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull test data correct");
    }

}
