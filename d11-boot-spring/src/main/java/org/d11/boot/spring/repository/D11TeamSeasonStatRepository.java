package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for D11TeamSeasonStat entities.
 */
@Repository
public interface D11TeamSeasonStatRepository extends D11EntityRepository<D11TeamSeasonStat> {

    /**
     * Finds D11 team season stats by season id ordered by ranking.
     *
     * @param seasonId The season id.
     * @return D11 team season stats for the season ordered by ranking.
     */
    List<D11TeamSeasonStat> findBySeasonIdOrderByRanking(@Param("seasonId") Long seasonId);

    /**
     * Finds D11 team season stats by D11 team id and season id.
     *
     * @param d11TeamId The D11 team id.
     * @param seasonId  The season id.
     * @return Optional with D11 team season stats for the D11 team and the season or empty optional if none was found.
     */
    Optional<D11TeamSeasonStat> findByD11TeamIdAndSeasonId(@Param("d11TeamId") Long d11TeamId,
                                                           @Param("seasonId") Long seasonId);

}
