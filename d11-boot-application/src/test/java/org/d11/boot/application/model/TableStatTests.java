package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.TableStat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Table stat tests.
 */
public class TableStatTests extends D11EasyRandomTests {

    /**
     * Tests table stat validity.
     */
    @Test
    @SuppressWarnings({"checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS",
            "PMD.ExcessiveMethodLength", "PMD.NcssCount", "PMD.JUnitTestContainsTooManyAsserts"})
    public void isValid() {
        final TableStat tableStat = generate(TableStat.class);

        tableStat.prePersist();

        assertTrue(tableStat.isValid(), "New table stat should be valid.");

        tableStat.setMatchesPlayed(-1);
        assertFalse(tableStat.isValid(), "Negative matches played should not be valid.");
        tableStat.setMatchesPlayed(1);

        tableStat.setMatchesWon(-1);
        assertFalse(tableStat.isValid(), "Negative matches won should not be valid.");
        tableStat.setMatchesWon(1);

        tableStat.setMatchesDrawn(-1);
        assertFalse(tableStat.isValid(), "Negative matches drawn should not be valid.");
        tableStat.setMatchesDrawn(1);

        tableStat.setMatchesLost(-1);
        assertFalse(tableStat.isValid(), "Negative matches lost should not be valid.");
        tableStat.setMatchesLost(1);

        tableStat.setGoalsFor(-1);
        assertFalse(tableStat.isValid(), "Negative goals for should not be valid.");
        tableStat.setGoalsFor(1);

        tableStat.setGoalsAgainst(-1);
        assertFalse(tableStat.isValid(), "Negative goals against should not be valid.");
        tableStat.setGoalsAgainst(1);

        tableStat.setPoints(-1);
        assertFalse(tableStat.isValid(), "Negative points should not be valid.");
        tableStat.setPoints(1);

        tableStat.setFormPoints(-1);
        assertFalse(tableStat.isValid(), "Negative form points should not be valid.");
        tableStat.setFormPoints(1);

        tableStat.setFormMatchPoints(null);
        assertFalse(tableStat.isValid(), "Form match points should not be null.");
        tableStat.setFormMatchPoints(new ArrayList<>());

        tableStat.setRanking(0);
        assertFalse(tableStat.isValid(), "Non positive ranking should not be valid.");
        tableStat.setRanking(1);

        tableStat.setPreviousRanking(0);
        assertFalse(tableStat.isValid(), "Non positive previous ranking should not be valid.");
        tableStat.setPreviousRanking(1);

        tableStat.setHomeMatchesPlayed(-1);
        assertFalse(tableStat.isValid(), "Negative home matches played should not be valid.");
        tableStat.setHomeMatchesPlayed(1);

        tableStat.setHomeMatchesWon(-1);
        assertFalse(tableStat.isValid(), "Negative home matches won should not be valid.");
        tableStat.setHomeMatchesWon(1);

        tableStat.setHomeMatchesDrawn(-1);
        assertFalse(tableStat.isValid(), "Negative home matches drawn should not be valid.");
        tableStat.setHomeMatchesDrawn(1);

        tableStat.setHomeMatchesLost(-1);
        assertFalse(tableStat.isValid(), "Negative home matches lost should not be valid.");
        tableStat.setHomeMatchesLost(1);

        tableStat.setHomeGoalsFor(-1);
        assertFalse(tableStat.isValid(), "Negative home goals for should not be valid.");
        tableStat.setHomeGoalsFor(1);

        tableStat.setHomeGoalsAgainst(-1);
        assertFalse(tableStat.isValid(), "Negative home goals against should not be valid.");
        tableStat.setHomeGoalsAgainst(1);

        tableStat.setHomePoints(-1);
        assertFalse(tableStat.isValid(), "Negative home points should not be valid.");
        tableStat.setHomePoints(1);

        tableStat.setHomeRanking(0);
        assertFalse(tableStat.isValid(), "Non positive home ranking should not be valid.");
        tableStat.setHomeRanking(1);

        tableStat.setAwayMatchesPlayed(-1);
        assertFalse(tableStat.isValid(), "Negative away matches played should not be valid.");
        tableStat.setAwayMatchesPlayed(1);

        tableStat.setAwayMatchesWon(-1);
        assertFalse(tableStat.isValid(), "Negative away matches won should not be valid.");
        tableStat.setAwayMatchesWon(1);

        tableStat.setAwayMatchesDrawn(-1);
        assertFalse(tableStat.isValid(), "Negative away matches drawn should not be valid.");
        tableStat.setAwayMatchesDrawn(1);

        tableStat.setAwayMatchesLost(-1);
        assertFalse(tableStat.isValid(), "Negative away matches lost should not be valid.");
        tableStat.setAwayMatchesLost(1);

        tableStat.setAwayGoalsFor(-1);
        assertFalse(tableStat.isValid(), "Negative away goals for should not be valid.");
        tableStat.setAwayGoalsFor(1);

        tableStat.setAwayGoalsAgainst(-1);
        assertFalse(tableStat.isValid(), "Negative away goals against should not be valid.");
        tableStat.setAwayGoalsAgainst(1);

        tableStat.setAwayPoints(-1);
        assertFalse(tableStat.isValid(), "Negative away points should not be valid.");
        tableStat.setAwayPoints(1);

        tableStat.setAwayRanking(0);
        assertFalse(tableStat.isValid(), "Non positive away ranking should not be valid.");
        tableStat.setAwayRanking(1);

        assertTrue(tableStat.isValid(), "Table stat should be valid.");
    }

}
