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
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final D11TeamMatchWeekStat d11TeamMatchWeekStat = generate(D11TeamMatchWeekStat.class);

        assertTrue(d11TeamMatchWeekStat.isValid(), "D11TeamMatchWeekStat::isValid");

        d11TeamMatchWeekStat.setD11Team(null);
        assertFalse(d11TeamMatchWeekStat.isValid(), "D11TeamMatchWeekStat::isValid D11 team null");
        d11TeamMatchWeekStat.setD11Team(new D11Team());

        d11TeamMatchWeekStat.setMatchWeek(null);
        assertFalse(d11TeamMatchWeekStat.isValid(), "D11TeamMatchWeekStat::isValid match week null");
        d11TeamMatchWeekStat.setMatchWeek(new MatchWeek());

        assertTrue(d11TeamMatchWeekStat.isValid(), "D11TeamMatchWeekStat::isValid valid");
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

        assertEquals(0, d11TeamMatchWeekStat.getMatchesPlayed(),
                     "D11TeamMatchWeekStat::updateStats pending matches played equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesWon(),
                     "D11TeamMatchWeekStat::updateStats pending matches won equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesDrawn(),
                     "D11TeamMatchWeekStat::updateStats pending matches drawn equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesLost(),
                     "D11TeamMatchWeekStat::updateStats pending matches lost equals");
        assertEquals(0, d11TeamMatchWeekStat.getGoalsFor(),
                     "D11TeamMatchWeekStat::updateStats pending goals for equals");
        assertEquals(0, d11TeamMatchWeekStat.getGoalsAgainst(),
                     "D11TeamMatchWeekStat::updateStats pending goals against equals");
        assertEquals(0, d11TeamMatchWeekStat.getGoalDifference(),
                     "D11TeamMatchWeekStat::updateStats pending goal difference equals");
        assertEquals(0, d11TeamMatchWeekStat.getPoints(),
                     "D11TeamMatchWeekStat::updateStats pending points equals");

        d11Match.setStatus(Status.FINISHED);
        d11Match.setHomeTeamGoalsScored(1);
        d11Match.setAwayTeamGoalsScored(0);

        d11TeamMatchWeekStat.updateStats();

        assertEquals(1, d11TeamMatchWeekStat.getMatchesPlayed(),
                     "D11TeamMatchWeekStat::updateStats win matches played equals");
        assertEquals(1, d11TeamMatchWeekStat.getMatchesWon(),
                     "D11TeamMatchWeekStat::updateStats win matches won equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesDrawn(),
                     "D11TeamMatchWeekStat::updateStats win matches drawn equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesLost(),
                     "D11TeamMatchWeekStat::updateStats win matches lost equals");
        assertEquals(1, d11TeamMatchWeekStat.getGoalsFor(),
                     "D11TeamMatchWeekStat::updateStats win goals for equals");
        assertEquals(0, d11TeamMatchWeekStat.getGoalsAgainst(),
                     "D11TeamMatchWeekStat::updateStats win goals against equals");
        assertEquals(1, d11TeamMatchWeekStat.getGoalDifference(),
                     "D11TeamMatchWeekStat::updateStats win goal difference equals");
        assertEquals(Match.WIN_POINTS, d11TeamMatchWeekStat.getPoints(),
                     "D11TeamMatchWeekStat::updateStats win points equals");

        d11Match.setAwayTeamGoalsScored(1);
        d11TeamMatchWeekStat.updateStats();

        assertEquals(1, d11TeamMatchWeekStat.getMatchesPlayed(),
                     "D11TeamMatchWeekStat::updateStats draw matches played equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesWon(),
                     "D11TeamMatchWeekStat::updateStats draw matches won equals");
        assertEquals(1, d11TeamMatchWeekStat.getMatchesDrawn(),
                     "D11TeamMatchWeekStat::updateStats draw matches drawn equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesLost(),
                     "D11TeamMatchWeekStat::updateStats draw matches lost equals");
        assertEquals(1, d11TeamMatchWeekStat.getGoalsFor(),
                     "D11TeamMatchWeekStat::updateStats draw goals for equals");
        assertEquals(1, d11TeamMatchWeekStat.getGoalsAgainst(),
                     "D11TeamMatchWeekStat::updateStats draw goals against equals");
        assertEquals(0, d11TeamMatchWeekStat.getGoalDifference(),
                     "D11TeamMatchWeekStat::updateStats draw goal difference equals");
        assertEquals(Match.DRAW_POINTS, d11TeamMatchWeekStat.getPoints(),
                     "D11TeamMatchWeekStat::updateStats draw points equals");

        d11Match.setHomeTeamGoalsScored(0);
        d11TeamMatchWeekStat.updateStats();

        assertEquals(1, d11TeamMatchWeekStat.getMatchesPlayed(),
                     "D11TeamMatchWeekStat::updateStats loss matches played equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesWon(),
                     "D11TeamMatchWeekStat::updateStats loss matches won equals");
        assertEquals(0, d11TeamMatchWeekStat.getMatchesDrawn(),
                     "D11TeamMatchWeekStat::updateStats loss matches drawn equals");
        assertEquals(1, d11TeamMatchWeekStat.getMatchesLost(),
                     "D11TeamMatchWeekStat::updateStats loss matches lost equals");
        assertEquals(0, d11TeamMatchWeekStat.getGoalsFor(),
                     "D11TeamMatchWeekStat::updateStats loss goals for equals");
        assertEquals(1, d11TeamMatchWeekStat.getGoalsAgainst(),
                     "D11TeamMatchWeekStat::updateStats loss goals against equals");
        assertEquals(-1, d11TeamMatchWeekStat.getGoalDifference(),
                     "D11TeamMatchWeekStat::updateStats loss goal difference equals");
        assertEquals(Match.LOSS_POINTS, d11TeamMatchWeekStat.getPoints(),
                     "D11TeamMatchWeekStat::updateStats loss points equals");
    }

}
