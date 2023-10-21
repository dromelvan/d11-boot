package org.d11.boot.spring.repository;


import org.d11.boot.spring.model.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Match entities.
 */
@Repository
public interface MatchRepository extends D11EntityRepository<Match> {

    /**
     * Finds a match by WhoScored id.
     *
     * @param whoscoredId The WhoScored id.
     * @return Match with the WhoScored id.
     */
    Optional<Match> findByWhoscoredId(@Param("whoscoredId") Integer whoscoredId);

    /**
     * Gets matches by team and season ordered by datetime.
     * It's tricky to do (? or ?) and ? with the brackets by defining the query in the method name, so we'll use JPQL.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return Match ids for the team and the season.
     */
    @Query("""
           SELECT match FROM Match match
           WHERE (match.homeTeam.id = :teamId OR match.awayTeam.id = :teamId) AND match.matchWeek.season.id = :seasonId
           ORDER BY match.datetime
           """)
    List<Match> findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(@Param("teamId") Long teamId,
                                                                @Param("seasonId") Long seasonId);

}
