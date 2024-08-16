package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Match service.
 */
@Service
public class MatchService extends RepositoryService<Match, MatchRepository> {

    /**
     * A set of statuses that means a match is current.
     */
    private static final Set<Status> CURRENT_STATUSES = Set.of(Status.ACTIVE, Status.FULL_TIME);

    /**
     * Match week service for finding the current match week.
     */
    private final MatchWeekService matchWeekService;

    /**
     * Creates a new match service.
     *
     * @param matchRepository  The repository the service will use.
     * @param matchWeekService The match week service the service will use.
     */
    @Autowired
    public MatchService(final MatchRepository matchRepository,
                        final MatchWeekService matchWeekService) {
        super(Match.class, matchRepository);
        this.matchWeekService = matchWeekService;
    }

    /**
     * Gets a list of matches by team and season ordered by datetime.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return List of all match weeks ordered by date.
     */
    public List<Match> getByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        return getJpaRepository().findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(teamId, seasonId);
    }

    /**
     * Get matches by match week id ordered by datetime and id.
     *
     * @param matchWeekId The match week id.
     * @return Matches by match week id ordered by datetime and id.
     */
    public List<Match> getByMatchWeekId(final Long matchWeekId) {
        if (matchWeekId == null || matchWeekId <= 0) {
            throw new BadRequestException("matchWeekId", "must be positive");
        }

        return getJpaRepository().findByMatchWeekIdOrderByDatetimeAscIdAsc(matchWeekId);
    }

    /**
     * Gets current matches.
     *
     * @return A list of current matches sorted by datetime.
     */
    public List<Match> getCurrentMatches() {
        final MatchWeek currentMatchWeek = this.matchWeekService.getCurrentMatchWeek();
        return getJpaRepository().findByMatchWeekIdOrStatusInOrderByDatetime(currentMatchWeek.getId(),
                                                                             CURRENT_STATUSES);
    }

}
