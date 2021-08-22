package org.d11.boot.application.model;

import org.d11.boot.api.model.PositionDTO;
import org.d11.boot.application.model.jpa.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Position tests.
 */
public class PositionTests extends D11EasyRandomTests {

    /**
     * Tests position validity.
     */
    @Test
    public void isValid() {
        final Position position = generate(Position.class);

        assertTrue(position.isValid(), "New position should be valid.");

        position.setName(null);
        assertFalse(position.isValid(), "Null name should not be valid.");
        position.setName("");
        assertFalse(position.isValid(), "Emtpy name should not be valid.");
        position.setName("Position");

        position.setCode(null);
        assertFalse(position.isValid(), "Null code should not be valid.");
        position.setCode("");
        assertFalse(position.isValid(), "Empty code should not be valid.");
        position.setCode("PPPP");
        assertFalse(position.isValid(), "Too long code should not be valid.");
        position.setCode("P");

        position.setMaxCount(-1);
        assertFalse(position.isValid(), "Negative max count should not be valid.");
        position.setMaxCount(Position.MAX_MAX_COUNT + 1);
        assertFalse(position.isValid(), "Too high max count should not be valid.");
        position.setMaxCount(1);

        position.setSortOrder(-1);
        assertFalse(position.isValid(), "Negative sort order should not be valid.");
        position.setSortOrder(1);

        assertTrue(position.isValid(), "Position should be valid.");
    }

    /**
     * Tests mapping between Position and PositionDTO.
     */
    @Test
    public void map() {
        final Position position = generate(Position.class);

        final PositionDTO positionDTO = map(position, PositionDTO.class);
        final Position mappedPosition = map(positionDTO, Position.class);

        assertEquals(position, mappedPosition, "Position  should equal mapped position.");
    }

}
