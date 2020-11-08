package org.d11.boot.api.service;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.client.api.SeasonApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides season API services.
 */
@Service
public class SeasonApiService extends D11ApiService {

    /**
     * Finds a season with a specific id.
     *
     * @param seasonId The id of the season that will be looked up.
     * @return season DTO for the specified id or null if no season was found.
     */
    public SeasonDTO findSeasonById(final Long seasonId) {
        try {
            final SeasonApi seasonApi = new SeasonApi(getApiClient());
            return seasonApi.findSeasonById(seasonId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets a list of all seasons.
     *
     * @return List of all seasons sorted by the sort order property.
     */
    public List<SeasonDTO> findAllSeasons() {
        final SeasonApi seasonApi = new SeasonApi(getApiClient());
        return seasonApi.findAllSeasons().collectList().block();
    }

    /**
     * Finds the current season.
     *
     * @return The current season or null if no current season was found.
     */
    public SeasonDTO findCurrentSeason() {
        try {
            final SeasonApi seasonApi = new SeasonApi(getApiClient());
            return seasonApi.findCurrentSeason().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
