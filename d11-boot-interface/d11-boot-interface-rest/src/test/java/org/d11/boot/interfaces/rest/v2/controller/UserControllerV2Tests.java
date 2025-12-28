package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.UserApi;
import org.d11.boot.api.v2.model.ConfirmUserRequestBodyDTO;
import org.d11.boot.api.v2.model.CreateUserRequestBodyDTO;
import org.d11.boot.api.v2.model.UpdateUserRequestBodyDTO;
import org.d11.boot.api.v2.model.UserResponseBodyDTO;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    @SuppressWarnings({ "PMD.ExcessiveMethodLength", "checkstyle:ExecutableStatementCount" })
    void testCreateUser() {
        final UserApi userApi = getApi(UserApi.class);

        final String name = "name";
        final String email = "email@email.com";
        final String confirmPassword = "confirmPassword";
        final String confirmRegistrationLink = "link?%s&%s";

        final CreateUserRequestBodyDTO createUserRequestBodyDTO = new CreateUserRequestBodyDTO()
                .email(email)
                .password(PASSWORD)
                .confirmPassword(PASSWORD)
                .confirmRegistrationLink(confirmRegistrationLink);

        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.name(name).email(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.name(name).email("INVALID_EMAIL");
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.email(email).password(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.password(PASSWORD).confirmPassword(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.confirmPassword(confirmPassword);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.confirmPassword(PASSWORD).confirmRegistrationLink(null);
        assertThrows(FeignException.BadRequest.class, () -> userApi.createUser(createUserRequestBodyDTO));

        createUserRequestBodyDTO.confirmRegistrationLink(confirmRegistrationLink);

        final long count = this.userRepository.count();
        final User existingUser = this.userRepository.findAll().get(0);

        createUserRequestBodyDTO.name(existingUser.getName());
        assertDoesNotThrow(() -> userApi.createUser(createUserRequestBodyDTO));
        assertEquals(count, this.userRepository.count());

        createUserRequestBodyDTO.name(name).email(existingUser.getEmail());
        assertDoesNotThrow(() -> userApi.createUser(createUserRequestBodyDTO));
        assertEquals(count, this.userRepository.count());

        createUserRequestBodyDTO.email(email);

        final UserResponseBodyDTO userResponseBodyDTO = userApi.createUser(createUserRequestBodyDTO);

        assertEquals(count + 1, this.userRepository.count());

        final User user = this.userRepository.findByName(userResponseBodyDTO.getUser().getName()).orElse(null);

        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertTrue(this.passwordEncoder.matches(PASSWORD, user.getEncryptedPassword()));
        assertFalse(user.isAdministrator());
        assertNotNull(user.getConfirmRegistrationToken());
    }

    /**
     * Tests UserController::confirmUser.
     */
    @Test
    void testConfirmUser() {
        final UserApi userApi = getApi(UserApi.class);
        final User user = this.userRepository.findByName("Unconfirmed").orElse(null);

        assertNotNull(user);
        assertNotNull(user.getConfirmRegistrationToken());

        final ConfirmUserRequestBodyDTO confirmUserRequestBodyDTO = new ConfirmUserRequestBodyDTO()
                .confirmRegistrationToken(UUID.randomUUID());

        // Bad Request -------------------------------------------------------------------------------------------------

        assertThrows(FeignException.BadRequest.class, () -> userApi.confirmUser(confirmUserRequestBodyDTO));

        confirmUserRequestBodyDTO.setEmail(user.getEmail());
        confirmUserRequestBodyDTO.setConfirmRegistrationToken(null);

        assertThrows(FeignException.BadRequest.class, () -> userApi.confirmUser(confirmUserRequestBodyDTO));

        confirmUserRequestBodyDTO.setConfirmRegistrationToken(UUID.randomUUID());

        // Unauthorized ------------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class, () -> userApi.confirmUser(confirmUserRequestBodyDTO));

        confirmUserRequestBodyDTO.setConfirmRegistrationToken(user.getConfirmRegistrationToken());

        // OK ----------------------------------------------------------------------------------------------------------

        assertDoesNotThrow(() -> userApi.confirmUser(confirmUserRequestBodyDTO));

        final User confirmedUser = this.userRepository.findById(user.getId()).orElse(null);

        assertNotNull(confirmedUser);
        assertNull(confirmedUser.getConfirmRegistrationToken());

        assertThrows(FeignException.Unauthorized.class, () -> userApi.confirmUser(confirmUserRequestBodyDTO));
    }

    /**
     * Tests UserController::updateUser.
     */
    @Test
    void testUpdateUser() {
        final User user = this.userRepository.findByName("User").orElse(null);
        final User admin = this.userRepository.findByName("Administrator").orElse(null);

        assertNotNull(user);
        assertNotNull(admin);

        final UpdateUserRequestBodyDTO updateUserRequestBodyDTO = new UpdateUserRequestBodyDTO()
                .currentPassword(PASSWORD)
                .password(PASSWORD)
                .confirmPassword(PASSWORD);

        // Unauthorized ------------------------------------------------------------------------------------------------

        final UserApi unauthorizedApi = getApi(UserApi.class);

        assertThrows(FeignException.Unauthorized.class,
                     () -> unauthorizedApi.updateUser(admin.getId(), updateUserRequestBodyDTO));

        // Bad Request -------------------------------------------------------------------------------------------------

        final UserApi userApi = getUserApi(UserApi.class);

        updateUserRequestBodyDTO.setCurrentPassword(null);

        assertThrows(FeignException.BadRequest.class, () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO));

        updateUserRequestBodyDTO.setCurrentPassword(PASSWORD);
        updateUserRequestBodyDTO.setPassword(null);

        assertThrows(FeignException.BadRequest.class, () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO));

        updateUserRequestBodyDTO.setPassword(PASSWORD);
        updateUserRequestBodyDTO.setConfirmPassword(null);

        assertThrows(FeignException.BadRequest.class, () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO));

        updateUserRequestBodyDTO.setConfirmPassword(user.getEncryptedPassword());

        assertThrows(FeignException.BadRequest.class, () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO));

        updateUserRequestBodyDTO.setConfirmPassword(PASSWORD);

        // Forbidden ---------------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> userApi.updateUser(admin.getId(), updateUserRequestBodyDTO));

        updateUserRequestBodyDTO.currentPassword("INVALID");

        assertThrows(FeignException.Forbidden.class, () -> userApi.updateUser(user.getId(), updateUserRequestBodyDTO));

        updateUserRequestBodyDTO.currentPassword(PASSWORD);

        // Not Found ---------------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class, () -> unauthorizedApi.updateUser(-1L, updateUserRequestBodyDTO));

        // OK ----------------------------------------------------------------------------------------------------------

        final String newPassword = "newPassword";

        updateUserRequestBodyDTO
                .password(newPassword)
                .confirmPassword(newPassword);

        final UserResponseBodyDTO userResponseBodyDTO = userApi.updateUser(user.getId(), updateUserRequestBodyDTO);
        final User result = this.userRepository.findByName(userResponseBodyDTO.getUser().getName()).orElse(null);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertTrue(this.passwordEncoder.matches(newPassword, result.getEncryptedPassword()));

        // Rollback ----------------------------------------------------------------------------------------------------

        result.setEncryptedPassword(this.passwordEncoder.encode(PASSWORD));
        this.userRepository.save(result);
    }

    /**
     * Tests UserController::deleteUser.
     */
    @Test
    void testDeleteUser() {
        final User unsavedUser = new User();
        unsavedUser.setName("Deleted");
        unsavedUser.setEmail("deleted@user.com");
        unsavedUser.setEncryptedPassword(this.passwordEncoder.encode(PASSWORD));
        final User user = this.userRepository.save(unsavedUser);

        // Unauthorized ------------------------------------------------------------------------------------------------

        final UserApi unauthorizedApi = getApi(UserApi.class);

        assertThrows(FeignException.Unauthorized.class, () -> unauthorizedApi.deleteUser(user.getId()));

        // Forbidden ---------------------------------------------------------------------------------------------------

        final UserApi forbiddenApi = getUserApi(UserApi.class);

        assertThrows(FeignException.Forbidden.class, () -> forbiddenApi.deleteUser(user.getId()));

        // Not Found ---------------------------------------------------------------------------------------------------

        final UserApi administratorApi = getAdministratorApi(UserApi.class);

        assertThrows(FeignException.NotFound.class, () -> administratorApi.deleteUser(-1L));

        // OK ----------------------------------------------------------------------------------------------------------

        assertDoesNotThrow(() -> administratorApi.deleteUser(user.getId()));

        assertFalse(this.userRepository.findById(user.getId()).isPresent());
        assertThrows(FeignException.NotFound.class, () -> administratorApi.deleteUser(user.getId()));
    }

}
