package org.d11.boot.application.repository;

import org.d11.boot.application.model.TeamTableStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for team table stat entities.
 */
@Repository
public interface TeamTableStatRepository extends D11EntityRepository<TeamTableStat> {

    /**
     * Finds the top 20 team table stats ordered by match week id, descending, and ranking.
     * This will be the current league table standings.
     *
     * @param premierLeagueId Id for the Premier League for which team table stats will be looked up.
     * @param main Value for main for the team table stats that will be looked up,
     * @return The current league table standings for the Premier League.
     */
    List<TeamTableStat> findByPremierLeagueIdAndMainOrderByMatchWeekIdDescRanking(
            @Param("premierLeagueId") Long premierLeagueId,
            @Param("main") boolean main);

}
