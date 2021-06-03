package org.d11.boot.application.service;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.api.model.SeasonSummaryDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.projection.EntityId;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Finds a season summary by id.
     *
     * @param seasonId The id of the season.
     * @return Season summary DTO for the season with the specified.
     * @throws NotFoundException If no season with provided id was found.
     */
    public SeasonSummaryDTO findSeasonSummaryById(final long seasonId) {
        final Optional<Season> optional = getJpaRepository().findById(seasonId);
        return optional
                .map(season -> map(season, SeasonSummaryDTO.class))
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Gets all seasons ordered by date, descending.
     *
     * @return List of season DTOs.
     */
    public List<Long> findAllSeasons() {
        final List<EntityId> seasonIds = getJpaRepository().findByOrderByDateDesc();
        return seasonIds.stream().map(EntityId::getId).collect(Collectors.toList());
    }

    /**
     * Gets the current season.
     *
     * @return The current season DTO.
     */
    public SeasonDTO findCurrentSeason() {
        final Optional<Season> optional = getJpaRepository().findFirstByOrderByDateDesc();
        return mapIfFound(optional.orElse(null));
    }

}
