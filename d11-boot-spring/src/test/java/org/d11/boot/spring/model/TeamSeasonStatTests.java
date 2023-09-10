package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

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

        teamSeasonStat.setTeam(null);
        assertFalse(teamSeasonStat.isValid(), "TeamSeasonStat::isValid team null");
        teamSeasonStat.setTeam(new Team());

        teamSeasonStat.setSeason(null);
        assertFalse(teamSeasonStat.isValid(), "TeamSeasonStat::isValid season null");
        teamSeasonStat.setSeason(new Season());

        assertTrue(teamSeasonStat.isValid(), "TeamSeasonStat::isValid valid");
    }

}
