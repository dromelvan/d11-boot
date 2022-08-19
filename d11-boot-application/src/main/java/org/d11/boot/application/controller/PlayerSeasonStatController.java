package org.d11.boot.application.controller;

import org.d11.boot.api.PlayerSeasonStatsApi;
import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.api.model.TeamPlayerSeasonStatsDTO;
import org.d11.boot.application.service.api.PlayerSeasonStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResponseEntity<List<PlayerSeasonStatDTO>> findPlayerSeasonStatByPlayerId(final Long playerId) {
        final List<PlayerSeasonStatDTO> playerSeasonStats = getRepositoryService().findPlayerSeasonStatByPlayerId(playerId);
        return ResponseEntity.ok(playerSeasonStats);
    }

    @Override
    public ResponseEntity<List<PlayerSeasonStatDTO>> findPlayerSeasonStatBySeasonId(final Long seasonId,
                                                                                    final Integer page,
                                                                                    final Boolean available,
                                                                                    final Boolean unavailable,
                                                                                    final List<Long> positionIds) {
        final List<PlayerSeasonStatDTO> playerSeasonStats =
                getRepositoryService().findPlayerSeasonStatBySeasonId(seasonId,
                                                                      page,
                                                                      available == null || available,
                                                                      unavailable == null || unavailable,
                                                                      positionIds == null ? new ArrayList<>() : positionIds);
        return ResponseEntity.ok(playerSeasonStats);
    }

    @Override
    public ResponseEntity<PlayerSeasonStatDTO> findPlayerSeasonStatByPlayerIdAndSeasonId(final Long playerId, final Long seasonId) {
        final PlayerSeasonStatDTO playerSeasonStatDTO = getRepositoryService().findPlayerSeasonStatByPlayerIdAndSeasonId(playerId, seasonId);
        return ResponseEntity.ok(playerSeasonStatDTO);
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

    @Override
    public ResponseEntity<List<TeamPlayerSeasonStatsDTO>> findAvailablePlayerSeasonStatBySeasonId(final Long seasonId) {
        final List<TeamPlayerSeasonStatsDTO> teamPlayerSeasonStats = getRepositoryService().findTeamPlayerSeasonStatsBySeasonIdAndAvailable(seasonId, true);
        return ResponseEntity.ok(teamPlayerSeasonStats);
    }

}
