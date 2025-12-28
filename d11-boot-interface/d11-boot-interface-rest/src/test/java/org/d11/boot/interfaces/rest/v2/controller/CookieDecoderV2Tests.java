package org.d11.boot.interfaces.rest.v2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.d11.boot.api.v2.client.ApiResponseDecoder;
import org.d11.boot.interfaces.rest.RefreshTokenCookieDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Decoder used to get cookie values when performing controller tests.
 */
public class CookieDecoderV2Tests extends ApiResponseDecoder {

    /**
     * Decoder that does the actual decoding.
     */
    private final RefreshTokenCookieDecoder refreshTokenCookieDecoder;

    /**
     * Creates a new decoder.
     *
     * @param objectMapper              The object mapper the decoder will use.
     * @param refreshTokenCookieDecoder The decoder that will do the actual decoding.
     */
    public CookieDecoderV2Tests(final ObjectMapper objectMapper,
                                final RefreshTokenCookieDecoder refreshTokenCookieDecoder) {
        super(objectMapper);
        this.refreshTokenCookieDecoder = refreshTokenCookieDecoder;
    }

    @Override
    public Object decode(final Response response, final Type type) throws IOException {
        final Object result = super.decode(response, type);

        this.refreshTokenCookieDecoder.decode(response);

        return result;
    }

}
