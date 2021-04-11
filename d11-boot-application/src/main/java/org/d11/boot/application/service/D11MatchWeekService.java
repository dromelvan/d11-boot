package org.d11.boot.application.service;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.application.model.D11MatchWeek;
import org.d11.boot.application.repository.D11MatchWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Provides D11 match week services.
 */
@Service
public class D11MatchWeekService extends AbstractRepositoryService<D11MatchWeek, D11MatchWeekDTO, D11MatchWeekRepository> {

    /**
     * Creates a new service.
     *
     * @param d11MatchWeekRepository The repository this service will use.
     */
    @Autowired
    public D11MatchWeekService(final D11MatchWeekRepository d11MatchWeekRepository) {
        super(d11MatchWeekRepository);
    }

    /**
     * Gets the current D11 match week.
     *
     * @return The current D11 match week DTO.
     */
    public D11MatchWeekDTO findCurrentD11MatchWeek() {
        final LocalDate localDate = LocalDate.now();
        final Optional<D11MatchWeek> optional = getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(localDate);
        return find(optional);
    }

    /**
     * Gets D11 match weeks for a specific D11 league ordered by date.
     *
     * @param d11LeagueId Id for the D11 league for which D11 match weeks will be looked up.
     * @return D11 match week DTOs for the D11 league.
     */
    public List<D11MatchWeekDTO> findD11MatchWeekByD11LeagueId(final long d11LeagueId) {
        final List<D11MatchWeek> d11MatchWeeks = getJpaRepository().findByD11LeagueIdOrderByDate(d11LeagueId);
        return map(d11MatchWeeks);
    }

}
