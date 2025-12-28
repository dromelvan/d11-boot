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
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testIsValid() {
        final D11Match d11Match = generate(D11Match.class);

        assertTrue(d11Match.isValid());

        d11Match.setDatetime(null);
        assertFalse(d11Match.isValid());
        d11Match.setDatetime(LocalDateTime.now());

        d11Match.setHomeTeamGoalsScored(-1);
        assertFalse(d11Match.isValid());
        d11Match.setHomeTeamGoalsScored(1);

        d11Match.setAwayTeamGoalsScored(-1);
        assertFalse(d11Match.isValid());
        d11Match.setAwayTeamGoalsScored(1);

        d11Match.setPreviousHomeTeamGoalsScored(-1);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousHomeTeamGoalsScored(1);

        d11Match.setPreviousAwayTeamGoalsScored(-1);
        assertFalse(d11Match.isValid());
        d11Match.setPreviousAwayTeamGoalsScored(1);

        d11Match.setElapsed(null);
        assertFalse(d11Match.isValid());
        d11Match.setElapsed("");
        assertFalse(d11Match.isValid());
        d11Match.setElapsed("ABCDEFGHIJKL");
        assertFalse(d11Match.isValid());
        d11Match.setElapsed("A");

        d11Match.setStatus(null);
        assertFalse(d11Match.isValid());
        d11Match.setStatus(Status.PENDING);

        d11Match.setHomeD11Team(null);
        assertFalse(d11Match.isValid());
        d11Match.setHomeD11Team(new D11Team());

        d11Match.setAwayD11Team(null);
        assertFalse(d11Match.isValid());
        d11Match.setAwayD11Team(new D11Team());

        d11Match.setMatchWeek(null);
        assertFalse(d11Match.isValid());
        d11Match.setMatchWeek(new MatchWeek());

        assertTrue(d11Match.isValid());
    }

    /**
     * Tests D11Match::isParticipant.
     */
    @Test
    void testIsParticipant() {
        final D11Match d11Match = generate(D11Match.class);

        assertTrue(d11Match.isParticipant(d11Match.getHomeD11Team()));
        assertTrue(d11Match.isParticipant(d11Match.getAwayD11Team()));

        final D11Team d11Team = new D11Team();
        d11Team.setId(-1L);

        assertFalse(d11Match.isParticipant(d11Team));
    }

    /**
     * Tests D11Match::isStarted.
     */
    @Test
    void testIsStarted() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setStatus(Status.PENDING);
        assertFalse(d11Match.isStarted());

        d11Match.setStatus(Status.POSTPONED);
        assertFalse(d11Match.isStarted());

        d11Match.setStatus(Status.ACTIVE);
        assertTrue(d11Match.isStarted());

        d11Match.setStatus(Status.FULL_TIME);
        assertTrue(d11Match.isStarted());

        d11Match.setStatus(Status.FINISHED);
        assertTrue(d11Match.isStarted());
    }

    /**
     * Tests D11Match::isWinner.
     */
    @Test
    void testIsWinner() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertTrue(d11Match.isWinner(d11Match.getHomeD11Team()));
        assertFalse(d11Match.isWinner(d11Match.getAwayD11Team()));

        d11Match.setHomeTeamGoalsScored(0);

        assertFalse(d11Match.isWinner(d11Match.getHomeD11Team()));
        assertFalse(d11Match.isWinner(d11Match.getAwayD11Team()));

        d11Match.setAwayTeamGoalsScored(1);

        assertFalse(d11Match.isWinner(d11Match.getHomeD11Team()));
        assertTrue(d11Match.isWinner(d11Match.getAwayD11Team()));
    }

    /**
     * Tests D11Match::isLoser.
     */
    @Test
    void testIsLoser() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(0);
        d11Match.setAwayTeamGoalsScored(1);

        assertTrue(d11Match.isLoser(d11Match.getHomeD11Team()));
        assertFalse(d11Match.isLoser(d11Match.getAwayD11Team()));

        d11Match.setAwayTeamGoalsScored(0);

        assertFalse(d11Match.isLoser(d11Match.getHomeD11Team()));
        assertFalse(d11Match.isLoser(d11Match.getAwayD11Team()));

        d11Match.setHomeTeamGoalsScored(1);

        assertFalse(d11Match.isLoser(d11Match.getHomeD11Team()));
        assertTrue(d11Match.isLoser(d11Match.getAwayD11Team()));
    }

    /**
     * Tests D11Match::isDraw.
     */
    @Test
    void testIsDraw() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertFalse(d11Match.isDraw());

        d11Match.setHomeTeamGoalsScored(0);

        assertTrue(d11Match.isDraw());

        d11Match.setAwayTeamGoalsScored(1);

        assertFalse(d11Match.isDraw());
    }

    /**
     * Tests D11Match::getGoalsFor.
     */
    @Test
    void testGetGoalsFor() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertEquals(d11Match.getHomeTeamGoalsScored(), d11Match.getGoalsFor(d11Match.getHomeD11Team()));
        assertEquals(d11Match.getAwayTeamGoalsScored(), d11Match.getGoalsFor(d11Match.getAwayD11Team()));
    }

    /**
     * Tests D11Match::getGoalsAgainst.
     */
    @Test
    void testGetGoalsAgainst() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertEquals(d11Match.getAwayTeamGoalsScored(), d11Match.getGoalsAgainst(d11Match.getHomeD11Team()));
        assertEquals(d11Match.getHomeTeamGoalsScored(), d11Match.getGoalsAgainst(d11Match.getAwayD11Team()));
    }

    /**
     * Tests D11Match::getPoints.
     */
    @Test
    void testGetPoints() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        assertEquals(Match.WIN_POINTS, d11Match.getPoints(d11Match.getHomeD11Team()));
        assertEquals(Match.LOSS_POINTS, d11Match.getPoints(d11Match.getAwayD11Team()));

        d11Match.setHomeTeamGoalsScored(0);

        assertEquals(Match.DRAW_POINTS, d11Match.getPoints(d11Match.getHomeD11Team()));
        assertEquals(Match.DRAW_POINTS, d11Match.getPoints(d11Match.getAwayD11Team()));

        d11Match.setAwayTeamGoalsScored(1);

        assertEquals(Match.LOSS_POINTS, d11Match.getPoints(d11Match.getHomeD11Team()));
        assertEquals(Match.WIN_POINTS, d11Match.getPoints(d11Match.getAwayD11Team()));
    }

    /**
     * Tests D11Match::update.
     */
    @Test
    void testUpdate() {
        final D11Match d11Match = generate(D11Match.class);

        d11Match.update();

        // Add proper tests here when we have player match stats

        assertEquals(0, d11Match.getHomeTeamGoalsScored());
        assertEquals(0, d11Match.getAwayTeamGoalsScored());
        assertEquals(0, d11Match.getHomeTeamPoints());
        assertEquals(0, d11Match.getAwayTeamPoints());
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

        assertEquals(0, d11Match.getHomeTeamGoalsScored());
        assertEquals(0, d11Match.getAwayTeamGoalsScored());
        assertEquals(1, d11Match.getPreviousHomeTeamGoalsScored());
        assertEquals(1, d11Match.getPreviousAwayTeamGoalsScored());
        assertEquals(0, d11Match.getHomeTeamPoints());
        assertEquals(0, d11Match.getAwayTeamPoints());
        assertEquals(1, d11Match.getPreviousHomeTeamPoints());
        assertEquals(1, d11Match.getPreviousAwayTeamPoints());
    }

}
