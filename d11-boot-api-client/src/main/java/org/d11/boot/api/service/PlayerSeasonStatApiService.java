package org.d11.boot.api.service;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.client.api.PlayerSeasonStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides player season stat API services.
 */
@Service
public class PlayerSeasonStatApiService extends D11ApiService {

    /**
     * Finds a player season stat with a specific id.
     *
     * @param playerSeasonStatId The id of the player season stat that will be looked up.
     * @return Player season stat DTO for the specified id or null if no player season stat was found.
     */
    public PlayerSeasonStatDTO findPlayerSeasonStatById(final Long playerSeasonStatId) {
        try {
            final PlayerSeasonStatApi playerSeasonStatApi = new PlayerSeasonStatApi(getApiClient());
            return playerSeasonStatApi.findPlayerSeasonStatById(playerSeasonStatId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds a player season stat for a specific player and a specific season.
     *
     * @param playerId Id of the player for which a player season stat will be looked up.
     * @param seasonId Id of the season for which a player season stat will be looked up.
     * @return Player season stat DTO for the specifiedplayer and the season.
     */
    public PlayerSeasonStatDTO findPlayerSeasonStatByPlayerIdAndSeasonId(final Long playerId, final Long seasonId) {
        try {
            final PlayerSeasonStatApi playerSeasonStatApi = new PlayerSeasonStatApi(getApiClient());
            return playerSeasonStatApi.findPlayerSeasonStatByPlayerIdAndSeasonId(playerId, seasonId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds player season stats for a specific team and season.
     *
     * @param teamId The id of the team for which player season stats will be looked up.
     * @param seasonId The id of the season for which player season stats will be looked up.
     * @return Player season stat DTOs for the specified team and season.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        try {
            final PlayerSeasonStatApi playerSeasonStatApi = new PlayerSeasonStatApi(getApiClient());
            return playerSeasonStatApi.findPlayerSeasonStatByTeamIdAndSeasonId(teamId, seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds player season stats for a specific D11 team and season.
     *
     * @param d11TeamId The id of the team for which player season stats will be looked up.
     * @param seasonId The id of the season for which player season stats will be looked up.
     * @return Player season stat DTOs for the specified team and season.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        try {
            final PlayerSeasonStatApi playerSeasonStatApi = new PlayerSeasonStatApi(getApiClient());
            return playerSeasonStatApi.findPlayerSeasonStatByD11TeamIdAndSeasonId(d11TeamId, seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
