package org.d11.boot.application.model;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.util.D11BootModelMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Season tests.
 */
public class SeasonTests {

    /**
     * Random season generator.
     */
    private final D11EasyRandom d11EasyRandom = new D11EasyRandom();

    /**
     * Tests season validity.
     */
    @Test
    public void isValid() {
        final Season season = this.d11EasyRandom.nextObject(Season.class);

        assertTrue(season.isValid(), "New season should be valid.");

        season.setName("");
        assertFalse(season.isValid(), "Empty name should not be valid.");
        season.setName(null);
        assertFalse(season.isValid(), "Null name should not be valid.");
        assertNull(season.getShortName(), "Null name should produce null shortName.");
        season.setName("INVALID_NAME");
        assertFalse(season.isValid(), "Invalid name should not be valid.");
        season.setName("1002-1001");
        assertFalse(season.isValid(), "Invalid year interval should not be valid.");
        season.setName("1000-1001");
        assertEquals(season.getShortName(), "00-01", "Valid name should produce correct shortName.");

        season.setStatus(null);
        assertFalse(season.isValid(), "Null status should not be valid.");
        season.setStatus(Status.PENDING);

        season.setDate(null);
        assertFalse(season.isValid(), "Null date should not be valid.");
        season.setDate(LocalDate.now());

        season.setPremierLeague(null);
        assertFalse(season.isValid(), "Null Premier League should not be valid.");
        season.setPremierLeague(new PremierLeague());

        season.setD11League(null);
        assertFalse(season.isValid(), "Null D11 league should not be valid.");
        season.setD11League(new D11League());

        assertTrue(season.isValid(), "Season should be valid.");
    }

    /**
     * Tests mapping between Season and SeasonDTO.
     */
    @Test
    public void map() {
        final Season season = this.d11EasyRandom.nextObject(Season.class);

        final ModelMapper modelMapper = new D11BootModelMapper();

        final SeasonDTO seasonDTO = modelMapper.map(season, SeasonDTO.class);
        final Season mappedSeason = modelMapper.map(seasonDTO, Season.class);

        assertEquals(season, mappedSeason, "Season should equal mapped season.");
    }

}
