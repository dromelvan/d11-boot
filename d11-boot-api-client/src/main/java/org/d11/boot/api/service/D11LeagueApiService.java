package org.d11.boot.api.service;

import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.client.api.D11LeagueApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides D11 league API services.
 */
@Service
public class D11LeagueApiService extends D11ApiService {

    /**
     * Finds a D11 league with a specific id.
     *
     * @param d11LeagueId The id of the D11 league that will be looked up.
     * @return D11 league DTO for the specified id or null if no D11 league was found.
     */
    public D11LeagueDTO findD11LeagueById(final Long d11LeagueId) {
        try {
            final D11LeagueApi d11LeagueApi = new D11LeagueApi(getApiClient());
            return d11LeagueApi.findD11LeagueById(d11LeagueId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
