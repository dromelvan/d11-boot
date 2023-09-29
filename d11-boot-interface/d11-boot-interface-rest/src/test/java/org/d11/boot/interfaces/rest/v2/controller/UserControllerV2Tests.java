package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.UserApi;
import org.d11.boot.api.v2.model.CreateUserRequestBodyDTO;
import org.d11.boot.api.v2.model.UserResponseBodyDTO;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * User controller tests.
 */
class UserControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Password encoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Tests UserController::createUser.
     */
    @Test
    void testCreateUser() {
        final UserApi userApi = getApi(UserApi.class);

        final String name = "name";
        final String email = "email@email.com";
        final String password = "password";
        final String confirmPassword = "confirmPassword";

        final CreateUserRequestBodyDTO createUserRequestBodyDTO = new CreateUserRequestBodyDTO()
                .email(email)
                .password(password)
                .confirmPassword(password);

        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser name missing throws");

        createUserRequestBodyDTO.name(name).email(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser email missing throws");

        createUserRequestBodyDTO.name(name).email("INVALID_EMAIL");
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser email invalid throws");

        createUserRequestBodyDTO.email(email).password(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser password missing throws");

        createUserRequestBodyDTO.password(password).confirmPassword(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser confirmPassword missing throws");

        createUserRequestBodyDTO.confirmPassword(confirmPassword);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser password/confirmPassword don't match throws");

        createUserRequestBodyDTO.confirmPassword(password);

        final User existingUser = this.userRepository.findAll().get(0);

        createUserRequestBodyDTO.name(existingUser.getName());
        assertThrows(FeignException.Conflict.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser name unavailable throws");

        createUserRequestBodyDTO.name(name).email(existingUser.getEmail());
        assertThrows(FeignException.Conflict.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser email unavailable throws");

        createUserRequestBodyDTO.email(email);

        final UserResponseBodyDTO userResponseBodyDTO = userApi.createUser(createUserRequestBodyDTO);

        final User user = this.userRepository.findByName(userResponseBodyDTO.getUser().getName())
                .orElseThrow(NotFoundException::new);

        assertEquals(name, user.getName(), "UserController::createUser user name equals");
        assertEquals(email, user.getEmail(), "UserController::createUser user email equals");
        assertTrue(this.passwordEncoder.matches(password, user.getEncryptedPassword()),
                   "UserController::createUser user password matches");
        assertFalse(user.isAdministrator(), "UserController::createUser user isAdministrator");
    }

}
