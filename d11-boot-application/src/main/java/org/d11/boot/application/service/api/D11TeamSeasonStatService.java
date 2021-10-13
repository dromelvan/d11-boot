package org.d11.boot.application.service.api;

import org.d11.boot.api.model.D11TeamSeasonStatDTO;
import org.d11.boot.application.model.D11TeamSeasonStat;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides D11 team season stat services.
 */
@Service
public class D11TeamSeasonStatService extends ApiRepositoryService<D11TeamSeasonStat, D11TeamSeasonStatDTO, D11TeamSeasonStatRepository> {

    /**
     * Creates a new service.
     *
     * @param d11TeamSeasonStatRepository The repository this service will use.
     */
    public D11TeamSeasonStatService(final D11TeamSeasonStatRepository d11TeamSeasonStatRepository) {
        super(d11TeamSeasonStatRepository);
    }

    /**
     * Gets the D11 team season stats for a season ordered by ranking.
     * This will be the current D11 league table standings.
     *
     * @param seasonId Id for the season for which D11 team season stats will be looked up.
     * @return The current D11 league table standings for the season.
     */
    public List<D11TeamSeasonStatDTO> findD11TeamSeasonStatBySeasonId(final Long seasonId) {
        final List<D11TeamSeasonStat> d11TeamSeasonStats = getJpaRepository().findBySeasonIdOrderByRanking(seasonId);
        return map(d11TeamSeasonStats);
    }

    /**
     * Gets D11 team season stats for a specific D11 team and a specific season.
     *
     * @param d11TeamId If for the D11 team for which D11 team season stats will be looked up.
     * @param seasonId Id for the season for which D11 team season stats will be looked up.
     * @return D11 team season stat for the team and the season.
     */
    public D11TeamSeasonStatDTO findD11TeamSeasonStatByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        final Optional<D11TeamSeasonStat> optional = getJpaRepository().findByD11TeamIdAndSeasonId(d11TeamId, seasonId);
        return mapIfFound(optional.orElse(null));
    }

}
