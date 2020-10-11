package org.d11.boot.application.api;

import org.d11.boot.application.util.MappingProvider;
import org.d11.boot.client.ApiClient;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Locale;

/**
 * Base class for API tests.
 */
public abstract class AbstractApiTests extends MappingProvider {

    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    private int localServerPort;

    /**
     * Base path for test server.
     *
     * @return http://localhost:localServerPort.
     */
    protected String getBasePath() {
        return String.format("http://localhost:%d", this.localServerPort);
    }

    /**
     * Gets a configured API client.
     *
     * @return Configured API client.
     */
    protected ApiClient getApiClient() {
        final ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(getBasePath());
        return apiClient;
    }

    /**
     * Gets an object from an URL.
     *
     * @param uri The URL to get the object from.
     * @return The object the URL returns.
     */
    protected Object getUri(final String uri) {
        return getWebClient().get().uri(uri).retrieve().bodyToMono(Object.class).block();
    }

    /**
     * Gets a configured WebClient.
     *
     * @return A configured WebClient.
     */
    protected WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(String.format("%s/%s/",
                                       getApiClient().getBasePath(),
                                       getClass().getSimpleName().replace("ApiTest", "")
                                               .toLowerCase(Locale.getDefault())))
                .build();
    }
}
