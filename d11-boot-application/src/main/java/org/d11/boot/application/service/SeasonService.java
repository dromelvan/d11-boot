package org.d11.boot.application.service;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides season services.
 */
@Service
public class SeasonService extends AbstractRepositoryService<Season, SeasonDTO, SeasonRepository> {

    /**
     * Creates a new service.
     *
     * @param seasonRepository The repository this service will use.
     */
    @Autowired
    public SeasonService(final SeasonRepository seasonRepository) {
        super(seasonRepository);
    }

    /**
     * Gets all seasons ordered by date, descending.
     *
     * @return List of season DTOs.
     */
    public List<SeasonDTO> findAllSeasons() {
        final List<Season> seasons = getJpaRepository().findByOrderByDateDesc();
        return map(seasons);
    }

    /**
     * Gets the current season.
     *
     * @return The current season DTO.
     */
    public SeasonDTO findCurrentSeason() {
        final Optional<Season> optional = getJpaRepository().findFirstByOrderByDateDesc();
        return find(optional);
    }

}
