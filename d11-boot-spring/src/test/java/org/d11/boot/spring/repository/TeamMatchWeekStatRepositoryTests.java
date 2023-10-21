package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.TeamMatchWeekStat;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team match week stat repository tests.
 */
class TeamMatchWeekStatRepositoryTests extends AbstractRepositoryTests<TeamMatchWeekStat, TeamMatchWeekStatRepository> {

    /**
     * Tests TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate.
     */
    @Test
    void testFindByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate() {
        final List<TeamMatchWeekStat> entities = getEntities();
        entities.sort(Comparator.comparing(teamMatchWeekStat -> teamMatchWeekStat.getMatchWeek().getDate()));

        final Set<Team> teams = entities.stream()
                .map(TeamMatchWeekStat::getTeam).collect(Collectors.toSet());
        final Set<Season> seasons = entities.stream()
                .map(teamMatchWeekStat -> teamMatchWeekStat.getMatchWeek().getSeason()).collect(Collectors.toSet());

        assertTrue(teams.size() > 1,
                   "TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate teams size > 1");
        assertTrue(seasons.size() > 1,
                   """
                   TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate seasons size > 1
                   """);

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final List<TeamMatchWeekStat> result =
                        getRepository().findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(team.getId(),
                                                                                             season.getId());

                final List<TeamMatchWeekStat> expected = entities.stream()
                        .filter(teamMatchWeekStat -> teamMatchWeekStat.getTeam().equals(team)
                                                     && teamMatchWeekStat.getMatchWeek().getSeason().equals(season))
                        .toList();

                assertTrue(expected.size() > 1,
                           """
                           TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate
                           expected size > 1
                           """);

                assertNotNull(result,
                              """
                              TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate 
                              not null
                              """);
                assertFalse(result.isEmpty(),
                            "TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate empty");
                assertEquals(expected, result,
                             """
                             TeamMatchWeekStatRepository::findByTeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate equals
                             """);
            }
        }
    }

}
