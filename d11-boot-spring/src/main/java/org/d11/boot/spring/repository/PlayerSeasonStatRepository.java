package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.PlayerSeasonStat;
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
     * Finds player season stats by season id.
     *
     * @param seasonId The season id.
     * @return Player season stats for the season.
     */
    List<PlayerSeasonStat> findBySeasonId(@Param("seasonId") Long seasonId);

}
