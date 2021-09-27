package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.TeamMatchWeekStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for team match week stat entities.
 */
@Repository
public interface TeamMatchWeekStatRepository extends D11EntityRepository<TeamMatchWeekStat> {

    /**
     * Gets team match week for a team and a season ordered by match week date.
     *
     * @param teamId   Id for the team for which team match week stats will be looked up.
     * @param seasonId Id for the season for which team match week stats will be looked up.
     * @return Team match week stats for the team and the season.
     */
    List<TeamMatchWeekStat> findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(
            @Param("teamId") Long teamId,
            @Param("seasonId") Long seasonId
    );

}
