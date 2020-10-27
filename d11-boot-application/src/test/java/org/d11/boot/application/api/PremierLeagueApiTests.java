package org.d11.boot.application.api;


import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.repository.PremierLeagueRepository;
import org.d11.boot.client.api.PremierLeagueApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Premier League API tests.
 */
public class PremierLeagueApiTests extends AbstractApiTests<PremierLeague> {

    /**
     * Premier League repository.
     */
    @Autowired
    private PremierLeagueRepository premierLeagueRepository;

    /**
     * Sets up mocked premier leagues for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(this.premierLeagueRepository.findAll());
    }

    /**
     * Tests the findPremierLeagueById API operation.
     */
    @Test
    public void findPremierLeagueById() {
        final PremierLeagueApi premierLeagueApi = new PremierLeagueApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Premier Leagues should not be empty.");

        for(final PremierLeague premierLeague : getEntities()) {
            final PremierLeagueDTO result = premierLeagueApi.findPremierLeagueById(premierLeague.getId()).block();
            final PremierLeagueDTO premierLeagueDTO = map(premierLeague, PremierLeagueDTO.class);

            assertNotNull(result, "Premier League by id should not be null.");
            assertEquals(premierLeagueDTO, result, "Season by id should equal Season.");
        }

        assertNotFound(premierLeagueApi.findPremierLeagueById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
