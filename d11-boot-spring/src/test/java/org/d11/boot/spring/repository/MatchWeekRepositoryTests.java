package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match week repository tests.
 */
class MatchWeekRepositoryTests extends AbstractRepositoryTests<MatchWeek, MatchWeekRepository> {

    /**
     * Tests MatchWeekRepository::findFirstByDateLessThanEqualOrderByDateDesc.
     */
    @Test
    void testFindFirstByDateLessThanEqualOrderByDateDesc() {
        final List<MatchWeek> matchWeeks = getEntities();
        matchWeeks.sort(Comparator.comparing(MatchWeek::getDate));

        final MatchWeek matchWeek = matchWeeks.get(0);

        final MatchWeek result = getRepository().findFirstByDateLessThanEqualOrderByDateDesc(matchWeek.getDate())
                .orElse(null);

        assertNotNull(result);
        assertEquals(matchWeek, result);
    }

    /**
     * Tests MatchWeekRepository::findFirstByDateGreaterThanOrderByDateAsc.
     */
    @Test
    void testFindFirstByDateGreaterThanOrderByDateAsc() {
        final List<MatchWeek> matchWeeks = getEntities();
        matchWeeks.sort(Comparator.comparing(MatchWeek::getDate));

        final MatchWeek matchWeek = matchWeeks.get(matchWeeks.size() - 1);
        final LocalDate localDate = matchWeek.getDate();
        matchWeek.setDate(localDate.plusDays(1));
        getRepository().save(matchWeek);

        final MatchWeek result = getRepository().findFirstByDateGreaterThanOrderByDateAsc(localDate).orElse(null);

        assertNotNull(result);
        assertEquals(matchWeek, result);
    }

    /**
     * Tests MatchWeekRepository::findFirstBySeasonStatusOrderByDateAsc.
     */
    @Test
    void testFindFirstBySeasonStatusOrderByDateAsc() {
        final List<MatchWeek> matchWeeks = getEntities();
        matchWeeks.sort(Comparator.comparing(MatchWeek::getDate));
        matchWeeks.forEach(matchWeek -> matchWeek.getSeason().setStatus(Status.FINISHED));

        final MatchWeek matchWeek = matchWeeks.get(0);
        matchWeek.getSeason().setStatus(Status.PENDING);
        getRepository().saveAll(matchWeeks);

        final MatchWeek result = getRepository().findFirstBySeasonStatusOrderByDateAsc(Status.PENDING).orElse(null);

        assertNotNull(result);
        assertEquals(matchWeek, result);
    }

    /**
     * Tests MatchWeekRepository::findBySeasonIdOrderByDate.
     */
    @Test
    void testFindBySeasonIdOrderByDate() {
        final List<MatchWeek> entities = getEntities();
        entities.sort(Comparator.comparing(MatchWeek::getDate));

        final Set<Season> seasons = entities.stream()
                .map(MatchWeek::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1);

        for (final Season season : seasons) {
            final List<MatchWeek> result = getRepository().findBySeasonIdOrderByDate(season.getId());

            final List<MatchWeek> expected = entities.stream()
                    .filter(matchWeek -> matchWeek.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(expected, result);
        }
    }

}
