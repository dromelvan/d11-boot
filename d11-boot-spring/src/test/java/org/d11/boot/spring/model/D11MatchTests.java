package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 match tests.
 */
class D11MatchTests extends EasyRandomTests {

    /**
     * Tests D11Match::isValid.
     */
    @Test
    @SuppressWarnings({"DataFlowIssue", "checkstyle:ExecutableStatementCount"})
    void testIsValid() {
        final D11Match d11Match = generate(D11Match.class);

        assertTrue(d11Match.isValid(), "D11Match::isValid");

        d11Match.setDatetime(null);
        assertFalse(d11Match.isValid(), "D11Match::isValid datetime null");
        d11Match.setDatetime(LocalDateTime.now());

        d11Match.setHomeTeamGoalsScored(-1);
        assertFalse(d11Match.isValid(), "D11Match::isValid home team goals scored negative");
        d11Match.setHomeTeamGoalsScored(1);

        d11Match.setAwayTeamGoalsScored(-1);
        assertFalse(d11Match.isValid(), "D11Match::isValid away team goals scored negative");
        d11Match.setAwayTeamGoalsScored(1);

        d11Match.setPreviousHomeTeamGoalsScored(-1);
        assertFalse(d11Match.isValid(), "D11Match::isValid previous home team goals scored negative");
        d11Match.setPreviousHomeTeamGoalsScored(1);

        d11Match.setPreviousAwayTeamGoalsScored(-1);
        assertFalse(d11Match.isValid(), "D11Match::isValid previous away team goals scored negative");
        d11Match.setPreviousAwayTeamGoalsScored(1);

        d11Match.setElapsed(null);
        assertFalse(d11Match.isValid(), "D11Match::isValid elapsed null");
        d11Match.setElapsed("");
        assertFalse(d11Match.isValid(), "D11Match::isValid elapsed empty");
        d11Match.setElapsed("ABCDEFGHIJKL");
        assertFalse(d11Match.isValid(), "D11Match::isValid elapsed too long");
        d11Match.setElapsed("A");

        d11Match.setStatus(null);
        assertFalse(d11Match.isValid(), "D11Match::isValid status null");
        d11Match.setStatus(Status.PENDING);

        d11Match.setHomeD11Team(null);
        assertFalse(d11Match.isValid(), "D11Match::isValid home D11 team null");
        d11Match.setHomeD11Team(new D11Team());

        d11Match.setAwayD11Team(null);
        assertFalse(d11Match.isValid(), "D11Match::isValid away D11 team null");
        d11Match.setAwayD11Team(new D11Team());

        d11Match.setMatchWeek(null);
        assertFalse(d11Match.isValid(), "D11Match::isValid match week null");
        d11Match.setMatchWeek(new MatchWeek());

        assertTrue(d11Match.isValid(), "Match::isValid valid");
    }

    /**
     * Tests D11Match::isParticipant.
     */
    @Test
    void testIsParticipant() {
        final D11Match d11Match = generate(D11Match.class);

        assertTrue(d11Match.isParticipant(d11Match.getHomeD11Team()), "D11Match::isParticipant home D11 team");
        assertTrue(d11Match.isParticipant(d11Match.getAwayD11Team()), "D11Match::isParticipant away D11 team");

        final D11Team d11Team = new D11Team();
        d11Team.setId(-1L);

        assertFalse(d11Match.isParticipant(d11Team), "D11Match::isParticipant non participant D11 team");
    }

    /**
     * Tests D11Match::isStarted.
     */
    @Test
    void testIsStarted() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setStatus(Status.PENDING);
        assertFalse(d11Match.isStarted(), "D11Match::isStarted status PENDING");

        d11Match.setStatus(Status.POSTPONED);
        assertFalse(d11Match.isStarted(), "D11Match::isStarted status POSTPONED");

        d11Match.setStatus(Status.ACTIVE);
        assertTrue(d11Match.isStarted(), "D11Match::isStarted status ACTIVE");

        d11Match.setStatus(Status.FULL_TIME);
        assertTrue(d11Match.isStarted(), "D11Match::isStarted status FULL_TIME");

