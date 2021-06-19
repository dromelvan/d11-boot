package org.d11.boot.api.service;

import org.d11.boot.api.model.TeamSeasonStatDTO;
import org.d11.boot.client.api.TeamSeasonStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides team season stat API services.
 */
@Service
public class TeamSeasonStatApiService extends D11ApiService {

    /**
     * Finds a team season stat with a specific id.
     *
     * @param teamSeasonStatId The id of the team season stat that will be looked up.
     * @return Team season stat DTO for the specified id or null if no team season stat was found.
     */
    public TeamSeasonStatDTO findTeamSeasonStatById(final Long teamSeasonStatId) {
        try {
            final TeamSeasonStatApi teamSeasonStatApi = new TeamSeasonStatApi(getApiClient());
            return teamSeasonStatApi.findTeamSeasonStatById(teamSeasonStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets team season stats for a season ordered by ranking.
     * This will be the current league table standings.
     *
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return The current league table standings for the season.
     */
    public List<TeamSeasonStatDTO> findTeamSeasonStatBySeasonId(final Long seasonId) {
        try {
            final TeamSeasonStatApi teamSeasonStatApi = new TeamSeasonStatApi(getApiClient());
            return teamSeasonStatApi.findTeamSeasonStatBySeasonId(seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets team season stats for a specific team and a specific season.
     *
     * @param teamId Id for the team for which team season stats will be looked up.
     * @param seasonId Id for the season for which team season stats will be looked up.
     * @return Team season stats for the team and the season.
     */
    public TeamSeasonStatDTO findTeamSeasonStatByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        try {
            final TeamSeasonStatApi teamSeasonStatApi = new TeamSeasonStatApi(getApiClient());
            return teamSeasonStatApi.findTeamSeasonStatByTeamIdAndSeasonId(teamId, seasonId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
