package org.d11.boot.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Configures security for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Converts from a JWT to a set of authorities.
     */
    private final JwtToGrantedAuthorityConverter jwtToGrantedAuthorityConverter;

    /**
     * Allowed origins for CORS configuration.
     */
    @Value("${d11.security.allowedOrigins}")
    private String[] allowedOrigins;

    /**
     * Creates a new security configuration.
     *
     * @param jwtToGrantedAuthorityConverter The JWT to granted authorities converter the configuration will use.
     */
    @Autowired
    public SecurityConfiguration(final JwtToGrantedAuthorityConverter jwtToGrantedAuthorityConverter) {
        this.jwtToGrantedAuthorityConverter = jwtToGrantedAuthorityConverter;
    }

    /**
     * Provides a security filter chain.
     *
     * @param httpSecurity Http security that will be configured.
     * @return Configured security filter chain.
     */
    @Bean
    @SuppressWarnings({ "checkstyle:IllegalCatch", "PMD.AvoidCatchingGenericException" })
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) {
        try {
            final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
            jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this.jwtToGrantedAuthorityConverter);

            // Not doing authorizeRequests since we'll use method security with @RolesAllowed etc. on methods.
            httpSecurity
                    // Add a CORS filter.
                    .cors(Customizer.withDefaults())
                    // Disable CSRF since we'll be using JWT.
                    .csrf(AbstractHttpConfigurer::disable)
                    // Stateless session management.
                    .sessionManagement(sessionManagement ->
                                           sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    // Disable Spring MVC error handling since this will be a REST application.
                    .exceptionHandling(AbstractHttpConfigurer::disable)
                    // Configure as an OAuth2 resource server that authenticates using JWT.
                    .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                    )
                    // Without this, the H2 console complains that 'X-Frame-Options' is set to 'deny'.
                    .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

            return httpSecurity.build();
        } catch (final Exception e) {
            // This should not happen.
            throw new IllegalStateException("Security filter chain could not be created.", e);
        }
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

    /**
     * Provides a CORS configuration source.
     *
     * @return CorsConfigurationSource for the application to use.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final List<String> wildCardList = Collections.singletonList("*");

        final CorsConfiguration configuration = new CorsConfiguration();
        // It seems wild card doesn't work for requests from an actual browser even though it does in Postman.
        configuration.setAllowedOrigins(Arrays.stream(this.allowedOrigins)
                                            .map(allowedOrigin -> "http://" + allowedOrigin)
                                            .collect(Collectors.toList()));
        configuration.setAllowedMethods(wildCardList);
        configuration.setAllowedHeaders(wildCardList);
        // Allow credentials to enable setting cookies for POST requests.
        configuration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
