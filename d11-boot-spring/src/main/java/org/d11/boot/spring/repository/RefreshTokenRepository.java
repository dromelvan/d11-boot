package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for RefreshToken entities.
 */
@Repository
public interface RefreshTokenRepository extends D11EntityRepository<RefreshToken> {

    /**
     * Finds a refresh token by uuid.
     *
     * @param uuid      The UUID of the refresh token.
     * @return Optional with the refresh token or empty optional if no refresh token was found.
     */
    Optional<RefreshToken> findByUuid(@Param("uuid") UUID uuid);

    /**
     * Finds a refresh token that is not expired by uuid.
     *
     * @param uuid      The UUID of the refresh token.
     * @param expiresAt The time the refresh token cannot have expired by.
     * @return Optional with the refresh token or empty optional if no unexpired refresh token was found.
     */
    @Query("SELECT refreshToken FROM RefreshToken refreshToken WHERE refreshToken.uuid = :uuid AND " +
           "(refreshToken.expiresAt > :expiresAt OR refreshToken.expiresAt IS NULL)")
    Optional<RefreshToken> findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(@Param("uuid") UUID uuid,
                                                                          @Param("expiresAt") LocalDateTime expiresAt);

}
