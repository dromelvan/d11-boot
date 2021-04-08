package org.d11.boot.application.controller;

import org.d11.boot.api.TeamTableStatsApi;
import org.d11.boot.api.model.TeamTableStatDTO;
import org.d11.boot.application.service.TeamTableStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the TeamTableStatsApi and provides team table stat endpoints.
 */
@RestController
public class TeamTableStatController extends AbstractRepositoryServiceController<TeamTableStatDTO, TeamTableStatService> implements TeamTableStatsApi {

    /**
     * Creates a new controller.
     *
     * @param teamTableStatService The repository service this controller will use.
     */
    @Autowired
    public TeamTableStatController(final TeamTableStatService teamTableStatService) {
        super(teamTableStatService);
    }

    @Override
    public ResponseEntity<TeamTableStatDTO> findTeamTableStatById(final Long teamTableStatId) {
        return findById(teamTableStatId);
    }

    @Override
    public ResponseEntity<List<TeamTableStatDTO>> findTeamTableStatByPremierLeagueId(final Long premierLeagueId) {
        final List<TeamTableStatDTO> teamTableStats = getRepositoryService().findTeamTableStatByPremierLeagueId(premierLeagueId);
        return ResponseEntity.ok(teamTableStats);
    }

}
