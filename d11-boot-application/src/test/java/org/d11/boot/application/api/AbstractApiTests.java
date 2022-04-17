package org.d11.boot.application.api;

import lombok.Getter;
import org.d11.boot.api.model.AuthenticationDTO;
import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.application.model.D11EasyRandomTests;
import org.d11.boot.application.repository.D11EntityRepository;
import org.d11.boot.client.ApiClient;
import org.d11.boot.client.api.AuthenticationApi;
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
     * Username for test administrator user.
     */
    private static final String ADMINISTRATOR_USERNAME = "administrator@email.com";
    /**
     * Username for test user.
     */
    private static final String USER_USERNAME = "user@email.com";
    /**
     * Password for test users.
     */
    private static final String PASSWORD = "password";

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
        this.apiClient.setBearerToken(null);
        return this.apiClient.buildClient(apiClass);
    }

    /**
     * Gets a Feign client of a specific class with provided user authentication.
     *
     * @param apiClass Feign client api class.
     * @param username Username for authentication.
     * @param password Password for authentication.
     * @param <V>      Feign client api class.
     * @return Feign client of the provided class.
     */
    protected <V extends ApiClient.Api> V getApi(final Class<V> apiClass, final String username, final String password) {
        final AuthenticationApi authenticationApi = this.apiClient.buildClient(AuthenticationApi.class);
        final AuthenticationDTO authenticationDTO = new AuthenticationDTO()
                .username(username)
                .password(password);
        final AuthenticationResultDTO authenticationResultDTO = authenticationApi.authenticate(authenticationDTO);
        this.apiClient.setBearerToken(authenticationResultDTO.getJwt());
        return this.apiClient.buildClient(apiClass);
    }

    /**
     * Gets a Feign client of a specific class with administrator authentication.
     *
     * @param apiClass Feign client api class.
     * @param <V>      Feign client api class.
     * @return Feign client of the provided class.
     */
    protected <V extends ApiClient.Api> V getAdministratorApi(final Class<V> apiClass) {
        return getApi(apiClass, ADMINISTRATOR_USERNAME, PASSWORD);
    }

    /**
     * Gets a Feign client of a specific class with user authentication.
     *
     * @param apiClass Feign client api class.
     * @param <V>      Feign client api class.
     * @return Feign client of the provided class.
     */
    protected <V extends ApiClient.Api> V getUserApi(final Class<V> apiClass) {
        return getApi(apiClass, USER_USERNAME, PASSWORD);
    }

}
