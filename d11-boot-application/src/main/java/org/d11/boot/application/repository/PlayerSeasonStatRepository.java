package org.d11.boot.application.repository;

import org.d11.boot.application.model.PlayerSeasonStat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository for player season stat entities.
 */
@Repository
@SuppressWarnings({ "PMD.AvoidDuplicateLiterals", "checkstyle:LineLength" })
public interface PlayerSeasonStatRepository extends D11EntityRepository<PlayerSeasonStat> {

    /**
     * Gets player season stats for a player ordered by season id, descending.
     *
     * @param playerId Id for the player for which a player season stats will be looked up.
     * @return Player season stats for the player ordered by season id, descending.
     */
    List<PlayerSeasonStat> findByPlayerIdOrderBySeasonIdDesc(@Param("playerId") Long playerId);

    /**
     * Gets player season stats for a season.
     *
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stats for the season.
     */
    List<PlayerSeasonStat> findBySeasonId(@Param("seasonId") Long seasonId);

    /**
     * Gets player season stats for a season.
     *
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @param pageable Pageable that defines page number, page size and sorting of the result.
     * @return Player season stats for the season ordered and paged by the pageable.
     */
    List<PlayerSeasonStat> findBySeasonId(@Param("seasonId") Long seasonId, Pageable pageable);

    /**
     * Gets filtered player season stats for a season.
     *
     * @param seasonId     Id for the season for which player season stats will be looked up.
     * @param positionId   Position ids that will be included.
     * @param d11TeamDummy D11 team dummy statuses that will be included.
     * @param pageable     Pageable that defines page number, page size and sorting of the result.
     * @return Player season stats for the season ordered and paged by the pageable.
     */
    List<PlayerSeasonStat> findBySeasonIdAndPositionIdInAndD11TeamDummyIn(@Param("seasonId") long seasonId,
                                                                          @Param("positionId") Set<Long> positionId,
                                                                          @Param("d11TeamDummy") Set<Boolean> d11TeamDummy,
                                                                          Pageable pageable);

    /**
     * Gets player season stats for a season.
     *
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @param d11TeamDummy Include only players from D11 teams with this dummy status.
     * @return Player season stats for the season for teams with the specified dummy status.
     */
    List<PlayerSeasonStat> findBySeasonIdAndD11TeamDummyOrderByRankingAsc(
            @Param("seasonId") Long seasonId,
            @Param("d11TeamDummy") Boolean d11TeamDummy
    );

    /**
     * Gets player season stats for a season.
     *
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @param teamDummy Include only players from teams with this dummy status.
     * @return Player season stats for the season for teams with the specified dummy status.
     */
    List<PlayerSeasonStat> findBySeasonIdAndTeamDummy(@Param("seasonId") Long seasonId, @Param("teamDummy") Boolean teamDummy);

    /**
     * Gets player season stats for a season ordered by team name, position sort order and player last name.
     *
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @param teamDummy Include only players from teams with this dummy status.
     * @return Player season stats for the season for teams with the specified dummy status.
     */
    List<PlayerSeasonStat> findBySeasonIdAndTeamDummyOrderByTeamNameAscPositionSortOrderAscPlayerLastNameAsc(
            @Param("seasonId") Long seasonId,
            @Param("teamDummy") Boolean teamDummy
    );

    /**
     * Gets player season stats for a season.
     *
     * @param seasonId     Id for the season for which player season stats will be looked up.
     * @param teamDummy    Include only players from teams with this dummy status.
     * @param d11TeamDummy Include only players from D11 teams with this dummy status.
     * @return Player season stats for the season for teams and D11 teams with the specified dummy status.
     */
    List<PlayerSeasonStat> findBySeasonIdAndTeamDummyAndD11TeamDummyOrderByTeamNameAscPositionSortOrderAscPlayerLastNameAsc(
            @Param("seasonId") Long seasonId,
            @Param("teamDummy") Boolean teamDummy,
            @Param("d11TeamDummy") Boolean d11TeamDummy
    );

    /**
     * Gets a player season stat for a specific player and a specific season.
     *
     * @param playerId Id for the player for which a player season stat will be looked up.
     * @param seasonId Id for the season for which a player season stat will be looked up.
     * @return Optional with a player season stat for the player and season or an empty optional if no player season
     *         stat exists.
     */
    Optional<PlayerSeasonStat> findByPlayerIdAndSeasonId(@Param("playerId") Long playerId, @Param("seasonId") Long seasonId);

    /**
     * Gets player season stats for a specific team and a specific season.
     *
     * @param teamId   Id for the team for which player season stats will be looked up.
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByTeamIdAndSeasonId(@Param("teamId") Long teamId, @Param("seasonId") Long seasonId);

    /**
     * Gets player season stats for a specific team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, last name and first name.
     *
     * @param teamId   Id for the team for which player season stats will be looked up.
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByTeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDescPlayerLastNameAscPlayerFirstNameAsc(
            @Param("teamId") Long teamId,
            @Param("seasonId") Long seasonId
    );

    /**
     * Gets player season stats for a specific D11 team and a specific season.
     *
     * @param d11TeamId Id for the D11 team for which player season stats will be looked up.
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByD11TeamIdAndSeasonId(@Param("d11TeamId") Long d11TeamId, @Param("seasonId") Long seasonId);

    /**
     * Gets player season stats for a specific D11 team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, descending.
     *
     * @param d11TeamId Id for the D11 team for which player season stats will be looked up.
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDescPlayerLastNameAscPlayerFirstNameAsc(
            @Param("d11TeamId") Long d11TeamId,
            @Param("seasonId") Long seasonId
    );

    /**
     * Gets player season stats for all players who have a player match stat for a specific match.
     *
     * @param matchId Id for the match for which player season stats will be looked up.
     * @return Player season stats for players with player match stats for the match.
     */
    @Query("SELECT playerSeasonStat FROM PlayerMatchStat playerMatchStat " +
            "JOIN Match match ON playerMatchStat.match = match " +
            "JOIN MatchWeek matchWeek ON match.matchWeek = matchWeek " +
            "JOIN PlayerSeasonStat playerSeasonStat ON playerSeasonStat.season = matchWeek.season AND playerSeasonStat.player = playerMatchStat.player " +
            "WHERE match.id = :matchId")
    List<PlayerSeasonStat> findByMatchId(@Param("matchId") Long matchId);

}
