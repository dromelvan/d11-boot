package org.d11.boot.application.service;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides match services.
 */
@Service
public class MatchService extends AbstractRepositoryService<Match, MatchDTO, MatchRepository> {

    /**
     * Creates a new service.
     *
     * @param matchRepository The repository this service will use.
     */
    @Autowired
    public MatchService(final MatchRepository matchRepository) {
        super(matchRepository);
    }

}
