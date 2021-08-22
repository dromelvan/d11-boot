package org.d11.boot.application.api;

import org.d11.boot.api.model.TeamMatchWeekStatDTO;
import org.d11.boot.api.service.TeamMatchWeekStatApiService;
import org.d11.boot.application.model.jpa.TeamMatchWeekStat;
import org.d11.boot.application.repository.TeamMatchWeekStatRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Team match week stat API tests.
 */
public class TeamMatchWeekStatApiTests extends AbstractRepositoryApiTests<TeamMatchWeekStat, TeamMatchWeekStatRepository, TeamMatchWeekStatApiService> {

    /**
     * Tests the findTeamMatchWeekStatById API operation.
     */
    @Test
    public void findTeamMatchWeekStatById() {
        for(final TeamMatchWeekStat teamMatchWeekStat : getEntities()) {
            final TeamMatchWeekStatDTO result = getApiService().findTeamMatchWeekStatById(teamMatchWeekStat.getId());
            final TeamMatchWeekStatDTO teamMatchWeekStatDTO = map(teamMatchWeekStat, TeamMatchWeekStatDTO.class);

            assertNotNull(result, "Team match week stat by id should not be null.");
            assertEquals(teamMatchWeekStatDTO, result, "Team match week stat by id should equal TeamMatchWeekStat.");
        }

        assertNull(getApiService().findTeamMatchWeekStatById(-1L), "Team match week stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
