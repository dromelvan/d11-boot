package org.d11.boot.interfaces.rest.v2.controller;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.api.v2.client.ApiClient;
import org.d11.boot.api.v2.client.SecurityApi;
import org.d11.boot.api.v2.client.auth.HttpBearerAuth;
import org.d11.boot.api.v2.model.AuthenticationRequestBodyDTO;
import org.d11.boot.api.v2.model.AuthenticationResponseBodyDTO;
import org.d11.boot.util.mapper.MapperProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for API tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class D11BootControllerV2Tests extends MapperProvider {

    /**
     * Username for test administrator user.
     */
    protected static final String ADMINISTRATOR_USERNAME = "administrator@email.com";

    /**
     *  Username for test user.
     */
    protected static final String USER_USERNAME = "user@email.com";

    /**
     * Password for test users.
     */
    protected static final String PASSWORD = "password";

    /**
     * Authorization name/scheme string.
     */
    private static final String BEARER = "Bearer";

    /**
     * Server port used when running tests with SpringBootTest.WebEnvironment.RANDOM_PORT.
     */
    @LocalServerPort
    @Getter(AccessLevel.PROTECTED)
    private int localServerPort;

    /**
     * API client that provides Feign client APIs.
     */
    @Getter(AccessLevel.PROTECTED)
    private final ApiClient apiClient;

    /**
     * Creates a new API test.
     */
    public D11BootControllerV2Tests() {
        this.apiClient = new ApiClient();
        this.apiClient.addAuthorization(BEARER, new HttpBearerAuth(BEARER));
    }

    /**
     * Sets up the base path port for the API service to use.
     */
    @BeforeAll
    public void beforeAllControllerTests() {
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
    protected <V extends ApiClient.Api> V getApi(final Class<V> apiClass,
                                                 final String username,
                                                 final String password) {
        final SecurityApi securityApi = this.apiClient.buildClient(SecurityApi.class);
        final AuthenticationRequestBodyDTO authenticationDTO = new AuthenticationRequestBodyDTO()
            .username(username)
            .password(password);
        final AuthenticationResponseBodyDTO authenticationResultDTO = securityApi.authenticate(authenticationDTO);
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
