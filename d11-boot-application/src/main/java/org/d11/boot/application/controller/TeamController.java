package org.d11.boot.application.controller;

import org.d11.boot.api.TeamsApi;
import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.api.model.TeamNameDTO;
import org.d11.boot.application.service.api.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the TeamsApi and provides team endpoints.
 */
@RestController
public class TeamController extends AbstractRepositoryServiceController<TeamDTO, TeamService> implements TeamsApi {

    /**
     * Creates a new controller.
     *
     * @param teamService The repository service this controller will use.
     */
    @Autowired
    public TeamController(final TeamService teamService) {
        super(teamService);
    }

    @Override
    public ResponseEntity<TeamDTO> findTeamById(final Long teamId) {
        return findById(teamId);
    }

    @Override
    public ResponseEntity<List<TeamNameDTO>> findAllTeams() {
        final List<TeamNameDTO> teamNameDTOs = getRepositoryService().findAllTeams();
        return ResponseEntity.ok(teamNameDTOs);
    }

}
