package org.d11.boot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}
