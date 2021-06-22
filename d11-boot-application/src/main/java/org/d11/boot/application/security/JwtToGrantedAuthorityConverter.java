package org.d11.boot.application.security;

import org.d11.boot.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Converts a JWT to a set of granted authorities by fetching them from the user in the database.
 */
@Component
public class JwtToGrantedAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * The user service that will be used to fetch the user details from the database.
     */
    private final UserService userService;

    /**
     * Creates a new JWT to granted authority converter.
     *
     * @param userService The user service the converter will use.
     */
    @Autowired
    public JwtToGrantedAuthorityConverter(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        final String username = jwt.getClaimAsString("username");
        final UserDetails userDetails = this.userService.loadCachedUserByUsername(username);
        return new ArrayList<>(userDetails.getAuthorities());
    }

}
