package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.repository.D11MatchRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Get D11 matches by match week id ordered by datetime and id.
     *
     * @param matchWeekId The match week id.
     * @return Matches by match week id ordered by datetime and id.
     */
    public List<D11Match> getByMatchWeekId(final Long matchWeekId) {
        if (matchWeekId == null || matchWeekId <= 0) {
            throw new BadRequestException("matchWeekId", "must be positive");
        }

        return getJpaRepository().findByMatchWeekIdOrderByDatetimeAscIdAsc(matchWeekId);
    }

}
