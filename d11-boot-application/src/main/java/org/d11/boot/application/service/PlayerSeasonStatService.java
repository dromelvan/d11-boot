package org.d11.boot.application.service;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides player season stat services.
 */
@Service
public class PlayerSeasonStatService extends AbstractRepositoryService<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatRepository> {

    /**
     * Creates a new service.
     *
     * @param playerSeasonStatRepository The repository this service will use.
     */
    @Autowired
    public PlayerSeasonStatService(final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(playerSeasonStatRepository);
    }

    /**
     * Gets player season stats for a specific team and season ordered by position and points descending.
     *
     * @param teamId Id for the team for which player season stats will be looked up.
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stat DTOs for the team and season.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByTeamIdAndSeasonId(final long teamId, final long seasonId) {
        final List<PlayerSeasonStat> playerSeasonStats = getJpaRepository()
                .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscPointsDesc(teamId, seasonId);
        return map(playerSeasonStats);
    }

    /**
     * Gets player season stats for a specific D11 team and season ordered by position and points descending.
     *
     * @param d11TeamId Id for the D11 team for which player season stats will be looked up.
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stat DTOs for the D11 team and season.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByD11TeamIdAndSeasonId(final long d11TeamId, final long seasonId) {
        final List<PlayerSeasonStat> playerSeasonStats = getJpaRepository()
                .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscPointsDesc(d11TeamId, seasonId);
        return map(playerSeasonStats);
    }

}
