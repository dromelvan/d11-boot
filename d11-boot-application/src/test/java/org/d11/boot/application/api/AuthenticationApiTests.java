package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.AuthenticationDTO;
import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.d11.boot.client.api.AuthenticationApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Authentication API tests.
 */
public class AuthenticationApiTests extends AbstractRepositoryApiTests<User, UserRepository> {

    /**
     * Tests the authenticate API operation.
     */
    @Test
    public void authenticate() {
        final AuthenticationApi authenticationApi = getApi(AuthenticationApi.class);
        for(final User user : getRepository().findAll()) {
            final AuthenticationDTO authenticationDTO = new AuthenticationDTO()
                    .username(user.getEmail())
                    .password("password");

            final AuthenticationResultDTO authenticationResultDTO = authenticationApi.authenticate(authenticationDTO);

            assertNotNull(authenticationResultDTO, "Authentication result should not be null.");
            assertNotNull(authenticationResultDTO.getJwt(), "Authentication result JWT should not be null.");
            assertFalse(authenticationResultDTO.getJwt().isEmpty(), "Authentication result JWT should not be empty.");
        }

        final AuthenticationDTO invalidAuthenticationDTO = new AuthenticationDTO()
                .username("invalidUsername")
                .password("invalidPassword");
        assertThrows(FeignException.Unauthorized.class,
                     () -> authenticationApi.authenticate(invalidAuthenticationDTO),
                     "Invalid login exception should have status code 401.");
    }

}
