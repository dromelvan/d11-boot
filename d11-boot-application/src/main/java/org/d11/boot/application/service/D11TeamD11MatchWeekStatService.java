package org.d11.boot.application.service;

import org.d11.boot.api.model.D11TeamD11MatchWeekStatDTO;
import org.d11.boot.application.model.D11TeamD11MatchWeekStat;
import org.d11.boot.application.repository.D11TeamD11MatchWeekStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides D11 team D11 match week stat services.
 */
@Service
public class D11TeamD11MatchWeekStatService extends AbstractRepositoryService<D11TeamD11MatchWeekStat, D11TeamD11MatchWeekStatDTO, D11TeamD11MatchWeekStatRepository> {

    /**
     * Creates a new service.
     *
     * @param d11TeamD11MatchWeekStatRepository The repository this service will use.
     */
    @Autowired
    public D11TeamD11MatchWeekStatService(final D11TeamD11MatchWeekStatRepository d11TeamD11MatchWeekStatRepository) {
        super(d11TeamD11MatchWeekStatRepository);
    }

}
