package org.d11.boot.application.model;


import org.d11.boot.api.model.TeamSeasonStatDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team season stat tests.
 */
public class TeamSeasonStatTests extends D11EasyRandomTests {

    /**
     * Tests team season stat validity.
     */
    @Test
    public void isValid() {
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        teamSeasonStat.prePersist();

        assertTrue(teamSeasonStat.isValid(), "New team season stat should be valid.");

        teamSeasonStat.setWinCount(-1);
        assertFalse(teamSeasonStat.isValid(), "Negative win count should not be valid.");
        teamSeasonStat.setWinCount(null);
        assertTrue(teamSeasonStat.isValid(), "Null win count should be valid.");

        teamSeasonStat.setTeam(null);
        assertFalse(teamSeasonStat.isValid(), "Null team should not be valid.");
        teamSeasonStat.setTeam(new Team());

        teamSeasonStat.setSeason(null);
        assertFalse(teamSeasonStat.isValid(), "Null season should not be valid.");
        teamSeasonStat.setSeason(new Season());

        assertTrue(teamSeasonStat.isValid(), "Team season stat should be valid.");
    }

    /**
     * Tests mapping between TeamSeasonStat and TeamSeasonStatDTO.
     */
    @Test
    public void map() {
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        final TeamSeasonStatDTO teamSeasonStatDTO = map(teamSeasonStat, TeamSeasonStatDTO.class);
        final TeamSeasonStat mappedTeamSeasonStat = map(teamSeasonStatDTO, TeamSeasonStat.class);

        assertEquals(teamSeasonStat, mappedTeamSeasonStat, "Team season stat should equal mapped team season stat.");
    }

}
