package org.d11.boot.api.service;

import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.client.api.TransferListingApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides transfer listing API services.
 */
@Service
public class TransferListingApiService extends D11ApiService {

    /**
     * Finds a transfer listing with a specific id.
     *
     * @param transferListingId The id of the transfer listing that will be looked up.
     * @return Transfer listing DTO for the specified id or null if no transfer listing was found.
     */
    public TransferListingDTO findTransferListingById(final Long transferListingId) {
        try {
            final TransferListingApi transferListingApi = new TransferListingApi(getApiClient());
            return transferListingApi.findTransferListingById(transferListingId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds transfer listings for a specific transfer day.
     *
     * @param transferDayId The id of the transfer day for which transfer listings will be looked up.
     * @param page Page number (25 per page) for the search result page that will be returned.
     * @return Transfer listing DTOs for the specified transfer day.
     */
    public List<TransferListingDTO> findTransferListingByTransferDayId(final Long transferDayId, final int page) {
        try {
            final TransferListingApi transferListingApi = new TransferListingApi(getApiClient());
            return transferListingApi.findTransferListingByTransferDayId(transferDayId, page).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
