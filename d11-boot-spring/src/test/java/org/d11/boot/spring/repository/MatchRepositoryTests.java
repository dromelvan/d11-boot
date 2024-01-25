package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
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
 * Match repository tests.
 */
class MatchRepositoryTests extends AbstractRepositoryTests<Match, MatchRepository> {

    /**
     * Tests MatchRepository::findByWhoscoredId.
     */
    @Test
    void testFindByWhoscoredId() {
        final List<Match> matches = getEntities();
        matches.forEach(match -> match.setWhoscoredId(matches.indexOf(match)));

        matches.forEach(match -> {
            final Match result = getRepository().findByWhoscoredId(match.getWhoscoredId()).orElse(null);

            assertNotNull(result, "MatchRepository::findByWhoscoredId not null");
            assertEquals(match, result, "MatchRepository::findByWhoscoredId equals");
        });
    }

    /**
     * Tests MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime.
     */
    @Test
    void testFindByTeamIdAndMatchWeekSeasonIdOrderByDatetime() {
        final List<Match> entities = getEntities();
        entities.sort(Comparator.comparing(Match::getDatetime));

        final Set<Team> teams = entities.stream()
                .map(Match::getHomeTeam).collect(Collectors.toSet());
        teams.addAll(entities.stream()
                                .map(Match::getAwayTeam).collect(Collectors.toSet()));
        final Set<Season> seasons = entities.stream()
                .map(match -> match.getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(teams.size() > 1,
                   "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime teams size > 1");
        assertTrue(seasons.size() > 1,
                   "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime seasons size > 1");

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final List<Match> result =
                        getRepository().findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(team.getId(),
                                                                                        season.getId());

                final List<Match> expected = entities.stream()
                        .filter(match -> (match.getHomeTeam().equals(team) || match.getAwayTeam().equals(team))
                                            && match.getMatchWeek().getSeason().equals(season))
                        .toList();

                assertTrue(expected.size() > 1,
                           "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime expected size > 1");

                assertNotNull(result,
                              "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime not null");
                assertFalse(result.isEmpty(),
                            "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime empty");
                assertEquals(expected, result,
                             "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime equals");
            }
        }
    }

    /**
     * Tests MatchRepository::findByMatchWeekIdOrderByDatetimeAscIdAsc.
     */
    @Test
    void testFindByMatchWeekIdOrderByDatetimeAscIdAsc() {
        final List<Match> entities = getEntities();
        entities.sort(Comparator.comparing(Match::getDatetime)
                .thenComparing(Match::getId));

        final Set<MatchWeek> matchWeeks = entities.stream()
                .map(Match::getMatchWeek)
                .collect(Collectors.toSet());

        assertTrue(matchWeeks.size() > 1,
                "MatchRepository::findByMatchWeekIdOrderByDatetimeAscIdAsc matchWeeks size > 1");

        for (final MatchWeek matchWeek : matchWeeks) {
            final List<Match> expected = entities.stream()
                    .filter(match -> match.getMatchWeek().equals(matchWeek))
                    .toList();

            assertTrue(expected.size() > 1,
                    "MatchRepository::findByMatchWeekIdOrderByDatetimeAscIdAsc expected size > 1");

            final List<Match> result = getRepository().findByMatchWeekIdOrderByDatetimeAscIdAsc(matchWeek.getId());

            assertNotNull(result, "MatchRepository::findByMatchWeekIdOrderByDatetimeAscIdAsc not null");
            assertFalse(result.isEmpty(), "MatchRepository::findByMatchWeekIdOrderByDatetimeAscIdAsc empty");
            assertEquals(expected, result, "MatchRepository::findByMatchWeekIdOrderByDatetimeAscIdAsc equals");
        }
    }

}
