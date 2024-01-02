package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TeamApi;
import org.d11.boot.api.v2.model.StadiumDTO;
import org.d11.boot.api.v2.model.TeamBaseDTO;
import org.d11.boot.api.v2.model.TeamDTO;
import org.d11.boot.api.v2.model.TeamResponseBodyDTO;
import org.d11.boot.api.v2.model.TeamsResponseBodyDTO;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
     * Tests TeamController::getTeamById.
     */
    @Test
    void testGetTeamById() {
        final TeamApi teamApi = getApi(TeamApi.class);

        assertThrows(FeignException.NotFound.class, () -> teamApi.getTeamById(0L),
                     "TeamController::getTeamById not found");

        final List<Team> teams = this.teamRepository.findAll();

        assertFalse(teams.isEmpty(), "TeamController::getTeamById players not empty");

        for (final Team team : teams) {
            final TeamResponseBodyDTO result = teamApi.getTeamById(team.getId());
            assertNotNull(result, "TeamController::getTeamById not null");
            assertEquals(getMapper().map(team, TeamDTO.class), result.getTeam(),
                    "TeamController::getTeamById equals");
            assertEquals(getMapper().map(team.getStadium(), StadiumDTO.class), result.getStadium(),
                    "TeamController::getTeamById stadium equals");
        }
    }

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

        final List<TeamBaseDTO> result = teamsResponseBodyDTO.getTeams();

        assertEquals(teams.size(), result.size(), "TeamController::getTeams size");

        for (int i = 0; i < teams.size(); ++i) {
            final Team team = teams.get(i);
            final TeamBaseDTO teamDTO = result.get(i);

            assertEquals(map(team, TeamBaseDTO.class), teamDTO, "TeamController::getTeams equals");
        }
    }

}
