package org.d11.boot.api.service;

import org.d11.boot.api.model.TransferBidDTO;
import org.d11.boot.client.api.TransferBidApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides transfer bid API services.
 */
@Service
public class TransferBidApiService extends D11ApiService {

    /**
     * Finds a transfer bid with a specific id.
     *
     * @param transferBidId The id of the transfer bid that will be looked up.
     * @return Transfer bid DTO for the specified id or null if no transfer bid was found.
     */
    public TransferBidDTO findTransferBidById(final Long transferBidId) {
        try {
            final TransferBidApi transferBidApi = new TransferBidApi(getApiClient());
            return transferBidApi.findTransferBidById(transferBidId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Finds transfer bids for a specific transfer day.
     *
     * @param transferDayId The id of the transfer day for which transfer bids will be looked up.
     * @return Transfer bid DTOs for the specified transfer day.
     */
    public List<TransferBidDTO> findTransferBidByTransferDayId(final Long transferDayId) {
        try {
            final TransferBidApi transferBidApi = new TransferBidApi(getApiClient());
            return transferBidApi.findTransferBidByTransferDayId(transferDayId).collectList().block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

}
