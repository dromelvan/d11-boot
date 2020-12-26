package org.d11.boot.application.api;

import org.d11.boot.api.model.TeamTableStatDTO;
import org.d11.boot.api.service.TeamTableStatApiService;
import org.d11.boot.application.model.TeamTableStat;
import org.d11.boot.application.repository.TeamTableStatRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Team table stat API tests.
 */
public class TeamTableStatApiTests extends AbstractApiTests<TeamTableStat, TeamTableStatRepository, TeamTableStatApiService> {

    /**
     * Tests the findTeamTableStatById API operation.
     */
    @Test
    public void findTeamTableStatById() {
        for(final TeamTableStat teamTableStat : getEntities()) {
            final TeamTableStatDTO result = getApiService().findTeamTableStatById(teamTableStat.getId());
            final TeamTableStatDTO teamTableStatDTO = map(teamTableStat, TeamTableStatDTO.class);

            assertNotNull(result, "Team table stat by id should not be null.");
            assertEquals(teamTableStatDTO, result, "Team table stat by id should equal TeamTableStat.");
        }

        assertNull(getApiService().findTeamTableStatById(-1L), "Team table stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
