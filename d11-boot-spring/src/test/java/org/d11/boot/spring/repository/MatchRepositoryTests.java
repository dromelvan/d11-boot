package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.model.Match;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Match repository tests.
 */
class MatchRepositoryTests extends D11BootRepositoryTests<Match, MatchRepository> {

    /**
     * Creates new match repository tests.
     */
    MatchRepositoryTests() {
        super(Match.class);
    }

    @Override
    protected void beforeSave(final Match match) {
        super.beforeSave(match);
        match.getMatchWeek().setId(null);
        match.getMatchWeek().getSeason().setId(null);
        match.getHomeTeam().setId(null);
        match.getHomeTeam().getStadium().setId(null);
        match.getAwayTeam().setId(null);
        match.getAwayTeam().getStadium().setId(null);
        match.getMatchWeek().setId(null);
        match.getStadium().setId(null);
    }

    /**
     * Tests MatchRepository::findByWhoscoredId.
     */
    @Test
    void testFindByWhoscoredId() {
        final List<Match> matches = getEntities();
        matches.forEach(match -> match.setWhoscoredId(matches.indexOf(match)));

        matches.forEach(match -> {
            final Match result = getRepository().findByWhoscoredId(match.getWhoscoredId())
                    .orElseThrow(NotFoundException::new);

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
        final Match match = entities.get(0);
        entities.get(1).setHomeTeam(match.getHomeTeam());
        entities.get(1).setMatchWeek(match.getMatchWeek());

        final List<Long> matchIds = entities.stream()
                .filter(filteredMatch ->
                                (filteredMatch.getHomeTeam().equals(match.getHomeTeam())
                                 || filteredMatch.getAwayTeam().equals(match.getAwayTeam()))
                                && filteredMatch.getMatchWeek().getSeason().equals(match.getMatchWeek().getSeason()))
                .sorted(Comparator.comparing(Match::getDatetime))
                .mapToLong(D11Entity::getId)
                .boxed()
                .toList();

        final List<Long> result = getRepository()
                .findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(match.getHomeTeam().getId(),
                                                                 match.getMatchWeek().getSeason().getId());

        assertNotNull(result, "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime not null");
        assertTrue(result.size() > 1, "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime size");
        assertEquals(matchIds, result, "MatchRepository::findByTeamIdAndMatchWeekSeasonIdOrderByDatetime equals");
    }

    /**
     * Tests MatchRepository::findByTeamIdAndMatchWeekSeasonId.
     */
    @Test
    void testFindByTeamIdAndMatchWeekSeasonId() {
        final List<Match> entities = getEntities();
        final Match match = entities.get(0);
        entities.get(1).setHomeTeam(match.getHomeTeam());
        entities.get(1).setMatchWeek(match.getMatchWeek());

        final List<Match> matchIds = entities.stream()
                .filter(filteredMatch ->
                                (filteredMatch.getHomeTeam().equals(match.getHomeTeam())
                                 || filteredMatch.getAwayTeam().equals(match.getAwayTeam()))
                                && filteredMatch.getMatchWeek().getSeason().equals(match.getMatchWeek().getSeason()))
                .sorted(Comparator.comparing(Match::getDatetime))
                .toList();

        final List<Match> result =
                getRepository().findByTeamIdAndMatchWeekSeasonId(match.getHomeTeam().getId(),
                                                                 match.getMatchWeek().getSeason().getId());

        assertNotNull(result, "MatchRepository::findByTeamIdAndMatchWeekSeasonId not null");
        assertTrue(result.size() > 1, "MatchRepository::findByTeamIdAndMatchWeekSeasonId size");
        assertEquals(matchIds, result, "MatchRepository::findByTeamIdAndMatchWeekSeasonId equals");
    }

}
