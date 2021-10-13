package org.d11.boot.application.repository;

import org.d11.boot.application.model.MatchWeek;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
     * Gets match weeks for a specific season ordered by date.
     *
     * @param seasonId Id for the season for which match weeks will be looked up.
     * @return Match weeks for the season.
     */
    @EntityGraph(attributePaths = { "premierLeagueLeader", "d11LeagueLeader", "mostValuablePlayer", "mostValuablePlayer.player", "matches" })
    List<MatchWeek> findBySeasonIdOrderByDate(@Param("seasonId") Long seasonId);

    /**
     * Updates previous rankings for a match week and its season.
     *
     * @param matchWeekId Id for the match week for which previous rankings will be updated.
     */
    @Modifying
    @Transactional
    @Query(value = "CALL {h-schema}update_previous_rankings_by_match_week_id(:matchWeekId)", nativeQuery = true)
    void updatePreviousRankingsByMatchWeekId(@Param("matchWeekId") Long matchWeekId);

}
