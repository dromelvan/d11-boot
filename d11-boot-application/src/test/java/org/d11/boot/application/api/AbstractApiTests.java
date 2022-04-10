package org.d11.boot.application.api;

import lombok.Getter;
import org.d11.boot.application.model.D11EasyRandomTests;
import org.d11.boot.application.repository.D11EntityRepository;
import org.d11.boot.client.ApiClient;
import org.d11.boot.client.auth.HttpBearerAuth;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for API tests.
 *
 * @param <T> The API service class this test will use.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public abstract class AbstractApiTests<T extends D11EntityRepository<?>> extends D11EasyRandomTests {

    /**
     * Authorization name/scheme string.
     */
    private static final String BEARER = "Bearer";

    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    private int localServerPort;

    /**
     * API client that provides Feign client APIs.
     */
    private final ApiClient apiClient;

    /**
     * Repository for the entity class the test is going to test.
     */
    @Getter
    @Autowired
    private T repository;

    /**
     * Creates a new API test.
     */
    public AbstractApiTests() {
        this.apiClient = new ApiClient();
        this.apiClient.addAuthorization(BEARER, new HttpBearerAuth(BEARER));
    }

    /**
     * Sets up the base path port for the API service to use.
     */
    @BeforeAll
    public void beforeAbstractFeignTests() {
        this.apiClient.setBasePath(String.format("http://localhost:%d", this.localServerPort));
    }

    /**
     * Gets a Feign client of a specific class.
     *
     * @param apiClass Feign client api class.
     * @param <V>      Feign client api class.
     * @return Feign client of the provided class.
     */
    protected <V extends ApiClient.Api> V getApi(final Class<V> apiClass) {
        return this.apiClient.buildClient(apiClass);
    }

}
