package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User repository tests.
 */
class UserRepositoryTests extends AbstractRepositoryTests<User, UserRepository> {

    /**
     * Tests UserRepository::findByName.
     */
    @Test
    void testFindByName() {
        getEntities().forEach(user -> {
            final User result = getRepository().findByName(user.getName()).orElse(null);
            assertNotNull(result);
            assertEquals(user, result);
        });
    }

    /**
     * Tests UserRepository::findByEmail.
     */
    @Test
    void testFindByEmail() {
        getEntities().forEach(user -> {
            final User result = getRepository().findByEmail(user.getEmail()).orElse(null);
            assertNotNull(result);
            assertEquals(user, result);
        });
    }

    /**
     * Tests UserRepository::findByEmailAndConfirmRegistrationToken.
     */
    @Test
    void testFindByEmailAndConfirmRegistrationToken() {
        getEntities().forEach(user -> {
            final User result = getRepository().findByEmailAndConfirmRegistrationToken(
                    user.getEmail(),
                    user.getConfirmRegistrationToken()
                ).orElse(null);
            assertNotNull(result);
            assertEquals(user, result);
        });
    }

    /**
     * Tests UserRepository::findByEmailAndResetPasswordToken.
     */
    @Test
    void testFindByEmailAndResetPasswordToken() {
        getEntities().forEach(user -> {
            final User result = getRepository().findByEmailAndResetPasswordToken(user.getEmail(),
                                                                                 user.getResetPasswordToken())
                .orElse(null);
            assertNotNull(result);
            assertEquals(user, result);
        });
    }

}
