package org.d11.boot.application.api;

import lombok.Getter;
import org.d11.boot.api.service.D11ApiService;
import org.d11.boot.application.model.D11EasyRandomTests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Base class for API tests.
 *
 * @param <V> The API service class this test will use.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class AbstractApiTests<V extends D11ApiService> extends D11EasyRandomTests {

    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    private int localServerPort;
    /**
     * API service for the tests to use.
     */
    @Getter
    @Autowired
    private V apiService;

    /**
     * Sets up the base path port for the API service to use.
     */
    @BeforeAll
    public void setBasePathPort() {
        this.apiService.setBasePathPort(this.localServerPort);
    }

    /**
     * Gets a mono from an URI.
     *
     * @param uri The uri we want to get a mono for, based on the webclient base path.
     * @return A mono for the request URI.
     */
    protected Mono<?> get(final String uri) {
        return get(getResourceString(), uri);
    }

    /**
     * Gets a mono from an URI combined with a resource string.
     *
     * @param resourceString Resource string that will be added to the base path.
     * @param uri The uri we want to get a mono for, based on the webclient base path.
     * @return A mono for the request URI combined with the resource string.
     */
    protected Mono<?> get(final String resourceString, final String uri) {
        return getWebClient(resourceString).get().uri(uri).retrieve().bodyToMono(Object.class);
    }

    /**
     * Gets a configured WebClient.
     *
     * @param resourceString Resource string that will be added to the base path.
     * @return A configured WebClient.
     */
    protected WebClient getWebClient(final String resourceString) {
        return WebClient.builder()
                .baseUrl(String.format("%s/%s/", this.apiService.getApiClient().getBasePath(), resourceString))
                .build();
    }

    /**
     * Turns FooBarApiTests into foo-bars.
     *
     * @return Kebab cased resource name derived from test class name.
     */
    protected String getResourceString() {
        final String replacement = "$1-$2";
        return getClass().getSimpleName()
                .replace("ApiTest", "")
                .replaceAll("([a-z])([A-Z])", replacement)
                .replaceAll("(D11)([A-Z])", replacement)
                .toLowerCase(Locale.getDefault());
    }

    /**
     * Blocks the mono and assert that it throws a 400 BAD_REQUEST exception.
     *
     * @param mono The mono performing thw web request we want to test.
     */
    protected void assertBadRequest(final Mono<?> mono) {
        final WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, mono::block);
        assertEquals(HttpStatus.BAD_REQUEST, webClientResponseException.getStatusCode(), "Response should have status BAD_REQUEST.");
    }

}
