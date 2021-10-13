package org.d11.boot.application.service.api;

import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.application.model.D11League;
import org.d11.boot.application.repository.D11LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides D11 league services.
 */
@Service
public class D11LeagueService extends ApiRepositoryService<D11League, D11LeagueDTO, D11LeagueRepository> {

    /**
     * Creates a new service.
     *
     * @param d11LeagueRepository The repository this service will use.
     */
    @Autowired
    public D11LeagueService(final D11LeagueRepository d11LeagueRepository) {
        super(d11LeagueRepository);
    }

}
