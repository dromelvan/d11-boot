package org.d11.boot.interfaces.rest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Data;

import java.util.Collections;
import java.util.UUID;

/**
 * Request interceptor that sets a refresh token cookie.
 */
@Data
public class CookieRequestInterceptor implements RequestInterceptor {

    /**
     * Name of the cookie header.
     */
    private static final String COOKIE_HEADER_NAME = "Cookie";

    /**
     * Template for refresh token cookie value.
     */
    private static final String REFRESH_TOKEN_COOKIE_VALUE = "refreshToken=%s;";

    /**
     * UUID of the latest refresh token cookie to pass through the interceptor.
     */
    private UUID refreshToken;

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        if (this.refreshToken != null) {
            requestTemplate.header(COOKIE_HEADER_NAME,
                                   Collections.singletonList(String.format(REFRESH_TOKEN_COOKIE_VALUE,
                                                                           refreshToken.toString())));
        }
    }

}
