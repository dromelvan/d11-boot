package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.model.jpa.projection.EntityId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repository for season entities.
 */
@Repository
public interface SeasonRepository extends D11EntityRepository<Season> {

    /**
     * Finds all season ids and orders them by date, descending.
     *
     * @return List of all season ids ordered by date, descending.
     */
    List<EntityId> findByOrderByDateDesc();

    /**
     * Finds the season with the latest start date. This is the current season.
     *
     * @return Optional with the current season or an empty optional if no seasons exist.
     */
    Optional<Season> findFirstByOrderByDateDesc();

    /**
     * Updates all player season stats, team season stats, team match week stats, D11 team season stats,
     * D11 team match week stats and match week winners for a season.
     *
     * @param seasonId Id for the season for which stats will be updated.
     */
    // We have to do a @Query instead of a @Procedure as longs as we're using dev/prod schemas in the same database.
    @Modifying
    @Transactional
    @Query(value = "CALL {h-schema}update_stats_by_season_id(:seasonId)", nativeQuery = true)
    void updateStatsBySeasonId(@Param("seasonId") Long seasonId);

}
