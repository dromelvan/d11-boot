package org.d11.boot.application.service.api;

import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.UserRepository;
import org.d11.boot.application.service.D11BootService;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Base class for API services.
 */
public class ApiService extends D11BootService {

    /**
     * The name of the JWT claim that contains the username.
     */
    private static final String USERNAME_CLAIM = "username";

    /**
     * Gets the user that is authenticated in the current security context. This method is intended to be used from
     * methods protected by @RolesAllowed where we can be sure there is a JWT authentication in the context.
     *
     * @return The user authenticated in the current security context.
     */
    protected User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        final UserRepository userRepository = getRepository(UserRepository.class);
        return userRepository.findByEmail(jwt.getClaimAsString(USERNAME_CLAIM)).orElseThrow(NotFoundException::new);
    }

}
