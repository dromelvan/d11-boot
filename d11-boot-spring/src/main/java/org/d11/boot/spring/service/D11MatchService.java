package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.D11MatchRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * D11 Match service.
 */
@Service
public class D11MatchService extends RepositoryService<D11Match, D11MatchRepository> {

    /**
     * A set of statuses that means a D11 match is current.
     */
    private static final Set<Status> CURRENT_STATUSES = Set.of(Status.ACTIVE, Status.FULL_TIME);

    /**
     * Match week service for finding the current match week.
     */
    private final MatchWeekService matchWeekService;

    /**
     * Creates a new D11 match service.
     *
     * @param d11MatchRepository The repository the service will use.
     * @param matchWeekService The match week service the service will use.
     */
    @Autowired
    public D11MatchService(final D11MatchRepository d11MatchRepository, final MatchWeekService matchWeekService) {
        super(D11Match.class, d11MatchRepository);
        this.matchWeekService = matchWeekService;
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

    /**
     * Gets current D11 matches.
     *
     * @return A list of current D11 matches sorted by datetime.
     */
    public List<D11Match> getCurrentD11Matches() {
        final MatchWeek currentMatchWeek = this.matchWeekService.getCurrentMatchWeek();
        return getJpaRepository().findByMatchWeekIdOrStatusInOrderByDatetime(currentMatchWeek.getId(),
                                                                             CURRENT_STATUSES);
    }

}
