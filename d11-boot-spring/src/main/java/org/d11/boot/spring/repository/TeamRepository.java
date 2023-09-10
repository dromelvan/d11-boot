package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Team entities.
 */
@Repository
public interface TeamRepository extends D11EntityRepository<Team> {

    /**
     * Finds a list of all teams ordered by ascending name.
     *
     * @return List of all teams ordered by ascending name.
     */
    List<Team> findByOrderByName();

}
