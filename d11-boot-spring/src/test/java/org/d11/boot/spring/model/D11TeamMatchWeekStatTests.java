package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team match week stat tests.
 */
class D11TeamMatchWeekStatTests extends EasyRandomTests {

    /**
     * Tests D11D11TeamMatchWeekStat::isValid.
     */
    @Test
    void testIsValid() {
        final D11TeamMatchWeekStat d11TeamMatchWeekStat = generate(D11TeamMatchWeekStat.class);

        assertTrue(d11TeamMatchWeekStat.isValid());

        d11TeamMatchWeekStat.setD11Team(null);
        assertFalse(d11TeamMatchWeekStat.isValid());
        d11TeamMatchWeekStat.setD11Team(new D11Team());

        d11TeamMatchWeekStat.setMatchWeek(null);
        assertFalse(d11TeamMatchWeekStat.isValid());
        d11TeamMatchWeekStat.setMatchWeek(new MatchWeek());

        assertTrue(d11TeamMatchWeekStat.isValid());
    }

    /**
     * Tests D11TeamMatchWeekStat::updateStats.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testUpdateStats() {
        final D11TeamMatchWeekStat d11TeamMatchWeekStat = generate(D11TeamMatchWeekStat.class);
        final D11Match d11Match = generate(D11Match.class);
        d11TeamMatchWeekStat.getMatchWeek().getD11Matches().add(d11Match);

        d11Match.setHomeD11Team(d11TeamMatchWeekStat.getD11Team());
        d11Match.setStatus(Status.PENDING);

        d11TeamMatchWeekStat.updateStats();

        assertEquals(0, d11TeamMatchWeekStat.getMatchesPlayed());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesWon());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesDrawn());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesLost());
        assertEquals(0, d11TeamMatchWeekStat.getGoalsFor());
        assertEquals(0, d11TeamMatchWeekStat.getGoalsAgainst());
        assertEquals(0, d11TeamMatchWeekStat.getGoalDifference());
        assertEquals(0, d11TeamMatchWeekStat.getPoints());

        d11Match.setStatus(Status.FINISHED);
        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        d11TeamMatchWeekStat.updateStats();

        assertEquals(1, d11TeamMatchWeekStat.getMatchesPlayed());
        assertEquals(1, d11TeamMatchWeekStat.getMatchesWon());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesDrawn());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesLost());
        assertEquals(1, d11TeamMatchWeekStat.getGoalsFor());
        assertEquals(0, d11TeamMatchWeekStat.getGoalsAgainst());
        assertEquals(1, d11TeamMatchWeekStat.getGoalDifference());
        assertEquals(Match.WIN_POINTS, d11TeamMatchWeekStat.getPoints());

        d11Match.setAwayTeamGoalsScored(1);
        d11TeamMatchWeekStat.updateStats();

        assertEquals(1, d11TeamMatchWeekStat.getMatchesPlayed());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesWon());
        assertEquals(1, d11TeamMatchWeekStat.getMatchesDrawn());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesLost());
        assertEquals(1, d11TeamMatchWeekStat.getGoalsFor());
        assertEquals(1, d11TeamMatchWeekStat.getGoalsAgainst());
        assertEquals(0, d11TeamMatchWeekStat.getGoalDifference());
        assertEquals(Match.DRAW_POINTS, d11TeamMatchWeekStat.getPoints());

        d11Match.setHomeTeamGoalsScored(0);
        d11TeamMatchWeekStat.updateStats();

        assertEquals(1, d11TeamMatchWeekStat.getMatchesPlayed());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesWon());
        assertEquals(0, d11TeamMatchWeekStat.getMatchesDrawn());
        assertEquals(1, d11TeamMatchWeekStat.getMatchesLost());
        assertEquals(0, d11TeamMatchWeekStat.getGoalsFor());
        assertEquals(1, d11TeamMatchWeekStat.getGoalsAgainst());
        assertEquals(-1, d11TeamMatchWeekStat.getGoalDifference());
        assertEquals(Match.LOSS_POINTS, d11TeamMatchWeekStat.getPoints());
    }

}
