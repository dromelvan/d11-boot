package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Season;
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
 * D11Match repository tests.
 */
class D11MatchRepositoryTests extends AbstractRepositoryTests<D11Match, D11MatchRepository> {

    /**
     * Tests D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime.
     */
    @Test
    void testFindByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime() {
        final List<D11Match> entities = getEntities();
        entities.sort(Comparator.comparing(D11Match::getDatetime));

        final Set<D11Team> d11Teams = entities.stream()
                .map(D11Match::getHomeD11Team).collect(Collectors.toSet());
        d11Teams.addAll(entities.stream()
                                .map(D11Match::getAwayD11Team).collect(Collectors.toSet()));
        final Set<Season> seasons = entities.stream()
                .map(d11Match -> d11Match.getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(d11Teams.size() > 1,
                   "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime d11Teams size > 1");
        assertTrue(seasons.size() > 1,
                   "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime seasons size > 1");

        for (final D11Team d11Team : d11Teams) {
            for (final Season season : seasons) {
                final List<D11Match> result =
                        getRepository().findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(d11Team.getId(),
                                                                                                season.getId());

                final List<D11Match> expected = entities.stream()
                        .filter(d11Match -> (d11Match.getHomeD11Team().equals(d11Team)
                                             || d11Match.getAwayD11Team().equals(d11Team))
                                            && d11Match.getMatchWeek().getSeason().equals(season))
                        .toList();

                assertTrue(expected.size() > 1,
                           "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime expected size > 1");

                assertNotNull(result,
                              "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime not null");
                assertFalse(result.isEmpty(),
                            "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime empty");
                assertEquals(expected, result,
                             "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime equals");
            }
        }
    }

}
