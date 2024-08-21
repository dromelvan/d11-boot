package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.PlayerSeasonStat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for PlayerSeasonStat entities.
 */
@Repository
public interface PlayerSeasonStatRepository extends D11EntityRepository<PlayerSeasonStat> {

    /**
     * Finds player season stats by player id ordered by season id, descending.
     *
     * @param playerId The player id.
     * @return Player season stats for the player ordered by season id, descending.
     */
    List<PlayerSeasonStat> findByPlayerIdOrderBySeasonIdDesc(@Param("playerId") Long playerId);

    /**
     * Finds player season stats by team id and season id ordered by position and ranking.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return Player season stats for the team and season ordered by position and ranking id.
     */
    List<PlayerSeasonStat> findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(@Param("teamId") Long teamId,
                                                                                     @Param("seasonId") Long seasonId);

    /**
     * Finds player season stats by season id.
     *
     * @param seasonId The season id.
     * @return Player season stats for the season.
     */
    List<PlayerSeasonStat> findBySeasonId(@Param("seasonId") Long seasonId);

    /**
     * Finds player season stats by season id, paged.
     *
     * @param seasonId The season id.
     * @param pageable Pageable that defines page number, page size and sorting of the result.
     * @return Player season stat for the season, paged
     */
    List<PlayerSeasonStat> findBySeasonId(@Param("seasonId") Long seasonId, Pageable pageable);

}