        d11Match.setStatus(Status.FINISHED);
        assertTrue(d11Match.isStarted(), "D11Match::isStarted status FINISHED");
    }

    /**
     * Tests D11Match::isWinner.
     */
    @Test
    void testIsWinner() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertTrue(d11Match.isWinner(d11Match.getHomeD11Team()), "D11Match::isWinner home team winner");
        assertFalse(d11Match.isWinner(d11Match.getAwayD11Team()), "D11Match::isWinner away team not winner");

        d11Match.setHomeTeamGoalsScored(0);

        assertFalse(d11Match.isWinner(d11Match.getHomeD11Team()), "D11Match::isWinner home team draw");
        assertFalse(d11Match.isWinner(d11Match.getAwayD11Team()), "D11Match::isWinner away team draw");

        d11Match.setAwayTeamGoalsScored(1);

        assertFalse(d11Match.isWinner(d11Match.getHomeD11Team()), "D11Match::isWinner home team not winner");
        assertTrue(d11Match.isWinner(d11Match.getAwayD11Team()), "D11Match::isWinner away team winner");
    }

    /**
     * Tests D11Match::isLoser.
     */
    @Test
    void testIsLoser() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(0);
        d11Match.setAwayTeamGoalsScored(1);

        assertTrue(d11Match.isLoser(d11Match.getHomeD11Team()), "D11Match::isLoser home team loser");
        assertFalse(d11Match.isLoser(d11Match.getAwayD11Team()), "D11Match::isLoser away team not loser");

        d11Match.setAwayTeamGoalsScored(0);

        assertFalse(d11Match.isLoser(d11Match.getHomeD11Team()), "D11Match::isLoser home team draw");
        assertFalse(d11Match.isLoser(d11Match.getAwayD11Team()), "D11Match::isLoser away team draw");

        d11Match.setHomeTeamGoalsScored(1);

        assertFalse(d11Match.isLoser(d11Match.getHomeD11Team()), "D11Match::isLoser home team not winner");
        assertTrue(d11Match.isLoser(d11Match.getAwayD11Team()), "D11Match::isLoser away team winner");
    }

    /**
     * Tests D11Match::isDraw.
     */
    @Test
    void testIsDraw() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertFalse(d11Match.isDraw(), "D11Match::isDraw home team winner");

        d11Match.setHomeTeamGoalsScored(0);

        assertTrue(d11Match.isDraw(), "D11Match::isDraw draw");

        d11Match.setAwayTeamGoalsScored(1);

        assertFalse(d11Match.isDraw(), "D11Match::isDraw away team winner");
    }

    /**
     * Tests D11Match::getGoalsFor.
     */
    @Test
    void testGetGoalsFor() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertEquals(d11Match.getHomeTeamGoalsScored(), d11Match.getGoalsFor(d11Match.getHomeD11Team()),
                     "D11Match::getGoalsFor home team");
        assertEquals(d11Match.getAwayTeamGoalsScored(), d11Match.getGoalsFor(d11Match.getAwayD11Team()),
                     "D11Match::getGoalsFor away team");
    }

    /**
     * Tests D11Match::getGoalsAgainst.
     */
    @Test
    void testGetGoalsAgainst() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertEquals(d11Match.getAwayTeamGoalsScored(), d11Match.getGoalsAgainst(d11Match.getHomeD11Team()),
                     "D11Match::getGoalsAgainst home team");
        assertEquals(d11Match.getHomeTeamGoalsScored(), d11Match.getGoalsAgainst(d11Match.getAwayD11Team()),
                     "D11Match::getGoalsAgainst away team");
    }

    /**
     * Tests D11Match::getPoints.
     */
    @Test
    void testGetPoints() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertEquals(Match.WIN_POINTS, d11Match.getPoints(d11Match.getHomeD11Team()),
                     "D11Match::getPoints home team winner");
        assertEquals(Match.LOSS_POINTS, d11Match.getPoints(d11Match.getAwayD11Team()),
                     "D11Match::getPoints away team loser");

        d11Match.setHomeTeamGoalsScored(0);

        assertEquals(Match.DRAW_POINTS, d11Match.getPoints(d11Match.getHomeD11Team()),
                     "D11Match::getPoints home team draw");
        assertEquals(Match.DRAW_POINTS, d11Match.getPoints(d11Match.getAwayD11Team()),
                     "D11Match::getPoints away team draw");

        d11Match.setAwayTeamGoalsScored(1);

        assertEquals(Match.LOSS_POINTS, d11Match.getPoints(d11Match.getHomeD11Team()),
                     "D11Match::getPoints home team loser");
        assertEquals(Match.WIN_POINTS, d11Match.getPoints(d11Match.getAwayD11Team()),
                     "D11Match::getPoints away team winner");
    }

    /**
     * Tests D11Match::update.
     */
    @Test
    void testUpdate() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.update();

        // Add proper tests here when we have player match stats

        assertEquals(0, d11Match.getHomeTeamGoalsScored(), "D11Match::update home team goals scored equals");
        assertEquals(0, d11Match.getAwayTeamGoalsScored(), "D11Match::update away team goals scored equals");
        assertEquals(0, d11Match.getHomeTeamPoints(), "D11Match::update home team points equals");
        assertEquals(0, d11Match.getAwayTeamPoints(), "D11Match::update away team points equals");
    }

    /**
     * Tests D11Match::reset.
     */
    @Test
    void testReset() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(1);
        d11Match.setPreviousHomeTeamGoalsScored(1);
        d11Match.setPreviousAwayTeamGoalsScored(1);
        d11Match.setHomeTeamPoints(1);
        d11Match.setAwayTeamPoints(1);
        d11Match.setPreviousHomeTeamPoints(1);
        d11Match.setPreviousAwayTeamPoints(1);

        d11Match.reset();

        assertEquals(0, d11Match.getHomeTeamGoalsScored(), "D11Match::reset home team goals scored equals");
        assertEquals(0, d11Match.getAwayTeamGoalsScored(), "D11Match::reset away team goals scored equals");
        assertEquals(1, d11Match.getPreviousHomeTeamGoalsScored(),
                     "D11Match::reset previous home team goals scored equals");
        assertEquals(1, d11Match.getPreviousAwayTeamGoalsScored(),
                     "D11Match::reset previous away team goals scored equals");
        assertEquals(0, d11Match.getHomeTeamPoints(), "D11Match::reset home team points equals");
        assertEquals(0, d11Match.getAwayTeamPoints(), "D11Match::reset away team points equals");
        assertEquals(1, d11Match.getPreviousHomeTeamPoints(), "D11Match::reset previous home team points equals");
        assertEquals(1, d11Match.getPreviousAwayTeamPoints(), "D11Match::reset previous away team points equals");
    }

}
