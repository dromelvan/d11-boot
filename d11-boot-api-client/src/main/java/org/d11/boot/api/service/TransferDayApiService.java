package org.d11.boot.api.service;

import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayResultDTO;
import org.d11.boot.client.api.TransferDayApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides transfer day API services.
 */
@Service
public class TransferDayApiService extends D11ApiService {

    /**
     * Finds a transfer day with a specific id.
     *
     * @param transferDayId The id of the transfer day that will be looked up.
     * @return Transfer day DTO for the specified id or null if no transfer day was found.
     */
    public TransferDayDTO findTransferDayById(final Long transferDayId) {
        try {
            final TransferDayApi transferDayApi = new TransferDayApi(getApiClient());
            return transferDayApi.findTransferDayById(transferDayId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds the current transfer day.
     *
     * @return The current transfer day or null if no current transfer day was found.
     */
    public TransferDayDTO findCurrentTransferDay() {
        try {
            final TransferDayApi transferDayApi = new TransferDayApi(getApiClient());
            return transferDayApi.findCurrentTransferDay().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds transfer days for a specific transfer window.
     *
     * @param transferWindowId The id of the transfer window for which transfer days will be looked up.
     * @return Transfer day DTOs for the specified transfer days.
     */
    public List<TransferDayDTO> findTransferDayByTransferWindowId(final Long transferWindowId) {
        try {
            final TransferDayApi transferDayApi = new TransferDayApi(getApiClient());
            return transferDayApi.findTransferDayByTransferWindowId(transferWindowId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Activates a transfer day and inserts transfer listings for all players who don't belong to a D11 team.
     *
     * @param updateTransferDayDTO Details for the transfer that will be inserted.
     * @return Result of the activation with id of the updated entity if successful and list of errors if not.
     */
    public UpdateTransferDayResultDTO updateTransferDay(final UpdateTransferDayDTO updateTransferDayDTO) {
        try {
            final TransferDayApi transferDayApi = new TransferDayApi(getApiClient());
            return transferDayApi.updateTransferDay(updateTransferDayDTO).block();
        } catch(WebClientResponseException e) {
            throw translate(e);
        }
    }

}
