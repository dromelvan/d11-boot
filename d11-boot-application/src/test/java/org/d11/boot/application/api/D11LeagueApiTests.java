package org.d11.boot.application.api;


import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.application.model.D11League;
import org.d11.boot.application.repository.D11LeagueRepository;
import org.d11.boot.client.api.D11LeagueApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * D11 league API tests.
 */
public class D11LeagueApiTests extends AbstractApiTests<D11League> {

    /**
     * D11 league repository.
     */
    @Autowired
    private D11LeagueRepository d11LeagueRepository;

    /**
     * Sets up mocked D11 leagues for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(this.d11LeagueRepository.findAll());
    }

    /**
     * Tests the findD11LeagueById API operation.
     */
    @Test
    public void findD11LeagueById() {
        final D11LeagueApi d11LeagueApi = new D11LeagueApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "D11 leagues should not be empty.");

        for(final D11League d11League : getEntities()) {
            final D11LeagueDTO result = d11LeagueApi.findD11LeagueById(d11League.getId()).block();
            final D11LeagueDTO d11LeagueDTO = map(d11League, D11LeagueDTO.class);

            assertNotNull(result, "D11 league by id should not be null.");
            assertEquals(d11LeagueDTO, result, "D11 league by id should equal D11League.");
        }

        assertNotFound(d11LeagueApi.findD11LeagueById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
