package org.d11.boot.interfaces.rest;

import feign.Response;
import org.d11.boot.spring.model.RefreshToken;

import java.net.HttpCookie;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Decodes response cookies that contain refresh tokens.
 */
public class RefreshTokenCookieDecoder {

    /**
     * Cookie header name.
     */
    private static final String SET_COOKIE = "Set-Cookie";

    /**
     * Cookie name -> HttpCookie map.
     */
    private final Map<String, HttpCookie> cookies = new ConcurrentHashMap<>();

    /**
     * Decodes cookies in a response.
     *
     * @param response The response that has the cookies that will be decoded.
     */
    public void decode(final Response response) {
        this.cookies.clear();

        final Collection<String> headers = response.headers().get(SET_COOKIE.toLowerCase(Locale.getDefault()));
        if (headers != null) {
            headers.forEach(header -> HttpCookie.parse(header)
                .forEach(httpCookie -> this.cookies.put(httpCookie.getName(), httpCookie)));
        }
    }

    /**
     * Gets the value of the refresh token cookie.
     *
     * @return The value of the cookie or null of the cookie didn't exist.
     */
    public UUID getCookieValue() {
        return this.cookies.containsKey(RefreshToken.COOKIE_NAME)
            ? UUID.fromString(this.cookies.get(RefreshToken.COOKIE_NAME).getValue())
            : null;
    }


    /**
     * Gets the max age of the refresh token cookie.
     *
     * @return The max age of the cookie or null of the cookie didn't exist.
     */
    public Long getCookieMaxAge() {
        return this.cookies.containsKey(RefreshToken.COOKIE_NAME)
            ? this.cookies.get(RefreshToken.COOKIE_NAME).getMaxAge()
            : null;
    }

}
