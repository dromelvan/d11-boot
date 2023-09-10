package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11TeamMatchWeekStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for D11TeamMatchWeekStat entities.
 */
@Repository
public interface D11TeamMatchWeekStatRepository extends D11EntityRepository<D11TeamMatchWeekStat> {

    /**
     * Finds D11 team match week stats by D11 team id and season id ordered by match week date.
     *
     * @param d11TeamId The D11 team id.
     * @param seasonId  The season id.
     * @return D11 team match week stats for the D11 team and the season ordered by match week date.
     */
    List<D11TeamMatchWeekStat> findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(
            @Param("d11TeamId") Long d11TeamId,
            @Param("seasonId") Long seasonId);

}
