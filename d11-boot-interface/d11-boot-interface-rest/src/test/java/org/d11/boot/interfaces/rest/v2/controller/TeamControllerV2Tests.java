package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.client.TeamApi;
import org.d11.boot.api.v2.model.TeamDTO;
import org.d11.boot.api.v2.model.TeamsResponseBodyDTO;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Team controller tests.
 */
class TeamControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Team repository.
     */
    @Autowired
    private TeamRepository teamRepository;

    /**
     * Tests TeamController::getTeams.
     */
    @Test
    void testGetTeams() {
        final TeamApi teamApi = getApi(TeamApi.class);

        final List<Team> teams = this.teamRepository.findByOrderByName();
        assertFalse(teams.isEmpty(), "TeamController::getTeams not empty");

        final TeamsResponseBodyDTO teamsResponseBodyDTO = teamApi.getTeams();

        assertNotNull(teamsResponseBodyDTO, "TeamController::getTeams not null");

        final List<TeamDTO> result = teamsResponseBodyDTO.getTeams();

        assertEquals(teams.size(), result.size(), "TeamController::getTeams size");

        for (int i = 0; i < teams.size(); ++i) {
            final Team team = teams.get(i);
            final TeamDTO teamDTO = result.get(i);

            assertEquals(map(team, TeamDTO.class), teamDTO, "TeamController::getTeams equals");
        }
    }

}
