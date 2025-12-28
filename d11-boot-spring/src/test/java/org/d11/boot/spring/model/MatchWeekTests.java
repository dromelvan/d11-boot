package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match week tests.
 */
class MatchWeekTests extends EasyRandomTests {

    /**
     * Tests MatchWeek::isValid.
     */
    @Test
    void testIsValid() {
        final MatchWeek matchWeek = generate(MatchWeek.class);

        assertTrue(matchWeek.isValid());

        matchWeek.setMatchWeekNumber(0);
        assertFalse(matchWeek.isValid());
        matchWeek.setMatchWeekNumber(MatchWeek.MAX_MATCH_WEEK_NUMBER + 1);
        assertFalse(matchWeek.isValid());
        matchWeek.setMatchWeekNumber(1);

        matchWeek.setDate(null);
        assertFalse(matchWeek.isValid());
        matchWeek.setDate(LocalDate.now());

        matchWeek.setElapsed(-1);
        assertFalse(matchWeek.isValid());
        matchWeek.setElapsed(MatchWeek.MAX_ELAPSED + 1);
        assertFalse(matchWeek.isValid());
        matchWeek.setElapsed(1);

        matchWeek.setStatus(null);
        assertFalse(matchWeek.isValid());
        matchWeek.setStatus(Status.PENDING);

        matchWeek.setSeason(null);
        assertFalse(matchWeek.isValid());
        matchWeek.setSeason(new Season());

        assertTrue(matchWeek.isValid());
    }

    /**
     * Tests MatchWeek::isStarted.
     */
    @Test
    void testIsStarted() {
        final MatchWeek matchWeek = generate(MatchWeek.class);

        matchWeek.setStatus(Status.PENDING);
        assertFalse(matchWeek.isStarted());

        matchWeek.setStatus(Status.POSTPONED);
        assertFalse(matchWeek.isStarted());

        matchWeek.setStatus(Status.ACTIVE);
        assertTrue(matchWeek.isStarted());

        matchWeek.setStatus(Status.FULL_TIME);
        assertTrue(matchWeek.isStarted());

        matchWeek.setStatus(Status.FINISHED);
        assertTrue(matchWeek.isStarted());
    }

    /**
     * Tests MatchWeek::getMatchByTeam.
     */
    @Test
    void testGetMatchByTeam() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        matchWeek.setMatches(generateList(Match.class));

        assertFalse(matchWeek.getMatches().isEmpty());

        for (final Match match : matchWeek.getMatches()) {
            final Optional<Match> homeTeamOptional = matchWeek.getMatchByTeam(match.getHomeTeam());
            final Optional<Match> awayTeamOptional = matchWeek.getMatchByTeam(match.getAwayTeam());

            assertTrue(homeTeamOptional.isPresent());
            homeTeamOptional.ifPresent(homeTeamMatch -> assertEquals(match, homeTeamMatch));

            assertTrue(awayTeamOptional.isPresent());
            awayTeamOptional.ifPresent(awayTeamMatch -> assertEquals(match, awayTeamMatch));
        }
    }

    /**
     * Tests MatchWeek::getD11MatchByD11Team.
     */
    @Test
    void testGetD11MatchByD11Team() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        matchWeek.setD11Matches(generateList(D11Match.class));

        assertFalse(matchWeek.getD11Matches().isEmpty());

        for (final D11Match d11Match : matchWeek.getD11Matches()) {
            final Optional<D11Match> homeD11TeamOptional = matchWeek.getD11MatchByD11Team(d11Match.getHomeD11Team());
            final Optional<D11Match> awayD11TeamOptional = matchWeek.getD11MatchByD11Team(d11Match.getAwayD11Team());

            assertTrue(homeD11TeamOptional.isPresent());
            homeD11TeamOptional.ifPresent(homeD11TeamMatch -> assertEquals(d11Match, homeD11TeamMatch));

            assertTrue(awayD11TeamOptional.isPresent());
            awayD11TeamOptional.ifPresent(awayD11TeamMatch -> assertEquals(d11Match, awayD11TeamMatch));
        }
    }

    /**
     * Tests MatchWeek::compareTo.
     */
    @Test
    void testCompareTo() {
        final List<MatchWeek> matchWeeks = generateList(MatchWeek.class);
        final List<MatchWeek> sorted = new ArrayList<>(matchWeeks);

        Collections.sort(sorted);

        matchWeeks.sort(Comparator.comparingInt(MatchWeek::getMatchWeekNumber));

        assertEquals(matchWeeks, sorted);
    }

}
