package org.d11.boot.application.controller;

import org.d11.boot.api.TransferListingsApi;
import org.d11.boot.api.model.InsertTransferListingDTO;
import org.d11.boot.api.model.InsertTransferListingResultDTO;
import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.application.service.api.TransferListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller that implements the TransferListingsApi and provides transfer listing endpoints.
 */
@RestController
public class TransferListingController extends AbstractRepositoryServiceController<TransferListingDTO, TransferListingService> implements TransferListingsApi {

    /**
     * Creates a new controller.
     *
     * @param transferListingService The repository service this controller will use.
     */
    public TransferListingController(final TransferListingService transferListingService) {
        super(transferListingService);
    }

    @Override
    public ResponseEntity<TransferListingDTO> findTransferListingById(final Long transferListingId) {
        return super.findById(transferListingId);
    }

    @Override
    public ResponseEntity<List<TransferListingDTO>> findTransferListingByTransferDayId(final Long transferDayId, final Integer page) {
        final List<TransferListingDTO> transferListings = getRepositoryService().findByTransferDayId(transferDayId, page);
        return ResponseEntity.ok(transferListings);
    }

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<InsertTransferListingResultDTO> insertTransferListing(@Valid final InsertTransferListingDTO insertTransferListingDTO) {
        final InsertTransferListingResultDTO insertTransferListingResultDTO = getRepositoryService().insertTransferListing(insertTransferListingDTO);
        return ResponseEntity.ok(insertTransferListingResultDTO);
    }

}
