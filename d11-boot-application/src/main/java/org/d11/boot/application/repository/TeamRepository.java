package org.d11.boot.application.repository;

import org.d11.boot.application.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for team entities.
 */
@Repository
public interface TeamRepository extends D11EntityRepository<Team> {

    /**
     * Finds all teams.
     *
     * @return List of all teams, ordered by name.
     */
    List<Team> findByOrderByName();

}
