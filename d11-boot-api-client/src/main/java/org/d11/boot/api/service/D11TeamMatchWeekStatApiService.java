package org.d11.boot.api.service;

import org.d11.boot.api.model.D11TeamMatchWeekStatDTO;
import org.d11.boot.client.api.D11TeamMatchWeekStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides D11 team match week stat API services.
 */
@Service
public class D11TeamMatchWeekStatApiService extends D11ApiService {

    /**
     * Finds a D11 team match week stat with a specific id.
     *
     * @param d11TeamMatchWeekStatId The id of the D11 team match week stat that will be looked up.
     * @return D11 team match week stat DTO for the specified id or null if no D11 team match week stat was found.
     */
    public D11TeamMatchWeekStatDTO findD11TeamMatchWeekStatById(final Long d11TeamMatchWeekStatId) {
        try {
            final D11TeamMatchWeekStatApi d11TeamMatchWeekStatApi = new D11TeamMatchWeekStatApi(getApiClient());
            return d11TeamMatchWeekStatApi.findD11TeamMatchWeekStatById(d11TeamMatchWeekStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
