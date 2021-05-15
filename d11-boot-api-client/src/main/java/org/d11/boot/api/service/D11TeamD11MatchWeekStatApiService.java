package org.d11.boot.api.service;

import org.d11.boot.api.model.D11TeamD11MatchWeekStatDTO;
import org.d11.boot.client.api.D11TeamD11MatchWeekStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides D11 team D11 match week stat API services.
 */
@Service
public class D11TeamD11MatchWeekStatApiService extends D11ApiService {

    /**
     * Finds a D11 team D11 match week stat with a specific id.
     *
     * @param d11TeamD11MatchWeekStatId The id of the D11 team D11 match week stat that will be looked up.
     * @return D11 team D11 match week stat DTO for the specified id or null if no D11 team D11 match week stat was found.
     */
    public D11TeamD11MatchWeekStatDTO findD11TeamD11MatchWeekStatById(final Long d11TeamD11MatchWeekStatId) {
        try {
            final D11TeamD11MatchWeekStatApi d11TeamD11MatchWeekStatApi = new D11TeamD11MatchWeekStatApi(getApiClient());
            return d11TeamD11MatchWeekStatApi.findD11TeamD11MatchWeekStatById(d11TeamD11MatchWeekStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
