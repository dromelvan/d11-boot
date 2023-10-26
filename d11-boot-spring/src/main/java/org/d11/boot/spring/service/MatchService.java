package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Match service.
 */
@Service
public class MatchService extends RepositoryService<Match, MatchRepository> {

    /**
     * Creates a new match service.
     *
     * @param matchRepository The repository the service will use.
     */
    @Autowired
    public MatchService(final MatchRepository matchRepository) {
        super(Match.class, matchRepository);
    }

    /**
     * Gets a list of matches by team and season ordered by datetime.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return List of all match weeks ordered by date.
     */
    public List<Match> getByTeamIdAmdSeasonId(final Long teamId, final Long seasonId) {
        return getJpaRepository().findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(teamId, seasonId);
    }

}
