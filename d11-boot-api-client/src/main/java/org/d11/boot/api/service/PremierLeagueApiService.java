package org.d11.boot.api.service;

import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.client.api.PremierLeagueApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides Premier LeagueAPI services.
 */
@Service
public class PremierLeagueApiService extends D11ApiService {

    /**
     * Finds a Premier League with a specific id.
     *
     * @param premierLeagueId The id of the Premier League that will be looked up.
     * @return PremierLeague DTO for the specified id or null if no Premier League was found.
     */
    public PremierLeagueDTO findPremierLeagueById(final Long premierLeagueId) {
        try {
            final PremierLeagueApi premierLeagueApi = new PremierLeagueApi(getApiClient());
            return premierLeagueApi.findPremierLeagueById(premierLeagueId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
