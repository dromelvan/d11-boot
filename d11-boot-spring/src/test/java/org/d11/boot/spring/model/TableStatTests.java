package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Table stat tests.
 */
class TableStatTests extends EasyRandomTests {

    /**
     * Tests TableStat::isValid.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testIsValid() {
        final TableStat tableStat = generate(TableStat.class);

        assertTrue(tableStat.isValid());

        tableStat.setMatchesPlayed(-1);
        assertFalse(tableStat.isValid());
        tableStat.setMatchesPlayed(1);

        tableStat.setMatchesWon(-1);
        assertFalse(tableStat.isValid());
        tableStat.setMatchesWon(1);

        tableStat.setMatchesDrawn(-1);
        assertFalse(tableStat.isValid());
        tableStat.setMatchesDrawn(1);

        tableStat.setMatchesLost(-1);
        assertFalse(tableStat.isValid());
        tableStat.setMatchesLost(1);

        tableStat.setGoalsFor(-1);
        assertFalse(tableStat.isValid());
        tableStat.setGoalsFor(1);

        tableStat.setGoalsAgainst(-1);
        assertFalse(tableStat.isValid());
        tableStat.setGoalsAgainst(1);

        tableStat.setPoints(-1);
        assertFalse(tableStat.isValid());
        tableStat.setPoints(1);

        tableStat.setFormPoints(-1);
        assertFalse(tableStat.isValid());
        tableStat.setFormPoints(1);

        tableStat.setFormMatchPoints(null);
        assertFalse(tableStat.isValid());
        tableStat.setFormMatchPoints(new ArrayList<>());

        tableStat.setRanking(-1);
        assertFalse(tableStat.isValid());
        tableStat.setRanking(1);

        tableStat.setPreviousRanking(-1);
        assertFalse(tableStat.isValid());
        tableStat.setPreviousRanking(1);

        assertTrue(tableStat.isValid());
    }

    /**
     * Tests TableStat::reset.
     */
    @Test
    void testReset() {
        final TableStat tableStat = generate(TableStat.class);

        tableStat.reset();

        assertEquals(0, tableStat.getMatchesPlayed());
        assertEquals(0, tableStat.getMatchesWon());
        assertEquals(0, tableStat.getMatchesDrawn());
        assertEquals(0, tableStat.getMatchesLost());
        assertEquals(0, tableStat.getGoalsFor());
        assertEquals(0, tableStat.getGoalsAgainst());
        assertEquals(0, tableStat.getGoalDifference());
        assertEquals(0, tableStat.getPoints());
        assertEquals(0, tableStat.getFormPoints());
        assertTrue(tableStat.getFormMatchPoints().isEmpty());
        assertEquals(0, tableStat.getRanking());
        assertEquals(0, tableStat.getPreviousRanking());
    }

    /**
     * Tests TableStat::updateCumulativeStats.
     */
    @Test
    @SuppressWarnings({"checkstyle:ExecutableStatementCount", "checkstyle:VariableDeclarationUsageDistance"})
    void testUpdateCumulativeStats() {
        final TableStat source = generate(TableStat.class);
        final TableStat destination = generate(TableStat.class);

        int matchesPlayed = destination.getMatchesPlayed();
        int matchesWon = destination.getMatchesWon();
        int matchesDrawn = destination.getMatchesDrawn();
        int matchesLost = destination.getMatchesLost();
        int goalsFor = destination.getGoalsFor();
        int goalsAgainst = destination.getGoalsAgainst();
        int goalDifference = destination.getGoalDifference();
        int points = destination.getPoints();
        int formPoints = destination.getFormPoints();

        destination.updateCumulativeStats(null);

        assertEquals(matchesPlayed, destination.getMatchesPlayed());
        assertEquals(matchesWon, destination.getMatchesWon());
        assertEquals(matchesDrawn, destination.getMatchesDrawn());
        assertEquals(matchesLost, destination.getMatchesLost());
        assertEquals(goalsFor, destination.getGoalsFor());
        assertEquals(goalsAgainst, destination.getGoalsAgainst());
        assertEquals(goalDifference, destination.getGoalDifference());
        assertEquals(points, destination.getPoints());
        assertEquals(formPoints, destination.getFormPoints());

        matchesPlayed += source.getMatchesPlayed();
        matchesWon += source.getMatchesWon();
        matchesDrawn += source.getMatchesDrawn();
        matchesLost += source.getMatchesLost();
        goalsFor += source.getGoalsFor();
        goalsAgainst += source.getGoalsAgainst();
        goalDifference += source.getGoalDifference();
        points += source.getPoints();
        formPoints += source.getFormPoints();

        destination.updateCumulativeStats(source);

        assertEquals(matchesPlayed, destination.getMatchesPlayed());
        assertEquals(matchesWon, destination.getMatchesWon());
        assertEquals(matchesDrawn, destination.getMatchesDrawn());
        assertEquals(matchesLost, destination.getMatchesLost());
        assertEquals(goalsFor, destination.getGoalsFor());
        assertEquals(goalsAgainst, destination.getGoalsAgainst());
        assertEquals(goalDifference, destination.getGoalDifference());
        assertEquals(points, destination.getPoints());
        assertEquals(formPoints, destination.getFormPoints());
    }

}
