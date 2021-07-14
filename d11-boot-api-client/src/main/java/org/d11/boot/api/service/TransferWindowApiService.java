package org.d11.boot.api.service;

import org.d11.boot.api.model.TransferWindowDTO;
import org.d11.boot.client.api.TransferWindowApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides transfer window API services.
 */
@Service
public class TransferWindowApiService extends D11ApiService {

    /**
     * Finds a transfer window with a specific id.
     *
     * @param transferWindowId The id of the transfer window that will be looked up.
     * @return Transfer window DTO for the specified id or null if no transfer window was found.
     */
    public TransferWindowDTO findTransferWindowById(final Long transferWindowId) {
        try {
            final TransferWindowApi transferWindowApi = new TransferWindowApi(getApiClient());
            return transferWindowApi.findTransferWindowById(transferWindowId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the current transfer window.
     *
     * @return The current transfer window or null if no current transfer window was found.
     */
    public TransferWindowDTO findCurrentTransferWindow() {
        try {
            final TransferWindowApi transferWindowApi = new TransferWindowApi(getApiClient());
            return transferWindowApi.findCurrentTransferWindow().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds transfer windows for a specific season.
     *
     * @param seasonId The id of the season for which transfer windows will be looked up.
     * @return Transfer window DTO for the specified season.
     */
    public List<TransferWindowDTO> findTransferWindowBySeasonId(final Long seasonId) {
        try {
            final TransferWindowApi transferWindowApi = new TransferWindowApi(getApiClient());
            return transferWindowApi.findTransferWindowBySeasonId(seasonId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
