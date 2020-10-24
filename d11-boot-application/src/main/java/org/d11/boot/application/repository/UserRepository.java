package org.d11.boot.application.repository;

import org.d11.boot.application.model.User;
import org.springframework.stereotype.Repository;

/**
 * Repository for user entities.
 */
@Repository
public interface UserRepository extends D11EntityRepository<User> {

}
