package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.PlayerSeasonStatApi;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.service.PlayerSeasonStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Player season stat API REST controller implementation.
 */
@RestController
public class PlayerSeasonStatControllerV2 extends RepositoryServiceController<PlayerSeasonStatService>
        implements PlayerSeasonStatApi {

    /**
     * Create a new controller.
     *
     * @param playerSeasonStatService The service the controller will use.
     */
    @Autowired
    public PlayerSeasonStatControllerV2(final PlayerSeasonStatService playerSeasonStatService) {
        super(playerSeasonStatService);
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsByPlayerId(final Long playerId) {
        final List<PlayerSeasonStat> playerSeasonStats = getRepositoryService().getByPlayerId(playerId);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsBySeasonId(final Long seasonId,
                                                                                           final Integer page) {
        final List<PlayerSeasonStat> playerSeasonStats = getRepositoryService().getBySeasonId(seasonId, page);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsByTeamIdAndSeasonId(
            final Long teamId,
            final Long seasonId
    ) {
        final List<PlayerSeasonStat> playerSeasonStats =
                getRepositoryService().getByTeamIdAndSeasonId(teamId, seasonId);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsByD11TeamIdAndSeasonId(
            final Long d11TeamId,
            final Long seasonId
    ) {
        final List<PlayerSeasonStat> playerSeasonStats =
                getRepositoryService().getByD11TeamIdAndSeasonId(d11TeamId, seasonId);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

}
