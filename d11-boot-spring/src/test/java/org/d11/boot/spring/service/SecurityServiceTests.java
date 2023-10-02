package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Authentication;
import org.d11.boot.spring.model.Authorization;
import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.RefreshTokenRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.spring.security.ResetPasswordLinkMailMessage;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

/**
 * Security service tests.
 */
class SecurityServiceTests extends BaseD11BootServiceTests {

    /**
     * Dummy password.
     */
    private static final String PASSWORD = "password";

    /**
     * Dummy JWT.
     */
    private static final String JWT = "JWT";

    /**
     * Mocked user repository.
     */
    @Mock
    private transient UserRepository userRepository;

    /**
     * Mocked refresh token repository.
     */
    @Mock
    private transient RefreshTokenRepository refreshTokenRepository;

    /**
     * Mocked password encoder.
     */
    @Mock
    private transient PasswordEncoder passwordEncoder;

    /**
     * Mocked JWT builder.
     */
    @Mock
    private transient JwtBuilder jwtBuilder;

    /**
     * Mocked Java mail sender.
     */
    @Mock
    private transient JavaMailSender javaMailSender;

    /**
     * Security service.
     */
    @InjectMocks
    private transient SecurityService securityService;

    /**
     * Tests SecurityService::authenticate for a non persistent authentication.
     */
    @Test
    void testAuthenticateNonPersistent() {
        final List<User> users = generateList(User.class);

        when(this.passwordEncoder.matches(eq(PASSWORD), anyString())).thenReturn(true);
        when(this.jwtBuilder.build(anyString(), any(LocalDateTime.class))).thenReturn(JWT);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(this.refreshTokenRepository.save(any(RefreshToken.class)))
            .thenAnswer(invocation -> invocation.getArguments()[0]);

        users.forEach(user -> {
            user.setEncryptedPassword(PASSWORD);

            assertThrows(BadCredentialsException.class,
                         () -> this.securityService.authenticate(user.getEmail(), user.getEncryptedPassword(), false),
                         "SecurityService::authenticate non persistent bad credentials");

            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            final Authentication result = this.securityService.authenticate(user.getEmail(),
                                                                            user.getEncryptedPassword(),
                                                                            false);

            assertNotNull(result, "SecurityService::authenticate non persistent not null");

            assertEquals(user, result.getUser(), "SecurityService::authenticate non persistent user");
            assertEquals(JWT, result.getJwt(), "SecurityService::authenticate non persistent jwt");
            assertEquals(LocalDate.now().plusDays(1), result.getExpiresAt().toLocalDate(),
                         "SecurityService::authenticate non persistent expires at");
            assertFalse(result.isPersistent(), "SecurityService::authenticate non persistent persistent");

            assertNotNull(result.getRefreshToken(),
                          "SecurityService::authenticate non persistent refresh token not null");
            assertEquals(result.getUser(), result.getRefreshToken().getUser(),
                         "SecurityService::authenticate non persistent refresh token user");
            assertEquals(result.getExpiresAt(), result.getRefreshToken().getExpiresAt(),
                         "SecurityService::authenticate non persistent refresh token expires at");
        });

    }

    /**
     * Tests SecurityServiceTests::authenticate for a persistent authentication.
     */
    @Test
    void testAuthenticatePersistent() {
        final List<User> users = generateList(User.class);

        when(this.passwordEncoder.matches(eq(PASSWORD), anyString())).thenReturn(true);
        when(this.jwtBuilder.build(anyString(), any(LocalDateTime.class))).thenReturn(JWT);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(this.refreshTokenRepository.save(any(RefreshToken.class)))
            .thenAnswer(invocation -> invocation.getArguments()[0]);

        users.forEach(user -> {
            user.setEncryptedPassword(PASSWORD);

            assertThrows(BadCredentialsException.class,
                         () -> this.securityService.authenticate(user.getEmail(), user.getEncryptedPassword(), true),
                         "SecurityService::authenticate persistent bad credentials");

            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            final Authentication result = this.securityService.authenticate(user.getEmail(),
                                                                            user.getEncryptedPassword(),
                                                                            true);

            assertNotNull(result, "SecurityService::authenticate persistent not null");

            assertEquals(user, result.getUser(), "SecurityService::authenticate persistent user");
            assertEquals(JWT, result.getJwt(), "SecurityService::authenticate persistent jwt");
            assertEquals(LocalDate.now().plusDays(Authentication.PERSISTENT_DAYS_VALID),
                         result.getExpiresAt().toLocalDate(),
                         "SecurityService::authenticate persistent expires at");
            assertTrue(result.isPersistent(), "SecurityService::authenticate persistent persistent");

            assertNotNull(result.getRefreshToken(), "SecurityService::authenticate persistent refresh token not null");
            assertEquals(result.getUser(), result.getRefreshToken().getUser(),
                         "SecurityService::authenticate persistent refresh token user");
            assertNull(result.getRefreshToken().getExpiresAt(),
                       "SecurityService::authenticate persistent refresh token expires at");
        });

    }

