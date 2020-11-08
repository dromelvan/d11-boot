package org.d11.boot.application.api;

import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.api.service.StadiumApiService;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.repository.StadiumRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Stadium API tests.
 */
public class StadiumApiTests extends AbstractApiTests<Stadium, StadiumRepository, StadiumApiService> {

    /**
     * Tests the findStadiumById API operation.
     */
    @Test
    public void findStadiumById() {
        for(final Stadium stadium : getEntities()) {
            final StadiumDTO result = getApiService().findStadiumById(stadium.getId());
            final StadiumDTO stadiumDTO = map(stadium, StadiumDTO.class);

            assertNotNull(result, "Stadium by id should not be null.");
            assertEquals(stadiumDTO, result, "Stadium by id should equal Stadium.");
        }

        assertNull(getApiService().findStadiumById(-1L), "Stadium not found should return null.");
        assertBadRequest(get("stadia", "BAD_REQUEST"));
    }

}
