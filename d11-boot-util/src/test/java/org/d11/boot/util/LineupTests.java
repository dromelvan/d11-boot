package org.d11.boot.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lineup tests.
 */
class LineupTests {

    /**
     * Tests Lineup::valueOfId.
     */
    @Test
    void testWithId() {
        for (final Lineup lineup : Lineup.values()) {
            assertEquals(lineup, Lineup.valueOfId(lineup.getId()));
        }

        assertThrows(IllegalArgumentException.class, () -> Lineup.valueOfId(-1));
    }

}
