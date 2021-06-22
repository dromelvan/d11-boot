package org.d11.boot.application.service;

import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.application.security.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Provides authentication services.
 */
@Service
public class AuthenticationService extends D11BootService {

    /**
     * User details service for looking up user information in the database.
     */
    private final UserDetailsService userDetailsService;
    /**
     * Password encoder that matches provided password with the encrypted user password in the database.
     */
    private final PasswordEncoder passwordEncoder;
    /**
     * JWT builder that generates JWTs after successful authentication.
     */
    private final JwtBuilder jwtBuilder;

    /**
     * Creates a new service.
     *
     * @param userDetailsService The user details service this service will use.
     * @param passwordEncoder    The password encoder this service will use.
     * @param jwtBuilder         The JWT builder this service will use.
     */
    @Autowired
    public AuthenticationService(final UserDetailsService userDetailsService,
                                 final PasswordEncoder passwordEncoder,
                                 final JwtBuilder jwtBuilder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtBuilder = jwtBuilder;
    }

    /**
     * Authenticates a username and password combination.
     *
     * @param username The username that will be authenticated.
     * @param password The password that will be authenticated.
     * @return Authentication result with a generated JWT.
     * @throws AccessDeniedException If authentication failed.
     */
    public AuthenticationResultDTO authenticate(final String username, final String password) {
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if(this.passwordEncoder.matches(password, userDetails.getPassword())) {
            final String jwt = this.jwtBuilder.build(username);
            return new AuthenticationResultDTO()
                    .jwt(jwt);
        }
        throw new BadCredentialsException("Authentication failed");
    }

}
