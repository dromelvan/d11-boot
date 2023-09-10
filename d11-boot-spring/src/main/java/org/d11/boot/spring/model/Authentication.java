package org.d11.boot.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Holds the result of a successful authentication.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Authentication extends D11Model {

    /**
     * The number of days a persistent authentication will be valid.
     */
    public static final int PERSISTENT_DAYS_VALID = 30;

    /**
     * The user that was authenticated.
     */
    private final User user;

    /**
     * JWT generated from the authentication.
     */
    private final String jwt;

    /**
     * JWT expiration time.
     */
    private final LocalDateTime expiresAt;

    /**
     * Refresh token generated from the authentication.
     */
    private RefreshToken refreshToken;

    /**
     * Gets persistent state.
     *
     * @return True if the authentication is persistent, false if not.
     */
    public boolean isPersistent() {
        return this.refreshToken != null
            && this.refreshToken.getExpiresAt() == null;
    }

}
