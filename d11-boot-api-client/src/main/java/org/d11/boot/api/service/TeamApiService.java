package org.d11.boot.api.service;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.client.api.TeamApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides team API services.
 */
@Service
public class TeamApiService extends D11ApiService {

    /**
     * Finds a team with a specific id.
     *
     * @param teamId The id of the team that will be looked up.
     * @return team DTO for the specified id or null if no team was found.
     */
    public TeamDTO findTeamById(final Long teamId) {
        try {
            final TeamApi teamApi = new TeamApi(getApiClient());
            return teamApi.findTeamById(teamId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
