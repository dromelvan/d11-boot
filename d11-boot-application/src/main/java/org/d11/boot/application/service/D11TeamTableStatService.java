package org.d11.boot.application.service;

import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.d11.boot.application.model.D11TeamTableStat;
import org.d11.boot.application.repository.D11TeamTableStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
