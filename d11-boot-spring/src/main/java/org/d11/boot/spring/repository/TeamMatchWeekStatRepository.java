package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TeamMatchWeekStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for TeamMatchWeekStat entities.
 */
@Repository
public interface TeamMatchWeekStatRepository extends D11EntityRepository<TeamMatchWeekStat> {

    /**
     * Finds team match week stats by team id and season id ordered by match week date.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return Team match week stats for the team and the season ordered by match week date.
     */
    List<TeamMatchWeekStat> findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(@Param("teamId") Long teamId,
                                                                                 @Param("seasonId") Long seasonId);

}
