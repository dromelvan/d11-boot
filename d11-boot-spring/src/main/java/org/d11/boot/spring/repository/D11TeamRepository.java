package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for D11 Team entities.
 */
@Repository
public interface D11TeamRepository extends D11EntityRepository<D11Team> {

    /**
     * Finds a list of all D11 teams ordered by ascending name.
     *
     * @return List of all D11 teams ordered by ascending name.
     */
    List<D11Team> findByOrderByName();

    /**
     * Finds the D11 team owned or co-owned by a user.
     *
     * @param owner   The owner user.
     * @param coOwner The co-owner user (pass the same user as owner to search both roles).
     * @return Optional D11 team owned or co-owned by the user.
     */
    Optional<D11Team> findByOwnerOrCoOwner(User owner, User coOwner);

}
