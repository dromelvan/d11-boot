package org.d11.boot.api.service;

import org.d11.boot.api.model.PositionDTO;
import org.d11.boot.client.api.PositionApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides position API services.
 */
@Service
public class PositionApiService extends D11ApiService {

    /**
     * Finds a position with a specific id.
     *
     * @param positionId The id of the position that will be looked up.
     * @return Position DTO for the specified id or null if no position was found.
     */
    public PositionDTO findPositionById(final Long positionId) {
        try {
            final PositionApi positionApi = new PositionApi(getApiClient());
            return positionApi.findPositionById(positionId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets a list of all positions.
     *
     * @return List of all positions sorted by the sort order property.
     */
    public List<PositionDTO> findAllPositions() {
        final PositionApi positionApi = new PositionApi(getApiClient());
        return positionApi.findAllPositions().collectList().block();
    }

}
