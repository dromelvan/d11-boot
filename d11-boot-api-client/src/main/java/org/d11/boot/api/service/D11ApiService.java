package org.d11.boot.api.service;

import org.d11.boot.client.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Base class for D11 API services.
 */
public class D11ApiService {

    /**
     * The base path to the D11 REST server. This should be configured in the application properties
     * file with key d11.api.basePath and contain the hostname and port, without a trailing /.
     */
    @Value("${d11.api.basePath}")
    private String basePath;

    /**
     * Formats the base path, replacing the first %d with the provided port.
     *
     * @param port The port this service should connect to.
     */
    public void setBasePathPort(final int port) {
        this.basePath = String.format(this.basePath, port);
    }

    /**
     * Gets a configured API client.
     *
     * @return Configured API client.
     */
    public ApiClient getApiClient() {
        final ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(this.basePath);
        return apiClient;
    }

    /**
     * Translates a WebClientResponseException to a D11ApiServiceException.
     *
     * @param e The WebClientResponseException that will be thrown.
     * @return A D11ApiServiceExceptions with properties from the WebClientResponseException.
     */
    protected D11ApiServiceException translate(final WebClientResponseException e) {
        return new D11ApiServiceException(e.getMessage(), e.getStatusCode(), e.getStatusText(),
                                          e.getResponseBodyAsByteArray(), e.getHeaders(), e.getRequest());
    }

}
