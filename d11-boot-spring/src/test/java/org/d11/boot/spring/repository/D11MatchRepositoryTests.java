package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.model.D11Match;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * D11Match repository tests.
 */
class D11MatchRepositoryTests extends D11BootRepositoryTests<D11Match, D11MatchRepository> {

    /**
     * Creates new D11 match repository tests.
     */
    D11MatchRepositoryTests() {
        super(D11Match.class);
    }

    @Override
    protected void beforeSave(final D11Match d11Match) {
        super.beforeSave(d11Match);
        d11Match.getMatchWeek().setId(null);
        d11Match.getMatchWeek().getSeason().setId(null);
        d11Match.getHomeD11Team().setId(null);
        d11Match.getHomeD11Team().getOwner().setId(null);
        d11Match.getHomeD11Team().getCoOwner().setId(null);
        d11Match.getAwayD11Team().setId(null);
        d11Match.getAwayD11Team().getOwner().setId(null);
        d11Match.getAwayD11Team().getCoOwner().setId(null);
    }

    /**
     * Tests D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime.
     */
    @Test
    void testFindByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime() {
        final List<D11Match> entities = getEntities();
        final D11Match d11Match = entities.get(0);
        entities.get(1).setHomeD11Team(d11Match.getHomeD11Team());
        entities.get(1).setMatchWeek(d11Match.getMatchWeek());

        final List<Long> matchIds = entities.stream()
                .filter(filteredMatch ->
                                (filteredMatch.getHomeD11Team().equals(d11Match.getHomeD11Team())
                                 || filteredMatch.getAwayD11Team().equals(d11Match.getAwayD11Team()))
                                && filteredMatch.getMatchWeek().getSeason().equals(d11Match.getMatchWeek().getSeason()))
                .sorted(Comparator.comparing(D11Match::getDatetime))
                .mapToLong(D11Entity::getId)
                .boxed()
                .toList();

        final List<Long> result = getRepository()
                .findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(d11Match.getHomeD11Team().getId(),
                                                                    d11Match.getMatchWeek().getSeason().getId());

        assertNotNull(result, "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime not null");
        assertTrue(result.size() > 1, "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime size");
        assertEquals(matchIds, result, "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime equals");
    }

    /**
     * Tests D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonId.
     */
    @Test
    void testFindByD11TeamIdAndMatchWeekSeasonId() {
        final List<D11Match> entities = getEntities();
        final D11Match d11Match = entities.get(0);
        entities.get(1).setHomeD11Team(d11Match.getHomeD11Team());
        entities.get(1).setMatchWeek(d11Match.getMatchWeek());

        final List<D11Match> matchIds = entities.stream()
                .filter(filteredMatch ->
                                (filteredMatch.getHomeD11Team().equals(d11Match.getHomeD11Team())
                                 || filteredMatch.getAwayD11Team().equals(d11Match.getAwayD11Team()))
                                && filteredMatch.getMatchWeek().getSeason().equals(d11Match.getMatchWeek().getSeason()))
                .sorted(Comparator.comparing(D11Match::getDatetime))
                .toList();

        final List<D11Match> result =
                getRepository().findByD11TeamIdAndMatchWeekSeasonId(d11Match.getHomeD11Team().getId(),
                                                                    d11Match.getMatchWeek().getSeason().getId());

        assertNotNull(result, "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonId not null");
        assertTrue(result.size() > 1, "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonId size");
        assertEquals(matchIds, result, "D11MatchRepository::findByD11TeamIdAndMatchWeekSeasonId equals");
    }

}
