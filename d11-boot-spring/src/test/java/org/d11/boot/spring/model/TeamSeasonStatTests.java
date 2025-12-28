package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team season stat tests.
 */
class TeamSeasonStatTests extends EasyRandomTests {

    /**
     * Tests TeamSeasonStat::isValid.
     */
    @Test
    void testIsValid() {
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        assertTrue(teamSeasonStat.isValid());

        teamSeasonStat.setWinCount(-1);
        assertFalse(teamSeasonStat.isValid());
        teamSeasonStat.setWinCount(1);

        teamSeasonStat.setPointsPenalty(-1);
        assertFalse(teamSeasonStat.isValid());
        teamSeasonStat.setPointsPenalty(1);

        teamSeasonStat.setTeam(null);
        assertFalse(teamSeasonStat.isValid());
        teamSeasonStat.setTeam(new Team());

        teamSeasonStat.setSeason(null);
        assertFalse(teamSeasonStat.isValid());
        teamSeasonStat.setSeason(new Season());

        assertTrue(teamSeasonStat.isValid());
    }

    /**
     * Tests TeamSeasonStat::reset.
     */
    @Test
    void testReset() {
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        teamSeasonStat.reset();

        assertEquals(0, teamSeasonStat.getWinCount());
        assertEquals(0, teamSeasonStat.getPointsPenalty());
    }

    /**
     * Tests TeamSeasonStat::updateCumulativeStats.
     */
    @Test
    @SuppressWarnings({"checkstyle:ExecutableStatementCount", "checkstyle:VariableDeclarationUsageDistance"})
    void testUpdateCumulativeStats() {
        final TeamSeasonStat source = generate(TeamSeasonStat.class);
        final TeamSeasonStat destination = generate(TeamSeasonStat.class);

        int winCount = destination.getWinCount();
        int pointsPenalty = destination.getPointsPenalty();

        destination.updateCumulativeStats(null);

        assertEquals(winCount, destination.getWinCount());
        assertEquals(pointsPenalty, destination.getPointsPenalty());

        winCount += source.getWinCount();
        pointsPenalty += source.getPointsPenalty();

        destination.updateCumulativeStats(source);

        assertEquals(winCount, destination.getWinCount());
        assertEquals(pointsPenalty, destination.getPointsPenalty());
    }

}
