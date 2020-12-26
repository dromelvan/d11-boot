package org.d11.boot.application.model;

import org.d11.boot.api.model.TeamTableStatDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team table stat tests.
 */
public class TeamTableStatTests extends D11EasyRandomTests {

    /**
     * Tests team table stat validity.
     */
    @Test
    public void isValid() {
        final TeamTableStat teamTableStat = generate(TeamTableStat.class);

        teamTableStat.prePersist();

        assertTrue(teamTableStat.isValid(), "New team table stat should be valid.");

        teamTableStat.setTeam(null);
        assertFalse(teamTableStat.isValid(), "Null team should not be valid.");
        teamTableStat.setTeam(new Team());

        teamTableStat.setPremierLeague(null);
        assertFalse(teamTableStat.isValid(), "Null Premier League should not be valid.");
        teamTableStat.setPremierLeague(new PremierLeague());

        teamTableStat.setMatchWeek(null);
        assertFalse(teamTableStat.isValid(), "Null match week should not be valid.");
        teamTableStat.setMatchWeek(new MatchWeek());

        assertTrue(teamTableStat.isValid(), "Team table stat should be valid.");
    }

    /**
     * Tests mapping between TeamTableStat and TeamTableStatDTO.
     */
    @Test
    public void map() {
        final TeamTableStat teamTableStat = generate(TeamTableStat.class);

        final TeamTableStatDTO teamTableStatDTO = map(teamTableStat, TeamTableStatDTO.class);
        final TeamTableStat mappedTeamTableStat = map(teamTableStatDTO, TeamTableStat.class);

        assertEquals(teamTableStat, mappedTeamTableStat, "Team table stat should equal mapped team table stat.");
    }

}
