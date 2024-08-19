package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.MatchApi;
import org.d11.boot.api.v2.model.MatchBaseDTO;
import org.d11.boot.api.v2.model.MatchDTO;
import org.d11.boot.api.v2.model.MatchResponseBodyDTO;
import org.d11.boot.api.v2.model.MatchesResponseBodyDTO;
import org.d11.boot.api.v2.model.StadiumDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Match API REST controller implementation.
 */
@RestController
public class MatchControllerV2 extends RepositoryServiceController<MatchService> implements MatchApi {

    /**
     * Create a new controller.
     *
     * @param matchService The service the controller will use.
     */
    @Autowired
    public MatchControllerV2(final MatchService matchService) {
        super(matchService);
    }

    @Override
    public ResponseEntity<MatchResponseBodyDTO> getMatchById(final Long matchId) {
        final Match match = getRepositoryService().getById(matchId);

        return ResponseEntity.ok(new MatchResponseBodyDTO()
                .match(getMapper().map(match, MatchDTO.class))
                .stadium(getMapper().map(match.getStadium(), StadiumDTO.class)));
    }

    @Override
    public ResponseEntity<MatchesResponseBodyDTO> getMatchesByMatchWeekId(final Long matchWeekId) {
        final List<Match> matches = getRepositoryService().getByMatchWeekId(matchWeekId);
        return ResponseEntity.ok(new MatchesResponseBodyDTO()
                .matches(getMapper().map(matches, MatchBaseDTO.class)));
    }

    @Override
    public ResponseEntity<MatchesResponseBodyDTO> getMatchesByTeamIdAndSeasonId(final Long teamId,
                                                                                final Long seasonId) {
        final List<Match> matches = getRepositoryService().getByTeamIdAndSeasonId(teamId, seasonId);
        return ResponseEntity.ok(new MatchesResponseBodyDTO()
                .matches(getMapper().map(matches, MatchBaseDTO.class)));
    }

    @Override
    public ResponseEntity<MatchesResponseBodyDTO> getCurrentMatches() {
        final List<Match> matches = getRepositoryService().getCurrentMatches();
        return ResponseEntity.ok(new MatchesResponseBodyDTO()
                .matches(getMapper().map(matches, MatchBaseDTO.class)));
    }

}
