package org.d11.boot.application.model;

import org.d11.boot.api.model.TeamMatchWeekStatDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team match week stat tests.
 */
public class TeamMatchWeekStatTests extends D11EasyRandomTests {

    /**
     * Tests team match week stat validity.
     */
    @Test
    public void isValid() {
        final TeamMatchWeekStat teamMatchWeekStat = generate(TeamMatchWeekStat.class);

        teamMatchWeekStat.prePersist();

        assertTrue(teamMatchWeekStat.isValid(), "New team match week stat should be valid.");

        teamMatchWeekStat.setTeam(null);
        assertFalse(teamMatchWeekStat.isValid(), "Null team should not be valid.");
        teamMatchWeekStat.setTeam(new Team());

        teamMatchWeekStat.setMatchWeek(null);
        assertFalse(teamMatchWeekStat.isValid(), "Null match week should not be valid.");
        teamMatchWeekStat.setMatchWeek(new MatchWeek());

        assertTrue(teamMatchWeekStat.isValid(), "Team match week stat should be valid.");
    }

    /**
     * Tests mapping between TeamMatchWeekStat and TeamMatchWeekStatDTO.
     */
    @Test
    public void map() {
        final TeamMatchWeekStat teamMatchWeekStat = generate(TeamMatchWeekStat.class);

        final TeamMatchWeekStatDTO teamMatchWeekStatDTO = map(teamMatchWeekStat, TeamMatchWeekStatDTO.class);
        final TeamMatchWeekStat mappedTeamMatchWeekStat = map(teamMatchWeekStatDTO, TeamMatchWeekStat.class);

        assertEquals(teamMatchWeekStat, mappedTeamMatchWeekStat, "Team match week stat should equal mapped team match week stat.");
    }

}
