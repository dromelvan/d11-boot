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
            assertEquals(status, Status.valueOfId(status.getId()), "Status::withId id " + status);
        }

        assertThrows(IllegalArgumentException.class, () -> Status.valueOfId(-1), "Status::valueOfId id invalid");
    }

    /**
     * Tests Status::isValidTransition.
     */
    @Test
    void testIsValidTransition() {
        assertFalse(Status.PENDING.isValidTransition(Status.PENDING), "Status::isValidTransition PENDING to PENDING");
        assertTrue(Status.PENDING.isValidTransition(Status.ACTIVE), "Status::isValidTransition PENDING to ACTIVE");
        assertFalse(Status.PENDING.isValidTransition(Status.FULL_TIME),
                    "Status::isValidTransition PENDING to FULL_TIME");
        assertFalse(Status.PENDING.isValidTransition(Status.FINISHED), "Status::isValidTransition PENDING to FINISHED");
        assertTrue(Status.PENDING.isValidTransition(Status.POSTPONED),
                   "Status::isValidTransition PENDING to POSTPONED");

        assertFalse(Status.ACTIVE.isValidTransition(Status.PENDING), "Status::isValidTransition ACTIVE to PENDING");
        assertFalse(Status.ACTIVE.isValidTransition(Status.ACTIVE), "Status::isValidTransition ACTIVE to ACTIVE");
        assertTrue(Status.ACTIVE.isValidTransition(Status.FULL_TIME), "Status::isValidTransition ACTIVE to FULL_TIME");
        assertTrue(Status.ACTIVE.isValidTransition(Status.FINISHED), "Status::isValidTransition ACTIVE to FINISHED");
        assertFalse(Status.ACTIVE.isValidTransition(Status.POSTPONED), "Status::isValidTransition ACTIVE to POSTPONED");

        assertFalse(Status.FULL_TIME.isValidTransition(Status.PENDING),
                    "Status::isValidTransition FULL_TIME to PENDING");
        assertFalse(Status.FULL_TIME.isValidTransition(Status.ACTIVE), "Status::isValidTransition FULL_TIME to ACTIVE");
        assertFalse(Status.FULL_TIME.isValidTransition(Status.FULL_TIME),
                    "Status::isValidTransition FULL_TIME to FULL_TIME");
        assertTrue(Status.FULL_TIME.isValidTransition(Status.FINISHED),
                   "Status::isValidTransition FULL_TIME to FINISHED");
        assertFalse(Status.FULL_TIME.isValidTransition(Status.POSTPONED),
                    "Status::isValidTransition FULL_TIME to POSTPONED");

        assertFalse(Status.FINISHED.isValidTransition(Status.PENDING), "Status::isValidTransition FINISHED to PENDING");
        assertFalse(Status.FINISHED.isValidTransition(Status.ACTIVE), "Status::isValidTransition FINISHED to ACTIVE");
        assertFalse(Status.FINISHED.isValidTransition(Status.FULL_TIME),
                    "Status::isValidTransition FINISHED to FULL_TIME");
        assertFalse(Status.FINISHED.isValidTransition(Status.FINISHED),
                    "Status::isValidTransition FINISHED to FINISHED");
        assertFalse(Status.FINISHED.isValidTransition(Status.POSTPONED),
                    "Status::isValidTransition FINISHED to POSTPONED");

        assertTrue(Status.POSTPONED.isValidTransition(Status.PENDING),
                   "Status::isValidTransition POSTPONED to PENDING");
        assertFalse(Status.POSTPONED.isValidTransition(Status.ACTIVE), "Status::isValidTransition POSTPONED to ACTIVE");
        assertFalse(Status.POSTPONED.isValidTransition(Status.FULL_TIME),
                   "Status::isValidTransition POSTPONED to FULL_TIME");
        assertFalse(Status.POSTPONED.isValidTransition(Status.FINISHED),
                   "Status::isValidTransition POSTPONED to FINISHED");
        assertFalse(Status.POSTPONED.isValidTransition(Status.POSTPONED),
                    "Status::isValidTransition POSTPONED to POSTPONED");
    }

}
