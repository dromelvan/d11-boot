package org.d11.boot.application.api;

import lombok.Getter;
import org.d11.boot.application.model.D11EasyRandomTests;
import org.d11.boot.application.model.D11Entity;
import org.d11.boot.application.repository.D11EntityRepository;
import org.d11.boot.client.ApiClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Base class for API tests.
 *
 * @param <T> The entity class this tests is based on.
 * @param <U> The repository class for the entity class.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractApiTests<T extends D11Entity, U extends D11EntityRepository<T>> extends D11EasyRandomTests {

    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    private int localServerPort;
    /**
     * Repository for the entity class the test is going to test.
     */
    @Getter
    @Autowired
    private U repository;
    /**
     * List of entities.
     */
    @Getter
    private final List<T> entities = new ArrayList<>();

    /**
     * Sets up entities for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(this.repository.findAll());
    }

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
     * Gets a mono from an URI.
     *
     * @param uri The uri we want to get a mono for, based on the webclient base path.
     * @return A mono for the request URI.
     */
    protected Mono<?> getMono(final String uri) {
        return getWebClient().get().uri(uri).retrieve().bodyToMono(Object.class);
    }

    /**
     * Gets a configured WebClient.
     *
     * @return A configured WebClient.
     */
    protected WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(String.format("%s/%s/", getApiClient().getBasePath(), getResourceString()))
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
     * Blocks the mono and assert that it throws a 404 NOT_FOUND exception.
     *
     * @param mono The mono performing thw web request we want to test.
     */
    protected void assertNotFound(final Mono<?> mono) {
        final WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, mono::block);
        assertEquals(HttpStatus.NOT_FOUND,
                webClientResponseException.getStatusCode(),
                "Response should have status NOT_FOUND.");
    }

    /**
     * Blocks the mono and assert that it throws a 400 BAD_REQUEST exception.
     *
     * @param mono The mono performing thw web request we want to test.
     */
    protected void assertBadRequest(final Mono<?> mono) {
        final WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, mono::block);
        assertEquals(HttpStatus.BAD_REQUEST,
                webClientResponseException.getStatusCode(),
                "Response should have status BAD_REQUEST.");
    }

}
