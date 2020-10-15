package org.d11.boot.application.api;


import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.application.model.D11League;
import org.d11.boot.application.repository.D11LeagueRepository;
import org.d11.boot.client.api.D11LeagueApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * D11 league API tests.
 */
public class D11LeagueApiTests extends AbstractApiTests {

    /**
     * D11 league repository.
     */
    @Autowired
    private D11LeagueRepository d11LeagueRepository;
    /**
     * List of D11 leagues.
     */
    private List<D11League> d11Leagues;

    /**
     * Sets up mocked D11 leagues for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.d11Leagues = this.d11LeagueRepository.findAll();
    }

    /**
     * Tests the findD11LeaguesById API operation.
     */
    @Test
    public void findD11LeaguesById() {
        final D11LeagueApi d11LeagueApi = new D11LeagueApi(getApiClient());

        for(final D11League d11League : this.d11Leagues) {
            final D11LeagueDTO result = d11LeagueApi.findD11LeagueById(d11League.getId()).block();
            final D11LeagueDTO d11LeagueDTO = map(d11League, D11LeagueDTO.class);

            assertNotNull(result, "D11 league by id should not be null.");
            assertEquals(d11LeagueDTO, result, "D11 league by id should equal D11League.");
        }

        assertNotFound(d11LeagueApi.findD11LeagueById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
