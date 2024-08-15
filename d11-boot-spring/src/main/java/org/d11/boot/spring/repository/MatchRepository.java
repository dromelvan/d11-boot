package org.d11.boot.spring.repository;


import org.d11.boot.spring.model.Match;
import org.d11.boot.util.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Repository for Match entities.
 */
@Repository
public interface MatchRepository extends D11EntityRepository<Match> {

    /**
     * Overrides parent method with Match.MATCH_ASSOCIATIONS named entity graph applied.
     *
     * @param id Match id.
     * @return Optional with the match or empty optional if none was found.
     */
    @Override
    @EntityGraph(Match.MATCH_ASSOCIATIONS)
    Optional<Match> findById(Long id);

    /**
     * Finds a match by WhoScored id.
     *
     * @param whoscoredId The WhoScored id.
     * @return Match with the WhoScored id.
     */
    Optional<Match> findByWhoscoredId(@Param("whoscoredId") Integer whoscoredId);

    /**
     * Gets matches by match week id or status in a set, ordered by datetime.
     *
     * @param matchWeekId The match week id.
     * @param status      The set of statuses.
     * @return Matches for the match week or statuses.
     */
    List<Match> findByMatchWeekIdOrStatusInOrderByDatetime(@Param("matchWeekId") Long matchWeekId,
                                                           @Param("status") Set<Status> status);

    /**
     * Gets matches by team and season ordered by datetime.
     * It's tricky to do (? or ?) and ? with the brackets by defining the query in the method name, so we'll use JPQL.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return Matches for the team and the season.
     */
    @Query("""
           SELECT match FROM Match match
           WHERE (match.homeTeam.id = :teamId OR match.awayTeam.id = :teamId) AND match.matchWeek.season.id = :seasonId
           ORDER BY match.datetime
           """)
    List<Match> findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(@Param("teamId") Long teamId,
                                                                @Param("seasonId") Long seasonId);

    /**
     * Gets matches by match week ordered by datetime and id.
     *
     * @param matchWeekId The match week id.
     * @return Matches for the match week.
     */
    List<Match> findByMatchWeekIdOrderByDatetimeAscIdAsc(@Param("matchWeekId") Long matchWeekId);

}
