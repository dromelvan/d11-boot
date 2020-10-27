package org.d11.boot.application.api;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.application.model.Team;
import org.d11.boot.client.api.TeamApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Team API tests.
 */
public class TeamApiTests extends AbstractApiTests<Team> {

    /**
     * Sets up mocked teams for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getTeamRepository().findAll());
    }

    /**
     * Tests the findTeamById API operation.
     */
    @Test
    public void findTeamsById() {
        final TeamApi teamApi = new TeamApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Teams should not be empty.");

        for(final Team team : getEntities()) {
            final TeamDTO result = teamApi.findTeamById(team.getId()).block();
            final TeamDTO teamDTO = map(team, TeamDTO.class);

            assertNotNull(result, "Team by id should not be null.");
            assertEquals(teamDTO, result, "Team by id should equal Team.");
        }

        assertNotFound(teamApi.findTeamById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
