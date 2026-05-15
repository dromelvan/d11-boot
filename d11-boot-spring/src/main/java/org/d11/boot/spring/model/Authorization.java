package org.d11.boot.spring.model;

import java.time.LocalDateTime;

/**
 * Holds the result of a successful authorization.
 */
public class Authorization extends Authentication {

    /**
     * Creates a new authorization.
     *
     * @param user         The user that was authorized.
     * @param jwt          JWT for further authorization.
     * @param expiresAt    JWT expiration time.
     * @param refreshToken Token that can be used to refresh the authorization.
     * @param persistent   Authorization persistent status.
     * @param d11Team      The D11 team owned or co-owned by the authorized user, if any.
     */
    public Authorization(final User user,
                         final String jwt,
                         final LocalDateTime expiresAt,
                         final RefreshToken refreshToken,
                         final boolean persistent,
                         final D11Team d11Team) {
        super(user, jwt, expiresAt, refreshToken, persistent, d11Team);
    }

}
