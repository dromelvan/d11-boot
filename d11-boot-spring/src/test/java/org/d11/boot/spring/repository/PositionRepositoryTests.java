package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Position;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Position repository tests.
 */
class PositionRepositoryTests extends AbstractRepositoryTests<Position, PositionRepository> {

    /**
     * Tests PositionRepository::findByOrderBySortOrder.
     */
    @Test
    void testFindByOrderBySortOrder() {
        final List<Position> positions = getEntities();
        positions.sort(Comparator.comparing(Position::getSortOrder));

        final List<Position> result = getRepository().findByOrderBySortOrder();

        assertNotNull(result, "PositionRepository::findByOrderBySortOrder not null");
        assertEquals(positions, result, "PositionRepository::findByOrderBySortOrder");
    }

}
