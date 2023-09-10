package org.d11.boot.interfaces.rest;

import org.d11.boot.spring.model.RefreshToken;
import org.springframework.http.ResponseCookie;

import java.util.UUID;

/**
 * Builds response cookies that contain refresh tokens.
 */
@SuppressWarnings("checkstyle:HiddenField")
public class RefreshTokenCookieBuilder {

    /**
     * Dummy UUID used when removing a refresh token cookie.
     */
    public static final String REFRESH_TOKEN_DUMMY_UUID = "00000000-0000-0000-0000-000000000000";

    /**
     * Same site value for the cookie.
     */
    private static final String REFRESH_TOKEN_SAME_SITE = "Strict";

    /**
     * Path for the cookie.
     */
    private static final String REFRESH_TOKEN_PATH = "/";

    /**
     * The UUID that will be set as value in the cookie. Default is a dummy UUID for when we're removing the cookie.
     */
    private UUID uuid = UUID.fromString(REFRESH_TOKEN_DUMMY_UUID);

    /**
     * The max age that will be set in the cookie.
     */
    private long maxAge;

    /**
     * Sets the UUID.
     *
     * @param uuid The UUID that will be set as value in the cookie.
     * @return This builder.
     */
    public RefreshTokenCookieBuilder withUuid(final UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Sets the max age.
     *
     * @param maxAge The max age that will be set in the cookie.
     * @return This builder.
     */
    public RefreshTokenCookieBuilder withMaxAge(final long maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    /**
     * Builds a response cookie for the refresh token.
     *
     * @return HTTP only response cookie with refresh token UUID as value and provided max age.
     */
    public ResponseCookie build() {
        return ResponseCookie.from(RefreshToken.COOKIE_NAME, this.uuid.toString())
            .httpOnly(true)
            .maxAge(this.maxAge)
            .sameSite(REFRESH_TOKEN_SAME_SITE)
            .path(REFRESH_TOKEN_PATH)
            .build();
    }

}
