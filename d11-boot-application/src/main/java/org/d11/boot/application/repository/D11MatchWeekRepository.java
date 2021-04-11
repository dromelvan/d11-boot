package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11MatchWeek;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for D11 match week entities.
 */
@Repository
public interface D11MatchWeekRepository extends D11EntityRepository<D11MatchWeek> {

    /**
     * Gets the first D11 match week with starting date before or on a given date.
     * If the date is today, this D11 match week is the current D11 match week.
     *
     * @param date The D11 match week starting date cutoff date.
     * @return Optional with the first D11 match week with starting date before or on the given cutoff date.
     */
    Optional<D11MatchWeek> findFirstByDateLessThanEqualOrderByDateDesc(LocalDate date);

    /**
     * Gets D11 match weeks for a specific D11 league ordered by date.
     *
     * @param d11LeagueId Id for the D11 league for which D11 match weeks will be looked up.
     * @return D11 match weeks for the D11 league.
     */
    List<D11MatchWeek> findByD11LeagueIdOrderByDate(@Param("d11LeagueId") Long d11LeagueId);

}
