package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User repository tests.
 */
class UserRepositoryTests extends D11BootRepositoryTests<User, UserRepository> {

    /**
     * Creates new User repository tests.
     */
    UserRepositoryTests() {
        super(User.class);
    }

    /**
     * Tests UserRepository::findByName.
     */
    @Test
    void testFindByName() {
        getEntities().forEach(user -> {
            final User result = getRepository().findByName(user.getName()).orElse(null);
            assertNotNull(result, "UserRepository::findByName not null");
            assertEquals(user, result, "UserRepository::findByName equals");
        });
    }

    /**
     * Tests UserRepository::findByEmail.
     */
    @Test
    void testFindByEmail() {
        getEntities().forEach(user -> {
            final User result = getRepository().findByEmail(user.getEmail()).orElse(null);
            assertNotNull(result, "UserRepository::findByEmail not null");
            assertEquals(user, result, "UserRepository::findByEmail equals");
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
            assertNotNull(result, "UserRepository::findByResetPasswordToken not null");
            assertEquals(user, result, "UserRepository::findByResetPasswordToken equals");
        });
    }

}
