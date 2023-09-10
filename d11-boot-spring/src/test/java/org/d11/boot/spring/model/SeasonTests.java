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
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final Season season = generate(Season.class);

        assertTrue(season.isValid(), "Season::isValid");

        season.setName("");
        assertFalse(season.isValid(), "Season::isValid name empty");
        season.setName(null);
        assertFalse(season.isValid(), "Season::isValid name null");
        assertNull(season.getShortName(), "Season::getShortName name null");
        season.setName("INVALID_NAME");
        assertFalse(season.isValid(), "Season::isValid name invalid");
        season.setName("1002-1001");
        assertFalse(season.isValid(), "Season::isValid year interval invalid");
        season.setName("1000-1001");
        assertEquals(season.getShortName(), "00-01", "Season::getShortName");
        assertTrue(season.isValid(), "Season::isValid name valid");

        season.setD11TeamBudget(0);
        assertFalse(season.isValid(), "Season::isValid team budget non positive");
        season.setD11TeamBudget(1);
        assertTrue(season.isValid(), "Season::isValid team budget valid");

        season.setD11TeamMaxTransfers(-1);
        assertFalse(season.isValid(), "Season::isValid max transfers negative");
        season.setD11TeamMaxTransfers(1);
        assertTrue(season.isValid(), "Season::isValid max transfers valid");

        season.setStatus(null);
        assertFalse(season.isValid(), "Season::isValid status null");
        season.setStatus(Status.PENDING);
        assertTrue(season.isValid(), "Season::isValid status valid");

        season.setDate(null);
        assertFalse(season.isValid(), "Season::isValid date null");
        season.setDate(LocalDate.now());

        assertTrue(season.isValid(), "Season::isValid valid");
    }

}
