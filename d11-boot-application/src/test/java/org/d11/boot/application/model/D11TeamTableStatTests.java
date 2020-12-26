package org.d11.boot.application.model;

import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team table stat tests.
 */
public class D11TeamTableStatTests extends D11EasyRandomTests {

    /**
     * Tests D11 team table stat validity.
     */
    @Test
    public void isValid() {
        final D11TeamTableStat d11TeamTableStat = generate(D11TeamTableStat.class);

        d11TeamTableStat.prePersist();

        assertTrue(d11TeamTableStat.isValid(), "New D11 team table stat should be valid.");

        d11TeamTableStat.setD11Team(null);
        assertFalse(d11TeamTableStat.isValid(), "Null D11 team should not be valid.");
        d11TeamTableStat.setD11Team(new D11Team());

        d11TeamTableStat.setD11League(null);
        assertFalse(d11TeamTableStat.isValid(), "Null D11 league should not be valid.");
        d11TeamTableStat.setD11League(new D11League());

        d11TeamTableStat.setD11MatchWeek(null);
        assertFalse(d11TeamTableStat.isValid(), "Null D11 match week should not be valid.");
        d11TeamTableStat.setD11MatchWeek(new D11MatchWeek());

        assertTrue(d11TeamTableStat.isValid(), "D11 team table stat should be valid.");
    }

    /**
     * Tests mapping between D11TeamTableStat and D11TeamTableStatDTO.
     */
    @Test
    public void map() {
        final D11TeamTableStat d11TeamTableStat = generate(D11TeamTableStat.class);

        final D11TeamTableStatDTO d11TeamTableStatDTO = map(d11TeamTableStat, D11TeamTableStatDTO.class);
        final D11TeamTableStat mappedD11TeamTableStat = map(d11TeamTableStatDTO, D11TeamTableStat.class);

        assertEquals(d11TeamTableStat, mappedD11TeamTableStat, "D11 team table stat should equal mapped D11 team table stat.");
    }

}
