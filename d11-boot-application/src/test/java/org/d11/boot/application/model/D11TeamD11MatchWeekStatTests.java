package org.d11.boot.application.model;

import org.d11.boot.api.model.D11TeamD11MatchWeekStatDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team D11 match week stat tests.
 */
public class D11TeamD11MatchWeekStatTests extends D11EasyRandomTests {

    /**
     * Tests D11 team D11 match week stat validity.
     */
    @Test
    public void isValid() {
        final D11TeamD11MatchWeekStat d11TeamD11MatchWeekStat = generate(D11TeamD11MatchWeekStat.class);

        d11TeamD11MatchWeekStat.prePersist();

        assertTrue(d11TeamD11MatchWeekStat.isValid(), "New D11 team D11 match week stat should be valid.");

        d11TeamD11MatchWeekStat.setD11Team(null);
        assertFalse(d11TeamD11MatchWeekStat.isValid(), "Null D11 team should not be valid.");
        d11TeamD11MatchWeekStat.setD11Team(new D11Team());

        d11TeamD11MatchWeekStat.setD11MatchWeek(null);
        assertFalse(d11TeamD11MatchWeekStat.isValid(), "Null D11 match week should not be valid.");
        d11TeamD11MatchWeekStat.setD11MatchWeek(new D11MatchWeek());

        assertTrue(d11TeamD11MatchWeekStat.isValid(), "D11 team D11 match week stat should be valid.");
    }

    /**
     * Tests mapping between D11TeamD11MatchWeekStat and D11TeamD11MatchWeekStatDTO.
     */
    @Test
    public void map() {
        final D11TeamD11MatchWeekStat d11TeamD11MatchWeekStat = generate(D11TeamD11MatchWeekStat.class);

        final D11TeamD11MatchWeekStatDTO d11TeamD11MatchWeekStatDTO = map(d11TeamD11MatchWeekStat, D11TeamD11MatchWeekStatDTO.class);
        final D11TeamD11MatchWeekStat mappedD11TeamD11MatchWeekStat = map(d11TeamD11MatchWeekStatDTO, D11TeamD11MatchWeekStat.class);

        assertEquals(d11TeamD11MatchWeekStat, mappedD11TeamD11MatchWeekStat, "D11 team D11 match week stat should equal mapped D11 team D11 match week stat.");
    }

}
