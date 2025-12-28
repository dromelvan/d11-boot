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
    void testIsValid() {
        final TeamMatchWeekStat teamMatchWeekStat = generate(TeamMatchWeekStat.class);

        assertTrue(teamMatchWeekStat.isValid());

        teamMatchWeekStat.setTeam(null);
        assertFalse(teamMatchWeekStat.isValid());
        teamMatchWeekStat.setTeam(new Team());

        teamMatchWeekStat.setMatchWeek(null);
        assertFalse(teamMatchWeekStat.isValid());
        teamMatchWeekStat.setMatchWeek(new MatchWeek());

        assertTrue(teamMatchWeekStat.isValid());
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

        assertEquals(0, teamMatchWeekStat.getMatchesPlayed());
        assertEquals(0, teamMatchWeekStat.getMatchesWon());
        assertEquals(0, teamMatchWeekStat.getMatchesDrawn());
        assertEquals(0, teamMatchWeekStat.getMatchesLost());
        assertEquals(0, teamMatchWeekStat.getGoalsFor());
        assertEquals(0, teamMatchWeekStat.getGoalsAgainst());
        assertEquals(0, teamMatchWeekStat.getGoalDifference());
        assertEquals(0, teamMatchWeekStat.getPoints());

        match.setStatus(Status.FINISHED);
        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        teamMatchWeekStat.updateStats();

        assertEquals(1, teamMatchWeekStat.getMatchesPlayed());
        assertEquals(1, teamMatchWeekStat.getMatchesWon());
        assertEquals(0, teamMatchWeekStat.getMatchesDrawn());
        assertEquals(0, teamMatchWeekStat.getMatchesLost());
        assertEquals(1, teamMatchWeekStat.getGoalsFor());
        assertEquals(0, teamMatchWeekStat.getGoalsAgainst());
        assertEquals(1, teamMatchWeekStat.getGoalDifference());
        assertEquals(Match.WIN_POINTS, teamMatchWeekStat.getPoints());

        match.setAwayTeamGoalsScored(1);
        teamMatchWeekStat.updateStats();

        assertEquals(1, teamMatchWeekStat.getMatchesPlayed());
        assertEquals(0, teamMatchWeekStat.getMatchesWon());
        assertEquals(1, teamMatchWeekStat.getMatchesDrawn());
        assertEquals(0, teamMatchWeekStat.getMatchesLost());
        assertEquals(1, teamMatchWeekStat.getGoalsFor());
        assertEquals(1, teamMatchWeekStat.getGoalsAgainst());
        assertEquals(0, teamMatchWeekStat.getGoalDifference());
        assertEquals(Match.DRAW_POINTS, teamMatchWeekStat.getPoints());

        match.setHomeTeamGoalsScored(0);
        teamMatchWeekStat.updateStats();

        assertEquals(1, teamMatchWeekStat.getMatchesPlayed());
        assertEquals(0, teamMatchWeekStat.getMatchesWon());
        assertEquals(0, teamMatchWeekStat.getMatchesDrawn());
        assertEquals(1, teamMatchWeekStat.getMatchesLost());
        assertEquals(0, teamMatchWeekStat.getGoalsFor());
        assertEquals(1, teamMatchWeekStat.getGoalsAgainst());
        assertEquals(-1, teamMatchWeekStat.getGoalDifference());
        assertEquals(Match.LOSS_POINTS, teamMatchWeekStat.getPoints());
    }

}
