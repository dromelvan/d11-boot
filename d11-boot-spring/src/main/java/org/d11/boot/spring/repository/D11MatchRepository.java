package org.d11.boot.spring.repository;


import org.d11.boot.spring.model.D11Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for D11Match entities.
 */
@Repository
public interface D11MatchRepository extends D11EntityRepository<D11Match> {

    /**
     * Gets D11 matches by D11 team and season ordered by datetime.
     * It's tricky to do (? or ?) and ? with the brackets by defining the query in the method name, so we'll use JPQL.
     *
     * @param d11TeamId The D11 team id.
     * @param seasonId  The season id.
     * @return D11 match ids for the D11 team and the season.
     */
    @Query("""
           SELECT d11Match FROM D11Match d11Match
           WHERE (d11Match.homeD11Team.id = :d11TeamId OR d11Match.awayD11Team.id = :d11TeamId)
             AND d11Match.matchWeek.season.id = :seasonId
           ORDER BY d11Match.datetime
           """)
    List<D11Match> findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(@Param("d11TeamId") Long d11TeamId,
                                                                      @Param("seasonId") Long seasonId);

}
