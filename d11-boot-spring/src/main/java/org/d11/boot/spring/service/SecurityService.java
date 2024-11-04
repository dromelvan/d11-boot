package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Authentication;
import org.d11.boot.spring.model.Authorization;
import org.d11.boot.spring.model.RefreshToken;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.RefreshTokenRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.spring.security.ResetPasswordLinkMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Security service.
 */
@Service
public class SecurityService extends D11BootService {

    /**
     * Authorization failed message.
     */
    public static final String AUTHORIZATION_FAILED_MESSAGE = "Authorization failed";

    /**
     * Authentication failed message.
     */
    private static final String AUTHENTICATION_FAILED_MESSAGE = "Authentication failed";

    /**
     * The user repository the service will use.
     */
    private final UserRepository userRepository;

    /**
     * The refresh token repository the service will use.
     */
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * Password encoder that matches provided password with the encrypted user password in the database.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * JWT builder that generates JWTs after successful authentication.
     */
    private final JwtBuilder jwtBuilder;

    /**
     * Password reset email sender.
     */
    private final JavaMailSender javaMailSender;

    /**
     * Creates a new security service.
     *
     * @param userRepository         The user repository the service will use.
     * @param refreshTokenRepository The refresh token repository the service will use.
     * @param passwordEncoder        The password encoder the service will use.
     * @param jwtBuilder             The JWT builder the service will use.
     * @param javaMailSender         Password reset email sender.
     */
    public SecurityService(final UserRepository userRepository,
                           final RefreshTokenRepository refreshTokenRepository,
                           final PasswordEncoder passwordEncoder,
                           final JwtBuilder jwtBuilder,
                           final JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtBuilder = jwtBuilder;
        this.javaMailSender = javaMailSender;
    }

    /**
     * Authenticates a user and generates a JWT. The JWT expiration time is one month forward if the login request is
     * for a persistent token and one day if not.
     *
     * @param username   User username.
     * @param password   User password.
     * @param persistent Remember user login.
     * @return Authentication details.
     */
    @Transactional
    public Authentication authenticate(final String username, final String password, final boolean persistent) {
        final User user = this.userRepository.findByEmail(username)
            .orElseThrow(() -> new BadCredentialsException(AUTHENTICATION_FAILED_MESSAGE));

        if (user.getConfirmRegistrationToken() == null
            && this.passwordEncoder.matches(password, user.getEncryptedPassword())) {
            final LocalDateTime expiresAt = persistent
                ? LocalDateTime.now().plusDays(Authentication.PERSISTENT_DAYS_VALID)
                : LocalDateTime.now().plusDays(1L);

            final String jwt = this.jwtBuilder.build(username, expiresAt);

            final RefreshToken refreshToken = persistent
                ? new RefreshToken(user)
                : new RefreshToken(user, expiresAt);

            return new Authentication(user, jwt, expiresAt, this.refreshTokenRepository.save(refreshToken));
        }
        throw new BadCredentialsException(AUTHENTICATION_FAILED_MESSAGE);
    }

    /**
     * Authorizes a user by verifying a refresh token, deleting it and creating a new one. Uses a transaction with
     * SERIALIZABLE isolation to prevent more than one concurrent request from authorizing the same token.
     *
     * @param uuid UUID of the refresh token that will be authorized.
     * @return Authorization details containing the new refresh token.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Authorization authorize(final UUID uuid) {
        final RefreshToken authorizeRefreshToken =
            this.refreshTokenRepository.findByUuidAndExpiresAtIsAfterOrExpiresAtIsNull(uuid, LocalDateTime.now())
                .orElseThrow(() -> new BadCredentialsException(AUTHORIZATION_FAILED_MESSAGE));
        final User user = authorizeRefreshToken.getUser();
        final LocalDateTime expiresAt = authorizeRefreshToken.getExpiresAt();
        this.refreshTokenRepository.delete(authorizeRefreshToken);

        final LocalDateTime jwtExpiresAt = authorizeRefreshToken.getExpiresAt() == null
            ? LocalDateTime.now().plusDays(Authentication.PERSISTENT_DAYS_VALID)
            : expiresAt;
        final String jwt = this.jwtBuilder.build(user.getEmail(), jwtExpiresAt);

        final RefreshToken refreshToken = new RefreshToken(user, expiresAt);
        this.refreshTokenRepository.save(refreshToken);

        return new Authorization(user, jwt, jwtExpiresAt, refreshToken);
    }

    /**
     * Unauthorizes a user by deleting the refresh token without creating a new one.
     *
     * @param uuid UUID of the refresh token that will be unauthorized.
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void unauthorize(final UUID uuid) {
        // We don't really care if something actually gets deleted or not since there will not be a usable token with
        // the provided UUID either way.
        this.refreshTokenRepository.findByUuid(uuid).ifPresent(this.refreshTokenRepository::delete);
    }

    /**
     * Requests a password reset for a user with a specific email. Updates the request password token of the user and
     * sends an email with a reset password link to the provided email address.
     *
     * @param email Email address of the user that requests a password reset.
     * @param link  Link to the api method that resets the password. Format this to insert the generated request
     *              password reset token.
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void requestPasswordReset(final String email, final String link) {
        final Optional<User> optional = this.userRepository.findByEmail(email);
        // Fail silently if we don't find a user with the provided email to prevent this method from being used to
        // figure out if a user with a specific email exists or not.
        optional.ifPresent(user -> {
            user.setResetPasswordToken(UUID.randomUUID());

            final ResetPasswordLinkMailMessage resetPasswordLinkMailMessage
                = new ResetPasswordLinkMailMessage(user, link);
            this.javaMailSender.send(resetPasswordLinkMailMessage);
        });
    }

    /**
     * Resets the password for a user.
     *
     * @param email              User email.
     * @param password           The new user password.
     * @param resetPasswordToken Reset password token that must match that of a user.
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void resetPassword(final String email, final String password, final UUID resetPasswordToken) {
        final User user = this.userRepository.findByEmailAndResetPasswordToken(email, resetPasswordToken)
            .orElseThrow(() -> new BadCredentialsException(AUTHENTICATION_FAILED_MESSAGE));

        user.setEncryptedPassword(this.passwordEncoder.encode(password));
        user.setResetPasswordToken(null);
    }

}
