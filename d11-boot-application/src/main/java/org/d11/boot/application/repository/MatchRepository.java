package org.d11.boot.application.repository;

import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    /**
     * Finds matches for a specific match or that have one of a set of statuses.
     *
     * @param matchWeekId Id for the match week for which matches will be looked up.
     * @param status Set of statuses for which matches will be looked up.
     * @return List of matches for the match week and statuses, ordered by datetime.
     */
    List<Match> findByMatchWeekIdOrStatusInOrderByDatetime(@Param("matchWeekId") Long matchWeekId, @Param("status") Set<Status> status);

}
