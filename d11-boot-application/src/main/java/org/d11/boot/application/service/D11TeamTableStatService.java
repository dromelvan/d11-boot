package org.d11.boot.application.service;

import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.d11.boot.application.model.D11TeamTableStat;
import org.d11.boot.application.repository.D11TeamTableStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides D11 team table stat services.
 */
@Service
public class D11TeamTableStatService extends AbstractRepositoryService<D11TeamTableStat, D11TeamTableStatDTO, D11TeamTableStatRepository> {

    /**
     * Creates a new service.
     *
     * @param d11TeamTableStatRepository The repository this service will use.
     */
    @Autowired
    public D11TeamTableStatService(final D11TeamTableStatRepository d11TeamTableStatRepository) {
        super(d11TeamTableStatRepository);
    }

    /**
     * Gets D11 team table stats for a D11 league ordered by D11 match week id, descending, and ranking.
     * This will be the current league table standings.
     *
     * @param d11LeagueId Id for the D11 league for which D11 team table stats will be looked up.
     * @return The current league table standings for the D11 league.
     */
    public List<D11TeamTableStatDTO> findD11TeamTableStatByD11LeagueId(final Long d11LeagueId) {
        final List<D11TeamTableStat> d11TeamTableStats = getJpaRepository()
                .findTop20ByD11LeagueIdOrderByD11MatchWeekIdDescRanking(d11LeagueId);
        return map(d11TeamTableStats);
    }

}
