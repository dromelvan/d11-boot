package org.d11.boot.api.service;

import org.d11.boot.api.model.D11TeamSeasonStatDTO;
import org.d11.boot.client.api.D11TeamSeasonStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides D11 team season stat API services.
 */
@Service
public class D11TeamSeasonStatApiService extends D11ApiService {

    /**
     * Finds a D11 team season stat with a specific id.
     *
     * @param d11TeamSeasonStatId The id of the D11 team season stat that will be looked up.
     * @return D11 team season stat DTO for the specified id or null if no D11 team season stat was found.
     */
    public D11TeamSeasonStatDTO findD11TeamSeasonStatById(final Long d11TeamSeasonStatId) {
        try {
            final D11TeamSeasonStatApi d11TeamSeasonStatApi = new D11TeamSeasonStatApi(getApiClient());
            return d11TeamSeasonStatApi.findD11TeamSeasonStatById(d11TeamSeasonStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets D11 team season stats for a season ordered by ranking.
     * This will be the current league table standings.
     *
     * @param seasonId Id for the season for which D11 team season stats will be looked up.
     * @return The current D11 league table standings for the season.
     */
    public List<D11TeamSeasonStatDTO> findD11TeamSeasonStatBySeasonId(final Long seasonId) {
        try {
            final D11TeamSeasonStatApi d11TeamSeasonStatApi = new D11TeamSeasonStatApi(getApiClient());
            return d11TeamSeasonStatApi.findD11TeamSeasonStatBySeasonId(seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
