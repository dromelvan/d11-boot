package org.d11.boot.interfaces.rest.v2.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import feign.FeignException;
import org.d11.boot.api.v2.client.SecurityApi;
import org.d11.boot.api.v2.model.AuthenticationRequestBodyDTO;
import org.d11.boot.api.v2.model.AuthenticationResponseBodyDTO;
import org.d11.boot.api.v2.model.AuthorizationResponseBodyDTO;
import org.d11.boot.api.v2.model.RequestPasswordResetRequestBodyDTO;
import org.d11.boot.api.v2.model.ResetPasswordRequestBodyDTO;
import org.d11.boot.api.v2.model.UnauthorizationResponseBodyDTO;
import org.d11.boot.interfaces.rest.CookieErrorDecoder;
import org.d11.boot.interfaces.rest.CookieRequestInterceptor;
import org.d11.boot.interfaces.rest.RefreshTokenCookieBuilder;
import org.d11.boot.interfaces.rest.RefreshTokenCookieDecoder;
import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.RefreshTokenRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Security controller tests.
 */
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
class SecurityControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Name of the JWT username claim.
     */
    private static final String USERNAME_CLAIM = "username";

    /**
     * JWT time to live. Defined in application-test.yaml.
     */
    private static final int TIME_TO_LIVE = 300;

    /**
     * Max age for a refresh token cookie. Defined in application-test.yaml.
     */
    private static final long COOKIE_MAX_AGE = 60L * 60L * 24L;

    /**
     * Max age for a persistent refresh token cookie. Defined in application-test.yaml.
     */
    private static final long PERSISTENT_COOKIE_MAX_AGE = 60L * 60L * 24L * 30L;

    /**
     * Dummy link for password reset requests.
     */
    private static final String EXAMPLE_URL = "http://example.com";

    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Refresh token repository.
     */
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    /**
     * JWT builder.
     */
    @Autowired
    private JwtBuilder jwtBuilder;

    /**
     * Password encoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Cookie and error decoder for the refresh token cookie in responses.
     */
    private final RefreshTokenCookieDecoder refreshTokenCookieDecoder = new RefreshTokenCookieDecoder();

    /**
     * Request interceptor to set the refresh token cookie in requests.
     */
    private final CookieRequestInterceptor cookieRequestInterceptor = new CookieRequestInterceptor();

    /**
     * Sets up the feign builder with cookie decoder and request interceptor.
     */
    @BeforeAll
    public void beforeAll() {
        getApiClient().getFeignBuilder()
            .errorDecoder(new CookieErrorDecoder(this.refreshTokenCookieDecoder))
            .decoder(new CookieDecoderV2Tests(getApiClient().getObjectMapper(), this.refreshTokenCookieDecoder))
            .requestInterceptor(this.cookieRequestInterceptor);
    }

    // Authentication --------------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::authenticate for a non-persistent authentication.
     */
    @Test
    void testAuthenticateNonPersistent() {
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            final AuthenticationResponseBodyDTO result = authenticate(user, false);
            final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

            assertNotNull(result);

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getUser());
            assertEquals(user.getName(), result.getUser().getName());
            assertEquals(user.isAdministrator(), result.getUser().isAdministrator());

            assertNotNull(result.getJwt());
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            assertEquals(user.getEmail(), decodedJWT.getClaim(USERNAME_CLAIM).asString());
            assertEquals(result.getExpiresAt(),
                         LocalDateTime.ofInstant(decodedJWT.getExpiresAtAsInstant(), ZoneId.systemDefault()));

            // It's tricky to calculate what the exact expires at should be down to the millisecond so check that it is
            // within five seconds of what we expect it to be.
            assertTrue(result.getExpiresAt().isBefore(now.plusSeconds(TIME_TO_LIVE).plusSeconds(5)));
            assertTrue(result.getExpiresAt().isAfter(now.plusSeconds(TIME_TO_LIVE).minusSeconds(5)));

            assertFalse(result.isPersistent());

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotNull(uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(COOKIE_MAX_AGE, maxAge);

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken);
            assertEquals(uuid, refreshToken.getUuid());
            assertEquals(user, refreshToken.getUser());
            assertEquals(now.plusSeconds(COOKIE_MAX_AGE), refreshToken.getExpiresAt());
        });
    }

    /**
     * Tests SecurityController::authenticate for a persistent authentication.
     */
    @Test
    void testAuthenticatePersistent() {
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            final AuthenticationResponseBodyDTO result = authenticate(user, true);
            final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

            assertNotNull(result);

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getUser());
            assertEquals(user.getName(), result.getUser().getName());
            assertEquals(user.isAdministrator(), result.getUser().isAdministrator());

            assertNotNull(result.getJwt());
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            assertEquals(user.getEmail(), decodedJWT.getClaim(USERNAME_CLAIM).asString());
            assertEquals(result.getExpiresAt(),
                         LocalDateTime.ofInstant(decodedJWT.getExpiresAtAsInstant(), ZoneId.systemDefault()));

            assertTrue(result.getExpiresAt().isBefore(now.plusSeconds(TIME_TO_LIVE).plusSeconds(5)));
            assertTrue(result.getExpiresAt().isAfter(now.plusSeconds(TIME_TO_LIVE).minusSeconds(5)));

            assertTrue(result.isPersistent());

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotNull(uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(PERSISTENT_COOKIE_MAX_AGE, maxAge);

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken);
            assertEquals(uuid, refreshToken.getUuid());
            assertEquals(user, refreshToken.getUser());
            assertEquals(now.plusSeconds(PERSISTENT_COOKIE_MAX_AGE), refreshToken.getExpiresAt());
        });
    }

    /**
     * Tests SecurityController::authenticate with a current refresh token. Here we just want to check that a current
     * active refresh token is deleted when we authenticate again.
     */
    @Test
    void testAuthenticateCurrentRefreshToken() {
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            assertDoesNotThrow(() -> authenticate(user, true));

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotNull(uuid);

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken);

            this.cookieRequestInterceptor.setRefreshToken(uuid);
            assertDoesNotThrow(() -> authenticate(user, true));
            final LocalDateTime expiresAt =
                    LocalDateTime.now().plusSeconds(PERSISTENT_COOKIE_MAX_AGE).truncatedTo(ChronoUnit.SECONDS);

            final UUID newUuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotNull(newUuid);
            assertNotEquals(uuid, newUuid);

            final RefreshToken newRefreshToken = this.refreshTokenRepository.findByUuid(newUuid).orElse(null);
            assertNotNull(newRefreshToken);
            assertNotEquals(refreshToken, newRefreshToken);
            assertEquals(expiresAt, newRefreshToken.getExpiresAt());

            assertTrue(this.refreshTokenRepository.findByUuid(uuid).isEmpty());

            // Check we don't get an exception when authenticating with an already deleted refresh token
            assertDoesNotThrow(() -> authenticate(user, true));
        });
    }

    /**
     * Tests SecurityController::authenticate for an unconfirmed user.
     */
    @Test
    void testAuthenticateUnconfirmed() {
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() != null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> assertThrows(FeignException.Unauthorized.class, () -> authenticate(user, true)));
    }

    /**
     * Tests SecurityController::authenticate for a bad request.
     */
    @Test
    void testAuthenticateBadRequest() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> securityApi.authenticate(new AuthenticationRequestBodyDTO()));
        assertNull(this.refreshTokenCookieDecoder.getCookieValue());
        assertNull(this.refreshTokenCookieDecoder.getCookieMaxAge());
    }

    /**
     * Tests SecurityController::authenticate for an unauthorized authentication.
     */
    @Test
    void testAuthenticateUnauthorized() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        final AuthenticationRequestBodyDTO authenticationRequestBodyDTO = new AuthenticationRequestBodyDTO()
            .username(PASSWORD)
            .password(PASSWORD);

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.authenticate(authenticationRequestBodyDTO));
        assertEquals(UUID.fromString(RefreshTokenCookieBuilder.REFRESH_TOKEN_DUMMY_UUID),
                     this.refreshTokenCookieDecoder.getCookieValue());
        assertEquals(0L, this.refreshTokenCookieDecoder.getCookieMaxAge());
    }

    // Authorization ---------------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::authorize for a non-persistent authentication.
     */
    @Test
    void testAuthorizeNonPersistent() {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);

        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            authenticate(user, false);
            final LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(TIME_TO_LIVE);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken);

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final AuthorizationResponseBodyDTO result = securityApi.authorize(authenticationUuid);

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getUser());
            assertEquals(user.getName(), result.getUser().getName());
            assertEquals(user.isAdministrator(), result.getUser().isAdministrator());

            assertNotNull(result.getJwt());
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            assertEquals(user.getEmail(), decodedJWT.getClaim(USERNAME_CLAIM).asString());
            assertEquals(result.getExpiresAt(),
                         LocalDateTime.ofInstant(decodedJWT.getExpiresAtAsInstant(), ZoneId.systemDefault()));

            assertTrue(result.getExpiresAt().isBefore(expiresAt.plusSeconds(5)));
            assertTrue(result.getExpiresAt().isAfter(expiresAt.minusSeconds(5)));

            assertFalse(result.isPersistent());

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotEquals(authenticationUuid, uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(COOKIE_MAX_AGE, maxAge);

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken);
            assertEquals(uuid, refreshToken.getUuid());
            assertEquals(authenticationRefreshToken.getUser(), refreshToken.getUser());
            assertEquals(authenticationRefreshToken.getExpiresAt(), refreshToken.getExpiresAt());
        });
    }

    /**
     * Tests SecurityController::authorize for a persistent authentication.
     */
    @Test
    void testAuthorizePersistent() {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            authenticate(user, true);
            final LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(TIME_TO_LIVE);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken);

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final AuthorizationResponseBodyDTO result = securityApi.authorize(authenticationUuid);

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getUser());
            assertEquals(user.getName(), result.getUser().getName());
            assertEquals(user.isAdministrator(), result.getUser().isAdministrator());

            assertNotNull(result.getJwt());
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            assertEquals(user.getEmail(), decodedJWT.getClaim(USERNAME_CLAIM).asString());
            assertEquals(result.getExpiresAt(),
                         LocalDateTime.ofInstant(decodedJWT.getExpiresAtAsInstant(), ZoneId.systemDefault()));

            assertTrue(result.getExpiresAt().isBefore(expiresAt.plusSeconds(5)));
            assertTrue(result.getExpiresAt().isAfter(expiresAt.minusSeconds(5)));

            assertTrue(result.isPersistent());

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotEquals(authenticationUuid, uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(PERSISTENT_COOKIE_MAX_AGE, maxAge);

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken);
            assertEquals(uuid, refreshToken.getUuid());
            assertEquals(authenticationRefreshToken.getUser(), refreshToken.getUser());
            assertEquals(authenticationRefreshToken.getExpiresAt(), refreshToken.getExpiresAt());
        });
    }

    /**
     * Tests SecurityController::authorize for a refresh token that has already been used.
     */
    @Test
    void testReAuthorize() {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);
        final List<User> users = this.userRepository.findAll().stream()
            .filter(user -> user.getConfirmRegistrationToken() == null)
            .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            authenticate(user, true);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken);

            // Authorize -----------------------------------------------------------------------------------------------

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            securityApi.authorize(authenticationUuid);

            final RefreshToken refreshToken =
                this.refreshTokenRepository.findByUuid(this.refreshTokenCookieDecoder.getCookieValue()).orElse(null);
            assertNotNull(refreshToken);

            // Reauthorize ---------------------------------------------------------------------------------------------

            final long count = this.refreshTokenRepository.count();

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            assertThrows(FeignException.Unauthorized.class, () -> securityApi.authorize(authenticationUuid));

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertEquals(UUID.fromString(RefreshTokenCookieBuilder.REFRESH_TOKEN_DUMMY_UUID), uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(0L, maxAge);

            assertEquals(count, this.refreshTokenRepository.count());
        });
    }

    /**
     * Tests SecurityController::authorize for an authorization without refresh token cookie.
     */
    @Test
    void testAuthorizeNoCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.cookieRequestInterceptor.setRefreshToken(null);

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.authorize(UUID.randomUUID()));
        assertNull(this.refreshTokenCookieDecoder.getCookieValue());
        assertNull(this.refreshTokenCookieDecoder.getCookieMaxAge());
    }

    /**
     * Tests SecurityController::authorize for an authorization with invalid refresh token cookie.
     */
    @Test
    void testAuthorizeInvalidCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.cookieRequestInterceptor.setRefreshToken(UUID.randomUUID());

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.authorize(UUID.randomUUID()));
        assertEquals(UUID.fromString(RefreshTokenCookieBuilder.REFRESH_TOKEN_DUMMY_UUID),
                     this.refreshTokenCookieDecoder.getCookieValue());
        assertEquals(0L, this.refreshTokenCookieDecoder.getCookieMaxAge());
    }

    // Unauthorization -------------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::unauthorize.
     */
    @Test
    void testUnauthorize() {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            authenticate(user, true);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken);

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final UnauthorizationResponseBodyDTO result = securityApi.unauthorize(authenticationUuid);

            // Check result --------------------------------------------------------------------------------------------

            assertTrue(result.isLoggedOut());

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertEquals(authenticationUuid, uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(0L, maxAge);

            // Check refresh token -------------------------------------------------------------------------------------

            final Optional<RefreshToken> optional = this.refreshTokenRepository.findByUuid(authenticationUuid);
            assertFalse(optional.isPresent());
        });
    }

    /**
     * Tests SecurityController::unauthorize for a refresh token that has already been unauthorized.
     */
    @Test
    void testReUnauthorize() {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty());

        users.forEach(user -> {
            authenticate(user, true);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken);

            // Unauthorize ---------------------------------------------------------------------------------------------

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            securityApi.unauthorize(authenticationUuid);

            assertTrue(securityApi.unauthorize(authenticationUuid).isLoggedOut());

            // Reunauthorize -------------------------------------------------------------------------------------------

            final long count = this.refreshTokenRepository.count();

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final UnauthorizationResponseBodyDTO result = securityApi.unauthorize(authenticationUuid);

            assertTrue(result.isLoggedOut());

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertEquals(authenticationUuid, uuid);

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(0L, maxAge);

            assertEquals(count, this.refreshTokenRepository.count());
        });
    }

    /**
     * Tests SecurityController::unauthorize for an unauthorization without refresh token cookie.
     */
    @Test
    void testUnauthorizeNoCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.cookieRequestInterceptor.setRefreshToken(null);

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.unauthorize(UUID.randomUUID()));
        assertNull(this.refreshTokenCookieDecoder.getCookieValue());
        assertNull(this.refreshTokenCookieDecoder.getCookieMaxAge());
    }

    /**
     * Tests SecurityController::unauthorize for an unauthorization with invalid refresh token cookie.
     */
    @Test
    void testUnAuthorizeInvalidCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        final UUID uuid = UUID.randomUUID();
        this.cookieRequestInterceptor.setRefreshToken(uuid);

        final UnauthorizationResponseBodyDTO result = securityApi.unauthorize(uuid);
        assertTrue(result.isLoggedOut());

        assertEquals(uuid, this.refreshTokenCookieDecoder.getCookieValue());
        assertEquals(0L, this.refreshTokenCookieDecoder.getCookieMaxAge());
    }

    // Request password reset ------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::requestPasswordReset.
     */
    @Test
    void testRequestPasswordReset() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.userRepository.findAll().forEach(user -> {
            securityApi.requestPasswordReset(new RequestPasswordResetRequestBodyDTO()
                                                 .email(user.getEmail())
                                                 .link(EXAMPLE_URL));

            final User updatedUser = this.userRepository.findById(user.getId()).orElse(null);

            assertNotNull(updatedUser);
            assertNotNull(updatedUser.getResetPasswordToken());

            // Clean up to not break other tests, this will be quicker than @DirtiesContext
            updatedUser.setResetPasswordToken(null);
            this.userRepository.save(updatedUser);
        });
    }

    /**
     * Tests SecurityController::requestPasswordReset for a bad request.
     */
    @Test
    void testRequestPasswordResetBadRequest() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> securityApi.requestPasswordReset(new RequestPasswordResetRequestBodyDTO()));
    }

    /**
     * Tests SecurityController::requestPasswordReset for user not found.
     */
    @Test
    void testRequestPasswordResetUserNotFound() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        securityApi.requestPasswordReset(new RequestPasswordResetRequestBodyDTO()
                                                                .email("not.found@email.com")
                                                                .link(EXAMPLE_URL));

        this.userRepository.findAll().forEach(user -> assertNull(user.getResetPasswordToken()));
    }

    // Reset password --------------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::resetPassword for a bad request.
     */
    @Test
    void testResetPassword() {
        final SecurityApi securityApi = getApi(SecurityApi.class);
        final String newPassword = "newPassword";

        this.userRepository.findAll().forEach(user -> {
            final String encryptedPassword = user.getEncryptedPassword();

            securityApi.requestPasswordReset(new RequestPasswordResetRequestBodyDTO()
                                                 .email(user.getEmail())
                                                 .link(EXAMPLE_URL));

            User updatedUser = this.userRepository.findById(user.getId()).orElse(null);
            assertNotNull(updatedUser);

            securityApi.resetPassword(new ResetPasswordRequestBodyDTO()
                                          .email(updatedUser.getEmail())
                                          .password(newPassword)
                                          .resetPasswordToken(updatedUser.getResetPasswordToken()));

            updatedUser = this.userRepository.findById(user.getId()).orElse(null);
            assertNotNull(updatedUser);

            assertTrue(this.passwordEncoder.matches(newPassword, updatedUser.getEncryptedPassword()));
            assertNull(updatedUser.getResetPasswordToken());

            updatedUser.setEncryptedPassword(encryptedPassword);
            this.userRepository.save(updatedUser);
        });
    }

    /**
     * Tests SecurityController::resetPassword for a bad request.
     */
    @Test
    void testResetPasswordBadRequest() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> securityApi.resetPassword(new ResetPasswordRequestBodyDTO()));
    }

    /**
     * Tests SecurityController::resetPassword for unauthorized request.
     */
    @Test
    void testResetPasswordUnauthorized() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        assertThrows(FeignException.Unauthorized.class,
                     () -> securityApi.resetPassword(new ResetPasswordRequestBodyDTO()
                                                         .email("unauthorized@email.com")
                                                         .password("invalid")
                                                         .resetPasswordToken(UUID.randomUUID())));
    }

    // Helper methods --------------------------------------------------------------------------------------------------

    /**
     * Performs an authentication.
     *
     * @param user       The user that will be authenticated.
     * @param persistent The password that will be used for authentication.
     * @return The result of the authentication.
     */
    private AuthenticationResponseBodyDTO authenticate(final User user, final boolean persistent) {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);
        return securityApi.authenticate(new AuthenticationRequestBodyDTO()
                                            .username(user.getEmail())
                                            .password(PASSWORD)
                                            .persistent(persistent)
        );
    }

}
