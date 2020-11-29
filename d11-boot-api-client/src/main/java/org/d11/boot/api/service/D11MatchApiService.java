package org.d11.boot.api.service;

import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.client.api.D11MatchApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides D11 match API services.
 */
@Service
public class D11MatchApiService extends D11ApiService {

    /**
     * Finds a D11 match with a specific id.
     *
     * @param d11MatchId The id of the D11 match that will be looked up.
     * @return D11 Match DTO for the specified id or null if no D11 match was found.
     */
    public D11MatchDTO findD11MatchById(final Long d11MatchId) {
        try {
            final D11MatchApi d11MatchApi = new D11MatchApi(getApiClient());
            return d11MatchApi.findD11MatchById(d11MatchId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
