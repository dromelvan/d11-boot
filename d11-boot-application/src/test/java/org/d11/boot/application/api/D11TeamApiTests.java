package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.api.service.D11TeamApiService;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.repository.D11TeamRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 team API tests.
 */
public class D11TeamApiTests extends AbstractApiTests<D11Team, D11TeamRepository, D11TeamApiService> {

    /**
     * Tests the findD11TeamById API operation.
     */
    @Test
    public void findD11TeamsById() {
        for(final D11Team d11Team : getEntities()) {
            final D11TeamDTO result = getApiService().findD11TeamById(d11Team.getId());
            final D11TeamDTO d11TeamDTO = map(d11Team, D11TeamDTO.class);

            assertNotNull(result, "D11 team by id should not be null.");
            assertEquals(d11TeamDTO, result, "D11 team by id should equal D11 team.");
        }

        assertNull(getApiService().findD11TeamById(-1L), "D11 team not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
