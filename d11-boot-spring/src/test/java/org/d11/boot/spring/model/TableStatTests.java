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
    @SuppressWarnings({"DataFlowIssue", "checkstyle:ExecutableStatementCount"})
    void testIsValid() {
        final TableStat tableStat = generate(TableStat.class);

        assertTrue(tableStat.isValid(), "TableStat::isValid");

        tableStat.setMatchesPlayed(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid matches played negative");
        tableStat.setMatchesPlayed(1);

        tableStat.setMatchesWon(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid matches won negative");
        tableStat.setMatchesWon(1);

        tableStat.setMatchesDrawn(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid matches drawn negative");
        tableStat.setMatchesDrawn(1);

        tableStat.setMatchesLost(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid matches lost negative");
        tableStat.setMatchesLost(1);

        tableStat.setGoalsFor(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid goals for negative");
        tableStat.setGoalsFor(1);

        tableStat.setGoalsAgainst(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid goals against negative");
        tableStat.setGoalsAgainst(1);

        tableStat.setPoints(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid points negative");
        tableStat.setPoints(1);

        tableStat.setFormPoints(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid form points negative");
        tableStat.setFormPoints(1);

        tableStat.setFormMatchPoints(null);
        assertFalse(tableStat.isValid(), "TableStat::isValid form match points null");
        tableStat.setFormMatchPoints(new ArrayList<>());

        tableStat.setRanking(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid ranking negative");
        tableStat.setRanking(1);

        tableStat.setPreviousRanking(-1);
        assertFalse(tableStat.isValid(), "TableStat::isValid previous ranking negative");
        tableStat.setPreviousRanking(1);

        assertTrue(tableStat.isValid(), "TableStat::isValid valid");
    }

    /**
     * Tests TableStat::reset.
     */
    @Test
    void testReset() {
        final TableStat tableStat = generate(TableStat.class);

        tableStat.reset();

        assertEquals(0, tableStat.getMatchesPlayed(), "TableStat::reset matches played equals");
        assertEquals(0, tableStat.getMatchesWon(), "TableStat::reset matches won equals");
        assertEquals(0, tableStat.getMatchesDrawn(), "TableStat::reset matches drawn equals");
        assertEquals(0, tableStat.getMatchesLost(), "TableStat::reset matches lost equals");
        assertEquals(0, tableStat.getGoalsFor(), "TableStat::reset goals for equals");
        assertEquals(0, tableStat.getGoalsAgainst(), "TableStat::reset goals against equals");
        assertEquals(0, tableStat.getGoalDifference(), "TableStat::reset goal difference equals");
        assertEquals(0, tableStat.getPoints(), "TableStat::reset points equals");
        assertEquals(0, tableStat.getFormPoints(), "TableStat::reset form points equals");
        assertTrue(tableStat.getFormMatchPoints().isEmpty(), "TableStat::reset form match points empty");
        assertEquals(0, tableStat.getRanking(), "TableStat::reset ranking equals");
        assertEquals(0, tableStat.getPreviousRanking(), "TableStat::reset previous ranking equals");
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

        assertEquals(matchesPlayed, destination.getMatchesPlayed(),
                     "TableStat::updateCumulativeStats null matches played equals");
        assertEquals(matchesWon, destination.getMatchesWon(),
                     "TableStat::updateCumulativeStats null matches won equals");
        assertEquals(matchesDrawn, destination.getMatchesDrawn(),
                     "TableStat::updateCumulativeStats null matches drawn equals");
        assertEquals(matchesLost, destination.getMatchesLost(),
                     "TableStat::updateCumulativeStats null matches lost equals");
        assertEquals(goalsFor, destination.getGoalsFor(), "TableStat::updateCumulativeStats null goals for equals");
        assertEquals(goalsAgainst, destination.getGoalsAgainst(),
                     "TableStat::updateCumulativeStats null goals against equals");
        assertEquals(goalDifference, destination.getGoalDifference(),
                     "TableStat::updateCumulativeStats null goal difference equals");
        assertEquals(points, destination.getPoints(), "TableStat::reset updateCumulativeStats null equals");
        assertEquals(formPoints, destination.getFormPoints(),
                     "TableStat::updateCumulativeStats null form points equals");

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

        assertEquals(matchesPlayed, destination.getMatchesPlayed(),
                     "TableStat::updateCumulativeStats matches played equals");
        assertEquals(matchesWon, destination.getMatchesWon(), "TableStat::updateCumulativeStats matches won equals");
        assertEquals(matchesDrawn, destination.getMatchesDrawn(),
                     "TableStat::updateCumulativeStats matches drawn equals");
        assertEquals(matchesLost, destination.getMatchesLost(), "TableStat::updateCumulativeStats matches lost equals");
        assertEquals(goalsFor, destination.getGoalsFor(), "TableStat::updateCumulativeStats goals for equals");
        assertEquals(goalsAgainst, destination.getGoalsAgainst(),
                     "TableStat::updateCumulativeStats goals against equals");
        assertEquals(goalDifference, destination.getGoalDifference(),
                     "TableStat::updateCumulativeStats goal difference equals");
        assertEquals(points, destination.getPoints(), "TableStat::reset updateCumulativeStats equals");
        assertEquals(formPoints, destination.getFormPoints(), "TableStat::updateCumulativeStats form points equals");
    }

}
