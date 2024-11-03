package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for User entities.
 */
@Repository
public interface UserRepository extends D11EntityRepository<User> {

    /**
     * Finds a user by name.
     *
     * @param name The name of the user.
     * @return Optional with the user or empty optional if no user was found.
     */
    Optional<User> findByName(@Param("name") String name);

    /**
     * Finds a user by email.
     *
     * @param email The email of the user.
     * @return Optional with the user or empty optional if no user was found.
     */
    Optional<User> findByEmail(@Param("email") String email);

    /**
     * Finds a user by email and confirm token.
     *
     * @param email              User email.
     * @param resetPasswordToken The confirm registration token of the user.
     * @return Optional with the user or empty optional if no user was found.
     */
    Optional<User> findByEmailAndConfirmRegistrationToken(@Param("email") String email,
                                                          @Param("confirmRegistrationToken") UUID resetPasswordToken);

    /**
     * Finds a user by email and reset password token.
     *
     * @param email              User email.
     * @param resetPasswordToken The reset password token of the user.
     * @return Optional with the user or empty optional if no user was found.
     */
    Optional<User> findByEmailAndResetPasswordToken(@Param("email") String email,
                                                    @Param("resetPasswordToken") UUID resetPasswordToken);

}
