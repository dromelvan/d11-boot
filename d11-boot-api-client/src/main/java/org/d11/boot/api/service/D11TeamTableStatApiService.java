package org.d11.boot.api.service;

import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.d11.boot.client.api.D11TeamTableStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides D11 team table stat API services.
 */
@Service
public class D11TeamTableStatApiService extends D11ApiService {

    /**
     * Finds a D11 team table stat with a specific id.
     *
     * @param d11TeamTableStatId The id of the D11 team table stat that will be looked up.
     * @return D11 team table stat DTO for the specified id or null if no D11 team table stat was found.
     */
    public D11TeamTableStatDTO findD11TeamTableStatById(final Long d11TeamTableStatId) {
        try {
            final D11TeamTableStatApi d11TeamTableStatApi = new D11TeamTableStatApi(getApiClient());
            return d11TeamTableStatApi.findD11TeamTableStatById(d11TeamTableStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
