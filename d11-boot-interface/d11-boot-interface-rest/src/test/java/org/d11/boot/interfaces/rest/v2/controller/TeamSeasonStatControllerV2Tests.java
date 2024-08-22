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
     * Tests TeamSeasonStatController::getTeamSeasonStatsBySeasonId.
     */
    @Test
    void testGetTeamSeasonStatsBySeasonId() {
        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatsBySeasonId((Long) null),
                     "TeamSeasonStatController::getTeamSeasonStatsBySeasonId seasonId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatsBySeasonId(-1L),
                     "TeamSeasonStatController::getTeamSeasonStatsBySeasonId seasonId negative throws");

        final List<TeamSeasonStat> teamSeasonStats = this.teamSeasonStatRepository.findAll();
        teamSeasonStats.sort(Comparator.comparing(TeamSeasonStat::getRanking));

        final Set<Season> seasons = teamSeasonStats.stream()
                .map(TeamSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1, "TeamSeasonStatController::getTeamSeasonStatsBySeasonId seasons size > 0");

        for (final Season season : seasons) {
            final TeamSeasonStatsResponseBodyDTO teamSeasonStatsBySeasonId =
                    teamSeasonStatApi.getTeamSeasonStatsBySeasonId(season.getId());
            assertNotNull(teamSeasonStatsBySeasonId,
                          "TeamSeasonStatController::getTeamSeasonStatsBySeasonId response not null");

            final List<TeamSeasonStat> expected = teamSeasonStats.stream()
                    .filter(teamSeasonStat -> teamSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1,
                       "TeamSeasonStatController::getTeamSeasonStatsBySeasonId expected size > 1");

            final List<TeamSeasonStatDTO> result = teamSeasonStatsBySeasonId.getTeamSeasonStats();

            assertNotNull(result, "TeamSeasonStatController::getTeamSeasonStatsBySeasonId not null ");
            assertFalse(result.isEmpty(), "TeamSeasonStatController::getTeamSeasonStatsBySeasonId empty");
            assertEquals(map(expected, TeamSeasonStatDTO.class), result,
                         "TeamSeasonStatController::getTeamSeasonStatsBySeasonId equals");
        }
    }

    /**
     * Tests TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId.
     */
    @Test
    void testGetTeamSeasonStatByTeamIdAndSeasonId() {
        final TeamSeasonStatApi teamSeasonStatApi = getApi(TeamSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(null, 1L),
                     "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId teamId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(-1L, 1L),
                     "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId teamId negative throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(1L, (Long) null),
                     "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId seasonId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(1L, -1L),
                     "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId seasonId negative throws");

        final List<TeamSeasonStat> teamSeasonStats = this.teamSeasonStatRepository.findAll();
        teamSeasonStats.sort(Comparator.comparing(TeamSeasonStat::getRanking));

        final Set<Team> teams = teamSeasonStats.stream()
                .map(TeamSeasonStat::getTeam)
                .collect(Collectors.toSet());
        final Set<Season> seasons = teamSeasonStats.stream()
                .map(TeamSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertFalse(teams.isEmpty(), "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId teams empty");
        assertFalse(seasons.isEmpty(), "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId seasons empty");

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final TeamSeasonStatResponseBodyDTO response =
                        teamSeasonStatApi.getTeamSeasonStatByTeamIdAndSeasonId(team.getId(), season.getId());
                assertNotNull(response,
                              "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId response not null");

                final Optional<TeamSeasonStat> expected = teamSeasonStats.stream()
                        .filter(teamSeasonStat -> teamSeasonStat.getTeam().equals(team)
                                                  && teamSeasonStat.getSeason().equals(season))
                        .findAny();

                assertTrue(expected.isPresent(),
                           "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId expected present");

                expected.ifPresent(teamSeasonStat -> {
                    final TeamSeasonStatDTO result = response.getTeamSeasonStat();

                    assertNotNull(result, "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId not null ");
                    assertEquals(map(teamSeasonStat, TeamSeasonStatDTO.class), result,
                                 "TeamSeasonStatController::getTeamSeasonStatByTeamIdAndSeasonId equals");
                });
            }
        }
    }

}
