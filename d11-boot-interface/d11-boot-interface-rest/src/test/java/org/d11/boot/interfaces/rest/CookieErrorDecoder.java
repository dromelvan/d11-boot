package org.d11.boot.interfaces.rest;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Error decoder used to get cookie values from requests that cause exceptions when performing controller tests.
 */
public class CookieErrorDecoder implements ErrorDecoder {

    /**
     * Decoder that does the actual decoding.
     */
    private final RefreshTokenCookieDecoder refreshTokenCookieDecoder;

    /**
     * Creates a new error decoder.
     *
     * @param refreshTokenCookieDecoder The decoder that will do the actual decoding.
     */
    public CookieErrorDecoder(final RefreshTokenCookieDecoder refreshTokenCookieDecoder) {
        this.refreshTokenCookieDecoder = refreshTokenCookieDecoder;
    }

    @Override
    public Exception decode(final String methodKey, final Response response) {
        this.refreshTokenCookieDecoder.decode(response);

        return FeignException.errorStatus(methodKey, response);
    }

}
