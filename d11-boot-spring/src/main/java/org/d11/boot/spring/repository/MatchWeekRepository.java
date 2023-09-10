package org.d11.boot.spring.repository;


import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.util.Status;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repository for MatchWeek entities.
 */
@Repository
public interface MatchWeekRepository extends D11EntityRepository<MatchWeek> {

    /**
     * Gets the first match week with starting date before or on a given date.
     * If the date is today, this match week is the current match week for an active season.
     *
     * @param date The match week starting date cutoff date.
     * @return Optional with the first match week with starting date before or on the given cutoff date.
     */
    Optional<MatchWeek> findFirstByDateLessThanEqualOrderByDateDesc(LocalDate date);

    /**
     * Gets the first match week with starting date after a given date.
     *
     * @param date The match week starting date cutoff date.
     * @return Optional with the first match week with starting date after the given cutoff date.
     */
    Optional<MatchWeek> findFirstByDateGreaterThanOrderByDateAsc(LocalDate date);

    /**
     * Gets the first match week of a season with the provided status.
     * This match week is the current match week for a pending season.
     *
     * @param status The status of the season.
     * @return Optional with the first match week of a season with the provided status.
     */
    Optional<MatchWeek> findFirstBySeasonStatusOrderByDateAsc(Status status);

}
