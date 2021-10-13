package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11TeamSeasonStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for D11 team season stat entities.
 */
@Repository
public interface D11TeamSeasonStatRepository extends D11EntityRepository<D11TeamSeasonStat> {

    /**
     * Finds D11 team season stats for a specific season.
     *
     * @param seasonId Id for the season for which D11 team season stats will be looked up.
     * @return D11 Team season stats belonging to the season.
     */
    List<D11TeamSeasonStat> findBySeasonIdOrderByRanking(@Param("seasonId") Long seasonId);

    /**
     * Finds D11 team season stats for a specific D11 team and a specific season.
     *
     * @param d11TeamId If for the D11 team for which D11 team season stats will be looked up.
     * @param seasonId  Id for the season for which D11team season stats will be looked up.
     * @return Optional with D11 team season stats for the D11 team and the season.
     */
    Optional<D11TeamSeasonStat> findByD11TeamIdAndSeasonId(@Param("d11TeamId") Long d11TeamId, @Param("seasonId") Long seasonId);

}
