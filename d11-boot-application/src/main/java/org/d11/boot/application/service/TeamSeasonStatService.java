package org.d11.boot.application.service;

import org.d11.boot.api.model.TeamSeasonStatDTO;
import org.d11.boot.application.model.TeamSeasonStat;
import org.d11.boot.application.repository.TeamSeasonStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides team season stat services.
 */
@Service
public class TeamSeasonStatService extends AbstractRepositoryService<TeamSeasonStat, TeamSeasonStatDTO, TeamSeasonStatRepository> {

    /**
     * Creates a new service.
     *
     * @param teamSeasonStatRepository The repository this service will use.
     */
    public TeamSeasonStatService(final TeamSeasonStatRepository teamSeasonStatRepository) {
        super(teamSeasonStatRepository);
    }

    /**
     * Gets the team season stats for a season ordered by ranking.
     * This will be the current league table standings.
     *
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return The current league table standings for the season.
     */
    public List<TeamSeasonStatDTO> findTeamSeasonStatBySeasonId(final Long seasonId) {
        final List<TeamSeasonStat> teamSeasonStats = getJpaRepository().findBySeasonIdOrderByRanking(seasonId);
        return map(teamSeasonStats);
    }

}
