package org.d11.boot.api.service;

import org.d11.boot.api.model.TeamTableStatDTO;
import org.d11.boot.client.api.TeamTableStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides team table stat API services.
 */
@Service
public class TeamTableStatApiService extends D11ApiService {

    /**
     * Finds a team table stat with a specific id.
     *
     * @param teamTableStatId The id of the team table stat that will be looked up.
     * @return Team table stat DTO for the specified id or null if no team table stat was found.
     */
    public TeamTableStatDTO findTeamTableStatById(final Long teamTableStatId) {
        try {
            final TeamTableStatApi teamTableStatApi = new TeamTableStatApi(getApiClient());
            return teamTableStatApi.findTeamTableStatById(teamTableStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets team table stats for a Premier League ordered by match week id, descending, and ranking.
     * This will be the current league table standings.
     *
     * @param premierLeagueId Id for the Premier League for which team table stats will be looked up.
     * @return The current league table standings for the Premier League.
     */
    public List<TeamTableStatDTO> findTeamTableStatByPremierLeagueId(final Long premierLeagueId) {
        try {
            final TeamTableStatApi teamTableStatApi = new TeamTableStatApi(getApiClient());
            return teamTableStatApi.findTeamTableStatByPremierLeagueId(premierLeagueId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
