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
     * Authentication persistent status.
     */
    private boolean persistent;

    /**
     * The D11 team owned or co-owned by the authenticated user, if any.
     */
    private D11Team d11Team;

}
