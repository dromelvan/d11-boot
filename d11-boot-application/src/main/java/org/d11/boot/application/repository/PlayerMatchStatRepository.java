package org.d11.boot.application.repository;

import org.d11.boot.application.model.Lineup;
import org.d11.boot.application.model.PlayerMatchStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
     * Gets player match stats for a specific match and team and lineup options ordered by player position sort order
     * and lineup descending.
     *
     * @param matchId Id for the match for which player match stats will be looked up.
     * @param teamId Id for the team for which player match stats will be looked up.
     * @param lineups Only player match stats with lineups contained in this set will be included.
     * @return Player match stats for the match, team and lineups.
     */
    List<PlayerMatchStat> findByMatchIdAndTeamIdAndLineupInOrderByPositionSortOrderAscLineupDesc(
            @Param("matchId") Long matchId,
            @Param("teamId") Long teamId,
            @Param("lineup") Set<Lineup> lineups
    );

    /**
     * Gets player match stats for a specific D11 match ordered by player position sort order.
     *
     * @param d11MatchId Id for the D11 match for which player match stats will be looked up.
     * @return Player match stats for the D11 match.
     */
    List<PlayerMatchStat> findByD11MatchIdOrderByPositionSortOrder(@Param("d11MatchId") Long d11MatchId);

    /**
     * Gets player match stats for a specific D11 match and a specific D11 team ordered by player position sort order
     * and lineup descending.
     *
     * @param d11MatchId Id for the D11 match for which player match stats will be looked up.
     * @param d11TeamId Id for the D11 team for which player match stats will be looked up.
     * @return Player match stats for the D11 match.
     */
    List<PlayerMatchStat> findByD11MatchIdAndD11TeamIdOrderByPositionSortOrderAscLineupDesc(
            @Param("d11MatchId") Long d11MatchId,
            @Param("d11TeamId") Long d11TeamId
    );

    /**
     * Gets player match stats for a specific player and match week that is not for a specific match.
     *
     * @param playerId    Id for the player for which player match stats will be looked up.
     * @param matchId     Id for the match that will be excluded.
     * @param matchWeekId Id for the season for which player match stats will be looked up.
     * @return Player match stats for the player and season but not the match.
     */
    List<PlayerMatchStat> findByPlayerIdAndMatchIdNotAndMatchMatchWeekId(
            @Param("playerId") Long playerId,
            @Param("matchId") Long matchId,
            @Param("matchWeekId") Long matchWeekId
    );

    /**
     * Gets player match stats for a specific player and season ordered by match datetime.
     *
     * @param playerId Id for the player for which player match stats will be looked up.
     * @param seasonId Id for the season for which player match stats will be looked up.
     * @return Player match stats for the player and season.
     */
    List<PlayerMatchStat> findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(
            @Param("playerId") Long playerId,
            @Param("seasonId") Long seasonId
    );

    /**
     * Finds the top 5 player match stats for a match week ordered by points and rating, descending.
     *
     * @param matchWeekId Id for the match week for which player match stats will be looked up.
     * @param lineups Only player match stats with lineups contained in this set will be included.
     * @return The top 5 player match stats for the match week.
     */
    List<PlayerMatchStat> findTop5ByMatchMatchWeekIdAndLineupInOrderByPointsDescRatingDesc(
            @Param("matchWeekId") Long matchWeekId,
            @Param("lineup") Set<Lineup> lineups
    );

    /**
     * Finds the top 5 player match stats for a match week ordered by points and rating. This will be the actual
     * bottom 5 player match stats.
     *
     * @param matchWeekId Id for the match week for which player match stats will be looked up.
     * @param lineups Only player match stats with lineups contained in this set will be included.
     * @return The bottom 5 player match stats for the match week.
     */
    List<PlayerMatchStat> findTop5ByMatchMatchWeekIdAndLineupInOrderByPointsAscRatingAsc(
            @Param("matchWeekId") Long matchWeekId,
            @Param("lineup") Set<Lineup> lineups
    );

}
