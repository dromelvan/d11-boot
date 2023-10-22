package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * Gets a list of all match weeks ordered by date.
     *
     * @param seasonId The season id.
     * @return List of all match weeks ordered by date.
     */
    public List<MatchWeek> getBySeasonId(final Long seasonId) {
        return getJpaRepository().findBySeasonIdOrderByDate(seasonId);
    }

}
