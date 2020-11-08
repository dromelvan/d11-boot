package org.d11.boot.api.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when an API request fails.
 */
public class D11ApiServiceException extends RuntimeException {

    /**
     * Status code of the response.
     */
    private final HttpStatus statusCode;
    /**
     * Status text of the response.
     */
    private final String statusText;
    /**
     * The response body.
     */
    private final byte[] responseBody;
    /**
     * Response HTTP headers.
     */
    private final HttpHeaders headers;
    /**
     * HTTP request.
     */
    private final HttpRequest request;

    /**
     * Creates a new exception.
     *
     * @param statusCode Status code of the response.
     * @param statusText Status text of the response.
     * @param responseBody The response body.
     * @param headers Response HTTP headers.
     * @param request HTTP request.
     */
    public D11ApiServiceException(final String message, final HttpStatus statusCode, final String statusText,
                                  final byte[] responseBody, final HttpHeaders headers, final HttpRequest request) {
        super(message);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.responseBody = responseBody;
        this.headers = headers;
        this.request = request;
    }

    /**
     * Returns the status code of the response.
     *
     * @return The status code of the response.
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    /**
     * Returns the status text of the response.
     *
     * @return The status text of the response.
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * Returns the response body.
     *
     * @return The response body.
     */
    public byte[] getResponseBody() {
        return responseBody;
    }

    /**
     * Returns the response HTTP headers.
     *
     * @return The response HTTP headers.
     */
    public HttpHeaders getHeaders() {
        return headers;
    }

    /**
     * Returns the HTTP request.
     *
     * @return The HTTP request.
     */
    public HttpRequest getRequest() {
        return request;
    }

}
