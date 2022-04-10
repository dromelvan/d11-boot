package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.TeamSeasonStatDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TableStat;
import org.d11.boot.application.model.TeamSeasonStat;
import org.d11.boot.application.repository.TeamSeasonStatRepository;
import org.d11.boot.client.api.TeamSeasonStatApi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Team season stat API tests.
 */
public class TeamSeasonStatApiTests extends AbstractRepositoryApiTests<TeamSeasonStat, TeamSeasonStatRepository> {

    /**
     * Tests the findTeamSeasonStatById API operation.
     */
    @Test
    public void findTeamSeasonStatById() {
        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);
        for(final TeamSeasonStat teamSeasonStat : getEntities()) {
            final TeamSeasonStatDTO result = teamSeasonStatApi.findTeamSeasonStatById(teamSeasonStat.getId());
            final TeamSeasonStatDTO teamSeasonStatDTO = map(teamSeasonStat, TeamSeasonStatDTO.class);

            assertNotNull(result, "Team season stat by id should not be null.");
            assertEquals(teamSeasonStatDTO, result, "Team season stat by id should equal TeamSeasonStat.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> teamSeasonStatApi.findTeamSeasonStatById(-1L),
                     "Team season stat not found should throw NotFound exception.");
    }

    /**
     * Tests the findTeamSeasonStatBySeasonId API operation.
     */
    @Test
    public void findTeamSeasonStatBySeasonId() {
        final Map<Season, List<TeamSeasonStat>> teamSeasonStatMap = new HashMap<>();
        for(final TeamSeasonStat teamSeasonStat : getEntities()) {
            final List<TeamSeasonStat> teamSeasonStats =
                    teamSeasonStatMap.computeIfAbsent(teamSeasonStat.getSeason(), season -> new ArrayList<>());
            teamSeasonStats.add(teamSeasonStat);
        }

        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);
        for(final Map.Entry<Season, List<TeamSeasonStat>> entry : teamSeasonStatMap.entrySet()) {
            final List<TeamSeasonStat> seasonTeamSeasonStats = entry.getValue();

            seasonTeamSeasonStats.sort(Comparator.comparingInt(TableStat::getRanking));

            final List<TeamSeasonStatDTO> teamSeasonStats = teamSeasonStatApi.findTeamSeasonStatBySeasonId(entry.getKey().getId());

            assertEquals(seasonTeamSeasonStats.size(), teamSeasonStats.size(),
                    "Season team season stats and team season stats sizes are not equal. " +
                    "This means the team season stat test data is not set up properly.");

            assertEquals(map(seasonTeamSeasonStats, TeamSeasonStatDTO.class), teamSeasonStats,
                    "Season team season stats should equal team season stats.");
        }
    }

    /**
     * Tests the findTeamSeasonStatByTeamIdAndSeasonId API operation.
     */
    @Test
    public void findTeamSeasonStatByTeamIdAndSeasonId() {
        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);
        for(final TeamSeasonStat teamSeasonStat : getEntities()) {
            final TeamSeasonStatDTO teamSeasonStatDTO = teamSeasonStatApi.findTeamSeasonStatByTeamIdAndSeasonId(
                    teamSeasonStat.getTeam().getId(),
                    teamSeasonStat.getSeason().getId()
            );

            assertNotNull(teamSeasonStatDTO, "Team season stat DTO should not be null.");
            assertEquals(map(teamSeasonStat, TeamSeasonStatDTO.class), teamSeasonStatDTO,
                    "Team season stat DTO should equal team season stat.");
        }
    }
}
