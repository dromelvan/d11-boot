package org.d11.boot.application.model;

import org.d11.boot.api.model.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * User tests.
 */
public class UserTests extends D11EasyRandomTests {

    /**
     * Tests user validity.
     */
    @Test
    public void isValid() {
        final User user = generate(User.class);

        assertTrue(user.isValid(), "New user should be valid.");

        user.setName("");
        assertFalse(user.isValid(), "Empty name should not be valid.");
        user.setName(null);
        assertFalse(user.isValid(), "Null name should not be valid.");
        user.setName("Name");

        user.setEmail("");
        assertFalse(user.isValid(), "Empty email should not be valid.");
        user.setEmail(null);
        assertFalse(user.isValid(), "Null email should not be valid.");
        user.setEmail("invalid email");
        assertFalse(user.isValid(), "Invalid email should not be valid.");
        user.setEmail("email@email.com");

        user.setEncryptedPassword("");
        assertFalse(user.isValid(), "Empty password should not be valid.");
        user.setEncryptedPassword(null);
        assertFalse(user.isValid(), "Null password should not be valid.");
        user.setEncryptedPassword("EncryptedPassword");

        assertTrue(user.isValid(), "User should be valid.");
    }

    /**
     * Tests mapping between User and UserDTO.
     */
    @Test
    public void map() {
        final User user = generate(User.class);

        final UserDTO userDTO = map(user, UserDTO.class);
        final User mappedUser = map(userDTO, User.class);
        // Email is not stored in the DTO
        mappedUser.setEmail(user.getEmail());

        assertEquals(user, mappedUser, "User should equal mapped user.");
    }

}
