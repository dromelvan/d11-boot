package org.d11.boot.application.model;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.util.D11BootModelMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team tests.
 */
@SuppressWarnings("checkstyle:ExecutableStatementCount")
public class TeamTests {

    /**
     * Random team generator.
     */
    private final D11EasyRandom d11EasyRandom = new D11EasyRandom();

    /**
     * Tests team validity.
     */
    @Test
    public void isValid() {
        final Team team = this.d11EasyRandom.nextObject(Team.class);

        assertTrue(team.isValid(), "New team should be valid.");

        team.setWhoscoredId(0);
        assertFalse(team.isValid(), "Too low whoscored id should not be valid.");
        team.setWhoscoredId(1);

        team.setName("");
        assertFalse(team.isValid(), "Empty name should not be valid.");
        team.setName(null);
        assertFalse(team.isValid(), "Null name should not be valid.");
        team.setName("Name");

        team.setShortName("");
        assertFalse(team.isValid(), "Empty shortName should not be valid.");
        team.setShortName(null);
        assertFalse(team.isValid(), "Null shortName should not be valid.");
        team.setShortName("ShortName");

        team.setCode("");
        assertFalse(team.isValid(), "Empty code should not be valid.");
        team.setCode(null);
        assertFalse(team.isValid(), "Null code should not be valid.");
        team.setCode("ABCD");
        assertFalse(team.isValid(), "Too long code should not be valid.");
        team.setCode("AB");
        assertFalse(team.isValid(), "Too short code should not be valid.");
        team.setCode("ABC");

        team.setNickname("");
        assertFalse(team.isValid(), "Empty nickname should not be valid.");
        team.setNickname(null);
        assertFalse(team.isValid(), "Null nickname should not be valid.");
        team.setNickname("Nickname");

        team.setEstablished(Team.MIN_ESTABLISHED_YEAR - 1);
        assertFalse(team.isValid(), "Too low established should not be valid.");
        team.setEstablished(Team.MAX_ESTABLISHED_YEAR + 1);
        assertFalse(team.isValid(), "Too high established should not be valid.");
        team.setEstablished(Team.MIN_ESTABLISHED_YEAR);

        team.setColour("");
        assertFalse(team.isValid(), "Empty colour should not be valid.");
        team.setColour(null);
        assertFalse(team.isValid(), "Null colour should not be valid.");
        team.setColour("Colour");

        team.setStadium(null);
        assertFalse(team.isValid(), "Null stadium should not be valid.");
        team.setStadium(new Stadium());

        assertTrue(team.isValid(), "Team should be valid.");
    }

    /**
     * Tests mapping between Team and TeamDTO.
     */
    @Test
    public void map() {
        final Team team = this.d11EasyRandom.nextObject(Team.class);

        final ModelMapper modelMapper = new D11BootModelMapper();

        final TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
        final Team mappedTeam = modelMapper.map(teamDTO, Team.class);

        assertEquals(team, mappedTeam, "Team should equal mapped team.");
    }
}
