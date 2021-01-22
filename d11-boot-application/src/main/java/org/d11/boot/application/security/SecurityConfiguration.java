package org.d11.boot.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

/**
 * Configures security for the application.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        // Turn off CSRF since we're using another token (JWT).
        // TODO: Sort out authorizations
        httpSecurity.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll();
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

}
