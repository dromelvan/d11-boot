package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TeamSeasonStatApi;
import org.d11.boot.api.v2.model.TeamSeasonStatDTO;
import org.d11.boot.api.v2.model.TeamSeasonStatResponseBodyDTO;
import org.d11.boot.api.v2.model.TeamSeasonStatsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.TeamSeasonStat;
import org.d11.boot.spring.service.TeamSeasonStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Team season stat API REST controller implementation.
 */
@RestController
public class TeamSeasonStatControllerV2 extends RepositoryServiceController<TeamSeasonStatService>
        implements TeamSeasonStatApi {

    /**
     * Create a new controller.
     *
     * @param teamSeasonStatService The service the controller will use.
     */
    @Autowired
    public TeamSeasonStatControllerV2(final TeamSeasonStatService teamSeasonStatService) {
        super(teamSeasonStatService);
    }

    @Override
    public ResponseEntity<TeamSeasonStatsResponseBodyDTO> getTeamSeasonStatsBySeasonId(final Long seasonId) {
        final List<TeamSeasonStat> teamSeasonStats = getRepositoryService().getBySeasonId(seasonId);

        return ResponseEntity.ok(new TeamSeasonStatsResponseBodyDTO()
                .teamSeasonStats(getMapper().map(teamSeasonStats, TeamSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<TeamSeasonStatResponseBodyDTO> getTeamSeasonStatByTeamIdAndSeasonId(final Long teamId,
                                                                                              final Long seasonId) {
        final TeamSeasonStat teamSeasonStat = getRepositoryService().getByTeamIdAndSeasonId(teamId, seasonId);

        return ResponseEntity.ok(new TeamSeasonStatResponseBodyDTO()
                .teamSeasonStat(getMapper().map(teamSeasonStat, TeamSeasonStatDTO.class)));
    }

}
