package org.d11.boot.application.service.api;

import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.api.model.D11TeamNameDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.security.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Provides authentication services.
 */
@Service
public class AuthenticationService extends ApiService {

    /**
     * User details service for looking up user information in the database.
     */
    private final UserDetailsService userDetailsService;
    /**
     * D11 team repository for finding user D11 teams.
     */
    private final D11TeamRepository d11TeamRepository;
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
     * @param d11TeamRepository  The D11 team repository this service will use.
     * @param passwordEncoder    The password encoder this service will use.
     * @param jwtBuilder         The JWT builder this service will use.
     */
    @Autowired
    public AuthenticationService(final UserDetailsService userDetailsService,
                                 final D11TeamRepository d11TeamRepository,
                                 final PasswordEncoder passwordEncoder,
                                 final JwtBuilder jwtBuilder) {
        this.userDetailsService = userDetailsService;
        this.d11TeamRepository = d11TeamRepository;
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
            final D11Team d11Team = this.d11TeamRepository.findByOwnerEmail(userDetails.getUsername()).orElse(null);
            final String jwt = this.jwtBuilder.build(username);
            return new AuthenticationResultDTO()
                    .d11Team(map(d11Team, D11TeamNameDTO.class))
                    .jwt(jwt)
                    .authorities(userDetails.getAuthorities().stream().map(GrantedAuthority::toString).collect(Collectors.toList()));
        }
        throw new BadCredentialsException("Authentication failed");
    }

}
