package org.d11.boot.application.repository;

import org.d11.boot.application.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for match entities.
 */
@Repository
public interface MatchRepository extends D11EntityRepository<Match> {

    /**
     * Gets match ids for a specific team and a specific season ordered by datetime.
     * It's tricky to do (? or ?) and ? with the brackets by defining the query in the method name so we'll use JPQL.
     *
     * @param teamId Id for the team for which match ids will be looked up.
     * @param seasonId Id for the season for which match ids will be looked up.
     * @return Match ids for the team and the season.
     */
    @Query("SELECT match.id FROM Match match " +
            "WHERE (match.homeTeam.id = :teamId OR match.awayTeam.id = :teamId) AND match.matchWeek.season.id = :seasonId " +
            "ORDER BY match.datetime")
    List<Long> findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(@Param("teamId") Long teamId, @Param("seasonId") Long seasonId);

}
