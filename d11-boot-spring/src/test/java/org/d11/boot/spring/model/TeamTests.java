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
    void testIsValid() {
        final Team team = generate(Team.class);

        assertTrue(team.isValid());

        team.setStatSourceId(0);
        assertFalse(team.isValid());
        team.setStatSourceId(1);

        team.setPremierLeagueId(-1);
        assertFalse(team.isValid());
        team.setPremierLeagueId(1);

        team.setName("");
        assertFalse(team.isValid());
        team.setName(null);
        assertFalse(team.isValid(), "Name null");
        team.setName("Name");

        team.setShortName("");
        assertFalse(team.isValid());
        team.setShortName(null);
        assertFalse(team.isValid());
        team.setShortName("ShortName");

        team.setCode("");
        assertFalse(team.isValid());
        team.setCode(null);
        assertFalse(team.isValid());
        team.setCode("ABCD");
        assertFalse(team.isValid());
        team.setCode("AB");
        assertFalse(team.isValid());
        team.setCode("ABC");

        team.setEstablished(Team.MIN_ESTABLISHED_YEAR - 1);
        assertFalse(team.isValid());
        team.setEstablished(Team.MAX_ESTABLISHED_YEAR + 1);
        assertFalse(team.isValid());
        team.setEstablished(Team.MIN_ESTABLISHED_YEAR);

        team.setColour("");
        assertFalse(team.isValid(), "Empty colour should not be valid.");
        team.setColour(null);
        assertFalse(team.isValid(), "Null colour should not be valid.");
        team.setColour("Colour");

        team.setStadium(null);
        assertFalse(team.isValid());
        team.setStadium(new Stadium());

        team.setUrl("");
        assertFalse(team.isValid());
        team.setUrl(null);
        assertFalse(team.isValid());
        team.setUrl("http");

        assertTrue(team.isValid());
    }

}
