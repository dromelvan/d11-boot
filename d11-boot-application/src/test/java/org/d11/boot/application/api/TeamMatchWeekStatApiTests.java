package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.TeamMatchWeekStatDTO;
import org.d11.boot.application.model.TeamMatchWeekStat;
import org.d11.boot.application.repository.TeamMatchWeekStatRepository;
import org.d11.boot.client.api.TeamMatchWeekStatApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Team match week stat API tests.
 */
public class TeamMatchWeekStatApiTests extends AbstractRepositoryApiTests<TeamMatchWeekStat, TeamMatchWeekStatRepository> {

    /**
     * Tests the findTeamMatchWeekStatById API operation.
     */
    @Test
    public void findTeamMatchWeekStatById() {
        final TeamMatchWeekStatApi teamMatchWeekStatApi = getApi(TeamMatchWeekStatApi.class);
        for(final TeamMatchWeekStat teamMatchWeekStat : getEntities()) {
            final TeamMatchWeekStatDTO result = teamMatchWeekStatApi.findTeamMatchWeekStatById(teamMatchWeekStat.getId());
            final TeamMatchWeekStatDTO teamMatchWeekStatDTO = map(teamMatchWeekStat, TeamMatchWeekStatDTO.class);

            assertNotNull(result, "Team match week stat by id should not be null.");
            assertEquals(teamMatchWeekStatDTO, result, "Team match week stat by id should equal TeamMatchWeekStat.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> teamMatchWeekStatApi.findTeamMatchWeekStatById(-1L),
                     "Team match week stat not found should throw NotFound exception.");
    }

}
