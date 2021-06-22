package org.d11.boot.application.repository;

import org.d11.boot.application.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for user entities.
 */
@Repository
public interface UserRepository extends D11EntityRepository<User> {

    /**
     * Finds a user by email.
     *
     * @param email The email of the user that will be looked up.
     * @return Optional with the user with the provided email.
     */
    Optional<User> findByEmail(@Param("email") String email);

}
