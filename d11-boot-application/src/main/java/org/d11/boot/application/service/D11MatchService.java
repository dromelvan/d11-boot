package org.d11.boot.application.service;

import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.repository.D11MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
