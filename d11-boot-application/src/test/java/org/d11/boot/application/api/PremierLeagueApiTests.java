package org.d11.boot.application.api;


import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.repository.PremierLeagueRepository;
import org.d11.boot.client.api.PremierLeagueApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Premier League API tests.
 */
public class PremierLeagueApiTests extends AbstractApiTests {

    /**
     * Premier League repository.
     */
    @Autowired
    private PremierLeagueRepository premierLeagueRepository;
    /**
     * List of Premier Leagues.
     */
    private List<PremierLeague> premierLeagues;

    /**
     * Sets up mocked premier leagues for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.premierLeagues = this.premierLeagueRepository.findAll();
    }

    /**
     * Tests the findPremierLeaguesById API operation.
     */
    @Test
    public void findPremierLeaguesById() {
        final PremierLeagueApi premierLeagueApi = new PremierLeagueApi(getApiClient());

        for(final PremierLeague premierLeague : this.premierLeagues) {
            final PremierLeagueDTO result = premierLeagueApi.findPremierLeagueById(premierLeague.getId()).block();
            final PremierLeagueDTO premierLeagueDTO = map(premierLeague, PremierLeagueDTO.class);

            assertNotNull(result, "Premier League by id should not be null.");
            assertEquals(premierLeagueDTO, result, "Season by id should equal Season.");
        }

        assertNotFound(premierLeagueApi.findPremierLeagueById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

}
