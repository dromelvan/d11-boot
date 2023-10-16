package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Position service tests.
 */
class PositionServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked position repository.
     */
    @Mock
    private PositionRepository positionRepository;

    /**
     * Position service.
     */
    @InjectMocks
    private PositionService positionService;

    /**
     * Tests PositionService::getPositions.
     */
    @Test
    void testGetPositions() {
        final List<Position> positions = generateList(Position.class);
        when(this.positionRepository.findByOrderBySortOrder()).thenReturn(positions);

        final List<Position> result = this.positionService.getPositions();

        assertNotNull(result, "PositionService::getPositions not null");
        assertFalse(result.isEmpty(), "PositionService::getPositions isEmpty");
        assertEquals(positions, result, "PositionService::getPositions");
    }

}
