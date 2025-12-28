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

        assertThrows(FeignException.NotFound.class, () -> teamApi.getTeamById(0L));

        final List<Team> teams = this.teamRepository.findAll();

        assertFalse(teams.isEmpty());

        for (final Team team : teams) {
            final TeamResponseBodyDTO result = teamApi.getTeamById(team.getId());
            assertNotNull(result);
            assertEquals(getMapper().map(team, TeamDTO.class), result.getTeam());
            assertEquals(getMapper().map(team.getStadium(), StadiumDTO.class), result.getStadium());
        }
    }

    /**
     * Tests TeamController::getTeams.
     */
    @Test
    void testGetTeams() {
        final TeamApi teamApi = getApi(TeamApi.class);

        final List<Team> teams = this.teamRepository.findByOrderByName();
        assertFalse(teams.isEmpty());

        final TeamsResponseBodyDTO teamsResponseBodyDTO = teamApi.getTeams();

        assertNotNull(teamsResponseBodyDTO);

        final List<TeamBaseDTO> result = teamsResponseBodyDTO.getTeams();

        assertEquals(teams.size(), result.size());

        for (int i = 0; i < teams.size(); ++i) {
            final Team team = teams.get(i);
            final TeamBaseDTO teamDTO = result.get(i);

            assertEquals(map(team, TeamBaseDTO.class), teamDTO);
        }
    }

}
