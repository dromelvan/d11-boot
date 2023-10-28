package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team match week stat tests.
 */
class TeamMatchWeekStatTests extends EasyRandomTests {

    /**
     * Tests TeamMatchWeekStat::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final TeamMatchWeekStat teamMatchWeekStat = generate(TeamMatchWeekStat.class);

        assertTrue(teamMatchWeekStat.isValid(), "TeamMatchWeekStat::isValid");

        teamMatchWeekStat.setTeam(null);
        assertFalse(teamMatchWeekStat.isValid(), "TeamMatchWeekStat::isValid team null");
        teamMatchWeekStat.setTeam(new Team());

        teamMatchWeekStat.setMatchWeek(null);
        assertFalse(teamMatchWeekStat.isValid(), "TeamMatchWeekStat::isValid match week null");
        teamMatchWeekStat.setMatchWeek(new MatchWeek());

        assertTrue(teamMatchWeekStat.isValid(), "TeamMatchWeekStat::isValid valid");
    }

    /**
     * Tests TeamMatchWeekStat::updateStats.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testUpdateStats() {
        final TeamMatchWeekStat teamMatchWeekStat = generate(TeamMatchWeekStat.class);
        final Match match = generate(Match.class);
        teamMatchWeekStat.getMatchWeek().getMatches().add(match);

        match.setHomeTeam(teamMatchWeekStat.getTeam());
        match.setStatus(Status.PENDING);

        teamMatchWeekStat.updateStats();

        assertEquals(0, teamMatchWeekStat.getMatchesPlayed(),
                     "TeamMatchWeekStat::updateStats pending matches played equals");
        assertEquals(0, teamMatchWeekStat.getMatchesWon(),
                     "TeamMatchWeekStat::updateStats pending matches won equals");
        assertEquals(0, teamMatchWeekStat.getMatchesDrawn(),
                     "TeamMatchWeekStat::updateStats pending matches drawn equals");
        assertEquals(0, teamMatchWeekStat.getMatchesLost(),
                     "TeamMatchWeekStat::updateStats pending matches lost equals");
        assertEquals(0, teamMatchWeekStat.getGoalsFor(),
                     "TeamMatchWeekStat::updateStats pending goals for equals");
        assertEquals(0, teamMatchWeekStat.getGoalsAgainst(),
                     "TeamMatchWeekStat::updateStats pending goals against equals");
        assertEquals(0, teamMatchWeekStat.getGoalDifference(),
                     "TeamMatchWeekStat::updateStats pending goal difference equals");
        assertEquals(0, teamMatchWeekStat.getPoints(),
                     "TeamMatchWeekStat::updateStats pending points equals");

        match.setStatus(Status.FINISHED);
        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        teamMatchWeekStat.updateStats();

        assertEquals(1, teamMatchWeekStat.getMatchesPlayed(),
                     "TeamMatchWeekStat::updateStats win matches played equals");
        assertEquals(1, teamMatchWeekStat.getMatchesWon(),
                     "TeamMatchWeekStat::updateStats win matches won equals");
        assertEquals(0, teamMatchWeekStat.getMatchesDrawn(),
                     "TeamMatchWeekStat::updateStats win matches drawn equals");
        assertEquals(0, teamMatchWeekStat.getMatchesLost(),
                     "TeamMatchWeekStat::updateStats win matches lost equals");
        assertEquals(1, teamMatchWeekStat.getGoalsFor(),
                     "TeamMatchWeekStat::updateStats win goals for equals");
        assertEquals(0, teamMatchWeekStat.getGoalsAgainst(),
                     "TeamMatchWeekStat::updateStats win goals against equals");
        assertEquals(1, teamMatchWeekStat.getGoalDifference(),
                     "TeamMatchWeekStat::updateStats win goal difference equals");
        assertEquals(Match.WIN_POINTS, teamMatchWeekStat.getPoints(),
                     "TeamMatchWeekStat::updateStats win points equals");

        match.setAwayTeamGoalsScored(1);
        teamMatchWeekStat.updateStats();

        assertEquals(1, teamMatchWeekStat.getMatchesPlayed(),
                     "TeamMatchWeekStat::updateStats draw matches played equals");
        assertEquals(0, teamMatchWeekStat.getMatchesWon(),
                     "TeamMatchWeekStat::updateStats draw matches won equals");
        assertEquals(1, teamMatchWeekStat.getMatchesDrawn(),
                     "TeamMatchWeekStat::updateStats draw matches drawn equals");
        assertEquals(0, teamMatchWeekStat.getMatchesLost(),
                     "TeamMatchWeekStat::updateStats draw matches lost equals");
        assertEquals(1, teamMatchWeekStat.getGoalsFor(),
                     "TeamMatchWeekStat::updateStats draw goals for equals");
        assertEquals(1, teamMatchWeekStat.getGoalsAgainst(),
                     "TeamMatchWeekStat::updateStats draw goals against equals");
        assertEquals(0, teamMatchWeekStat.getGoalDifference(),
                     "TeamMatchWeekStat::updateStats draw goal difference equals");
        assertEquals(Match.DRAW_POINTS, teamMatchWeekStat.getPoints(),
                     "TeamMatchWeekStat::updateStats draw points equals");

        match.setHomeTeamGoalsScored(0);
        teamMatchWeekStat.updateStats();

        assertEquals(1, teamMatchWeekStat.getMatchesPlayed(),
                     "TeamMatchWeekStat::updateStats loss matches played equals");
        assertEquals(0, teamMatchWeekStat.getMatchesWon(),
                     "TeamMatchWeekStat::updateStats loss matches won equals");
        assertEquals(0, teamMatchWeekStat.getMatchesDrawn(),
                     "TeamMatchWeekStat::updateStats loss matches drawn equals");
        assertEquals(1, teamMatchWeekStat.getMatchesLost(),
                     "TeamMatchWeekStat::updateStats loss matches lost equals");
        assertEquals(0, teamMatchWeekStat.getGoalsFor(),
                     "TeamMatchWeekStat::updateStats loss goals for equals");
        assertEquals(1, teamMatchWeekStat.getGoalsAgainst(),
                     "TeamMatchWeekStat::updateStats loss goals against equals");
        assertEquals(-1, teamMatchWeekStat.getGoalDifference(),
                     "TeamMatchWeekStat::updateStats loss goal difference equals");
        assertEquals(Match.LOSS_POINTS, teamMatchWeekStat.getPoints(),
                     "TeamMatchWeekStat::updateStats loss points equals");
    }

}
