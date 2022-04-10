package org.d11.boot.application.api;


import feign.FeignException;
import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.model.PremierLeague;
import org.d11.boot.application.repository.PremierLeagueRepository;
import org.d11.boot.client.api.PremierLeagueApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Premier League API tests.
 */
public class PremierLeagueApiTests extends AbstractRepositoryApiTests<PremierLeague, PremierLeagueRepository> {

    /**
     * Tests the findPremierLeagueById API operation.
     */
    @Test
    public void findPremierLeagueById() {
        final PremierLeagueApi premierLeagueApi = getApi(PremierLeagueApi.class);
        for(final PremierLeague premierLeague : getEntities()) {
            final PremierLeagueDTO result = premierLeagueApi.findPremierLeagueById(premierLeague.getId());
            final PremierLeagueDTO premierLeagueDTO = map(premierLeague, PremierLeagueDTO.class);

            assertNotNull(result, "Premier League by id should not be null.");
            assertEquals(premierLeagueDTO, result, "Season by id should equal Season.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> premierLeagueApi.findPremierLeagueById(-1L),
                     "Premier league not found should throw NotFound exception.");
    }

}
