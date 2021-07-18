package org.d11.boot.api.service;

import org.d11.boot.api.model.AvailablePlayersTeamDTO;
import org.d11.boot.client.api.AvailablePlayerApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides available player API services.
 */
@Service
public class AvailablePlayerApiService extends D11ApiService {

    /**
     * Finds currently available players.
     *
     * @return Currently available players.
     */
    public List<AvailablePlayersTeamDTO> findAvailablePlayers() {
        try {
            final AvailablePlayerApi availablePlayerApi = new AvailablePlayerApi(getApiClient());
            return availablePlayerApi.findAvailablePlayers().collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
