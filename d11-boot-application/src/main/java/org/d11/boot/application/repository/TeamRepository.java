package org.d11.boot.application.repository;

import org.d11.boot.application.model.Team;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * Finds a team with a specific WhoScored id.
     *
     * @param whoscoredId WhoScored id of the team that will be looked up.
     * @return Team with the specified WhoScored id.
     */
    Optional<Team> findByWhoscoredId(@Param("whoscoredId") Integer whoscoredId);

}
