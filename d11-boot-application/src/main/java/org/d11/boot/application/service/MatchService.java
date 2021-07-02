package org.d11.boot.application.service;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.api.model.MatchesByDateDTO;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.repository.MatchRepository;
import org.d11.boot.application.util.MatchesByDateMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Provides match services.
 */
@Service
public class MatchService extends AbstractRepositoryService<Match, MatchDTO, MatchRepository> {

    /**
     * Converts a list of matches to a map of date to matches mappings.
     */
    private final MatchesByDateMapperConverter matchesByDateMapperConverter = new MatchesByDateMapperConverter();

    /**
     * Creates a new service.
     *
     * @param matchRepository The repository this service will use.
     */
    @Autowired
    public MatchService(final MatchRepository matchRepository) {
        super(matchRepository);
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
        final List<Match> matches = getJpaRepository().findCurrent();
        final Map<String, List<Long>> matchesByDate = this.matchesByDateMapperConverter.convert(matches);
        final MatchesByDateDTO matchesByDateDTO = new MatchesByDateDTO();
        matchesByDateDTO.setMatches(matchesByDate);
        return matchesByDateDTO;
    }

}
