package org.d11.boot.spring.service;

import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.security.JwtBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * D11 Boot service tests.
 */
class D11BootServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked authentication.
     */
    @Mock
    private Authentication authentication;

    /**
     * Mocked JWT.
     */
    @Mock
    private Jwt jwt;

    /**
     * Mocked application context.
     */
    @Mock
    private ApplicationContext applicationContext;

    /**
     * Mocked user repository.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Tests D11BootService::getRepository.
     */
    @Test
    void testGetRepository() {
        final D11BootService d11BootService = new D11BootService();

        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        d11BootService.setApplicationContext(this.applicationContext);

        final UserRepository result = d11BootService.getRepository(UserRepository.class);

        assertNotNull(result, "D11BootService::getRepository result not null");
        assertEquals(this.userRepository, result, "D11BootService::getRepository result equals");

        verify(this.applicationContext, times(1)).getBean(eq(UserRepository.class));
    }

    /**
     * Tests D11BootService::getCurrentUser.
     */
    @Test
    void testGetCurrentUser() {
        final D11BootService d11BootService = new D11BootService();

        assertFalse(d11BootService.getCurrentUser().isPresent(),
                    "D11BootService::getCurrentUser no authentication present");

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.authentication);

        assertFalse(d11BootService.getCurrentUser().isPresent(),
                    "D11BootService::getCurrentUser non JWT authentication present");

        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        d11BootService.setApplicationContext(this.applicationContext);

        final User user = new User();
        user.setEmail("user@email.com");

        when(this.jwt.getClaimAsString(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(user.getEmail());
        when(this.userRepository.findByEmail(eq(user.getEmail()))).thenReturn(Optional.of(user));

        final User result = d11BootService.getCurrentUser().orElse(null);

        assertNotNull(result, "D11BootService::getCurrentUser result not null");
        assertEquals(user, result, "D11BootService::getCurrentUser result equals");
    }

}
