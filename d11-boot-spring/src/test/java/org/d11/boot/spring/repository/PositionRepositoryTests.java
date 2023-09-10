package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Position;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Position repository tests.
 */
class PositionRepositoryTests extends D11BootRepositoryTests<Position, PositionRepository> {

    /**
     * Creates new Position repository tests.
     */
    PositionRepositoryTests() {
        super(Position.class);
    }

    /**
     * Tests PositionRepository::findByOrderBySortOrder.
     */
    @Test
    void testFindByOrderBySortOrder() {
        final List<Position> positions = getEntities();
        positions.sort(Comparator.comparing(Position::getSortOrder));

        final List<Position> result = getRepository().findByOrderBySortOrder();

        assertNotNull(result, "PositionRepository::findByOrderBySortOrder not null");
        assertTrue(result.size() >= 2, "PositionRepository::findByOrderBySortOrder size");
        assertEquals(positions, result, "PositionRepository::findByOrderBySortOrder");
    }

}
