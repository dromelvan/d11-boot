package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.spring.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Refresh token repository tests.
 */
class RefreshTokenRepositoryTests extends D11BootRepositoryTests<RefreshToken, RefreshTokenRepository> {

    /**
     * User repository for test users.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Creates new refresh token repository tests.
     */
    RefreshTokenRepositoryTests() {
        super(RefreshToken.class);
    }

    /**
     * Creates refresh tokens and saves them.
     */
    @Override
    @BeforeEach
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    protected void beforeEach() {
        final User user = this.userRepository.save(generate(User.class));

        for (int i = 0; i < DEFAULT_GENERATED_LIST_SIZE; ++i) {
            final RefreshToken refreshToken = new RefreshToken(user, LocalDateTime.now().plusDays(1));
            getEntities().add(getRepository().save(refreshToken));
        }
    }

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
     * Tests RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull for an expired refresh token.
     */
    @Test
    void testFindByUuidAndExpiresAtIsAfterOrExpiresAtIsNullExpired() {
        final User user = this.userRepository.save(generate(User.class));
        final RefreshToken refreshToken = new RefreshToken(user, LocalDateTime.now().minusDays(1));

        getRepository().save(refreshToken);

        assertTrue(getRepository().findByUuid(refreshToken.getUuid()).isPresent(),
                   "RefreshTokenRepository::findByUuid expired present");

        final Optional<RefreshToken> optional = getRepository()
            .findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(refreshToken.getUuid(), LocalDateTime.now());

        assertFalse(optional.isPresent(),
                    "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull expired present");
    }

    /**
     * Tests RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull for a non persistent refresh token.
     */
    @Test
    void testFindByUuidAndExpiresAtIsAfterOrExpiresAtIsNullNonPersistent() {
        final User user = this.userRepository.save(generate(User.class));
        final RefreshToken refreshToken = new RefreshToken(user, LocalDateTime.now().plusDays(1));

        getRepository().save(refreshToken);

        final RefreshToken result = getRepository()
            .findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(refreshToken.getUuid(), LocalDateTime.now())
            .orElse(null);

        assertNotNull(result,
                      "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull non persistent not " +
                      "null");
        assertEquals(refreshToken, result,
                     "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull non persistent");
        assertNotNull(refreshToken.getExpiresAt(),
                      "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull non persistent expires " +
                      "at");
    }

    /**
     * Tests RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull for a persistent refresh token.
     */
    @Test
    void testFindByUuidAndExpiresAtIsAfterOrExpiresAtIsNullPersistent() {
        final User user = this.userRepository.save(generate(User.class));
        final RefreshToken refreshToken = new RefreshToken(user);

        getRepository().save(refreshToken);

        final RefreshToken result = getRepository()
            .findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(refreshToken.getUuid(), LocalDateTime.now())
            .orElse(null);

        assertNotNull(result,
                      "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull persistent not null");
        assertEquals(refreshToken, result,
                     "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull persistent");
        assertNull(refreshToken.getExpiresAt(),
                   "RefreshTokenRepository::findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull persistent expires at");
    }

}
