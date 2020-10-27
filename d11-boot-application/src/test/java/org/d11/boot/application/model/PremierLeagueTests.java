package org.d11.boot.application.model;

import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.util.D11BootModelMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Premier League tests.
 */
public class PremierLeagueTests extends D11EasyRandomTests {

    /**
     * Tests Premier League validity.
     */
    @Test
    public void isValid() {
        final PremierLeague premierLeague = generate(PremierLeague.class);
        premierLeague.setSeason(new Season());

        assertTrue(premierLeague.isValid(), "New Premier League should be valid.");

        premierLeague.setName("");
        assertFalse(premierLeague.isValid(), "Empty name should not be valid.");
        premierLeague.setName(null);
        assertFalse(premierLeague.isValid(), "Null name should not be valid.");
        premierLeague.setName("Name");

        premierLeague.setSeason(null);
        assertFalse(premierLeague.isValid(), "Null season should not be valid.");
        premierLeague.setSeason(new Season());

        assertTrue(premierLeague.isValid(), "Premier League should be valid.");
    }

    /**
     * Tests mapping between PremierLeague and PremierLeagueDTO.
     */
    @Test
    public void map() {
        final PremierLeague premierLeague = generate(PremierLeague.class);

        final ModelMapper modelMapper = new D11BootModelMapper();

        final PremierLeagueDTO premierLeagueDTO = modelMapper.map(premierLeague, PremierLeagueDTO.class);
        final PremierLeague mappedPremierLeague = modelMapper.map(premierLeagueDTO, PremierLeague.class);

        assertEquals(premierLeague, mappedPremierLeague, "Premier league should equal mapped premier league.");
    }

}
