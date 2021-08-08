package org.d11.boot.api.service;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.api.model.TeamNameDTO;
import org.d11.boot.client.api.TeamApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

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

    /**
     * Gets a list of all teams.
     *
     * @return List of all teams sorted by name.
     */
    public List<TeamNameDTO> findAllTeams() {
        final TeamApi teamApi = new TeamApi(getApiClient());
        return teamApi.findAllTeams().collectList().block();
    }

}
