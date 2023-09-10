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
     */
    public Authorization(final User user,
                         final String jwt,
                         final LocalDateTime expiresAt,
                         final RefreshToken refreshToken) {
        super(user, jwt, expiresAt, refreshToken);
    }

}
