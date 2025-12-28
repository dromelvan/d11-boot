package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Position;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Position repository tests.
 */
class PositionRepositoryTests extends AbstractRepositoryTests<Position, PositionRepository> {

    /**
     * Tests PositionRepository::findByCode.
     */
    @Test
    void testFindByCode() {
        final List<Position> positions = getEntities();
        for (final Position position : positions) {
            final Optional<Position> optional = getRepository().findByCode(position.getCode());

            assertTrue(optional.isPresent());

            optional.ifPresent(result -> assertEquals(position, result));
        }
    }

    /**
     * Tests PositionRepository::findByOrderBySortOrder.
     */
    @Test
    void testFindByOrderBySortOrder() {
        final List<Position> positions = getEntities();
        positions.sort(Comparator.comparing(Position::getSortOrder));

        final List<Position> result = getRepository().findByOrderBySortOrder();

        assertNotNull(result);
        assertEquals(positions, result);
    }

}
