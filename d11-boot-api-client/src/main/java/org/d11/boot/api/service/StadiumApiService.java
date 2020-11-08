package org.d11.boot.api.service;

import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.client.api.StadiumApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides stadium API services.
 */
@Service
public class StadiumApiService extends D11ApiService {

    /**
     * Finds a stadium with a specific id.
     *
     * @param stadiumId The id of the stadium that will be looked up.
     * @return Stadium DTO for the specified id or null if no stadium was found.
     */
    public StadiumDTO findStadiumById(final Long stadiumId) {
        try {
            final StadiumApi stadiumApi = new StadiumApi(getApiClient());
            return stadiumApi.findStadiumById(stadiumId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
