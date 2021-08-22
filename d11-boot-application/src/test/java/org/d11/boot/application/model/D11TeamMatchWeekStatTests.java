package org.d11.boot.application.model;

import org.d11.boot.api.model.D11TeamMatchWeekStatDTO;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.D11TeamMatchWeekStat;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team match week stat tests.
 */
public class D11TeamMatchWeekStatTests extends D11EasyRandomTests {

    /**
     * Tests D11 team match week stat validity.
     */
    @Test
    public void isValid() {
        final D11TeamMatchWeekStat d11TeamMatchWeekStat = generate(D11TeamMatchWeekStat.class);

        d11TeamMatchWeekStat.prePersist();

        assertTrue(d11TeamMatchWeekStat.isValid(), "New D11 team match week stat should be valid.");

        d11TeamMatchWeekStat.setD11Team(null);
        assertFalse(d11TeamMatchWeekStat.isValid(), "Null D11 team should not be valid.");
        d11TeamMatchWeekStat.setD11Team(new D11Team());

        d11TeamMatchWeekStat.setMatchWeek(null);
        assertFalse(d11TeamMatchWeekStat.isValid(), "Null match week should not be valid.");
        d11TeamMatchWeekStat.setMatchWeek(new MatchWeek());

        assertTrue(d11TeamMatchWeekStat.isValid(), "D11 team match week stat should be valid.");
    }

    /**
     * Tests mapping between D11TeamMatchWeekStat and D11TeamMatchWeekStatDTO.
     */
    @Test
    public void map() {
        final D11TeamMatchWeekStat d11TeamMatchWeekStat = generate(D11TeamMatchWeekStat.class);

        final D11TeamMatchWeekStatDTO d11TeamMatchWeekStatDTO = map(d11TeamMatchWeekStat, D11TeamMatchWeekStatDTO.class);
        final D11TeamMatchWeekStat mappedD11TeamMatchWeekStat = map(d11TeamMatchWeekStatDTO, D11TeamMatchWeekStat.class);

        assertEquals(d11TeamMatchWeekStat, mappedD11TeamMatchWeekStat, "D11 team match week stat should equal mapped D11 team match week stat.");
    }

}
