package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Season tests.
 */
class SeasonTests extends EasyRandomTests {

    /**
     * Tests Season::isValid.
     */
    @Test
    void testIsValid() {
        final Season season = generate(Season.class);

        assertTrue(season.isValid());

        season.setName("");
        assertFalse(season.isValid());
        season.setName(null);
        assertFalse(season.isValid());
        assertNull(season.getShortName());
        season.setName("INVALID_NAME");
        assertFalse(season.isValid());
        season.setName("1002-1001");
        assertFalse(season.isValid());
        season.setName("1000-1001");
        assertEquals("00-01", season.getShortName());
        assertTrue(season.isValid());

        season.setD11TeamBudget(0);
        assertFalse(season.isValid());
        season.setD11TeamBudget(1);
        assertTrue(season.isValid());

        season.setD11TeamMaxTransfers(-1);
        assertFalse(season.isValid());
        season.setD11TeamMaxTransfers(1);
        assertTrue(season.isValid());

        season.setStatus(null);
        assertFalse(season.isValid());
        season.setStatus(Status.PENDING);
        assertTrue(season.isValid());

        season.setDate(null);
        assertFalse(season.isValid());
        season.setDate(LocalDate.now());

        assertTrue(season.isValid());
    }

}
