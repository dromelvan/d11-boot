package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Season service.
 */
@Service
public class SeasonService extends RepositoryService<Season, SeasonRepository> {

    /**
     * Creates a new season service.
     *
     * @param seasonRepository The repository the service will use.
     */
    @Autowired
    public SeasonService(final SeasonRepository seasonRepository) {
        super(Season.class, seasonRepository);
    }

    /**
     * Gets a list of all seasons ordered by descending date.
     *
     * @return List of all seasons ordered by descending date.
     */
    public List<Season> getSeasons() {
        return getJpaRepository().findByOrderByDateDesc();
    }

    /**
     * Gets the current season.
     *
     * @return The current season.
     */
    public Season getCurrentSeason() {
        return getJpaRepository().findFirstByOrderByDateDesc()
                .orElseThrow(() -> new NotFoundException("Current season does not exist."));
    }

}
