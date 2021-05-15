package org.d11.boot.application.controller;

import org.d11.boot.api.TeamMatchWeekStatsApi;
import org.d11.boot.api.model.TeamMatchWeekStatDTO;
import org.d11.boot.application.service.TeamMatchWeekStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the TeamMatchWeekStatsApi and provides team match week stat endpoints.
 */
@RestController
public class TeamMatchWeekStatController extends AbstractRepositoryServiceController<TeamMatchWeekStatDTO, TeamMatchWeekStatService> implements TeamMatchWeekStatsApi {

    /**
     * Creates a new controller.
     *
     * @param teamMatchWeekStatService The repository service this controller will use.
     */
    @Autowired
    public TeamMatchWeekStatController(final TeamMatchWeekStatService teamMatchWeekStatService) {
        super(teamMatchWeekStatService);
    }

    @Override
    public ResponseEntity<TeamMatchWeekStatDTO> findTeamMatchWeekStatById(final Long teamMatchWeekStatId) {
        return findById(teamMatchWeekStatId);
    }

}
