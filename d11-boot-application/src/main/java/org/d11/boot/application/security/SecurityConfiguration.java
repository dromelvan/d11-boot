package org.d11.boot.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.List;

/**
 * Configures security for the application.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Converts from a JWT to a set of authorities.
     */
    private final JwtToGrantedAuthorityConverter jwtToGrantedAuthorityConverter;

    /**
     * Creates a new security configuration.
     *
     * @param jwtToGrantedAuthorityConverter The JWT to granted authorities converter the configuration will use.
     */
    @Autowired
    public SecurityConfiguration(final JwtToGrantedAuthorityConverter jwtToGrantedAuthorityConverter) {
        this.jwtToGrantedAuthorityConverter = jwtToGrantedAuthorityConverter;
    }

    @Override
    @SuppressWarnings("checkstyle:Indentation")
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this.jwtToGrantedAuthorityConverter);

        // Not doing any authorizeRequests since we'll be using method security with @RolesAllowed etc on individual methods.
        httpSecurity
                // Add a CORS filter.
                .cors()
                .and()
                // Disable CSRF since we'll be using JWT.
                .csrf().disable()
                // Stateless session management.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Disable Spring MVC error handling since this will be a REST application.
                .exceptionHandling().disable()
                // Configure as an OAuth2 resource server that authenticates using JWT.
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                );
    }

    /**
     * Provides a CORS configuration source.
     *
     * @return CorsConfigurationSource for the application to use.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // TODO: Maybe tighten this up.
        final List<String> wildCardList = Collections.singletonList("*");
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(wildCardList);
        configuration.setAllowedMethods(wildCardList);
        configuration.setAllowedHeaders(wildCardList);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Provides a password encoder.
     *
     * @return A BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides a JWT decoder.
     *
     * @param rsaPublicKey The public key that will be used to decode the JWT.
     * @return A JWT decoder for decoding JWT encoded with the provided public key.
     */
    @Bean
    public JwtDecoder jwtDecoder(final RSAPublicKey rsaPublicKey) {
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

}
