package org.d11.boot.application.api;

import org.d11.boot.api.model.TeamTableStatDTO;
import org.d11.boot.api.service.TeamTableStatApiService;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.model.TeamTableStat;
import org.d11.boot.application.repository.TeamTableStatRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Team table stat API tests.
 */
public class TeamTableStatApiTests extends AbstractRepositoryApiTests<TeamTableStat, TeamTableStatRepository, TeamTableStatApiService> {

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

    /**
     * Tests the findTeamTableStatByPremierLeagueId API operation.
     */
    @Test
    public void findTeamTableStatByPremierLeagueId() {
        final Map<PremierLeague, List<TeamTableStat>> teamTableStatMap = new HashMap<>();
        for(final TeamTableStat teamTableStat : getEntities()) {
            if(teamTableStat.isMain()) {
                final List<TeamTableStat> teamTableStats =
                        teamTableStatMap.computeIfAbsent(teamTableStat.getPremierLeague(), premierLeague -> new ArrayList<>());
                teamTableStats.add(teamTableStat);
            }
        }

        for(final Map.Entry<PremierLeague, List<TeamTableStat>> entry : teamTableStatMap.entrySet()) {
            final List<TeamTableStat> premierLeagueTeamTableStats = entry.getValue();

            premierLeagueTeamTableStats.sort((teamTableStat1, teamTableStat2) -> {
                int compare = (int) (teamTableStat2.getMatchWeek().getId() - teamTableStat1.getMatchWeek().getId());
                if(compare == 0) {
                    compare = teamTableStat1.getRanking() - teamTableStat2.getRanking();
                }
                return compare;
            });

            final List<TeamTableStatDTO> teamTableStats = getApiService().findTeamTableStatByPremierLeagueId(entry.getKey().getId());

            assertEquals(premierLeagueTeamTableStats.size(), teamTableStats.size(),
                    "Premier League team table stats and team table stats sizes are not equal. " +
                    "This means the team table stat test data is not set up properly.");

            assertEquals(map(premierLeagueTeamTableStats, TeamTableStatDTO.class), teamTableStats,
                    "Premier League team table stats should equal team table stats.");
        }
    }

}
