package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * User tests.
 */
class UserTests extends EasyRandomTests {

    /**
     * Tests User::isValid.
     */
    @Test
    void testIsValid() {
        final User user = generate(User.class);

        assertTrue(user.isValid(), "User::isValid");

        user.setName("");
        assertFalse(user.isValid(), "User::isValid name empty");
        user.setName(null);
        assertFalse(user.isValid(), "User::isValid name null");
        user.setName("Name");
        assertTrue(user.isValid(), "User::isValid name valid");

        user.setEmail("");
        assertFalse(user.isValid(), "User::isValid email empty");
        user.setEmail(null);
        assertFalse(user.isValid(), "User::isValid email null");
        user.setEmail("invalid email");
        assertFalse(user.isValid(), "User::isValid email invalid");
        user.setEmail("email@email.com");
        assertTrue(user.isValid(), "User::isValid email valid");

        user.setEncryptedPassword("");
        assertFalse(user.isValid(), "User::isValid encrypted password empty");
        user.setEncryptedPassword(null);
        assertFalse(user.isValid(), "User::isValid encrypted password null");
        user.setEncryptedPassword("EncryptedPassword");

        assertTrue(user.isValid(), "User::isValid valid");
    }

}
