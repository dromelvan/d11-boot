package org.d11.boot.application.model;

import org.d11.boot.api.model.StadiumDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Stadium tests.
 */
public class StadiumTests extends D11EasyRandomTests {

    /**
     * Tests stadium validity.
     */
    @Test
    public void isValid() {
        final Stadium stadium = generate(Stadium.class);

        assertTrue(stadium.isValid(), "New stadium should be valid.");
        assertNotNull(stadium.getTeams(), "100% test coverage or go home.");

        stadium.setName("");
        assertFalse(stadium.isValid(), "Empty name should not be valid.");
        stadium.setName(null);
        assertFalse(stadium.isValid(), "Null name should not be valid.");
        stadium.setName("Name");

        stadium.setCity("");
        assertFalse(stadium.isValid(), "Empty city should not be valid.");
        stadium.setCity(null);
        assertFalse(stadium.isValid(), "Null city should not be valid.");
        stadium.setCity("City");

        stadium.setCapacity(0);
        assertFalse(stadium.isValid(), "Capacity must be positive.");
        stadium.setCapacity(1);

        stadium.setOpened(Stadium.MIN_OPENED_YEAR - 1);
        assertFalse(stadium.isValid(), "Too low year opened should not be valid.");
        stadium.setOpened(Stadium.MAX_OPENED_YEAR + 1);
        assertFalse(stadium.isValid(), "Too high year opened should not be valid.");
        stadium.setOpened(Stadium.MIN_OPENED_YEAR);

        assertTrue(stadium.isValid(), "Stadium should be valid.");
    }

    /**
     * Tests mapping between Stadium and StadiumDTO.
     */
    @Test
    public void map() {
        final Stadium stadium = generate(Stadium.class);

        final StadiumDTO stadiumDTO = map(stadium, StadiumDTO.class);
        final Stadium mappedStadium = map(stadiumDTO, Stadium.class);

        assertEquals(stadium, mappedStadium, "Stadium should equal mapped stadium.");
    }

}
