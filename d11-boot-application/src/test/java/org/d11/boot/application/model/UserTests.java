package org.d11.boot.application.model;

import org.d11.boot.api.model.UserDTO;
import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.util.D11BootModelMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * User tests.
 */
public class UserTests {

    /**
     * Random user generator.
     */
    private final D11EasyRandom d11EasyRandom = new D11EasyRandom();

    /**
     * Tests user validity.
     */
    @Test
    public void isValid() {
        final User user = this.d11EasyRandom.nextObject(User.class);

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
        final User user = this.d11EasyRandom.nextObject(User.class);

        final ModelMapper modelMapper = new D11BootModelMapper();

        final UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        final User mappedUser = modelMapper.map(userDTO, User.class);
        // Email is not stored in the DTO
        mappedUser.setEmail(user.getEmail());

        assertEquals(user, mappedUser, "User should equal mapped user.");
    }

}
