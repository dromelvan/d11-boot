package org.d11.boot.application.service.api;

import org.d11.boot.api.model.D11TeamMatchWeekStatDTO;
import org.d11.boot.application.model.jpa.D11TeamMatchWeekStat;
import org.d11.boot.application.repository.D11TeamMatchWeekStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides D11 team match week stat services.
 */
@Service
public class D11TeamMatchWeekStatService extends ApiRepositoryService<D11TeamMatchWeekStat, D11TeamMatchWeekStatDTO, D11TeamMatchWeekStatRepository> {

    /**
     * Creates a new service.
     *
     * @param d11TeamMatchWeekStatRepository The repository this service will use.
     */
    @Autowired
    public D11TeamMatchWeekStatService(final D11TeamMatchWeekStatRepository d11TeamMatchWeekStatRepository) {
        super(d11TeamMatchWeekStatRepository);
    }

}
