package org.d11.boot.application.controller;

import org.d11.boot.api.AuthenticationApi;
import org.d11.boot.api.model.AuthenticationResultDTO;
import org.d11.boot.application.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the AuthenticationApi and provides authentication endpoints.
 */
@RestController
public class AuthenticationController implements AuthenticationApi {

    /**
     * The authentication service that will be used to authenticate login attempts.
     */
    private final AuthenticationService authenticationService;

    /**
     * Creates a new controller.
     *
     * @param authenticationService The authentication service that will be used to authenticate login attempts.
     */
    @Autowired
    public AuthenticationController(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<AuthenticationResultDTO> authenticate(final String username, final String password) {
        final AuthenticationResultDTO authenticationResultDTO = this.authenticationService.authenticate(username, password);
        return ResponseEntity.ok(authenticationResultDTO);
    }

}
