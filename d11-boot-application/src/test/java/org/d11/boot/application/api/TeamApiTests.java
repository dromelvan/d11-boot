package org.d11.boot.application.api;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.api.service.TeamApiService;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.TeamRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Team API tests.
 */
public class TeamApiTests extends AbstractApiTests<Team, TeamRepository, TeamApiService> {

    /**
     * Tests the findTeamById API operation.
     */
    @Test
    public void findTeamsById() {
        for(final Team team : getEntities()) {
            final TeamDTO result = getApiService().findTeamById(team.getId());
            final TeamDTO teamDTO = map(team, TeamDTO.class);

            assertNotNull(result, "Team by id should not be null.");
            assertEquals(teamDTO, result, "Team by id should equal Team.");
        }

        assertNull(getApiService().findTeamById(-1L), "Team not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
