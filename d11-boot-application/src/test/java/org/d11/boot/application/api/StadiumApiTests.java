package org.d11.boot.application.api;

import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.repository.StadiumRepository;
import org.d11.boot.client.api.StadiumApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Stadium API tests.
 */
public class StadiumApiTests extends AbstractApiTests<Stadium, StadiumRepository> {

    /**
     * Tests the findStadiumById API operation.
     */
    @Test
    public void findStadiumById() {
        final StadiumApi stadiumApi = new StadiumApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Stadia should not be empty.");

        for(final Stadium stadium : getEntities()) {
            final StadiumDTO result = stadiumApi.findStadiumById(stadium.getId()).block();
            final StadiumDTO stadiumDTO = map(stadium, StadiumDTO.class);

            assertNotNull(result, "Stadium by id should not be null.");
            assertEquals(stadiumDTO, result, "Stadium by id should equal Stadium.");
        }

        assertNotFound(stadiumApi.findStadiumById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

    /**
     * Correct resource string for 'stadium'.
     *
     * @return "stadia".
     */
    @Override
    protected String getResourceString() {
        return "stadia";
    }

}
