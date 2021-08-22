package org.d11.boot.application.controller;

import org.d11.boot.api.PlayerMatchStatsApi;
import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.application.service.api.PlayerMatchStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the PlayerMatchStatsApi and provides player match stat endpoints.
 */
@RestController
public class PlayerMatchStatController extends AbstractRepositoryServiceController<PlayerMatchStatDTO, PlayerMatchStatService> implements PlayerMatchStatsApi {

    /**
     * Creates a new controller.
     *
     * @param playerMatchStatService The repository service this
     */
    public PlayerMatchStatController(final PlayerMatchStatService playerMatchStatService) {
        super(playerMatchStatService);
    }

    @Override
    public ResponseEntity<PlayerMatchStatDTO> findPlayerMatchStatById(final Long playerMatchStatId) {
        return findById(playerMatchStatId);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findPlayerMatchStatByMatchId(final Long matchId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findPlayerMatchStatByMatchId(matchId);
        return ResponseEntity.ok(playerMatchStats);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findActivePlayerMatchStatByMatchIdAndTeamId(final Long matchId, final Long teamId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findActivePlayerMatchStatByMatchIdAndTeamId(matchId, teamId);
        return ResponseEntity.ok(playerMatchStats);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findPlayerMatchStatByD11MatchId(final Long d11MatchId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findPlayerMatchStatByD11MatchId(d11MatchId);
        return ResponseEntity.ok(playerMatchStats);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findPlayerMatchStatByD11MatchIdAndD11TeamId(final Long d11MatchId, final Long d11TeamId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findPlayerMatchStatByD11MatchIdAndD11TeamId(d11MatchId, d11TeamId);
        return ResponseEntity.ok(playerMatchStats);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findPlayerMatchStatByPlayerIdAndSeasonId(final Long playerId, final Long seasonId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findPlayerMatchStatByPlayerIdAndSeasonId(playerId, seasonId);
        return ResponseEntity.ok(playerMatchStats);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findTop5PlayerMatchStatByMatchWeek(final Long matchWeekId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findTop5PlayerMatchStatByMatchWeek(matchWeekId);
        return ResponseEntity.ok(playerMatchStats);
    }

    @Override
    public ResponseEntity<List<PlayerMatchStatDTO>> findBottom5PlayerMatchStatByMatchWeek(final Long matchWeekId) {
        final List<PlayerMatchStatDTO> playerMatchStats = getRepositoryService().findBottom5PlayerMatchStatByMatchWeek(matchWeekId);
        return ResponseEntity.ok(playerMatchStats);
    }

}
