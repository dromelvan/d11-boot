package org.d11.boot.application.controller;

import org.d11.boot.api.TeamSeasonStatsApi;
import org.d11.boot.api.model.TeamSeasonStatDTO;
import org.d11.boot.application.service.api.TeamSeasonStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the TeamSeasonStatsApi and provides team season stat endpoints.
 */
@RestController
public class TeamSeasonStatController extends AbstractRepositoryServiceController<TeamSeasonStatDTO, TeamSeasonStatService> implements TeamSeasonStatsApi {

    /**
     * Creates a new controller.
     *
     * @param teamSeasonStatService The repository service this controller will use.
     */
    @Autowired
    public TeamSeasonStatController(final TeamSeasonStatService teamSeasonStatService) {
        super(teamSeasonStatService);
    }

    @Override
    public ResponseEntity<TeamSeasonStatDTO> findTeamSeasonStatById(final Long teamSeasonStatId) {
        return findById(teamSeasonStatId);
    }

    @Override
    public ResponseEntity<List<TeamSeasonStatDTO>> findTeamSeasonStatBySeasonId(final Long seasonId) {
        final List<TeamSeasonStatDTO> teamSeasonStats = getRepositoryService().findTeamSeasonStatBySeasonId(seasonId);
        return ResponseEntity.ok(teamSeasonStats);
    }

    @Override
    public ResponseEntity<TeamSeasonStatDTO> findTeamSeasonStatByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        final TeamSeasonStatDTO teamSeasonStatDTO = getRepositoryService().findTeamSeasonStatByTeamIdAndSeasonId(teamId, seasonId);
        return ResponseEntity.ok(teamSeasonStatDTO);
    }

}
