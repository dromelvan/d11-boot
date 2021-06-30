package org.d11.boot.api.service;

import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.client.api.D11TeamApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides D11 team API services.
 */
@Service
public class D11TeamApiService extends D11ApiService {

    /**
     * Finds a D11 team with a specific id.
     *
     * @param d11TeamId The id of the D11 team that will be looked up.
     * @return D11 team DTO for the specified id or null if no D11 team was found.
     */
    public D11TeamDTO findD11TeamById(final Long d11TeamId) {
        try {
            final D11TeamApi d11TeamApi = new D11TeamApi(getApiClient());
            return d11TeamApi.findD11TeamById(d11TeamId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds D11 teams for a specific season.
     *
     * @param seasonId The id of the season for which D11 teams will be looked up.
     * @return D11 team DTOs for the specified season.
     */
    public List<D11TeamDTO> findD11TeamBySeasonId(final Long seasonId) {
        try {
            final D11TeamApi d11TeamApi = new D11TeamApi(getApiClient());
            return d11TeamApi.findD11TeamBySeasonId(seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
