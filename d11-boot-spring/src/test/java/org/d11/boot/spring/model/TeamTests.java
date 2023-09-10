package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team tests.
 */
@SuppressWarnings("checkstyle:ExecutableStatementCount")
class TeamTests extends EasyRandomTests {

    /**
     * Tests Team::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final Team team = generate(Team.class);

        assertTrue(team.isValid(), "Team::isValid");

        team.setWhoscoredId(0);
        assertFalse(team.isValid(), "Team::isValid WhoScored id too low");
        team.setWhoscoredId(1);

        team.setPremierLeagueId(-1);
        assertFalse(team.isValid(), "Team::isValid Premier League id negative");
        team.setPremierLeagueId(1);

        team.setName("");
        assertFalse(team.isValid(), "Team::isValid name empty");
        team.setName(null);
        assertFalse(team.isValid(), "Name null");
        team.setName("Name");

        team.setShortName("");
        assertFalse(team.isValid(), "Team::isValid short name empty");
        team.setShortName(null);
        assertFalse(team.isValid(), "Team::isValid short name null");
        team.setShortName("ShortName");

        team.setCode("");
        assertFalse(team.isValid(), "Team::isValid code empty");
        team.setCode(null);
        assertFalse(team.isValid(), "Team::isValid code null");
        team.setCode("ABCD");
        assertFalse(team.isValid(), "Team::isValid code too long");
        team.setCode("AB");
        assertFalse(team.isValid(), "Team::isValid code too short");
        team.setCode("ABC");

        team.setEstablished(Team.MIN_ESTABLISHED_YEAR - 1);
        assertFalse(team.isValid(), "Team::isValid established too low");
        team.setEstablished(Team.MAX_ESTABLISHED_YEAR + 1);
        assertFalse(team.isValid(), "Team::isValid established too high");
        team.setEstablished(Team.MIN_ESTABLISHED_YEAR);

        team.setColour("");
        assertFalse(team.isValid(), "Empty colour should not be valid.");
        team.setColour(null);
        assertFalse(team.isValid(), "Null colour should not be valid.");
        team.setColour("Colour");

        team.setStadium(null);
        assertFalse(team.isValid(), "Team::isValid stadium null");
        team.setStadium(new Stadium());

        team.setUrl("");
        assertFalse(team.isValid(), "Team::isValid url empty");
        team.setUrl(null);
        assertFalse(team.isValid(), "Team::isValid url null");
        team.setUrl("http");

        assertTrue(team.isValid(), "Team::isValid valid");
    }

}
