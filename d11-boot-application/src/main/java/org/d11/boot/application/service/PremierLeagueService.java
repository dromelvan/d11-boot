package org.d11.boot.application.service;

import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.repository.PremierLeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides premier league services.
 */
@Service
public class PremierLeagueService extends AbstractRepositoryService<PremierLeague, PremierLeagueDTO, PremierLeagueRepository> {

    /**
     * Creates a new service.
     *
     * @param premierLeagueRepository The repository this service will use.
     */
    @Autowired
    public PremierLeagueService(PremierLeagueRepository premierLeagueRepository) {
        super(premierLeagueRepository);
    }

}
