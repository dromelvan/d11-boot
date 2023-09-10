package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Position tests.
 */
class PositionTests extends EasyRandomTests {

    /**
     * Tests Position::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final Position position = generate(Position.class);

        assertTrue(position.isValid(), "Position::isValid");

        position.setName(null);
        assertFalse(position.isValid(), "Position::isValid name null");
        position.setName("");
        assertFalse(position.isValid(), "Position::isValid name empty");
        position.setName("Position");

        position.setCode(null);
        assertFalse(position.isValid(), "Position::isValid code null");
        position.setCode("");
        assertFalse(position.isValid(), "Position::isValid code empty");
        position.setCode("PPPP");
        assertFalse(position.isValid(), "Position::isValid code too long");
        position.setCode("P");

        position.setMaxCount(-1);
        assertFalse(position.isValid(), "Position::isValid max count negative");
        position.setMaxCount(Position.MAX_MAX_COUNT + 1);
        assertFalse(position.isValid(), "Position::isValid max count too high");
        position.setMaxCount(1);

        position.setSortOrder(-1);
        assertFalse(position.isValid(), "Position::isValid sort order negative");
        position.setSortOrder(1);

        assertTrue(position.isValid(), "Position::isValid valid");
    }

}
