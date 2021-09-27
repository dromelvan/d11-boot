package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.D11TeamMatchWeekStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for D11 team match week stat entities.
 */
@Repository
public interface D11TeamMatchWeekStatRepository extends D11EntityRepository<D11TeamMatchWeekStat> {

    /**
     * Gets D11 team match week stat for a D11 team and a season ordered by match week date.
     *
     * @param d11TeamId   Id for the D11 team for which D11 team match week stats will be looked up.
     * @param seasonId Id for the season for which D11 team match week stats will be looked up.
     * @return D11 team match week stats for the D11 team and the season.
     */
    List<D11TeamMatchWeekStat> findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(
            @Param("d11TeamId") Long d11TeamId,
            @Param("seasonId") Long seasonId
    );

}
