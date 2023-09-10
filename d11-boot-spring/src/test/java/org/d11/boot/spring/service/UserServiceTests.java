package org.d11.boot.spring.service;

import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * User service tests.
 */
class UserServiceTests extends D11BootServiceTests {

    /**
     * Invalid user name.
     */
    private static final String INVALID_USER_NAME = "INVALID_USER_NAME";

    /**
     * Mocked user repository.
     */
    @Mock
    private transient UserRepository userRepository;

    /**
     * User service.
     */
    @InjectMocks
    private transient UserService userService;

    /**
     * Tests UserServiceTests::testLoadCachedUserByUsername.
     */
    @Test
    void testLoadCachedUserByUsername() {
        final List<User> users = generateList(User.class);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        users.forEach(user -> {
            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            final UserDetails result = this.userService.loadCachedUserByUsername(user.getEmail());
            assertNotNull(result, "UserService::loadCachedUserByUsername not null");
            assertEquals(user.getEmail(), result.getUsername(), "UserService::loadCachedUserByUsername email");
            assertEquals(user.getEncryptedPassword(), result.getPassword(),
                         "UserService::loadCachedUserByUsername encrypted password");
        });

        assertThrows(UsernameNotFoundException.class,
                     () -> this.userService.loadCachedUserByUsername(INVALID_USER_NAME),
                     "UserService::loadCachedUserByUsername not found");
    }

    /**
     * Tests UserServiceTests::testLoadUserByUsername.
     */
    @Test
    void testLoadUserByUsername() {
        final List<User> users = generateList(User.class);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        users.forEach(user -> {
            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            final UserDetails result = this.userService.loadUserByUsername(user.getEmail());
            assertNotNull(result, "UserService::loadUserByUsername not null");
            assertEquals(user.getEmail(), result.getUsername(), "UserService::loadUserByUsername email");
            assertEquals(user.getEncryptedPassword(), result.getPassword(),
                         "UserService::loadUserByUsername encrypted password");
        });

        assertThrows(UsernameNotFoundException.class,
                     () -> this.userService.loadUserByUsername(INVALID_USER_NAME),
                     "UserService::loadUserByUsername not found");
    }

}
