package org.d11.boot.application.service.api;

import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.model.jpa.PremierLeague;
import org.d11.boot.application.repository.PremierLeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides premier league services.
 */
@Service
public class PremierLeagueService extends ApiRepositoryService<PremierLeague, PremierLeagueDTO, PremierLeagueRepository> {

    /**
     * Creates a new service.
     *
     * @param premierLeagueRepository The repository this service will use.
     */
    @Autowired
    public PremierLeagueService(final PremierLeagueRepository premierLeagueRepository) {
        super(premierLeagueRepository);
    }

}
