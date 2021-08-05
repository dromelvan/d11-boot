package org.d11.boot.api.service;

import org.d11.boot.api.model.AuthenticationDTO;
import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.client.api.AuthenticationApi;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides authentication API services.
 */
@Service
public class AuthenticationApiService extends D11ApiService {

    /**
     * Authenticates a user.
     *
     * @param authenticationDTO The user credentials that will be authenticated.
     * @return Authentication result of the authentication.
     */
    public AuthenticationResultDTO authenticate(final AuthenticationDTO authenticationDTO) {
        try {
            final AuthenticationApi authenticationApi = new AuthenticationApi(getApiClient());
            return authenticationApi.authenticate(authenticationDTO).block();
        } catch(WebClientResponseException e) {
            throw translate(e);
        }
    }

}
