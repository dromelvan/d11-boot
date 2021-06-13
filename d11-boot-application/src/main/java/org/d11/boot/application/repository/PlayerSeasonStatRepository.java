package org.d11.boot.application.repository;

import org.d11.boot.application.model.PlayerSeasonStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for player season stat entities.
 */
@Repository
public interface PlayerSeasonStatRepository extends D11EntityRepository<PlayerSeasonStat> {

    /**
     * Gets player season stats for a specific team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, descending.
     *
     * @param teamId Id for the team for which player season stats will be looked up.
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
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stats for the team and the season.
     */
    List<PlayerSeasonStat> findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDesc(
            @Param("d11TeamId") Long d11TeamId,
            @Param("seasonId") Long seasonId
    );

}
