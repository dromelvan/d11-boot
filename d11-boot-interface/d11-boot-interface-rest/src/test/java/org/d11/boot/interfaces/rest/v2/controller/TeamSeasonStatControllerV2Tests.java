package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TeamSeasonStatApi;
import org.d11.boot.api.v2.model.TeamSeasonStatDTO;
import org.d11.boot.api.v2.model.TeamSeasonStatResponseBodyDTO;
import org.d11.boot.api.v2.model.TeamSeasonStatsResponseBodyDTO;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.TeamSeasonStat;
import org.d11.boot.spring.repository.TeamSeasonStatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team season stat controller tests.
 */
class TeamSeasonStatControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Team season stat repository.
     */
    @Autowired
    private TeamSeasonStatRepository teamSeasonStatRepository;

    /**
     * Tests TeamSeasonStatController::getTeamSeasonStats.
     */
    @Test
    void testGetTeamSeasonStats() {
        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStats(null, null));

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStats(1L, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStats(-1L, null));

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStats(null, -1L));

        // By seasonId ------------------------------------------------------------------------------------------------

        final List<TeamSeasonStat> teamSeasonStatsBySeasonId = this.teamSeasonStatRepository.findAll();
        teamSeasonStatsBySeasonId.sort(Comparator.comparing(TeamSeasonStat::getRanking));

        final Set<Season> seasons = teamSeasonStatsBySeasonId.stream()
                .map(TeamSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1);

        for (final Season season : seasons) {
            final TeamSeasonStatsResponseBodyDTO response =
                    teamSeasonStatApi.getTeamSeasonStats(season.getId(), null);
            assertNotNull(response);

            final List<TeamSeasonStat> expected = teamSeasonStatsBySeasonId.stream()
                    .filter(teamSeasonStat -> teamSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1);

            final List<TeamSeasonStatDTO> result = response.getTeamSeasonStats();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(map(expected, TeamSeasonStatDTO.class), result);
        }

        // By teamId --------------------------------------------------------------------------------------------------

        final List<TeamSeasonStat> teamSeasonStatsByTeamId = this.teamSeasonStatRepository.findAll();
        teamSeasonStatsByTeamId.sort(Comparator.comparing(
                (TeamSeasonStat tss) -> tss.getSeason().getId(), Comparator.reverseOrder()));

        final Set<Team> teams = teamSeasonStatsByTeamId.stream()
                .map(TeamSeasonStat::getTeam)
                .collect(Collectors.toSet());

        assertTrue(teams.size() > 1);

        for (final Team team : teams) {
            final TeamSeasonStatsResponseBodyDTO response =
                    teamSeasonStatApi.getTeamSeasonStats(null, team.getId());
            assertNotNull(response);

            final List<TeamSeasonStat> expected = teamSeasonStatsByTeamId.stream()
                    .filter(teamSeasonStat -> teamSeasonStat.getTeam().equals(team))
                    .toList();

            assertTrue(expected.size() > 1);

            final List<TeamSeasonStatDTO> result = response.getTeamSeasonStats();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(map(expected, TeamSeasonStatDTO.class), result);
        }
    }

    /**
     * Tests TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId.
     */
    @Test
    void testGetTeamSeasonStatByTeamIdAndSeasonId() {
        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(null, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(-1L, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(1L, (Long) null));

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(1L, -1L));

        final List<TeamSeasonStat> teamSeasonStats = this.teamSeasonStatRepository.findAll();
        teamSeasonStats.sort(Comparator.comparing(TeamSeasonStat::getRanking));

        final Set<Team> teams = teamSeasonStats.stream()
                .map(TeamSeasonStat::getTeam)
                .collect(Collectors.toSet());
        final Set<Season> seasons = teamSeasonStats.stream()
                .map(TeamSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertFalse(teams.isEmpty());
        assertFalse(seasons.isEmpty());

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final TeamSeasonStatResponseBodyDTO response =
                        teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(team.getId(), season.getId());
                assertNotNull(response);

                final Optional<TeamSeasonStat> expected = teamSeasonStats.stream()
                        .filter(teamSeasonStat -> teamSeasonStat.getTeam().equals(team)
                                                  && teamSeasonStat.getSeason().equals(season))
                        .findAny();

                assertTrue(expected.isPresent());

                expected.ifPresent(teamSeasonStat -> {
                    final TeamSeasonStatDTO result = response.getTeamSeasonStat();

                    assertNotNull(result);
                    assertEquals(map(teamSeasonStat, TeamSeasonStatDTO.class), result);
                });
            }
        }
    }

}
