package org.d11.boot.api.service;

import org.d11.boot.api.model.TeamMatchWeekStatDTO;
import org.d11.boot.client.api.TeamMatchWeekStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides team match week stat API services.
 */
@Service
public class TeamMatchWeekStatApiService extends D11ApiService {

    /**
     * Finds a team match week stat with a specific id.
     *
     * @param teamMatchWeekStatId The id of the team match week stat that will be looked up.
     * @return Team match week stat DTO for the specified id or null if no team match week stat was found.
     */
    public TeamMatchWeekStatDTO findTeamMatchWeekStatById(final Long teamMatchWeekStatId) {
        try {
            final TeamMatchWeekStatApi teamMatchWeekStatApi = new TeamMatchWeekStatApi(getApiClient());
            return teamMatchWeekStatApi.findTeamMatchWeekStatById(teamMatchWeekStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
