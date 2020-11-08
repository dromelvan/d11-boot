package org.d11.boot.application.api;


import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.api.service.PremierLeagueApiService;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.repository.PremierLeagueRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Premier League API tests.
 */
public class PremierLeagueApiTests extends AbstractApiTests<PremierLeague, PremierLeagueRepository, PremierLeagueApiService> {

    /**
     * Tests the findPremierLeagueById API operation.
     */
    @Test
    public void findPremierLeagueById() {
        for(final PremierLeague premierLeague : getEntities()) {
            final PremierLeagueDTO result = getApiService().findPremierLeagueById(premierLeague.getId());
            final PremierLeagueDTO premierLeagueDTO = map(premierLeague, PremierLeagueDTO.class);

            assertNotNull(result, "Premier League by id should not be null.");
            assertEquals(premierLeagueDTO, result, "Season by id should equal Season.");
        }

        assertNull(getApiService().findPremierLeagueById(-1L), "Premier league not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
