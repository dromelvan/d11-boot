package org.d11.boot.application.model;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 match week tests.
 */
public class D11MatchWeekTests extends D11EasyRandomTests {

    /**
     * Tests D11 match week validity.
     */
    @Test
    public void isValid() {
        final D11MatchWeek d11MatchWeek = generate(D11MatchWeek.class);

        assertTrue(d11MatchWeek.isValid(), "New D11 match week should be valid.");

        d11MatchWeek.setMatchWeekNumber(0);
        assertFalse(d11MatchWeek.isValid(), "Too low match week number should not be valid.");
        d11MatchWeek.setMatchWeekNumber(MatchWeek.MAX_MATCH_WEEK_NUMBER + 1);
        assertFalse(d11MatchWeek.isValid(), "Too high match week number should not be valid.");
        d11MatchWeek.setMatchWeekNumber(1);

        d11MatchWeek.setDate(null);
        assertFalse(d11MatchWeek.isValid(), "Null date should not be valid.");
        d11MatchWeek.setDate(LocalDate.now());

        d11MatchWeek.setD11League(null);
        assertFalse(d11MatchWeek.isValid(), "Null D11 league should not be valid.");
        d11MatchWeek.setD11League(new D11League());

        d11MatchWeek.setMatchWeek(null);
        assertFalse(d11MatchWeek.isValid(), "Null match week should not be valid.");
        d11MatchWeek.setMatchWeek(new MatchWeek());

        assertTrue(d11MatchWeek.isValid(), "D11 match week should be valid.");
    }

    /**
     * Tests mapping between D11MatchWeek and D11MatchWeekDTO.
     */
    @Test
    public void map() {
        final D11MatchWeek d11MatchWeek = generate(D11MatchWeek.class);

        final D11MatchWeekDTO d11MatchWeekDTO = map(d11MatchWeek, D11MatchWeekDTO.class);
        final D11MatchWeek mappedD11MatchWeek = map(d11MatchWeekDTO, D11MatchWeek.class);

        assertEquals(d11MatchWeek, mappedD11MatchWeek, "D11 match week should equal mapped D11 match week.");
    }

}
