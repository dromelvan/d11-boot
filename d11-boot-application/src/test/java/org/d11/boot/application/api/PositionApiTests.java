package org.d11.boot.application.api;

import org.d11.boot.api.model.PositionDTO;
import org.d11.boot.application.model.Position;
import org.d11.boot.application.repository.PositionRepository;
import org.d11.boot.client.api.PositionApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Position API tests.
 */
public class PositionApiTests extends AbstractApiTests<Position, PositionRepository> {

    /**
     * Sets up positions for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findByOrderBySortOrder());
    }

    /**
     * Tests the findPositionById API operation.
     */
    @Test
    public void findPositionById() {
        final PositionApi positionApi = new PositionApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Positions should not be empty.");

        for(final Position position : getEntities()) {
            final PositionDTO result = positionApi.findPositionById(position.getId()).block();
            final PositionDTO positionDTO = map(position, PositionDTO.class);
            assertNotNull(result, "Position by id should not be null.");
            assertEquals(positionDTO, result, "Position by id should equal Position.");
        }

        assertNotFound(positionApi.findPositionById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

    /**
     * Tests the findAllPositions API operation.
     */
    @Test
    public void findAllPositions() {
        final PositionApi positionApi = new PositionApi(getApiClient());
        final List<PositionDTO> result = positionApi.findAllPositions().collectList().block();

        final List<PositionDTO> positionDTOs = map(getEntities(), PositionDTO.class);

        assertNotNull(result, "All positions should not be null.");
        assertEquals(positionDTOs, result, "All positions should equal positions.");
    }

}
