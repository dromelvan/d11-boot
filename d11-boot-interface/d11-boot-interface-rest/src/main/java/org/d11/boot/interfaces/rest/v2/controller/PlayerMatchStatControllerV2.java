package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.PlayerMatchStatApi;
import org.d11.boot.api.v2.model.PlayerMatchStatDTO;
import org.d11.boot.api.v2.model.PlayerMatchStatsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.service.PlayerMatchStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Player match stat API REST controller implementation.
 */
@RestController
public class PlayerMatchStatControllerV2 extends RepositoryServiceController<PlayerMatchStatService>
        implements PlayerMatchStatApi {

    /**
     * Create a new controller.
     *
     * @param playerMatchStatService The service the controller will use.
     */
    @Autowired
    public PlayerMatchStatControllerV2(final PlayerMatchStatService playerMatchStatService) {
        super(playerMatchStatService);
    }

    @Override
    public ResponseEntity<PlayerMatchStatsResponseBodyDTO> getPlayerMatchStatsByMatchId(final Long matchId) {
        final List<PlayerMatchStat> playerMatchStats = getRepositoryService().getByMatchId(matchId);

        return ResponseEntity.ok(new PlayerMatchStatsResponseBodyDTO()
                .playerMatchStats(getMapper().map(playerMatchStats, PlayerMatchStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerMatchStatsResponseBodyDTO> getPlayerMatchStatsByD11MatchId(final Long d11MatchId) {
        final List<PlayerMatchStat> playerMatchStats = getRepositoryService().getByD11MatchId(d11MatchId);

        return ResponseEntity.ok(new PlayerMatchStatsResponseBodyDTO()
                .playerMatchStats(getMapper().map(playerMatchStats, PlayerMatchStatDTO.class)));
    }

}
