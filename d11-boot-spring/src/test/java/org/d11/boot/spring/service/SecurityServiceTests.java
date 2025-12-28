package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Authentication;
import org.d11.boot.spring.model.Authorization;
import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.RefreshTokenRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuildResult;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.spring.security.ResetPasswordLinkMailMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
     * Time to live for non-persistent request tokens.
     */
    private static final int TIME_TO_LIVE = 60 * 60 * 24;

    /**
     * Time to live for persistent request tokens.
     */
    private static final int TIME_TO_LIVE_PERSISTENT = 60 * 60 * 24 * 30;

    /**
     * Mocked user repository.
     */
    @Mock
    private transient UserRepository userRepository;

    /**
     * Mocked refresh token repository.
     */
    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    /**
     * Mocked password encoder.
     */
    @Mock
    private PasswordEncoder passwordEncoder;

    /**
     * Mocked JWT builder.
     */
    @Mock
    private JwtBuilder jwtBuilder;

    /**
     * Mocked Java mail sender.
     */
    @Mock
    private JavaMailSender javaMailSender;

    /**
     * Security service.
     */
    private SecurityService securityService;

    /**
     * Can't do @InjectMocks with the time to live variables so we'll create the service here.
     */
    @BeforeEach
    void setUp() {
        securityService = new SecurityService(this.userRepository,
                                              this.refreshTokenRepository,
                                              TIME_TO_LIVE,
                                              TIME_TO_LIVE_PERSISTENT,
                                              passwordEncoder,
                                              jwtBuilder,
                                              javaMailSender);
    }

    /**
     * Tests SecurityService::authenticate for a non-persistent authentication.
     */
    @Test
    void testAuthenticateNonPersistent() {
        final List<User> users = generateList(User.class);
        final JwtBuildResult jwtBuildResult = new JwtBuildResult(JWT, LocalDateTime.now());

        when(this.passwordEncoder.matches(eq(PASSWORD), anyString())).thenReturn(true);
        when(this.jwtBuilder.build(anyString())).thenReturn(jwtBuildResult);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(this.refreshTokenRepository.save(any(RefreshToken.class)))
            .thenAnswer(invocation -> invocation.getArguments()[0]);

        users.forEach(user -> {
            user.setEncryptedPassword(PASSWORD);
            user.setConfirmRegistrationToken(null);

            assertThrows(BadCredentialsException.class,
                         () -> this.securityService.authenticate(user.getEmail(),
                                                                 user.getEncryptedPassword(),
                                                                 false,
                                                                 null));

            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            final Authentication result = this.securityService.authenticate(user.getEmail(),
                                                                            user.getEncryptedPassword(),
                                                                            false,
                                                                            null);
            final LocalDateTime expiresAt =
                    LocalDateTime.now().plusSeconds(TIME_TO_LIVE).truncatedTo(ChronoUnit.SECONDS);

            assertNotNull(result);

            assertEquals(user, result.getUser());
            assertEquals(jwtBuildResult.jwt(), result.getJwt());
            assertEquals(jwtBuildResult.expiresAt(), result.getExpiresAt());
            assertFalse(result.isPersistent());

            assertNotNull(result.getRefreshToken());
            assertEquals(result.getUser(), result.getRefreshToken().getUser());
            assertEquals(expiresAt, result.getRefreshToken().getExpiresAt());
        });

        verify(this.refreshTokenRepository, times(0)).delete(any(RefreshToken.class));
    }

    /**
     * Tests SecurityServiceTests::authenticate for a persistent authentication.
     */
    @Test
    void testAuthenticatePersistent() {
        final List<User> users = generateList(User.class);
        final JwtBuildResult jwtBuildResult = new JwtBuildResult(JWT, LocalDateTime.now());

        when(this.passwordEncoder.matches(eq(PASSWORD), anyString())).thenReturn(true);
        when(this.jwtBuilder.build(anyString())).thenReturn(jwtBuildResult);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(this.refreshTokenRepository.save(any(RefreshToken.class)))
            .thenAnswer(invocation -> invocation.getArguments()[0]);

        users.forEach(user -> {
            user.setEncryptedPassword(PASSWORD);
            user.setConfirmRegistrationToken(null);

            assertThrows(BadCredentialsException.class,
                         () -> this.securityService.authenticate(user.getEmail(),
                                                                 user.getEncryptedPassword(),
                                                                 true,
                                                                 null));

            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            final Authentication result = this.securityService.authenticate(user.getEmail(),
                                                                            user.getEncryptedPassword(),
                                                                            true,
                                                                            null);
            final LocalDateTime expiresAt =
                    LocalDateTime.now().plusSeconds(TIME_TO_LIVE_PERSISTENT).truncatedTo(ChronoUnit.SECONDS);

            assertNotNull(result);

            assertEquals(user, result.getUser());
            assertEquals(jwtBuildResult.jwt(), result.getJwt());
            assertEquals(jwtBuildResult.expiresAt(), result.getExpiresAt());
            assertTrue(result.isPersistent());

            assertNotNull(result.getRefreshToken());
            assertEquals(result.getUser(), result.getRefreshToken().getUser());
            assertEquals(expiresAt, result.getRefreshToken().getExpiresAt());
        });

        verify(this.refreshTokenRepository, times(0)).delete(any(RefreshToken.class));
    }

    /**
     * Tests SecurityServiceTests::authenticate with a current refresh token.
     */
    @Test
    void testAuthenticateWithCurrentRefreshToken() {
        final User user = generate(User.class);
        final JwtBuildResult jwtBuildResult = new JwtBuildResult(JWT, LocalDateTime.now());

        user.setEncryptedPassword(PASSWORD);
        user.setConfirmRegistrationToken(null);

        final RefreshToken refreshToken = generate(RefreshToken.class);

        when(this.passwordEncoder.matches(eq(PASSWORD), anyString())).thenReturn(true);
        when(this.jwtBuilder.build(anyString())).thenReturn(jwtBuildResult);
        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(this.refreshTokenRepository.save(any(RefreshToken.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        when(this.refreshTokenRepository.findByUuid(refreshToken.getUuid())).thenReturn(Optional.of(refreshToken));

        final Authentication result = this.securityService.authenticate(user.getEmail(),
                                                                        user.getEncryptedPassword(),
                                                                        true,
                                                                        refreshToken.getUuid());
        final LocalDateTime expiresAt =
                LocalDateTime.now().plusSeconds(TIME_TO_LIVE_PERSISTENT).truncatedTo(ChronoUnit.SECONDS);

        assertNotNull(result);

        assertEquals(user, result.getUser());
        assertEquals(jwtBuildResult.jwt(), result.getJwt());
        assertEquals(jwtBuildResult.expiresAt(), result.getExpiresAt());
        assertTrue(result.isPersistent());

        assertNotNull(result.getRefreshToken());
        assertEquals(result.getUser(), result.getRefreshToken().getUser());
        assertEquals(expiresAt, result.getRefreshToken().getExpiresAt());

        verify(this.refreshTokenRepository, times(1)).findByUuid(eq(refreshToken.getUuid()));
        verify(this.refreshTokenRepository, times(1)).delete(eq(refreshToken));
    }

    /**
     * Tests SecurityServiceTests::authenticate with a current refresh token that's already deleted.
     */
    @Test
    void testAuthenticateWithCurrentRefreshTokenAlreadyDeleted() {
        final User user = generate(User.class);
        final JwtBuildResult jwtBuildResult = new JwtBuildResult(JWT, LocalDateTime.now());

        user.setEncryptedPassword(PASSWORD);
        user.setConfirmRegistrationToken(null);

        final RefreshToken refreshToken = generate(RefreshToken.class);

        when(this.passwordEncoder.matches(eq(PASSWORD), anyString())).thenReturn(true);
        when(this.jwtBuilder.build(anyString())).thenReturn(jwtBuildResult);
        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(this.refreshTokenRepository.save(any(RefreshToken.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);
        when(this.refreshTokenRepository.findByUuid(refreshToken.getUuid())).thenReturn(Optional.empty());

        final Authentication result = this.securityService.authenticate(user.getEmail(),
                                                                        user.getEncryptedPassword(),
                                                                        true,
                                                                        refreshToken.getUuid());
        final LocalDateTime expiresAt =
                LocalDateTime.now().plusSeconds(TIME_TO_LIVE_PERSISTENT).truncatedTo(ChronoUnit.SECONDS);

        assertNotNull(result);

        assertEquals(user, result.getUser());
        assertEquals(jwtBuildResult.jwt(), result.getJwt());
        assertEquals(jwtBuildResult.expiresAt(), result.getExpiresAt());
        assertTrue(result.isPersistent());

        assertNotNull(result.getRefreshToken());
        assertEquals(result.getUser(), result.getRefreshToken().getUser());
        assertEquals(expiresAt, result.getRefreshToken().getExpiresAt());

        verify(this.refreshTokenRepository, times(1)).findByUuid(eq(refreshToken.getUuid()));
        verify(this.refreshTokenRepository, times(0)).delete(any(RefreshToken.class));
    }

    /**
     * Tests SecurityServiceTests::authenticate for an unconfirmed user.
     */
    @Test
    void testAuthenticateUnconfirmed() {
        final List<User> users = generateList(User.class);

        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        users.forEach(user -> {
            user.setEncryptedPassword(PASSWORD);
            user.setConfirmRegistrationToken(UUID.randomUUID());

            when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

            assertThrows(BadCredentialsException.class,
                         () -> this.securityService.authenticate(user.getEmail(),
                                                                 user.getEncryptedPassword(),
                                                                 true,
                                                                 null));
        });
    }

    /**
     * Tests SecurityService::authorize for a non-persistent authentication.
     */
    @Test
    void testAuthorizeNonPersistent() {
        final User user = generate(User.class);
        final JwtBuildResult jwtBuildResult = new JwtBuildResult(JWT, LocalDateTime.now());

        final RefreshToken refreshToken =
                new RefreshToken(user, LocalDateTime.now().plusSeconds(TIME_TO_LIVE).truncatedTo(ChronoUnit.SECONDS));

        assertThrows(BadCredentialsException.class, () -> this.securityService.authorize(refreshToken.getUuid()));

        when(this.refreshTokenRepository.findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(any(), any()))
            .thenReturn(Optional.of(refreshToken));
        when(this.jwtBuilder.build(anyString())).thenReturn(jwtBuildResult);

        final Authorization result = this.securityService.authorize(refreshToken.getUuid());

        assertNotNull(result);

        assertEquals(user, result.getUser());
        assertEquals(jwtBuildResult.jwt(), result.getJwt());
        assertEquals(jwtBuildResult.expiresAt(), result.getExpiresAt());
        assertFalse(result.isPersistent());

        assertNotNull(result.getRefreshToken());
        assertEquals(result.getUser(), result.getRefreshToken().getUser());
        assertEquals(refreshToken.getExpiresAt(), result.getRefreshToken().getExpiresAt());
    }

    /**
     * Tests SecurityService::authorize for a persistent authentication.
     */
    @Test
    void testAuthorizePersistent() {
        final User user = generate(User.class);
        final JwtBuildResult jwtBuildResult = new JwtBuildResult(JWT, LocalDateTime.now());

        final RefreshToken refreshToken =
                new RefreshToken(user, LocalDateTime.now().plusSeconds(TIME_TO_LIVE_PERSISTENT)
                        .truncatedTo(ChronoUnit.SECONDS));

        assertThrows(BadCredentialsException.class, () -> this.securityService.authorize(refreshToken.getUuid()));

        when(this.refreshTokenRepository.findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(any(), any()))
            .thenReturn(Optional.of(refreshToken));
        when(this.jwtBuilder.build(anyString())).thenReturn(jwtBuildResult);

        final Authorization result = this.securityService.authorize(refreshToken.getUuid());

        assertNotNull(result);

        assertEquals(user, result.getUser());
        assertEquals(jwtBuildResult.jwt(), result.getJwt());
        assertEquals(jwtBuildResult.expiresAt(), result.getExpiresAt());
        assertTrue(result.isPersistent());

        assertNotNull(result.getRefreshToken());
        assertEquals(result.getUser(), result.getRefreshToken().getUser());
        assertEquals(refreshToken.getExpiresAt(), result.getRefreshToken().getExpiresAt());
    }

    /**
     * Tests SecurityService::unauthorize.
     */
    @Test
    void testUnauthorize() {
        final User user = generate(User.class);
        final RefreshToken refreshToken =
                new RefreshToken(user, LocalDateTime.now().plusSeconds(TIME_TO_LIVE).truncatedTo(ChronoUnit.SECONDS));

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
        assertNull(user.getResetPasswordToken());

        when(this.userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        this.securityService.requestPasswordReset(user.getEmail(), link);

        final ResetPasswordLinkMailMessage resetPasswordLinkMailMessage = new ResetPasswordLinkMailMessage(user, link);

        assertNotNull(user.getResetPasswordToken());
        assertNotNull(resetPasswordLinkMailMessage.getTo());
        assertEquals(1, resetPasswordLinkMailMessage.getTo().length);
        assertEquals(resetPasswordLinkMailMessage.getTo()[0], user.getEmail());
        assertNotNull(resetPasswordLinkMailMessage.getText());
        assertTrue(resetPasswordLinkMailMessage.getText().contains(user.getResetPasswordToken().toString()));
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
        assertEquals(user.getEncryptedPassword(), oldPassword);
        assertNotNull(user.getResetPasswordToken());
        verifyNoInteractions(this.passwordEncoder);

        when(this.userRepository.findByEmailAndResetPasswordToken(eq(user.getEmail()),
                                                                  eq(user.getResetPasswordToken())))
            .thenReturn(Optional.of(user));
        when(this.passwordEncoder.encode(eq(PASSWORD))).thenReturn(encryptedPassword);

        this.securityService.resetPassword(user.getEmail(), PASSWORD, user.getResetPasswordToken());

        assertEquals(encryptedPassword, user.getEncryptedPassword());
        assertNull(user.getResetPasswordToken());
        verify(this.passwordEncoder, times(1)).encode(eq(PASSWORD));
    }

}
