package org.d11.boot.application.service;

import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.repository.D11MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides D11 match services.
 */
@Service
public class D11MatchService extends AbstractRepositoryService<D11Match, D11MatchDTO, D11MatchRepository> {

    /**
     * Creates a new service.
     *
     * @param d11MatchRepository The repository this service will use.
     */
    @Autowired
    public D11MatchService(final D11MatchRepository d11MatchRepository) {
        super(d11MatchRepository);
    }

    /**
     * Gets D11 match ids for a specific D11team and a specific season.
     *
     * @param d11TeamId Id for the D11 team for which D11 match ids will be looked up.
     * @param seasonId Id for the season for which D11 match ids will be looked up.
     * @return D11 match ids for the D11team and season.
     */
    public List<Long> findD11MatchByD11TeamIdAndSeasonId(final long d11TeamId, final long seasonId) {
        return getJpaRepository().findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(d11TeamId, seasonId);
    }

}
