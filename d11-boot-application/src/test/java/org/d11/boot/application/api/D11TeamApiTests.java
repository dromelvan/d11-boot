package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.client.api.D11TeamApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * D11 team API tests.
 */
public class D11TeamApiTests extends AbstractApiTests<D11Team, D11TeamRepository> {

    /**
     * Tests the findD11TeamById API operation.
     */
    @Test
    public void findD11TeamsById() {
        final D11TeamApi d11TeamApi = new D11TeamApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "D11 teams should not be empty.");

        for(final D11Team d11Team : getEntities()) {
            final D11TeamDTO result = d11TeamApi.findD11TeamById(d11Team.getId()).block();
            final D11TeamDTO d11TeamDTO = map(d11Team, D11TeamDTO.class);

            assertNotNull(result, "D11 team by id should not be null.");
            assertEquals(d11TeamDTO, result, "D11 team by id should equal D11 team.");
        }

        assertNotFound(d11TeamApi.findD11TeamById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
