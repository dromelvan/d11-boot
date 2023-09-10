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
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final MatchWeek matchWeek = generate(MatchWeek.class);

        assertTrue(matchWeek.isValid(), "MatchWeek::isValid");

        matchWeek.setMatchWeekNumber(0);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid match week number too low");
        matchWeek.setMatchWeekNumber(MatchWeek.MAX_MATCH_WEEK_NUMBER + 1);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid match week number too high");
        matchWeek.setMatchWeekNumber(1);

        matchWeek.setDate(null);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid date null");
        matchWeek.setDate(LocalDate.now());

        matchWeek.setElapsed(-1);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid elapsed too low");
        matchWeek.setElapsed(MatchWeek.MAX_ELAPSED + 1);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid elapsed too high");
        matchWeek.setElapsed(1);

        matchWeek.setStatus(null);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid status null");
        matchWeek.setStatus(Status.PENDING);

        matchWeek.setSeason(null);
        assertFalse(matchWeek.isValid(), "MatchWeek::isValid season null");
        matchWeek.setSeason(new Season());

        assertTrue(matchWeek.isValid(), "MatchWeek::isValid valid");
    }

    /**
     * Tests MatchWeek::isStarted.
     */
    @Test
    void testIsStarted() {
        final MatchWeek matchWeek = generate(MatchWeek.class);

        matchWeek.setStatus(Status.PENDING);
        assertFalse(matchWeek.isStarted(), "MatchWeek::isStarted status PENDING");

        matchWeek.setStatus(Status.POSTPONED);
        assertFalse(matchWeek.isStarted(), "MatchWeek::isStarted status POSTPONED");

        matchWeek.setStatus(Status.ACTIVE);
        assertTrue(matchWeek.isStarted(), "MatchWeek::isStarted status ACTIVE");

        matchWeek.setStatus(Status.FULL_TIME);
        assertTrue(matchWeek.isStarted(), "MatchWeek::isStarted status FULL_TIME");

        matchWeek.setStatus(Status.FINISHED);
        assertTrue(matchWeek.isStarted(), "MatchWeek::isStarted status FINISHED");
    }

    /**
     * Tests MatchWeek::getMatchByTeam.
     */
    @Test
    void testGetMatchByTeam() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        matchWeek.setMatches(generateList(Match.class));

        assertFalse(matchWeek.getMatches().isEmpty(), "MatchWeek::getMatchByTeam matches empty");

        for (final Match match : matchWeek.getMatches()) {
            final Optional<Match> homeTeamOptional = matchWeek.getMatchByTeam(match.getHomeTeam());
            final Optional<Match> awayTeamOptional = matchWeek.getMatchByTeam(match.getAwayTeam());

            assertTrue(homeTeamOptional.isPresent(), "MatchWeek::getMatchByTeam home team present");
            homeTeamOptional.ifPresent(homeTeamMatch -> assertEquals(match, homeTeamMatch,
                                                                     "MatchWeek::getMatchByTeam home team equals"));

            assertTrue(awayTeamOptional.isPresent(), "MatchWeek::getMatchByTeam away team present");
            awayTeamOptional.ifPresent(awayTeamMatch -> assertEquals(match, awayTeamMatch,
                                                                     "MatchWeek::getMatchByTeam away team equals"));
        }
    }

    /**
     * Tests MatchWeek::getD11MatchByD11Team.
     */
    @Test
    void testGetD11MatchByD11Team() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        matchWeek.setD11Matches(generateList(D11Match.class));

        assertFalse(matchWeek.getD11Matches().isEmpty(), "MatchWeek::getD11MatchByD11Team D11 matches empty");

        for (final D11Match d11Match : matchWeek.getD11Matches()) {
            final Optional<D11Match> homeD11TeamOptional = matchWeek.getD11MatchByD11Team(d11Match.getHomeD11Team());
            final Optional<D11Match> awayD11TeamOptional = matchWeek.getD11MatchByD11Team(d11Match.getAwayD11Team());

            assertTrue(homeD11TeamOptional.isPresent(), "MatchWeek::getD11MatchByD11Team home D11 team present");
            homeD11TeamOptional.ifPresent(homeD11TeamMatch ->
                                                  assertEquals(d11Match, homeD11TeamMatch,
                                                               "MatchWeek::getD11MatchByD11Team home D11 team equals"));

            assertTrue(awayD11TeamOptional.isPresent(), "MatchWeek::getD11MatchByD11Team away D11 team present");
            awayD11TeamOptional.ifPresent(awayD11TeamMatch ->
                                                  assertEquals(d11Match, awayD11TeamMatch,
                                                               "MatchWeek::getD11MatchByD11Team away D11 team equals"));
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

        assertEquals(matchWeeks, sorted, "MatchWeek::compareTo equals");
    }

}
