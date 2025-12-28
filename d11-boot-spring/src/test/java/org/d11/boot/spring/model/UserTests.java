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

        assertTrue(user.isValid());

        user.setName("");
        assertFalse(user.isValid());
        user.setName(null);
        assertFalse(user.isValid());
        user.setName("Name");
        assertTrue(user.isValid());

        user.setEmail("");
        assertFalse(user.isValid());
        user.setEmail(null);
        assertFalse(user.isValid());
        user.setEmail("invalid email");
        assertFalse(user.isValid());
        user.setEmail("email@email.com");
        assertTrue(user.isValid());

        user.setEncryptedPassword("");
        assertFalse(user.isValid());
        user.setEncryptedPassword(null);
        assertFalse(user.isValid());
        user.setEncryptedPassword("EncryptedPassword");

        assertTrue(user.isValid());
    }

}
