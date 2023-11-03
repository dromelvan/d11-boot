package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.repository.D11MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * D11 Match service.
 */
@Service
public class D11MatchService extends RepositoryService<D11Match, D11MatchRepository> {

    /**
     * Creates a new D11 match service.
     *
     * @param d11MatchRepository The repository the service will use.
     */
    @Autowired
    public D11MatchService(final D11MatchRepository d11MatchRepository) {
        super(D11Match.class, d11MatchRepository);
    }

}
