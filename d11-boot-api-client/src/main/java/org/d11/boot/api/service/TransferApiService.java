package org.d11.boot.api.service;

import org.d11.boot.api.model.InsertTransferDTO;
import org.d11.boot.api.model.InsertTransferResultDTO;
import org.d11.boot.api.model.TransferDTO;
import org.d11.boot.client.api.TransferApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides transfer API services.
 */
@Service
public class TransferApiService extends D11ApiService {

    /**
     * Finds a transfer with a specific id.
     *
     * @param transferId The id of the transfer that will be looked up.
     * @return Transfer DTO for the specified id or null if no transfer was found.
     */
    public TransferDTO findTransferById(final Long transferId) {
        try {
            final TransferApi transferApi = new TransferApi(getApiClient());
            return transferApi.findTransferById(transferId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds transfers for a specific transfer day.
     *
     * @param transferId The id of the transfer day for which transfers will be looked up.
     * @return Transfer DTOs for the specified transfer day.
     */
    public List<TransferDTO> findTransferByTransferDayId(final Long transferId) {
        try {
            final TransferApi transferApi = new TransferApi(getApiClient());
            return transferApi.findTransferByTransferDayId(transferId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds transfer for a specific player.
     *
     * @param playerId The id of the player for which transfers will be looked up.
     * @return Transfer DTOs for the specified player.
     */
    public List<TransferDTO> findTransferByPlayerId(final Long playerId) {
        try {
            final TransferApi transferApi = new TransferApi(getApiClient());
            return transferApi.findTransferByPlayerId(playerId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Inserts a transfer for the current transfer day.
     *
     * @param insertTransferDTO Details for the transfer that will be inserted.
     * @return Result of the insert with ids of created entities if successful and a list of errors if not.
     */
    public InsertTransferResultDTO insertTransfer(InsertTransferDTO insertTransferDTO) {
        try {
            final TransferApi transferApi = new TransferApi(getApiClient());
            return transferApi.insertTransfer(insertTransferDTO).block();
        } catch(WebClientResponseException e) {
            throw translate(e);
        }
    }

}
