package org.d11.boot.application.api;

import org.d11.boot.api.model.PositionDTO;
import org.d11.boot.api.service.PositionApiService;
import org.d11.boot.application.model.Position;
import org.d11.boot.application.repository.PositionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Position API tests.
 */
public class PositionApiTests extends AbstractRepositoryApiTests<Position, PositionRepository, PositionApiService> {

    /**
     * Sets up positions for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findByOrderBySortOrder());
        assertFalse(getEntities().isEmpty(), "Positions should not be empty.");
    }

    /**
     * Tests the findPositionById API operation.
     */
    @Test
    public void findPositionById() {
        for(final Position position : getEntities()) {
            final PositionDTO result = getApiService().findPositionById(position.getId());
            final PositionDTO positionDTO = map(position, PositionDTO.class);
            assertNotNull(result, "Position by id should not be null.");
            assertEquals(positionDTO, result, "Position by id should equal Position.");
        }

        assertNull(getApiService().findPositionById(-1L), "Position not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findAllPositions API operation.
     */
    @Test
    public void findAllPositions() {
        final List<PositionDTO> result = getApiService().findAllPositions();

        final List<PositionDTO> positionDTOs = map(getEntities(), PositionDTO.class);

        assertNotNull(result, "All positions should not be null.");
        assertEquals(positionDTOs, result, "All positions should equal positions.");
    }

}
