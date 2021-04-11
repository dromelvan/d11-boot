package org.d11.boot.api.service;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.client.api.D11MatchWeekApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides D11 match week API services.
 */
@Service
public class D11MatchWeekApiService extends D11ApiService {

    /**
     * Finds a D11 match week with a specific id.
     *
     * @param d11MatchWeekId The id of the D11 match week that will be looked up.
     * @return D11 match week DTO for the specified id or null if no D11 match week was found.
     */
    public D11MatchWeekDTO findD11MatchWeekById(final Long d11MatchWeekId) {
        try {
            final D11MatchWeekApi d11MatchWeekApi = new D11MatchWeekApi(getApiClient());
            return d11MatchWeekApi.findD11MatchWeekById(d11MatchWeekId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the current D11 match week.
     *
     * @return The current D11 match week or null if no current match week was found.
     */
    public D11MatchWeekDTO findCurrentD11MatchWeek() {
        try {
            final D11MatchWeekApi d11MatchWeekApi = new D11MatchWeekApi(getApiClient());
            return d11MatchWeekApi.findCurrentD11MatchWeek().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the D11 match weeks for a specific D11 league.
     *
     * @param d11LeagueId Id for the D11 league for which D11 match weeks will be looked up.
     * @return D11 match week DTOs for the D11 league.
     */
    public List<D11MatchWeekDTO> findD11MatchWeekByD11LeagueId(final Long d11LeagueId) {
        try {
            final D11MatchWeekApi d11MatchWeekApi = new D11MatchWeekApi(getApiClient());
            return d11MatchWeekApi.findD11MatchWeekByD11LeagueId(d11LeagueId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
