package org.d11.boot.application.controller;

import org.d11.boot.api.TransferBidsApi;
import org.d11.boot.api.model.InsertTransferBidDTO;
import org.d11.boot.api.model.InsertTransferBidResultDTO;
import org.d11.boot.api.model.TransferBidDTO;
import org.d11.boot.application.service.api.TransferBidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller that implements the TransferBidsApi and provides transfer bid endpoints.
 */
@RestController
public class TransferBidController extends AbstractRepositoryServiceController<TransferBidDTO, TransferBidService> implements TransferBidsApi {

    /**
     * Creates a new controller.
     *
     * @param transferBidService The repository service this controller will use.
     */
    public TransferBidController(final TransferBidService transferBidService) {
        super(transferBidService);
    }

    @Override
    public ResponseEntity<TransferBidDTO> findTransferBidById(final Long transferBidId) {
        return super.findById(transferBidId);
    }

    @Override
    public ResponseEntity<List<TransferBidDTO>> findTransferBidByTransferDayId(final Long transferDayId) {
        final List<TransferBidDTO> transferBids = getRepositoryService().findByTransferDayId(transferDayId);
        return ResponseEntity.ok(transferBids);
    }

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<InsertTransferBidResultDTO> insertTransferBid(@Valid final InsertTransferBidDTO insertTransferBidDTO) {
        final InsertTransferBidResultDTO insertTransferBidResultDTO = getRepositoryService().insertTransferBid(insertTransferBidDTO);
        return ResponseEntity.ok(insertTransferBidResultDTO);
    }

}
