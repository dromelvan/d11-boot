package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Match week service.
 */
@Service
public class MatchWeekService extends RepositoryService<MatchWeek, MatchWeekRepository> {

    /**
     * Creates a new match week service.
     *
     * @param matchWeekRepository The repository the service will use.
     */
    @Autowired
    public MatchWeekService(final MatchWeekRepository matchWeekRepository) {
        super(MatchWeek.class, matchWeekRepository);
    }

    /**
     * Gets the current match week.
     *
     * @return The current match week.
     */
    public MatchWeek getCurrentMatchWeek() {
        final LocalDate localDate = LocalDate.now();
        final Optional<MatchWeek> optional =
                getJpaRepository().findFirstBySeasonStatusOrderByDateAsc(Status.PENDING)
                        .or(() -> getJpaRepository().findFirstByDateLessThanEqualOrderByDateDesc(localDate));

        return optional.orElseThrow(() -> new ConflictException("Current match week does not exist"));
    }

    /**
     * Gets a list of match weeks by season ordered by date.
     *
     * @param seasonId The season id.
     * @return List of all match weeks ordered by date.
     */
    public List<MatchWeek> getBySeasonId(final Long seasonId) {
        return getJpaRepository().findBySeasonIdOrderByDate(seasonId);
    }

}
