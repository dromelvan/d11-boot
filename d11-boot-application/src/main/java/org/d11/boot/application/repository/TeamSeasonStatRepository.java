package org.d11.boot.application.repository;

import org.d11.boot.application.model.TeamSeasonStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for team season stat entities.
 */
@Repository
public interface TeamSeasonStatRepository extends D11EntityRepository<TeamSeasonStat> {

    /**
     * Finds team season stats for a specific season.
     *
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return Team season stats belonging to the season.
     */
    List<TeamSeasonStat> findBySeasonIdOrderByRanking(@Param("seasonId") Long seasonId);

    /**
     * Finds team season stats for a specific team and a specific season.
     *
     * @param teamId If for the team for which team season stats will be looked up.
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return Optional with team season stats for the team and the season.
     */
    Optional<TeamSeasonStat> findByTeamIdAndSeasonId(@Param("teamId") Long teamId, @Param("seasonId") Long seasonId);

}
