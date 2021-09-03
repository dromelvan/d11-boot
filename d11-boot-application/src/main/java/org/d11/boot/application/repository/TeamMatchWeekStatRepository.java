package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.TeamMatchWeekStat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Updates rankings for all team match week stats for a specific match week.
     *
     * @param matchWeekId Id for the match week for which team match week stat rankings will be updated.
     */
    @Modifying
    @Transactional
    @Query(value = "CALL {h-schema}update_team_match_week_stat_ranking(:matchWeekId)", nativeQuery = true)
    void updateRankingsByMatchWeekId(@Param("matchWeekId") Long matchWeekId);

    /**
     * Updates previous rankings for all team match week stats for a specific season.
     *
     * @param seasonId Id for the season for which team match week stat previous rankings will be updated.
     */
    @Modifying
    @Transactional
    @Query(value = "CALL {h-schema}update_team_match_week_stat_previous_ranking(:seasonId)", nativeQuery = true)
    void updatePreviousRankingsBySeasonId(@Param("seasonId") Long seasonId);

}
