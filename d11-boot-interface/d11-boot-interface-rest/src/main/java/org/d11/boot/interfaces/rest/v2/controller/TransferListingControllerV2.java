package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferListingApi;
import org.d11.boot.api.v2.model.TransferListingDTO;
import org.d11.boot.api.v2.model.TransferListingsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.service.TransferListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Transfer listing API REST controller implementation.
 */
@RestController
public class TransferListingControllerV2 extends RepositoryServiceController<TransferListingService>
        implements TransferListingApi {

    /**
     * Create a new controller.
     *
     * @param transferListingService The service the controller will use.
     */
    @Autowired
    public TransferListingControllerV2(final TransferListingService transferListingService) {
        super(transferListingService);
    }

    @Override
    public ResponseEntity<TransferListingsResponseBodyDTO> getTransferListings(final Long transferDayId,
                                                                               final Integer page) {
        final List<TransferListing> transferListings = getRepositoryService().getByTransferDayId(transferDayId, page);
        return ResponseEntity.ok(new TransferListingsResponseBodyDTO()
                .transferListings(map(transferListings, TransferListingDTO.class)));
    }

}
