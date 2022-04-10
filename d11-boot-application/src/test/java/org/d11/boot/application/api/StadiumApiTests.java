package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.repository.StadiumRepository;
import org.d11.boot.client.api.StadiumApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Stadium API tests.
 */
public class StadiumApiTests extends AbstractRepositoryApiTests<Stadium, StadiumRepository> {

    /**
     * Tests the findStadiumById API operation.
     */
    @Test
    public void findStadiumById() {
        final StadiumApi stadiumApi = getApi(StadiumApi.class);
        for(final Stadium stadium : getEntities()) {
            final StadiumDTO result = stadiumApi.findStadiumById(stadium.getId());
            final StadiumDTO stadiumDTO = map(stadium, StadiumDTO.class);

            assertNotNull(result, "Stadium by id should not be null.");
            assertEquals(stadiumDTO, result, "Stadium by id should equal Stadium.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> stadiumApi.findStadiumById(-1L),
                     "Stadium not found should throw NotFound exception.");
    }

}
