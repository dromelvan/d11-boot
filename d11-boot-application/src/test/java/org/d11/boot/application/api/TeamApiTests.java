package org.d11.boot.application.api;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.client.api.TeamApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Team API tests.
 */
public class TeamApiTests extends AbstractApiTests {

    /**
     * Stadium repository.
     */
    @Autowired
    private TeamRepository teamRepository;
    /**
     * List of teams.
     */
    private List<Team> teams;

    /**
     * Sets up mocked teams for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.teams = this.teamRepository.findAll();
    }

    /**
     * Tests the findTeamById API operation.
     */
    @Test
    public void findTeamsById() {
        final TeamApi teamApi = new TeamApi(getApiClient());

        for(final Team team : this.teams) {
            final TeamDTO result = teamApi.findTeamById(team.getId()).block();
            final TeamDTO teamDTO = map(team, TeamDTO.class);

            assertNotNull(result, "Team by id should not be null.");
            assertEquals(teamDTO, result, "Team by id should equal Team.");
        }

        assertNotFound(teamApi.findTeamById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
