package org.d11.boot.api.service;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.client.api.MatchApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides match API services.
 */
@Service
public class MatchApiService extends D11ApiService {

    /**
     * Finds a match with a specific id.
     *
     * @param matchId The id of the match that will be looked up.
     * @return Match DTO for the specified id or null if no match was found.
     */
    public MatchDTO findMatchById(final Long matchId) {
        try {
            final MatchApi matchApi = new MatchApi(getApiClient());
            return matchApi.findMatchById(matchId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
