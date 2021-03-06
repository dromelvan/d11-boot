package org.d11.boot.application.repository;

import org.d11.boot.application.model.PlayerSeasonStat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for player season stat entities.
 */
@Repository
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
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
     * @param pageable Pageable that defines page number, page size and sorting of the result.
     * @return Player season stats for the season ordered and paged by the pageable.
     */
    List<PlayerSeasonStat> findBySeasonId(@Param("seasonId") Long seasonId, Pageable pageable);

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
     * Gets player season stats for a specific team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, descending.
     *
     * @param teamId   Id for the team for which player season stats will be looked up.
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByTeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDesc(
            @Param("teamId") Long teamId,
            @Param("seasonId") Long seasonId
    );

    /**
     * Gets player season stats for a specific D11 team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, descending.
     *
     * @param d11TeamId Id for the D11 team for which player season stats will be looked up.
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDesc(
            @Param("d11TeamId") Long d11TeamId,
            @Param("seasonId") Long seasonId
    );

}
