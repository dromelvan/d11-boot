package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.api.model.TeamNameDTO;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.client.api.TeamApi;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Team API tests.
 */
public class TeamApiTests extends AbstractRepositoryApiTests<Team, TeamRepository> {

    /**
     * Tests the findTeamById API operation.
     */
    @Test
    public void findTeamsById() {
        final TeamApi teamApi = getApi(TeamApi.class);
        for(final Team team : getEntities()) {
            final TeamDTO result = teamApi.findTeamById(team.getId());
            final TeamDTO teamDTO = map(team, TeamDTO.class);

            assertNotNull(result, "Team by id should not be null.");
            assertEquals(teamDTO, result, "Team by id should equal Team.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> teamApi.findTeamById(-1L),
                     "Team not found should throw NotFound exception.");
    }

    /**
     * Tests the findAllTeams API operation.
     */
    @Test
    public void findAllTeams() {
        final TeamApi teamApi = getApi(TeamApi.class);
        final List<TeamNameDTO> result = teamApi.findAllTeams();

        final List<TeamNameDTO> teamNameDTOs = map(getEntities(), TeamNameDTO.class);
        teamNameDTOs.sort(Comparator.comparing(TeamNameDTO::getName));

        assertNotNull(result, "All teams should not be null.");
        assertEquals(teamNameDTOs, result, "All teams should equal teams.");
    }

}
