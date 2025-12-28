package org.d11.boot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Status tests.
 */
class StatusTests {

    /**
     * Tests Status::valueOfId.
     */
    @Test
    void testWithId() {
        for (final Status status : Status.values()) {
            assertEquals(status, Status.valueOfId(status.getId()));
        }

        assertThrows(IllegalArgumentException.class, () -> Status.valueOfId(-1));
    }

    /**
     * Tests Status::isValidTransition.
     */
    @Test
    void testIsValidTransition() {
        assertFalse(Status.PENDING.isValidTransition(Status.PENDING));
        assertTrue(Status.PENDING.isValidTransition(Status.ACTIVE));
        assertFalse(Status.PENDING.isValidTransition(Status.FULL_TIME));
        assertFalse(Status.PENDING.isValidTransition(Status.FINISHED));
        assertTrue(Status.PENDING.isValidTransition(Status.POSTPONED));

        assertFalse(Status.ACTIVE.isValidTransition(Status.PENDING));
        assertFalse(Status.ACTIVE.isValidTransition(Status.ACTIVE));
        assertTrue(Status.ACTIVE.isValidTransition(Status.FULL_TIME));
        assertTrue(Status.ACTIVE.isValidTransition(Status.FINISHED));
        assertFalse(Status.ACTIVE.isValidTransition(Status.POSTPONED));

        assertFalse(Status.FULL_TIME.isValidTransition(Status.PENDING));
        assertFalse(Status.FULL_TIME.isValidTransition(Status.ACTIVE));
        assertFalse(Status.FULL_TIME.isValidTransition(Status.FULL_TIME));
        assertTrue(Status.FULL_TIME.isValidTransition(Status.FINISHED));
        assertFalse(Status.FULL_TIME.isValidTransition(Status.POSTPONED));

        assertFalse(Status.FINISHED.isValidTransition(Status.PENDING));
        assertFalse(Status.FINISHED.isValidTransition(Status.ACTIVE));
        assertFalse(Status.FINISHED.isValidTransition(Status.FULL_TIME));
        assertFalse(Status.FINISHED.isValidTransition(Status.FINISHED));
        assertFalse(Status.FINISHED.isValidTransition(Status.POSTPONED));

        assertTrue(Status.POSTPONED.isValidTransition(Status.PENDING));
        assertFalse(Status.POSTPONED.isValidTransition(Status.ACTIVE));
        assertFalse(Status.POSTPONED.isValidTransition(Status.FULL_TIME));
        assertFalse(Status.POSTPONED.isValidTransition(Status.FINISHED));
        assertFalse(Status.POSTPONED.isValidTransition(Status.POSTPONED));
    }

}
