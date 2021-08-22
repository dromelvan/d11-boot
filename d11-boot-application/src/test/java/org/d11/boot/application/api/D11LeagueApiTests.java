package org.d11.boot.application.api;


import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.api.service.D11LeagueApiService;
import org.d11.boot.application.model.jpa.D11League;
import org.d11.boot.application.repository.D11LeagueRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 league API tests.
 */
public class D11LeagueApiTests extends AbstractRepositoryApiTests<D11League, D11LeagueRepository, D11LeagueApiService> {

    /**
     * Tests the findD11LeagueById API operation.
     */
    @Test
    public void findD11LeagueById() {
        for(final D11League d11League : getEntities()) {
            final D11LeagueDTO result = getApiService().findD11LeagueById(d11League.getId());
            final D11LeagueDTO d11LeagueDTO = map(d11League, D11LeagueDTO.class);

            assertNotNull(result, "D11 league by id should not be null.");
            assertEquals(d11LeagueDTO, result, "D11 league by id should equal D11League.");
        }

        assertNull(getApiService().findD11LeagueById(-1L), "D11 league not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
