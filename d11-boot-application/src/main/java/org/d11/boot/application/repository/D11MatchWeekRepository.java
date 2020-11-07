package org.d11.boot.application.repository;

import org.d11.boot.application.model.D11MatchWeek;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    //  @Override
    //  //@EntityGraph(attributePaths = { "d11Matches" })
    //  Optional<D11MatchWeek> findById(Long id);

}
