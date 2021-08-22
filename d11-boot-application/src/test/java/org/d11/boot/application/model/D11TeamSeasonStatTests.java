package org.d11.boot.application.model;


import org.d11.boot.api.model.D11TeamSeasonStatDTO;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.D11TeamSeasonStat;
import org.d11.boot.application.model.jpa.Season;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team season stat tests.
 */
public class D11TeamSeasonStatTests extends D11EasyRandomTests {

    /**
     * Tests D11 team season stat validity.
     */
    @Test
    public void isValid() {
        final D11TeamSeasonStat d11TeamSeasonStat = generate(D11TeamSeasonStat.class);

        d11TeamSeasonStat.prePersist();

        assertTrue(d11TeamSeasonStat.isValid(), "New D11 team season stat should be valid.");

        d11TeamSeasonStat.setWinCount(-1);
        assertFalse(d11TeamSeasonStat.isValid(), "Negative win count should not be valid.");
        d11TeamSeasonStat.setWinCount(null);
        assertTrue(d11TeamSeasonStat.isValid(), "Null win count should be valid.");

        d11TeamSeasonStat.setD11Team(null);
        assertFalse(d11TeamSeasonStat.isValid(), "Null D11 team should not be valid.");
        d11TeamSeasonStat.setD11Team(new D11Team());

        d11TeamSeasonStat.setSeason(null);
        assertFalse(d11TeamSeasonStat.isValid(), "Null season should not be valid.");
        d11TeamSeasonStat.setSeason(new Season());

        assertTrue(d11TeamSeasonStat.isValid(), "D11 team season stat should be valid.");
    }

    /**
     * Tests mapping between D11TeamSeasonStat and D11TeamSeasonStatDTO.
     */
    @Test
    public void map() {
        final D11TeamSeasonStat d11TeamSeasonStat = generate(D11TeamSeasonStat.class);

        final D11TeamSeasonStatDTO d11TeamSeasonStatDTO = map(d11TeamSeasonStat, D11TeamSeasonStatDTO.class);
        final D11TeamSeasonStat mappedD11TeamSeasonStat = map(d11TeamSeasonStatDTO, D11TeamSeasonStat.class);

        assertEquals(d11TeamSeasonStat, mappedD11TeamSeasonStat,
                "D11 team season stat should equal mapped D11 team season stat.");
    }

}
