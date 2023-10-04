package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.UserApi;
import org.d11.boot.api.v2.model.CreateUserRequestBodyDTO;
import org.d11.boot.api.v2.model.UpdateUserRequestBodyDTO;
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
     * Password.
     */
    private static final String PASSWORD = "password";

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
        final String confirmPassword = "confirmPassword";

        final CreateUserRequestBodyDTO createUserRequestBodyDTO = new CreateUserRequestBodyDTO()
                .email(email)
                .password(PASSWORD)
                .confirmPassword(PASSWORD);

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

        createUserRequestBodyDTO.password(PASSWORD).confirmPassword(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser confirmPassword missing throws");

        createUserRequestBodyDTO.confirmPassword(confirmPassword);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO),
                     "UserController::createUser password/confirmPassword don't match throws");

        createUserRequestBodyDTO.confirmPassword(PASSWORD);

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
        assertTrue(this.passwordEncoder.matches(PASSWORD, user.getEncryptedPassword()),
                   "UserController::createUser user password matches");
        assertFalse(user.isAdministrator(), "UserController::createUser user isAdministrator");
    }

    /**
     * Tests UserController::updateUser.
     */
    @Test
    void testUpdateUser() {
        final User user = this.userRepository.findByName("User").orElseThrow(NotFoundException::new);
        final User admin = this.userRepository.findByName("Administrator").orElseThrow(NotFoundException::new);

        final UpdateUserRequestBodyDTO updateUserRequestBodyDTO = new UpdateUserRequestBodyDTO()
                .currentPassword(PASSWORD)
                .password(PASSWORD)
                .confirmPassword(PASSWORD);

        // Unauthorized ------------------------------------------------------------------------------------------------

        final UserApi unauthorizedApi = getApi(UserApi.class);

        assertThrows(FeignException.Unauthorized.class,
                     () -> unauthorizedApi.updateUser(admin.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser unauthorized throws");

        // Bad Request -------------------------------------------------------------------------------------------------

        final UserApi userApi = getUserApi(UserApi.class);

        updateUserRequestBodyDTO.setCurrentPassword(null);

        assertThrows(FeignException.BadRequest.class,
                     () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser currentPassword missing throws");

        updateUserRequestBodyDTO.setCurrentPassword(PASSWORD);
        updateUserRequestBodyDTO.setPassword(null);

        assertThrows(FeignException.BadRequest.class,
                     () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser password missing throws");

        updateUserRequestBodyDTO.setPassword(PASSWORD);
        updateUserRequestBodyDTO.setConfirmPassword(null);

        assertThrows(FeignException.BadRequest.class,
                     () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser confirmPassword missing throws");

        updateUserRequestBodyDTO.setConfirmPassword(user.getEncryptedPassword());

        assertThrows(FeignException.BadRequest.class,
                     () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser password mismatch throws");

        updateUserRequestBodyDTO.setConfirmPassword(PASSWORD);

        // Forbidden ---------------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> userApi.updateUser(admin.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser not current user throws");

        updateUserRequestBodyDTO.currentPassword("INVALID");

        assertThrows(FeignException.Forbidden.class,
                     () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO),
                     "UserController::updateUser invalid current password throws");

        updateUserRequestBodyDTO.currentPassword(PASSWORD);

        // Not Found ---------------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class,
                     () -> unauthorizedApi.updateUser(-1L, updateUserRequestBodyDTO),
                     "UserController::updateUser not found throws");

        // OK ----------------------------------------------------------------------------------------------------------

        final String newPassword = "newPassword";

        updateUserRequestBodyDTO
                .password(newPassword)
                .confirmPassword(newPassword);

        final UserResponseBodyDTO userResponseBodyDTO = userApi.updateUser(user.getId(), updateUserRequestBodyDTO);
        final User result = this.userRepository.findById(userResponseBodyDTO.getUser().getId())
                .orElseThrow(NotFoundException::new);

        assertEquals(user.getId(), result.getId(), "UserController::updateUser result id equals");
        assertTrue(this.passwordEncoder.matches(newPassword, result.getEncryptedPassword()),
                   "UserController::updateUser new password matches");

        // Rollback ----------------------------------------------------------------------------------------------------

        result.setEncryptedPassword(this.passwordEncoder.encode(PASSWORD));
        this.userRepository.save(result);
    }

}
