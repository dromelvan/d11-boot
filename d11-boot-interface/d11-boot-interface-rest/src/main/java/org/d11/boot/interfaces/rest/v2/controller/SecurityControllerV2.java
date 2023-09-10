package org.d11.boot.interfaces.rest.v2.controller;

import jakarta.validation.Valid;
import org.d11.boot.api.v2.SecurityApi;
import org.d11.boot.api.v2.model.AuthenticationRequestBodyDTO;
import org.d11.boot.api.v2.model.AuthenticationResponseBodyDTO;
import org.d11.boot.api.v2.model.AuthorizationResponseBodyDTO;
import org.d11.boot.api.v2.model.RequestPasswordResetRequestBodyDTO;
import org.d11.boot.api.v2.model.ResetPasswordRequestBodyDTO;
import org.d11.boot.api.v2.model.UnauthorizationResponseBodyDTO;
import org.d11.boot.interfaces.rest.D11BootRestController;
import org.d11.boot.interfaces.rest.RefreshTokenCookieBuilder;
import org.d11.boot.spring.model.Authentication;
import org.d11.boot.spring.model.Authorization;
import org.d11.boot.spring.service.SecurityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Security API REST controller implementation.
 */
@RestController
public class SecurityControllerV2 extends D11BootRestController implements SecurityApi {

    /**
     * Expiry time in seconds of a non-persistent refresh token cookie. Refresh tokens will be valid for one day.
     */
    private static final int REFRESH_TOKEN_MAX_AGE = 86_400;

    /**
     * Expiry time in seconds of a persistent refresh token cookie. Refresh tokens will be valid for one month.
     */
    private static final int PERSISTENT_REFRESH_TOKEN_MAX_AGE = 2_592_000;

    /**
     * The security service the controller will use.
     */
    private final SecurityService securityService;

    /**
     * Create a new controller.
     *
     * @param securityService The service the controller will use.
     */
    public SecurityControllerV2(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public ResponseEntity<AuthenticationResponseBodyDTO> authenticate(
        final @Valid AuthenticationRequestBodyDTO authenticationRequestBodyDTO) {

        final Authentication authentication = this.securityService.authenticate(
            authenticationRequestBodyDTO.getUsername(),
            authenticationRequestBodyDTO.getPassword(),
            authenticationRequestBodyDTO.isPersistent()
        );

        final int maxAge = authentication.isPersistent()
            ? PERSISTENT_REFRESH_TOKEN_MAX_AGE
            : REFRESH_TOKEN_MAX_AGE;

        final ResponseCookie responseCookie = new RefreshTokenCookieBuilder()
            .withUuid(authentication.getRefreshToken().getUuid())
            .withMaxAge(maxAge)
            .build();

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
            .body(getMapper().map(authentication, AuthenticationResponseBodyDTO.class));
    }

    @Override
    public ResponseEntity<AuthorizationResponseBodyDTO> authorize(final UUID uuid) {
        final Authorization authorization = this.securityService.authorize(uuid);

        final int maxAge = authorization.isPersistent()
            ? PERSISTENT_REFRESH_TOKEN_MAX_AGE
            : REFRESH_TOKEN_MAX_AGE;

        final ResponseCookie responseCookie = new RefreshTokenCookieBuilder()
            .withUuid(authorization.getRefreshToken().getUuid())
            .withMaxAge(maxAge)
            .build();

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
            .body(getMapper().map(authorization, AuthorizationResponseBodyDTO.class));
    }

    @Override
    public ResponseEntity<UnauthorizationResponseBodyDTO> unauthorize(final UUID uuid) {
        this.securityService.unauthorize(uuid);

        final ResponseCookie responseCookie = new RefreshTokenCookieBuilder()
            .withUuid(uuid)
            .build();

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
            .body(new UnauthorizationResponseBodyDTO().loggedOut(true));
    }

    @Override
    public ResponseEntity<Void> requestPasswordReset(
        final @Valid RequestPasswordResetRequestBodyDTO requestPasswordResetRequestBodyDTO) {
        this.securityService.requestPasswordReset(requestPasswordResetRequestBodyDTO.getEmail(),
                                                  requestPasswordResetRequestBodyDTO.getLink());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> resetPassword(final @Valid ResetPasswordRequestBodyDTO resetPasswordRequestBodyDTO) {
        this.securityService.resetPassword(resetPasswordRequestBodyDTO.getEmail(),
                                           resetPasswordRequestBodyDTO.getPassword(),
                                           resetPasswordRequestBodyDTO.getResetPasswordToken());
        return ResponseEntity.ok().build();
    }

}
