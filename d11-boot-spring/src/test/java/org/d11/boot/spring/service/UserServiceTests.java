package org.d11.boot.spring.service;

import org.d11.boot.spring.model.User;
import org.d11.boot.spring.model.UserRegistration;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User service tests.
 */
class UserServiceTests extends BaseD11BootServiceTests {

    /**
     * Name property.
     */
    private static final String NAME_PROPERTY = "name";

    /**
     * Email property.
     */
    private static final String EMAIL_PROPERTY = "email";

    /**
     * Password property.
     */
    private static final String PASSWORD_PROPERTY = "password";

    /**
     * Confirm password property.
     */
    private static final String CONFIRM_PASSWORD_PROPERTY = "confirmPassword";

    /**
     * Current password property.
     */
    private static final String CURRENT_PASSWORD_PROPERTY = "currentPassword";

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
        final UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail(EMAIL_PROPERTY);
        userRegistration.setPassword(PASSWORD_PROPERTY);
        userRegistration.setConfirmPassword(PASSWORD_PROPERTY);

        // Name --------------------------------------------------------------------------------------------------------

        BadRequestException exception = assertThrows(BadRequestException.class,
                                                     () -> this.userService.createUser(userRegistration),
                                                     "UserService::createUser name missing throws");
        assertEquals(NAME_PROPERTY, exception.getParameter(), "UserService::createUser name missing parameter equals");

        userRegistration.setName("EXISTING_NAME");
        when(this.userRepository.findByName(eq(userRegistration.getName()))).thenReturn(Optional.of(new User()));

        assertThrows(ConflictException.class,
                     () -> this.userService.createUser(userRegistration),
                     "UserService::createUser name unavailable throws");

        userRegistration.setName(NAME_PROPERTY);
        userRegistration.setEmail(null);

        // Email -------------------------------------------------------------------------------------------------------

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser email missing throws");
        assertEquals(EMAIL_PROPERTY, exception.getParameter(),
                     "UserService::createUser email missing parameter equals");

        userRegistration.setEmail("EXISTING_EMAIL");
        when(this.userRepository.findByEmail(eq(userRegistration.getEmail()))).thenReturn(Optional.of(new User()));

        assertThrows(ConflictException.class,
                     () -> this.userService.createUser(userRegistration),
                     "UserService::createUser email unavailable throws");

        userRegistration.setEmail(EMAIL_PROPERTY);

        // Password-----------------------------------------------------------------------------------------------------

        userRegistration.setPassword(null);

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser password missing throws");
        assertEquals(PASSWORD_PROPERTY, exception.getParameter(),
                     "UserService::createUser password missing parameter equals");

        userRegistration.setPassword(PASSWORD_PROPERTY);

        // Confirm password --------------------------------------------------------------------------------------------

        userRegistration.setConfirmPassword(null);

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser confirmPassword missing throws");
        assertEquals(CONFIRM_PASSWORD_PROPERTY, exception.getParameter(),
                     "UserService::createUser confirmPassword missing parameter equals");

        final String encodedPassword = "ENCODED_PASSWORD";

        userRegistration.setConfirmPassword(encodedPassword);

