package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.client.PositionApi;
import org.d11.boot.api.v2.model.PositionDTO;
import org.d11.boot.api.v2.model.PositionsResponseBodyDTO;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Position controller tests.
 */
class PositionControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Position repository.
     */
    @Autowired
    private PositionRepository positionRepository;

    /**
     * Tests PositionController::getPositions.
     */
    @Test
    void testGetPositions() {
        final PositionApi positionApi = getApi(PositionApi.class);

        final List<Position> positions = this.positionRepository.findByOrderBySortOrder();
        assertFalse(positions.isEmpty());

        final PositionsResponseBodyDTO positionsResponseBodyDTO = positionApi.getPositions();

        assertNotNull(positionsResponseBodyDTO);

        final List<PositionDTO> result = positionsResponseBodyDTO.getPositions();

        assertEquals(positions.size(), result.size());

        for (int i = 0; i < positions.size(); ++i) {
            final Position position = positions.get(i);
            final PositionDTO positionDTO = result.get(i);

            assertEquals(map(position, PositionDTO.class), positionDTO);
        }
    }

}
