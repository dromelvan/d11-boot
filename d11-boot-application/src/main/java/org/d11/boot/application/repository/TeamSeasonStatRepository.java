package org.d11.boot.application.repository;

import org.d11.boot.application.model.TeamSeasonStat;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for team season stat entities.
 */
@Repository
public interface TeamSeasonStatRepository extends D11EntityRepository<TeamSeasonStat> {

    /**
     * Finds team season stats for a specific season.
     *
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return Team season stats belonging to the season.
     */
    List<TeamSeasonStat> findBySeasonIdOrderByRanking(@Param("seasonId") Long seasonId);

}
