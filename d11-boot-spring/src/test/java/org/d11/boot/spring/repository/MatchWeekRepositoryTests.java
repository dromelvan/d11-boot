package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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

        assertNotNull(result, "MatchWeekRepository::findFirstByDateLessThanEqualOrderByDateDesc not null");
        assertEquals(matchWeek, result, "MatchWeekRepository::findFirstByDateLessThanEqualOrderByDateDesc equals");
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

        assertNotNull(result, "MatchWeekRepository::findFirstByDateGreaterThanOrderByDateAsc not null");
        assertEquals(matchWeek, result, "MatchWeekRepository::findFirstByDateGreaterThanOrderByDateAsc equals");
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

        assertNotNull(result, "MatchWeekRepository::findFirstBySeasonStatusOrderByDateAsc not null");
        assertEquals(matchWeek, result, "MatchWeekRepository::findFirstBySeasonStatusOrderByDateAsc equals");
    }

}
