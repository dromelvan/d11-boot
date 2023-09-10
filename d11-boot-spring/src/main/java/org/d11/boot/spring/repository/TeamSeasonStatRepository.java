package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TeamSeasonStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for TeamSeasonStat entities.
 */
@Repository
public interface TeamSeasonStatRepository extends D11EntityRepository<TeamSeasonStat> {

    /**
     * Finds team season stats by season id ordered by ranking.
     *
     * @param seasonId The season id.
     * @return Team season stats for the season ordered by ranking.
     */
    List<TeamSeasonStat> findBySeasonIdOrderByRanking(@Param("seasonId") Long seasonId);

    /**
     * Finds team season stats by team id and season id.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return Optional with team season stats for the team and the season or empty optional if none was found.
     */
    Optional<TeamSeasonStat> findByTeamIdAndSeasonId(@Param("teamId") Long teamId, @Param("seasonId") Long seasonId);

}
