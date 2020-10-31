package org.d11.boot.application.service;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Provides match week services.
 */
@Service
public class MatchWeekService extends AbstractRepositoryService<MatchWeek, MatchWeekDTO, MatchWeekRepository> {

    /**
     * Creates a new service.
     *
     * @param matchWeekRepository The repository this service will use.
     */
    @Autowired
    public MatchWeekService(final MatchWeekRepository matchWeekRepository) {
        super(matchWeekRepository);
    }

    /**
     * Gets the current match week.
     *
     * @return The current match week DTO.
     */
    public MatchWeekDTO findCurrentMatchWeek() {
        final LocalDate localDate = LocalDate.now();
        final Optional<MatchWeek> optional = getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(localDate);
        return find(optional);
    }

}
