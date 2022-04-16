package org.d11.boot.application.model;

import org.d11.boot.api.model.SeasonDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Season tests.
 */
public class SeasonTests extends D11EasyRandomTests {

    /**
     * Tests season validity.
     */
    @Test
    public void isValid() {
        final Season season = generate(Season.class);

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

        season.setD11TeamBudget(0);
        assertFalse(season.isValid(), "Non positive D11 team budget should not be valid.");
        season.setD11TeamBudget(1);

        season.setMaxTransfers(-1);
        assertFalse(season.isValid(), "Negative max transfers should not be valid.");
        season.setMaxTransfers(1);

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
        final Season season = generate(Season.class);

        final SeasonDTO seasonDTO = map(season, SeasonDTO.class);
        final Season mappedSeason = map(seasonDTO, Season.class);

        assertEquals(season, mappedSeason, "Season should equal mapped season.");
    }

}
