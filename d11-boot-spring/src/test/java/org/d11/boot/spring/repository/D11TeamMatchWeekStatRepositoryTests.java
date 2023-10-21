package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.D11TeamMatchWeekStat;
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
 * D11 Team match week stat repository tests.
 */
class D11TeamMatchWeekStatRepositoryTests extends AbstractRepositoryTests<D11TeamMatchWeekStat,
                                                                         D11TeamMatchWeekStatRepository> {

    /**
     * Tests D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate.
     */
    @Test
    void testFindByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate() {
        final List<D11TeamMatchWeekStat> entities = getEntities();
        entities.sort(Comparator.comparing(d11TeamMatchWeekStat -> d11TeamMatchWeekStat.getMatchWeek().getDate()));

        final Set<D11Team> d11Teams = entities.stream()
                .map(D11TeamMatchWeekStat::getD11Team).collect(Collectors.toSet());
        final Set<Season> seasons = entities.stream()
                .map(d11TeamMatchWeekStat -> d11TeamMatchWeekStat.getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(d11Teams.size() > 1,
                   """
                   D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate
                   d11Teams size > 1
                   """);
        assertTrue(seasons.size() > 1,
                   """
                   D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate
                   seasons size > 1
                   """);

        for (final D11Team d11Team : d11Teams) {
            for (final Season season : seasons) {
                final List<D11TeamMatchWeekStat> result =
                        getRepository().findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate(d11Team.getId(),
                                                                                                season.getId());

                final List<D11TeamMatchWeekStat> expected = entities.stream()
                        .filter(d11TeamMatchWeekStat -> d11TeamMatchWeekStat.getD11Team().equals(d11Team)
                                                        && d11TeamMatchWeekStat.getMatchWeek().getSeason()
                                                                .equals(season))
                        .toList();

                assertTrue(expected.size() > 1,
                           """
                           D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate
                           expected size > 1
                           """);

                assertNotNull(result,
                              """
                              D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate
                              not null
                              """);
                assertFalse(result.isEmpty(),
                            """
                            D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate 
                            empty
                            """);
                assertEquals(expected, result,
                             """
                             D11TeamMatchWeekStatRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByMatchWeekDate 
                             equals
                             """);
            }
        }
    }

}
