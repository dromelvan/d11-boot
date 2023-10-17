package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TeamApi;
import org.d11.boot.api.v2.model.TeamDTO;
import org.d11.boot.api.v2.model.TeamsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Team API REST controller implementation.
 */
@RestController
public class TeamControllerV2 extends RepositoryServiceController<TeamService> implements TeamApi {

    /**
     * Create a new controller.
     *
     * @param teamService The service the controller will use.
     */
    @Autowired
    public TeamControllerV2(final TeamService teamService) {
        super(teamService);
    }

    @Override
    public ResponseEntity<TeamsResponseBodyDTO> getTeams() {
        final List<Team> teams = getRepositoryService().getTeams();

        return ResponseEntity.ok(new TeamsResponseBodyDTO()
                .teams(getMapper().map(teams, TeamDTO.class)));
    }

}