    /**
     * Tests SecurityService::authorize for a non persistent authentication.
     */
    @Test
    void testAuthorizeNonPersistent() {
        final User user = generate(User.class);

        final RefreshToken refreshToken = new RefreshToken(user, LocalDateTime.now().plusDays(1));

        assertThrows(BadCredentialsException.class, () -> this.securityService.authorize(refreshToken.getUuid()),
                     "SecurityService::authorize non persistent bad credentials");

        when(this.refreshTokenRepository.findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(any(), any()))
            .thenReturn(Optional.of(refreshToken));
        when(this.jwtBuilder.build(anyString(), any(LocalDateTime.class))).thenReturn(JWT);

        final Authorization result = this.securityService.authorize(refreshToken.getUuid());

        assertNotNull(result, "SecurityService::authorize non persistent not null");

        assertEquals(user, result.getUser(), "SecurityService::authorize non persistent user");
        assertEquals(JWT, result.getJwt(), "SecurityService::authorize non persistent jwt");
        assertNotNull(result.getExpiresAt(), "SecurityService::authorize non persistent expires at not null,");
        assertEquals(refreshToken.getExpiresAt(), result.getExpiresAt(),
                     "SecurityService::authorize non persistent expires at");
        assertFalse(result.isPersistent(), "SecurityService::authorize non persistent persistent");

        assertNotNull(result.getRefreshToken(), "SecurityService::authorize non persistent refresh token not null,");
        assertEquals(result.getUser(), result.getRefreshToken().getUser(),
                     "SecurityService::authorize non persistent refresh token user");
        assertEquals(result.getExpiresAt(), result.getRefreshToken().getExpiresAt(),
                     "SecurityService::authorize non persistent refresh token expires at");
    }

    /**
     * Tests SecurityService::authorize for a persistent authentication.
     */
    @Test
    void testAuthorizePersistent() {
        final User user = generate(User.class);

        final RefreshToken refreshToken = new RefreshToken(user);

        assertThrows(BadCredentialsException.class, () -> this.securityService.authorize(refreshToken.getUuid()),
                     "SecurityService::authorize persistent bad credentials");

        when(this.refreshTokenRepository.findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(any(), any()))
            .thenReturn(Optional.of(refreshToken));
        when(this.jwtBuilder.build(anyString(), any(LocalDateTime.class))).thenReturn(JWT);

        final Authorization result = this.securityService.authorize(refreshToken.getUuid());

        assertNotNull(result, "SecurityService::authorize persistent not null");

        assertEquals(user, result.getUser(), "SecurityService::authorize persistent user");
        assertEquals(JWT, result.getJwt(), "SecurityService::authorize persistent jwt");
        assertNotNull(result.getExpiresAt(), "SecurityService::authorize persistent expires at not null");
        assertEquals(LocalDateTime.now().plusDays(Authorization.PERSISTENT_DAYS_VALID).toLocalDate(),
                     result.getExpiresAt().toLocalDate(),
                     "SecurityService::authorize persistent expires at");
        assertTrue(result.isPersistent(), "SecurityService::authorize persistent persistent");

        assertNotNull(result.getRefreshToken(), "SecurityService::authorize persistent refresh token not null");
        assertEquals(result.getUser(), result.getRefreshToken().getUser(),
                     "SecurityService::authorize persistent refresh token user");
        assertNull(result.getRefreshToken().getExpiresAt(),
                   "SecurityService::authorize persistent refresh token expires at");
    }

