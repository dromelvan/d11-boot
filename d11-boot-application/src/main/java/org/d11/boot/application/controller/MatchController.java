package org.d11.boot.application.controller;

import org.d11.boot.api.MatchesApi;
import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.api.model.MatchesByDateDTO;
import org.d11.boot.application.service.api.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the MatchesApi and provides match endpoints.
 */
@RestController
public class MatchController extends AbstractRepositoryServiceController<MatchDTO, MatchService> implements MatchesApi {

    /**
     * Creates a new controller.
     *
     * @param matchService The service that will be used by this controller.
     */
    @Autowired
    public MatchController(final MatchService matchService) {
        super(matchService);
    }

    @Override
    public ResponseEntity<MatchDTO> findMatchById(final Long matchId) {
        return findById(matchId);
    }

    @Override
    public ResponseEntity<List<Long>> findMatchByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        final List<Long> matchIds = getRepositoryService().findMatchByTeamIdAndSeasonId(teamId, seasonId);
        return ResponseEntity.ok(matchIds);
    }

    @Override
    public ResponseEntity<MatchesByDateDTO> findCurrentMatches() {
        final MatchesByDateDTO matchesByDateDTO = getRepositoryService().findCurrentMatches();
        return ResponseEntity.ok(matchesByDateDTO);
    }

}

