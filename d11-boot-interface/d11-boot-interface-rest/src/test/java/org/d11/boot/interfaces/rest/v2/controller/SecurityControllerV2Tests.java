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
import org.d11.boot.api.v2.model.UserDTO;
import org.d11.boot.interfaces.rest.CookieErrorDecoder;
import org.d11.boot.interfaces.rest.CookieRequestInterceptor;
import org.d11.boot.interfaces.rest.RefreshTokenCookieBuilder;
import org.d11.boot.interfaces.rest.RefreshTokenCookieDecoder;
import org.d11.boot.spring.model.Authentication;
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
@SuppressWarnings({ "PMD.TooManyMethods", "checkstyle:ClassFanOutComplexity" })
class SecurityControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Name of the JWT username claim.
     */
    private static final String USERNAME_CLAIM = "username";

    /**
     * Max age for a refresh token cookie.
     */
    private static final long COOKIE_MAX_AGE = 60L * 60L * 24L;

    /**
     * Max age for a persistent refresh token cookie.
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
            .decoder(new CookieDecoderV2(getApiClient().getObjectMapper(), this.refreshTokenCookieDecoder))
            .requestInterceptor(this.cookieRequestInterceptor);
    }

    // Authentication --------------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::authenticate for a non persistent authentication.
     */
    @Test
    void testAuthenticateNonPersistent() {
        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty(),
                    "SecurityController::authenticate non persistent users empty");

        users.forEach(user -> {
            final AuthenticationResponseBodyDTO result = authenticate(user, false);
            assertNotNull(result, "SecurityController::authenticate non persistent not null");

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getJwt(), "SecurityController::authenticate non persistent jwt not null");
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            final LocalDateTime expiresAt = LocalDateTime.now().plusDays(1);

            assertEquals(getMapper().map(user, UserDTO.class), result.getUser(),
                         "SecurityController::authenticate non persistent user");
            assertEquals(decodedJWT.getClaim(USERNAME_CLAIM).asString(), user.getEmail(),
                         "SecurityController::authenticate non persistent user email");
            // It's tricky to calculate what the exact expires at should be down to the millisecond so check that it is
            // within one minute of what we expect it to be.
            assertTrue(result.getExpiresAt().isBefore(expiresAt),
                       "SecurityController::authenticate non persistent expires at before");
            assertTrue(result.getExpiresAt().isAfter(expiresAt.minusMinutes(1)),
                       "SecurityController::authenticate non persistent expires at after");
            // JWT expires at does not have milliseconds so truncate to seconds before comparing.
            assertEquals(result.getExpiresAt().truncatedTo(ChronoUnit.SECONDS),
                         LocalDateTime.ofInstant(decodedJWT.getExpiresAtAsInstant(), ZoneId.systemDefault()),
                         "SecurityController::authenticate non persistent jwt expires at");
            assertFalse(result.isPersistent(),
                        "SecurityController::authenticate non persistent persistent");

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotNull(uuid, "SecurityController::authenticate non persistent cookie value not null");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(COOKIE_MAX_AGE, maxAge, "SecurityController::authenticate non persistent cookie max age");

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken, "SecurityController::authenticate non persistent refresh token not null");
            assertEquals(uuid, refreshToken.getUuid(),
                         "SecurityController::authenticate non persistent refresh token uuid");
            assertEquals(user, refreshToken.getUser(),
                         "SecurityController::authenticate non persistent refresh token user");
            assertEquals(result.getExpiresAt(), refreshToken.getExpiresAt(),
                         "SecurityController::authenticate non persistent refresh token expires at");
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

        assertFalse(users.isEmpty(),
                    "SecurityController::authenticate persistent users empty");

        users.forEach(user -> {
            final AuthenticationResponseBodyDTO result = authenticate(user, true);
            assertNotNull(result, "SecurityController::authenticate persistent not null");

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getJwt(), "SecurityController::authenticate persistent jwt not null");
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            final LocalDateTime expiresAt = LocalDateTime.now().plusDays(Authentication.PERSISTENT_DAYS_VALID);

            assertEquals(getMapper().map(user, UserDTO.class), result.getUser(),
                         "SecurityController::authenticate persistent user");
            assertEquals(decodedJWT.getClaim(USERNAME_CLAIM).asString(), user.getEmail(),
                         "SecurityController::authenticate persistent user email");
            assertTrue(result.getExpiresAt().isBefore(expiresAt),
                       "SecurityController::authenticate persistent expires at before");
            assertTrue(result.getExpiresAt().isAfter(expiresAt.minusMinutes(1)),
                       "SecurityController::authenticate persistent expires at after");
            assertEquals(result.getExpiresAt().truncatedTo(ChronoUnit.SECONDS),
                         LocalDateTime.ofInstant(decodedJWT.getExpiresAtAsInstant(), ZoneId.systemDefault()),
                         "SecurityController::authenticate persistent jwt expires at");
            assertTrue(result.isPersistent(),
                        "SecurityController::authenticate persistent persistent");

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotNull(uuid, "SecurityController::authenticate persistent cookie value not null");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(PERSISTENT_COOKIE_MAX_AGE, maxAge,
                         "SecurityController::authenticate persistent cookie max age");

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken, "SecurityController::authenticate persistent refresh token not null");
            assertEquals(uuid, refreshToken.getUuid(),
                         "SecurityController::authenticate persistent refresh token uuid");
            assertEquals(user, refreshToken.getUser(),
                         "SecurityController::authenticate persistent refresh token user");
            assertNull(refreshToken.getExpiresAt(), "SecurityController::authenticate persistent refresh token null");
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

        assertFalse(users.isEmpty(),
                    "SecurityController::authenticate unconfirmed users empty");

        users.forEach(user -> assertThrows(FeignException.Unauthorized.class,
                                           () -> authenticate(user, true),
                                           "SecurityController::authenticate unconfirmed bad request"));
    }

    /**
     * Tests SecurityController::authenticate for a bad request.
     */
    @Test
    void testAuthenticateBadRequest() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> securityApi.authenticate(new AuthenticationRequestBodyDTO()),
                     "SecurityController::authenticate bad request");
        assertNull(this.refreshTokenCookieDecoder.getCookieValue(),
                   "SecurityController::authenticate bad request cookie value null");
        assertNull(this.refreshTokenCookieDecoder.getCookieMaxAge(),
                   "SecurityController::authenticate bad request cookie max age null");
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

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.authenticate(authenticationRequestBodyDTO),
                     "SecurityController::authenticate unauthorized");
        assertEquals(UUID.fromString(RefreshTokenCookieBuilder.REFRESH_TOKEN_DUMMY_UUID),
                     this.refreshTokenCookieDecoder.getCookieValue(),
                     "SecurityController::authenticate unauthorized cookie value");
        assertEquals(0L, this.refreshTokenCookieDecoder.getCookieMaxAge(),
                     "SecurityController::authenticate unauthorized cookie max age");
    }

    // Authorization ---------------------------------------------------------------------------------------------------

    /**
     * Tests SecurityController::authorize for a non persistent authentication.
     */
    @Test
    void testAuthorizeNonPersistent() {
        final SecurityApi securityApi = getUserApi(SecurityApi.class);

        final List<User> users = this.userRepository.findAll().stream()
                .filter(user -> user.getConfirmRegistrationToken() == null)
                .toList();

        assertFalse(users.isEmpty(),
                    "SecurityController::authorize non persistent users empty");

        users.forEach(user -> {
            authenticate(user, false);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken,
                          "SecurityController::authorize non persistent authentication refresh token not null");

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final AuthorizationResponseBodyDTO result = securityApi.authorize(authenticationUuid);

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getJwt(), "SecurityController::authorize non persistent jwt not null");
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            assertEquals(getMapper().map(user, UserDTO.class), result.getUser(),
                         "SecurityController::authorize non persistent user");
            assertEquals(decodedJWT.getClaim(USERNAME_CLAIM).asString(), user.getEmail(),
                         "SecurityController::authorize non persistent user email");
            assertEquals(authenticationRefreshToken.getExpiresAt(), result.getExpiresAt(),
                       "SecurityController::authorize non persistent expires at");
            assertFalse(result.isPersistent(),
                       "SecurityController::authorize non persistent persistent");

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotEquals(authenticationUuid, uuid, "SecurityController::authorize non persistent cookie value");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(COOKIE_MAX_AGE, maxAge, "SecurityController::authorize non persistent cookie max age");

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken, "SecurityController::authorize non persistent refresh token not null");
            assertEquals(authenticationRefreshToken.getUser(), refreshToken.getUser(),
                         "SecurityController::authorize non persistent refresh token user");
            assertEquals(uuid, refreshToken.getUuid(),
                         "SecurityController::authorize non persistent refresh token uuid");
            assertEquals(authenticationRefreshToken.getExpiresAt(), refreshToken.getExpiresAt(),
                         "SecurityController::authorize non persistent refresh token expires at");
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

        assertFalse(users.isEmpty(),
                    "SecurityController::authorize persistent users empty");

        users.forEach(user -> {
            authenticate(user, true);
            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken,
                          "SecurityController::authorize persistent authentication refresh token not null");

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final AuthorizationResponseBodyDTO result = securityApi.authorize(authenticationUuid);

            // Check result --------------------------------------------------------------------------------------------

            assertNotNull(result.getJwt(), "SecurityController::authorize persistent jwt not null");
            final DecodedJWT decodedJWT = this.jwtBuilder.decode(result.getJwt());

            final LocalDateTime expiresAt = LocalDateTime.now().plusDays(Authentication.PERSISTENT_DAYS_VALID);

            assertEquals(getMapper().map(user, UserDTO.class), result.getUser(),
                         "SecurityController::authorize persistent user");
            assertEquals(decodedJWT.getClaim(USERNAME_CLAIM).asString(), user.getEmail(),
                         "SecurityController::authorize persistent user email");
            assertTrue(result.getExpiresAt().isBefore(expiresAt),
                       "SecurityController::authorize persistent expires at before");
            assertTrue(result.getExpiresAt().isAfter(expiresAt.minusMinutes(1)),
                       "SecurityController::authorize persistent expires at after");
            assertTrue(result.isPersistent(),
                       "SecurityController::authorize persistent persistent");

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertNotEquals(authenticationUuid, uuid, "SecurityController::authorize persistent cookie value");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(PERSISTENT_COOKIE_MAX_AGE, maxAge, "SecurityController::authorize persistent cookie max age");

            // Check refresh token -------------------------------------------------------------------------------------

            final RefreshToken refreshToken = this.refreshTokenRepository.findByUuid(uuid).orElse(null);
            assertNotNull(refreshToken, "SecurityController::authorize persistent refresh token not null");
            assertEquals(authenticationRefreshToken.getUser(), refreshToken.getUser(),
                         "SecurityController::authorize persistent refresh token user");
            assertEquals(uuid, refreshToken.getUuid(),
                         "SecurityController::authorize persistent refresh token uuid");
            assertNull(refreshToken.getExpiresAt(),
                       "SecurityController::authorize persistent refresh token expires at null");
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

        assertFalse(users.isEmpty(),
                    "SecurityController::authorize reauthorize users empty");

        users.forEach(user -> {
            authenticate(user, true);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken,
                          "SecurityController::authorize reauthorize authentication refresh token not null");

            // Authorize -----------------------------------------------------------------------------------------------

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            securityApi.authorize(authenticationUuid);

            final RefreshToken refreshToken =
                this.refreshTokenRepository.findByUuid(this.refreshTokenCookieDecoder.getCookieValue()).orElse(null);
            assertNotNull(refreshToken, "SecurityController::authorize reauthorize refresh token not null");

            // Reauthorize ---------------------------------------------------------------------------------------------

            final long count = this.refreshTokenRepository.count();

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            assertThrows(FeignException.Unauthorized.class, () -> securityApi.authorize(authenticationUuid),
                         "SecurityController::authorize reauthorize unauthorized");

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertEquals(UUID.fromString(RefreshTokenCookieBuilder.REFRESH_TOKEN_DUMMY_UUID), uuid,
                         "SecurityController::authorize reauthorize cookie value");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(0L, maxAge,
                         "SecurityController::authorize reauthorize cookie max age");

            assertEquals(count, this.refreshTokenRepository.count(),
                         "SecurityController::authorize reauthorize refresh token count");
        });
    }

    /**
     * Tests SecurityController::authorize for an authorization without refresh token cookie.
     */
    @Test
    void testAuthorizeNoCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.cookieRequestInterceptor.setRefreshToken(null);

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.authorize(UUID.randomUUID()),
                     "SecurityController::authorize no cookie unauthorized");
        assertNull(this.refreshTokenCookieDecoder.getCookieValue(),
                   "SecurityController::authorize no cookie cookie value null");
        assertNull(this.refreshTokenCookieDecoder.getCookieMaxAge(),
                   "SecurityController::authorize no cookie cookie max age null");
    }

    /**
     * Tests SecurityController::authorize for an authorization with invalid refresh token cookie.
     */
    @Test
    void testAuthorizeInvalidCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.cookieRequestInterceptor.setRefreshToken(UUID.randomUUID());

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.authorize(UUID.randomUUID()),
                     "SecurityController::authorize invalid cookie unauthorized");
        assertEquals(UUID.fromString(RefreshTokenCookieBuilder.REFRESH_TOKEN_DUMMY_UUID),
                     this.refreshTokenCookieDecoder.getCookieValue(),
                     "SecurityController::authorize invalid cookie cookie value");
        assertEquals(0L, this.refreshTokenCookieDecoder.getCookieMaxAge(),
                     "SecurityController::authorize invalid cookie cookie max age");
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

        assertFalse(users.isEmpty(),
                    "SecurityController::unauthorize users empty");

        users.forEach(user -> {
            authenticate(user, true);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken,
                          "SecurityController::unauthorize authentication refresh token not null");

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final UnauthorizationResponseBodyDTO result = securityApi.unauthorize(authenticationUuid);

            // Check result --------------------------------------------------------------------------------------------

            assertTrue(result.isLoggedOut(), "SecurityController::unauthorize logged out");

            // Check cookie --------------------------------------------------------------------------------------------

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertEquals(authenticationUuid, uuid, "SecurityController::unauthorize cookie value");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(0L, maxAge, "SecurityController::unauthorize cookie max age");

            // Check refresh token -------------------------------------------------------------------------------------

            final Optional<RefreshToken> optional = this.refreshTokenRepository.findByUuid(authenticationUuid);
            assertFalse(optional.isPresent(), "SecurityController::unauthorize refresh token present");
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

        assertFalse(users.isEmpty(),
                    "SecurityController::unauthorize reunauthorize users empty");

        users.forEach(user -> {
            authenticate(user, true);

            final UUID authenticationUuid = this.refreshTokenCookieDecoder.getCookieValue();

            final RefreshToken authenticationRefreshToken
                = this.refreshTokenRepository.findByUuid(authenticationUuid).orElse(null);
            assertNotNull(authenticationRefreshToken,
                          "SecurityController::authorize reunauthorize authentication refresh token not null");

            // Unauthorize ---------------------------------------------------------------------------------------------

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            securityApi.unauthorize(authenticationUuid);

            assertTrue(securityApi.unauthorize(authenticationUuid).isLoggedOut(),
                       "SecurityController::unauthorize reunauthorize logged out");

            // Reunauthorize -------------------------------------------------------------------------------------------

            final long count = this.refreshTokenRepository.count();

            this.cookieRequestInterceptor.setRefreshToken(authenticationUuid);
            final UnauthorizationResponseBodyDTO result = securityApi.unauthorize(authenticationUuid);

            assertTrue(result.isLoggedOut(), "SecurityController::unauthorize reunauthorize reunauthorize logged out");

            final UUID uuid = this.refreshTokenCookieDecoder.getCookieValue();
            assertEquals(authenticationUuid, uuid, "SecurityController::unauthorize reunauthorize cookie value");

            final Long maxAge = this.refreshTokenCookieDecoder.getCookieMaxAge();
            assertEquals(0L, maxAge,
                         "SecurityController::authorize reunauthorize cookie max age");

            assertEquals(count, this.refreshTokenRepository.count(),
                         "SecurityController::authorize reunauthorize refresh token count");
        });
    }

    /**
     * Tests SecurityController::unauthorize for an unauthorization without refresh token cookie.
     */
    @Test
    void testUnauthorizeNoCookie() {
        final SecurityApi securityApi = getApi(SecurityApi.class);

        this.cookieRequestInterceptor.setRefreshToken(null);

        assertThrows(FeignException.Unauthorized.class, () -> securityApi.unauthorize(UUID.randomUUID()),
                     "SecurityController::unauthorize no cookie unauthorized");
        assertNull(this.refreshTokenCookieDecoder.getCookieValue(),
                   "SecurityController::unauthorize no cookie cookie value null");
        assertNull(this.refreshTokenCookieDecoder.getCookieMaxAge(),
                   "SecurityController::unauthorize no cookie cookie max age null");
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
        assertTrue(result.isLoggedOut(), "SecurityController::unauthorize invalid cookie logged out");

        assertEquals(uuid, this.refreshTokenCookieDecoder.getCookieValue(),
                     "SecurityController::unauthorize invalid cookie cookie value");
        assertEquals(0L, this.refreshTokenCookieDecoder.getCookieMaxAge(),
                     "SecurityController::unauthorize invalid cookie cookie max age");
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

            assertNotNull(updatedUser, "SecurityController::requestPasswordReset updatedUser not null");
            assertNotNull(updatedUser.getResetPasswordToken(),
                          "SecurityController::requestPasswordReset resetPasswordToken not null");

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
                     () -> securityApi.requestPasswordReset(new RequestPasswordResetRequestBodyDTO()),
                     "SecurityController::requestPasswordReset bad request");
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

        this.userRepository.findAll().forEach(user ->
                assertNull(user.getResetPasswordToken(),
                           "SecurityController::requestPasswordReset not found resetPasswordToken null"));
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
            assertNotNull(updatedUser, "SecurityController::resetPassword updatedUser not null");

            securityApi.resetPassword(new ResetPasswordRequestBodyDTO()
                                          .email(updatedUser.getEmail())
                                          .password(newPassword)
                                          .resetPasswordToken(updatedUser.getResetPasswordToken()));

            updatedUser = this.userRepository.findById(user.getId()).orElse(null);
            assertNotNull(updatedUser, "SecurityController::resetPassword updatedUser x2 not null");

            assertTrue(this.passwordEncoder.matches(newPassword, updatedUser.getEncryptedPassword()),
                       "SecurityController::resetPassword encryptedPassword matches");
            assertNull(updatedUser.getResetPasswordToken(),
                       "SecurityController::resetPassword resetPasswordToken null");

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
                     () -> securityApi.resetPassword(new ResetPasswordRequestBodyDTO()),
                     "SecurityController::resetPassword bad request");
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
                                                         .resetPasswordToken(UUID.randomUUID())),
                     "SecurityController::resetPassword unauthorized");
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
