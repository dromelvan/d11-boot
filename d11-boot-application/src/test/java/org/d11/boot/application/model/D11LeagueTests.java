package org.d11.boot.application.model;

import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.application.model.jpa.D11League;
import org.d11.boot.application.model.jpa.Season;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 league tests.
 */
public class D11LeagueTests extends D11EasyRandomTests {

    /**
     * Tests D11 league validity.
     */
    @Test
    public void isValid() {
        final D11League d11League = generate(D11League.class);
        d11League.setSeason(new Season());

        assertTrue(d11League.isValid(), "New D11 league should be valid.");

        d11League.setName("");
        assertFalse(d11League.isValid(), "Empty name should not be valid.");
        d11League.setName(null);
        assertFalse(d11League.isValid(), "Null name should not be valid.");
        d11League.setName("Name");

        d11League.setSeason(null);
        assertFalse(d11League.isValid(), "Null season should not be valid.");
        d11League.setSeason(new Season());

        assertTrue(d11League.isValid(), "D11 league should be valid.");
    }

    /**
     * Tests mapping between D11League and D11LeagueDTO.
     */
    @Test
    public void map() {
        final D11League d11League = generate(D11League.class);

        final D11LeagueDTO d11LeagueDTO = map(d11League, D11LeagueDTO.class);
        final D11League mappedD11League = map(d11LeagueDTO, D11League.class);

        assertEquals(d11League, mappedD11League, "D11 league should equal mapped D11 league.");
    }

}
