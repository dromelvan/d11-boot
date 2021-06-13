package org.d11.boot.application.service;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
