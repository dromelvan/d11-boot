package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.repository.D11TeamSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * D11 team season stat service.
 */
@Service
public class D11TeamSeasonStatService extends RepositoryService<D11TeamSeasonStat, D11TeamSeasonStatRepository> {

    /**
     * Season id property name.
     */
    private static final String SEASON_ID = "seasonId";

    /**
     * Must be positive error value.
     */
    private static final String MUST_BE_POSITIVE = "must be positive";

    /**
     * Creates a new D11 team season stat service.
     *
     * @param d11TeamSeasonStatRepository The repository the service will use.
     */
    @Autowired
    public D11TeamSeasonStatService(final D11TeamSeasonStatRepository d11TeamSeasonStatRepository) {
        super(D11TeamSeasonStat.class, d11TeamSeasonStatRepository);
    }

    /**
     * Get D11 team season stats by season id ordered by ranking.
     *
     * @param seasonId The season id.
     * @return D11 team season stats by season id ordered by ranking.
     */
    public List<D11TeamSeasonStat> getBySeasonId(final Long seasonId) {
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        return getJpaRepository().findBySeasonIdOrderByRanking(seasonId);
    }

    /**
     * Get D11 team season stat by D11 team id and season id.
     *
     * @param d11TeamId The D11 team id.
     * @param seasonId  The season id.
     * @return D11 team season stat by D11 team id and season id.
     */
    public D11TeamSeasonStat getByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        if (d11TeamId == null || d11TeamId <= 0) {
            throw new BadRequestException("d11TeamId", MUST_BE_POSITIVE);
        }
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByD11TeamIdAndSeasonId(d11TeamId, seasonId)
                .orElseThrow(() -> new NotFoundException(d11TeamId, D11TeamSeasonStat.class));
    }

}
