package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.repository.D11TeamSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * D11 team season stat service.
 */
@Service
public class D11TeamSeasonStatService extends RepositoryService<D11TeamSeasonStat, D11TeamSeasonStatRepository> {

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
            throw new BadRequestException("seasonId", "must be positive");
        }

        return getJpaRepository().findBySeasonIdOrderByRanking(seasonId);
    }

}
