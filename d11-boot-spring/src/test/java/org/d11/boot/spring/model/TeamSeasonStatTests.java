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
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        assertTrue(teamSeasonStat.isValid(), "TeamSeasonStat::isValid");

        teamSeasonStat.setWinCount(-1);
        assertFalse(teamSeasonStat.isValid(), "TeamSeasonStat::isValid win count negative");
        teamSeasonStat.setWinCount(1);

        teamSeasonStat.setPointsPenalty(-1);
        assertFalse(teamSeasonStat.isValid(), "TeamSeasonStat::isValid points penalty negative");
        teamSeasonStat.setPointsPenalty(1);

        teamSeasonStat.setTeam(null);
        assertFalse(teamSeasonStat.isValid(), "TeamSeasonStat::isValid team null");
        teamSeasonStat.setTeam(new Team());

        teamSeasonStat.setSeason(null);
        assertFalse(teamSeasonStat.isValid(), "TeamSeasonStat::isValid season null");
        teamSeasonStat.setSeason(new Season());

        assertTrue(teamSeasonStat.isValid(), "TeamSeasonStat::isValid valid");
    }

    /**
     * Tests TeamSeasonStat::reset.
     */
    @Test
    void testReset() {
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        teamSeasonStat.reset();

        assertEquals(0, teamSeasonStat.getWinCount(), "TeamSeasonStat::reset win count equals");
        assertEquals(0, teamSeasonStat.getPointsPenalty(), "TeamSeasonStat::reset points penalty equals");
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

        assertEquals(winCount, destination.getWinCount(),
                "TeamSeasonStat::updateCumulativeStats null win count equals");
        assertEquals(pointsPenalty, destination.getPointsPenalty(),
                "TeamSeasonStat::updateCumulativeStats null points penalty equals");

        winCount += source.getWinCount();
        pointsPenalty += source.getPointsPenalty();

        destination.updateCumulativeStats(source);

        assertEquals(winCount, destination.getWinCount(),
                "TeamSeasonStat::updateCumulativeStats win count equals");
        assertEquals(pointsPenalty, destination.getPointsPenalty(),
                    "TeamSeasonStat::updateCumulativeStats points penalty equals");
    }

}
