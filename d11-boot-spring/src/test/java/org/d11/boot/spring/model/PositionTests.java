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
    void testIsValid() {
        final Position position = generate(Position.class);

        assertTrue(position.isValid());

        position.setName(null);
        assertFalse(position.isValid());
        position.setName("");
        assertFalse(position.isValid());
        position.setName("Position");

        position.setCode(null);
        assertFalse(position.isValid());
        position.setCode("");
        assertFalse(position.isValid());
        position.setCode("PPPP");
        assertFalse(position.isValid());
        position.setCode("P");

        position.setMaxCount(-1);
        assertFalse(position.isValid());
        position.setMaxCount(Position.MAX_MAX_COUNT + 1);
        assertFalse(position.isValid());
        position.setMaxCount(1);

        position.setSortOrder(-1);
        assertFalse(position.isValid());
        position.setSortOrder(1);

        assertTrue(position.isValid());
    }

}
