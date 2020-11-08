package org.d11.boot.api.service;

import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.client.api.PlayerApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Provides player API services.
 */
@Service
public class PlayerApiService extends D11ApiService {

    /**
     * Finds a player with a specific id.
     *
     * @param playerId The id of the player that will be looked up.
     * @return Player DTO for the specified id or null if no player was found.
     */
    public PlayerDTO findPlayerById(Long playerId) {
        try {
            final PlayerApi playerApi = new PlayerApi(getApiClient());
            return playerApi.findPlayerById(playerId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
