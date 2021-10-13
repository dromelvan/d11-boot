package org.d11.boot.application.api;

import org.d11.boot.api.model.AuthenticationDTO;
import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.api.service.AuthenticationApiService;
import org.d11.boot.api.service.D11ApiServiceException;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Authentication API tests.
 */
public class AuthenticationApiTests extends AbstractRepositoryApiTests<User, UserRepository, AuthenticationApiService> {

    /**
     * Tests the authenticate API operation.
     */
    @Test
    public void authenticate() {
        for(final User user : getRepository().findAll()) {
            final AuthenticationDTO authenticationDTO = new AuthenticationDTO()
                    .username(user.getEmail())
                    .password("password");

            final AuthenticationResultDTO authenticationResultDTO = getApiService().authenticate(authenticationDTO);

            assertNotNull(authenticationResultDTO, "Authentication result should not be null.");
            assertNotNull(authenticationResultDTO.getJwt(), "Authentication result JWT should not be null.");
            assertFalse(authenticationResultDTO.getJwt().isEmpty(), "Authentication result JWT should not be empty.");
        }

        final AuthenticationDTO invalidAuthenticationDTO = new AuthenticationDTO()
                .username("invalidUsername")
                .password("invalidPassword");
        final D11ApiServiceException d11ApiServiceException =
                assertThrows(D11ApiServiceException.class, () -> getApiService().authenticate(invalidAuthenticationDTO));
        assertEquals(HttpStatus.UNAUTHORIZED, d11ApiServiceException.getStatusCode(),
                "Invalid login exception should have status code 401.");
    }

}
