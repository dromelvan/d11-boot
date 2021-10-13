package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Repository for D11 match entities.
 */
@Repository
public interface D11MatchRepository extends D11EntityRepository<D11Match> {

    /**
     * Gets D11 matches for a specific match week.
     *
     * @param matchWeekId Id for the match week for which D11 matches will be looked up.
     * @return List of D11 matches
     */
    List<D11Match> findByMatchWeekId(@Param("matchWeekId") Long matchWeekId);

    /**
     * Gets D11 match ids for a specific D11 team and a specific season ordered by datetime.
     * It's tricky to do (? or ?) and ? with the brackets by defining the query in the method name so we'll use JPQL.
     *
     * @param d11TeamId Id for the D11 team for which D11 match ids will be looked up.
     * @param seasonId Id for the season for which D11 match ids will be looked up.
     * @return D11 match ids for the D11 team and the season.
     */
    @Query("SELECT d11Match.id FROM D11Match d11Match " +
            "WHERE (d11Match.homeD11Team.id = :d11TeamId OR d11Match.awayD11Team.id = :d11TeamId) AND d11Match.matchWeek.season.id = :seasonId " +
            "ORDER BY d11Match.datetime")
    List<Long> findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(@Param("d11TeamId") Long d11TeamId, @Param("seasonId") Long seasonId);

    /**
     * Gets matches for a specific D11 team and a specific season ordered by datetime.
     * It's tricky to do (? or ?) and ? with the brackets by defining the query in the method name so we'll use JPQL.
     *
     * @param d11TeamId Id for the D11 team for which D11 matches will be looked up.
     * @param seasonId Id for the season for which D11 matches will be looked up.
     * @return D11 matches for the D11 team and the season.
     */
    @Query("SELECT d11Match FROM D11Match d11Match " +
            "WHERE (d11Match.homeD11Team.id = :d11TeamId OR d11Match.awayD11Team.id = :d11TeamId) AND d11Match.matchWeek.season.id = :seasonId " +
            "ORDER BY d11Match.datetime")
    List<D11Match> findByD11TeamIdAndMatchWeekSeasonId(@Param("d11TeamId") Long d11TeamId, @Param("seasonId") Long seasonId);

    /**
     * Finds D11 matches for a specific match week or that have one of a set of statuses.
     *
     * @param matchWeekId Id for the match week for which D11 matches will be looked up.
     * @param status Set of statuses for which D11 matches will be looked up.
     * @return List of D11 matches for the match week and statuses, ordered by datetime.
     */
    List<D11Match> findByMatchWeekIdOrStatusInOrderByDatetime(@Param("matchWeekId") Long matchWeekId, @Param("status") Set<Status> status);

}
