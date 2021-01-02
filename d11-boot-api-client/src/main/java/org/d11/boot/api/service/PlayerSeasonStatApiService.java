package org.d11.boot.api.service;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.client.api.PlayerSeasonStatApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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
    public PlayerSeasonStatDTO findPlayerSeasonStatById(Long playerSeasonStatId) {
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

}
