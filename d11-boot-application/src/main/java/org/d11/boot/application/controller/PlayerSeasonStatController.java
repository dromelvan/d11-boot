package org.d11.boot.application.controller;

import org.d11.boot.api.PlayerSeasonStatsApi;
import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.application.service.PlayerSeasonStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the PlayerSeasonStatsApi and provides player season stat endpoints.
 */
@RestController
public class PlayerSeasonStatController extends AbstractRepositoryServiceController<PlayerSeasonStatDTO, PlayerSeasonStatService> implements PlayerSeasonStatsApi {

    /**
     * Creates a new controller.
     *
     * @param playerSeasonStatService The repository service this
     */
    public PlayerSeasonStatController(final PlayerSeasonStatService playerSeasonStatService) {
        super(playerSeasonStatService);
    }

    @Override
    public ResponseEntity<PlayerSeasonStatDTO> findPlayerSeasonStatById(final Long playerSeasonStatId) {
        return findById(playerSeasonStatId);
    }

    @Override
    public ResponseEntity<List<PlayerSeasonStatDTO>> findPlayerSeasonStatByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        final List<PlayerSeasonStatDTO> playerSeasonStats = getRepositoryService().findPlayerSeasonStatByTeamIdAndSeasonId(teamId, seasonId);
        return ResponseEntity.ok(playerSeasonStats);
    }

    @Override
    public ResponseEntity<List<PlayerSeasonStatDTO>> findPlayerSeasonStatByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        final List<PlayerSeasonStatDTO> playerSeasonStats = getRepositoryService().findPlayerSeasonStatByD11TeamIdAndSeasonId(d11TeamId, seasonId);
        return ResponseEntity.ok(playerSeasonStats);
    }

}