        exception = assertThrows(BadRequestException.class,
                                 () -> this.userService.createUser(userRegistration),
                                 "UserService::createUser confirmPassword invalid throws");
        assertEquals(CONFIRM_PASSWORD_PROPERTY, exception.getParameter(),
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
     * Tests UserService::updateUserPassword.
     */
    @Test
    @SuppressWarnings({ "PMD.ExcessiveMethodLength", "checkstyle:ExecutableStatementCount" })
    void testUpdateUserPassword() {

        // Current password --------------------------------------------------------------------------------------------

        BadRequestException badRequestException =
                assertThrows(BadRequestException.class,
                             () -> this.userService.updateUserPassword(1L,
                                                                       null,
                                                                       PASSWORD_PROPERTY,
                                                                       CONFIRM_PASSWORD_PROPERTY),
                             "UserService::updateUserPassword currentPassword missing throws");
        assertEquals(CURRENT_PASSWORD_PROPERTY, badRequestException.getParameter(),
                     "UserService::updateUserPassword currentPassword missing parameter equals");

        // Password-----------------------------------------------------------------------------------------------------

        badRequestException = assertThrows(BadRequestException.class,
                                 () -> this.userService.updateUserPassword(1L,
                                                                           CURRENT_PASSWORD_PROPERTY,
                                                                           null,
                                                                           CONFIRM_PASSWORD_PROPERTY),
                                 "UserService::updateUserPassword password missing throws");
        assertEquals(PASSWORD_PROPERTY, badRequestException.getParameter(),
                     "UserService::updateUserPassword password missing parameter equals");

        // Confirm password --------------------------------------------------------------------------------------------

        badRequestException = assertThrows(BadRequestException.class,
                                 () -> this.userService.updateUserPassword(1L,
                                                                           CURRENT_PASSWORD_PROPERTY,
                                                                           PASSWORD_PROPERTY,
                                                                           null),
                                 "UserService::updateUserPassword confirmPassword missing throws");
        assertEquals(CONFIRM_PASSWORD_PROPERTY, badRequestException.getParameter(),
                     "UserService::updateUserPassword confirmPassword missing parameter equals");

        badRequestException = assertThrows(BadRequestException.class,
                                 () -> this.userService.updateUserPassword(1L,
                                                                           CURRENT_PASSWORD_PROPERTY,
                                                                           PASSWORD_PROPERTY,
                                                                           CONFIRM_PASSWORD_PROPERTY),
                                 "UserService::updateUserPassword confirmPassword not matching throws");
        assertEquals(CONFIRM_PASSWORD_PROPERTY, badRequestException.getParameter(),
                     "UserService::updateUserPassword confirmPassword not matching parameter equals");

        // User not found ----------------------------------------------------------------------------------------------

        when(this.userRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.userService.updateUserPassword(1L,
                                                                                        CURRENT_PASSWORD_PROPERTY,
                                                                                        PASSWORD_PROPERTY,
                                                                                        PASSWORD_PROPERTY),
                "UserService::updateUserPassword user not found throws");

        // No current user ---------------------------------------------------------------------------------------------

        final User user = new User();
        user.setId(0L);
        user.setEncryptedPassword(PASSWORD_PROPERTY);
        user.setEmail("user@email.com");

        when(this.userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        // Have to explicitly set this to null here in case some other test has authenticated before this.
        SecurityContextHolder.getContext().setAuthentication(null);

        assertThrows(UnauthorizedException.class, () -> this.userService.updateUserPassword(1L,
                                                                                            CURRENT_PASSWORD_PROPERTY,
                                                                                            PASSWORD_PROPERTY,
                                                                                            PASSWORD_PROPERTY),
                     "UserService::updateUserPassword no current user throws");

        // Wrong user --------------------------------------------------------------------------------------------------

        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        SecurityContextHolder.getContext().setAuthentication(this.authentication);

        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        this.userService.setApplicationContext(this.applicationContext);

        when(this.jwt.getClaimAsString(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(user.getEmail());
        when(this.userRepository.findByEmail(eq(user.getEmail()))).thenReturn(Optional.of(user));

        assertThrows(ForbiddenException.class,
                     () -> this.userService.updateUserPassword(1L,
                                                               CURRENT_PASSWORD_PROPERTY,
                                                               PASSWORD_PROPERTY,
                                                               PASSWORD_PROPERTY),
                     "UserService::updateUserPassword wrong user throws");

        // Wrong current password --------------------------------------------------------------------------------------

        user.setId(1L);
        reset(this.passwordEncoder);

        assertThrows(ForbiddenException.class,
                     () -> this.userService.updateUserPassword(1L,
                                                               CURRENT_PASSWORD_PROPERTY,
                                                               PASSWORD_PROPERTY,
                                                               PASSWORD_PROPERTY),
                     "UserService::updateUserPassword wrong current password throws");

        verify(this.passwordEncoder, times(1)).matches(CURRENT_PASSWORD_PROPERTY, user.getEncryptedPassword());

        // OK ----------------------------------------------------------------------------------------------------------

        user.setEncryptedPassword(CURRENT_PASSWORD_PROPERTY);
        when(this.userRepository.save(eq(user))).thenReturn(user);
        when(this.passwordEncoder.matches(eq(CURRENT_PASSWORD_PROPERTY), eq(CURRENT_PASSWORD_PROPERTY)))
                .thenReturn(true);
        when(this.passwordEncoder.encode(eq(PASSWORD_PROPERTY))).thenReturn(PASSWORD_PROPERTY);

        final User result = this.userService.updateUserPassword(1L,
                                                                CURRENT_PASSWORD_PROPERTY,
                                                                PASSWORD_PROPERTY,
                                                                PASSWORD_PROPERTY);

        assertNotNull(result, "UserService::updateUserPassword result not null");
        assertEquals(user, result, "UserService::updateUserPassword result equals");
        assertEquals(PASSWORD_PROPERTY, result.getEncryptedPassword(),
                     "UserService::updateUserPassword result password equals");

        verify(this.passwordEncoder, times(1)).encode(eq(PASSWORD_PROPERTY));
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
