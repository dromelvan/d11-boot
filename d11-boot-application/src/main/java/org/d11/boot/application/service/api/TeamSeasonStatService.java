package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TeamSeasonStatDTO;
import org.d11.boot.application.model.TeamSeasonStat;
import org.d11.boot.application.repository.TeamSeasonStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Provides team season stat services.
 */
@Service
public class TeamSeasonStatService extends ApiRepositoryService<TeamSeasonStat, TeamSeasonStatDTO, TeamSeasonStatRepository> {

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

    /**
     * Gets team season stats for a specific team and a specific season.
     *
     * @param teamId If for the team for which team season stats will be looked up.
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return Team season stat for the team and the season.
     */
    public TeamSeasonStatDTO findTeamSeasonStatByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        final Optional<TeamSeasonStat> optional = getJpaRepository().findByTeamIdAndSeasonId(teamId, seasonId);
        return mapIfFound(optional.orElse(null));
    }
}