    /**
     * Tests SecurityService::unauthorize.
     */
    @Test
    void testUnauthorize() {
        final User user = generate(User.class);
        final RefreshToken refreshToken = new RefreshToken(user);


        this.securityService.unauthorize(refreshToken.getUuid());

        verify(this.refreshTokenRepository, times(1)).findByUuid(eq(refreshToken.getUuid()));
        verify(this.refreshTokenRepository, times(0)).delete(eq(refreshToken));

        when(this.refreshTokenRepository.findByUuid(eq(refreshToken.getUuid()))).thenReturn(Optional.of(refreshToken));

        this.securityService.unauthorize(refreshToken.getUuid());

        verify(this.refreshTokenRepository, times(2)).findByUuid(eq(refreshToken.getUuid()));
        verify(this.refreshTokenRepository, times(1)).delete(eq(refreshToken));
    }

    /**
     * Tests SecurityService::requestPasswordReset.
     */
    @Test
    void testRequestPasswordReset() {
        final User user = generate(User.class);
        user.setResetPasswordToken(null);
        final String link = "%s";

        this.securityService.requestPasswordReset("INVALID_EMAIL", link);

        verifyNoInteractions(this.javaMailSender);
        assertNull(user.getResetPasswordToken(), "SecurityService::requestPasswordReset resetPasswordToken null");

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        this.securityService.requestPasswordReset(user.getEmail(), link);

        final ResetPasswordLinkMailMessage resetPasswordLinkMailMessage = new ResetPasswordLinkMailMessage(user, link);

        assertNotNull(user.getResetPasswordToken(),
                      "SecurityService::requestPasswordReset resetPasswordToken not null");
        assertNotNull(resetPasswordLinkMailMessage.getTo(), "SecurityService::requestPasswordReset to not null");
        assertEquals(resetPasswordLinkMailMessage.getTo().length, 1,
                     "SecurityService::requestPasswordReset to length equals");
        assertEquals(resetPasswordLinkMailMessage.getTo()[0], user.getEmail(),
                     "SecurityService::requestPasswordReset to equals");
        assertNotNull(resetPasswordLinkMailMessage.getText(), "SecurityService::requestPasswordReset text not null");
        assertTrue(resetPasswordLinkMailMessage.getText().contains(user.getResetPasswordToken().toString()),
                   "SecurityService::requestPasswordReset text contains resetPasswordToken");
        verify(this.javaMailSender, times(1)).send(eq(resetPasswordLinkMailMessage));
    }

    /**
     * Tests SecurityService::resetPassword.
     */
    @Test
    void testResetPassword() {
        final User user = generate(User.class);
        final String oldPassword = user.getEncryptedPassword();
        final String encryptedPassword = "encryptedPassword";

        when(this.userRepository.findByEmailAndResetPasswordToken(eq(user.getEmail()),
                                                                  eq(user.getResetPasswordToken())))
            .thenReturn(Optional.empty());

        assertThrows(BadCredentialsException.class,
                     () -> this.securityService.resetPassword(user.getEmail(), PASSWORD, user.getResetPasswordToken()));
        assertEquals(user.getEncryptedPassword(), oldPassword,
                     "SecurityService::resetPassword oldPassword equals");
        assertNotNull(user.getResetPasswordToken(), "SecurityService::resetPassword resetPasswordToken not null");
        verifyNoInteractions(this.passwordEncoder);

        when(this.userRepository.findByEmailAndResetPasswordToken(eq(user.getEmail()),
                                                                  eq(user.getResetPasswordToken())))
            .thenReturn(Optional.of(user));
        when(this.passwordEncoder.encode(eq(PASSWORD))).thenReturn(encryptedPassword);

        this.securityService.resetPassword(user.getEmail(), PASSWORD, user.getResetPasswordToken());

        assertEquals(user.getEncryptedPassword(), encryptedPassword,
                     "SecurityService::resetPassword encryptedPassword equals");
        assertNull(user.getResetPasswordToken(), "SecurityService::resetPassword resetPasswordToken null");
        verify(this.passwordEncoder, times(1)).encode(eq(PASSWORD));
    }

}
