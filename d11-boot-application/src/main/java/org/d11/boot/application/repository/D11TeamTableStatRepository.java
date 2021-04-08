package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11TeamTableStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for D11 team table stat entities.
 */
@Repository
public interface D11TeamTableStatRepository extends D11EntityRepository<D11TeamTableStat> {

    /**
     * Finds the top 20 D11 team table stats ordered by D11 match week id, descending, and ranking.
     * This will be the current league table standings.
     *
     * @param d11LeagueId Id for the D11 league for which D11 team table stats will be looked up.
     * @return The current league table standings for the D11 league.
     */
    List<D11TeamTableStat> findTop20ByD11LeagueIdOrderByD11MatchWeekIdDescRanking(@Param("d11LeagueId") Long d11LeagueId);

}
