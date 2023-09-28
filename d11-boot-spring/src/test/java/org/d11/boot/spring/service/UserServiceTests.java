package org.d11.boot.spring.service;

import org.d11.boot.spring.model.User;
import org.d11.boot.spring.model.UserRegistration;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    private UserRepository userRepository;

    /**
     * Mocked password encoder.
     */
    @Mock
    private PasswordEncoder passwordEncoder;

    /**
     * User service.
     */
    @InjectMocks
    private UserService userService;

    /**
     * Tests UserService::createUser.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testCreateUser() {
        final String nameProperty = "name";
        final String emailProperty = "email";
        final String passwordProperty = "password";
        final String confirmPasswordProperty = "confirmPassword";

        final UserRegistration userRegistration = new UserRegistration();

        // Name --------------------------------------------------------------------------------------------------------

        BadRequestException exception = assertThrows(BadRequestException.class,
                                                     () -> this.userService.createUser(userRegistration),
                                                     "UserService::createUser name missing throws");
        assertEquals(nameProperty, exception.getParameter(), "UserService::createUser name missing parameter equals");

        userRegistration.setName("EXISTING_NAME");
        when(this.userRepository.findByName(eq(userRegistration.getName()))).thenReturn(Optional.of(new User()));

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser name invalid throws");
        assertEquals(nameProperty, exception.getParameter(), "UserService::createUser name invalid parameter equals");

        userRegistration.setName("USER_NAME");

        // Email -------------------------------------------------------------------------------------------------------

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser email missing throws");
        assertEquals(emailProperty, exception.getParameter(), "UserService::createUser email missing parameter equals");

        userRegistration.setEmail("EXISTING_EMAIL");
        when(this.userRepository.findByEmail(eq(userRegistration.getEmail()))).thenReturn(Optional.of(new User()));

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser email invalid throws");
        assertEquals(emailProperty, exception.getParameter(), "UserService::createUser email invalid parameter equals");

        userRegistration.setEmail("USER_EMAIL");

        // Password-----------------------------------------------------------------------------------------------------

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser password missing throws");
        assertEquals(passwordProperty, exception.getParameter(),
                     "UserService::createUser password missing parameter equals");

        userRegistration.setPassword("USER_PASSWORD");

        // Confirm password --------------------------------------------------------------------------------------------

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser confirmPassword missing throws");
        assertEquals(confirmPasswordProperty, exception.getParameter(),
                     "UserService::createUser confirmPassword missing parameter equals");

        final String encodedPassword = "ENCODED_PASSWORD";

        userRegistration.setConfirmPassword(encodedPassword);

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser confirmPassword invalid throws");
        assertEquals(confirmPasswordProperty, exception.getParameter(),
                     "UserService::createUser confirmPassword invalid parameter equals");

        userRegistration.setConfirmPassword(userRegistration.getPassword());

        // OK ----------------------------------------------------------------------------------------------------------

        when(this.passwordEncoder.encode(eq(userRegistration.getPassword()))).thenReturn(encodedPassword);
        when(this.userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        final User user = this.userService.createUser(userRegistration);

        assertEquals(userRegistration.getName(), user.getName(), "UserService::createUser name equals");
        assertEquals(userRegistration.getEmail(), user.getEmail(), "UserService::createUser email equals");
        assertEquals(encodedPassword, user.getEncryptedPassword(), "UserService::createUser encryptedPassword equals");
        assertFalse(user.isAdministrator(), "UserService::createUser administrator");

        verify(this.userRepository, times(1)).save(eq(user));
    }

    /**
     * Tests UserService::loadCachedUserByUsername.
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
     * Tests UserService::loadUserByUsername.
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
