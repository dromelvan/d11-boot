package org.d11.boot.application.repository;

import org.d11.boot.application.model.MatchWeek;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for match week entities.
 */
@Repository
public interface MatchWeekRepository extends D11EntityRepository<MatchWeek> {

    /**
     * Gets the first match week with starting date before or on a given date.
     * If the date is today, this match week is the current match week.
     *
     * @param date The match week starting date cutoff date.
     * @return Optional with the first match week with starting date before or on the given cutoff date.
     */
    Optional<MatchWeek> findFirstByDateLessThanEqualOrderByDateDesc(LocalDate date);

    /**
     * Gets match weeks for a specific Premier League ordered by date.
     *
     * @param premierLeagueId Id for the Premier League for which match weeks will be looked up.
     * @return Match weeks for the Premier League.
     */
    @EntityGraph(attributePaths = { "leagueLeader", "mostValuablePlayer", "mostValuablePlayer.player", "d11MatchWeek", "matches" })
    List<MatchWeek> findByPremierLeagueIdOrderByDate(@Param("premierLeagueId") Long premierLeagueId);

}
