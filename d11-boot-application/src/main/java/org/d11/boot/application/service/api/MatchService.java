package org.d11.boot.application.service.api;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.model.MatchesByDateDTO;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.util.MatchesByDateMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Provides match services.
 */
@Service
public class MatchService extends ApiRepositoryService<Match, MatchDTO, MatchRepository> {

    /**
     * A set of statuses that means a match is current.
     */
    private final Set<Status> currentStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);
    /**
     * Converts a list of matches to a map of date to matches mappings.
     */
    private final MatchesByDateMapperConverter matchesByDateMapperConverter = new MatchesByDateMapperConverter();
    /**
     * Service for looking up current match week.
     */
    private final MatchWeekService matchWeekService;

    /**
     * Creates a new service.
     *
     * @param matchRepository The repository this service will use.
     * @param matchWeekService Service for looking up current match week.
     */
    @Autowired
    public MatchService(final MatchRepository matchRepository, final MatchWeekService matchWeekService) {
        super(matchRepository);
        this.matchWeekService = matchWeekService;
    }

    /**
     * Gets match ids for a specific team and a specific season.
     *
     * @param teamId Id for the team for which match ids will be looked up.
     * @param seasonId Id for the season for which match ids will be looked up.
     * @return Match ids for the team and season.
     */
    public List<Long> findMatchByTeamIdAndSeasonId(final long teamId, final long seasonId) {
        return getJpaRepository().findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(teamId, seasonId);
    }

    /**
     * Gets current matches.
     *
     * @return A set of current matches mapped and sorted by datetime.
     */
    public MatchesByDateDTO findCurrentMatches() {
        final MatchWeekDTO matchWeek = this.matchWeekService.findCurrentMatchWeek();
        final List<Match> matches = getJpaRepository().findByMatchWeekIdOrStatusInOrderByDatetime(matchWeek.getId(), this.currentStatuses);

        return new MatchesByDateDTO().matches(this.matchesByDateMapperConverter.convert(matches));
    }

}
