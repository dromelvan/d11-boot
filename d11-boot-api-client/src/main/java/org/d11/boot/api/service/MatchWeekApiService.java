package org.d11.boot.api.service;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.client.api.MatchWeekApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides match week API services.
 */
@Service
public class MatchWeekApiService extends D11ApiService {

    /**
     * Finds a match week with a specific id.
     *
     * @param matchWeekId The id of the match week that will be looked up.
     * @return match week DTO for the specified id or null if no match week was found.
     */
    public MatchWeekDTO findMatchWeekById(final Long matchWeekId) {
        try {
            final MatchWeekApi matchWeekApi = new MatchWeekApi(getApiClient());
            return matchWeekApi.findMatchWeekById(matchWeekId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the current match week.
     *
     * @return The current match week or null if no current match week was found.
     */
    public MatchWeekDTO findCurrentMatchWeek() {
        try {
            final MatchWeekApi matchWeekApi = new MatchWeekApi(getApiClient());
            return matchWeekApi.findCurrentMatchWeek().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the match weeks for a specific Premier League.
     *
     * @param premierLeagueId Id for the Premier League for which match weeks will be looked up.
     * @return Match week DTOs for the Premier League.
     */
    public List<MatchWeekDTO> findMatchWeekByPremierLeagueId(final Long premierLeagueId) {
        try {
            final MatchWeekApi matchWeekApi = new MatchWeekApi(getApiClient());
            return matchWeekApi.findMatchWeekByPremierLeagueId(premierLeagueId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
