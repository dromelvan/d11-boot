package org.d11.boot.application.repository;

import org.d11.boot.application.model.PlayerMatchStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for player match stat entities.
 */
@Repository
public interface PlayerMatchStatRepository extends D11EntityRepository<PlayerMatchStat> {

    /**
     * Gets player match stats for a specific match ordered by player position sort order.
     *
     * @param matchId Id for the match for which player match stats will be looked up.
     * @return Player match stats for the match.
     */
    List<PlayerMatchStat> findByMatchIdOrderByPositionSortOrder(@Param("matchId") Long matchId);

    /**
     * Gets player match stats for a specific D11 match ordered by player position sort order.
     *
     * @param d11MatchId Id for the D11 match for which player match stats will be looked up.
     * @return Player match stats for the D11 match.
     */
    List<PlayerMatchStat> findByD11MatchIdOrderByPositionSortOrder(@Param("d11MatchId") Long d11MatchId);

    /**
     * Gets player match stats for a specific player and season ordered by match datetime.
     *
     * @param playerId Id for the player for which player match stats will be looked up.
     * @param seasonId Id for the season for which player match stats will be looked up.
     * @return Player match stats for the player and season.
     */
    List<PlayerMatchStat> findByPlayerIdAndMatchMatchWeekPremierLeagueSeasonIdOrderByMatchDatetime(
            @Param("playerId") Long playerId,
            @Param("seasonId") Long seasonId
    );

}
