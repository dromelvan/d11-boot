package org.d11.boot.application.controller;

import org.d11.boot.api.TransfersApi;
import org.d11.boot.api.model.InsertTransferDTO;
import org.d11.boot.api.model.InsertTransferResultDTO;
import org.d11.boot.api.model.TransferDTO;
import org.d11.boot.application.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller that implements the TransfersApi and provides transfer endpoints.
 */
@RestController
public class TransferController extends AbstractRepositoryServiceController<TransferDTO, TransferService> implements TransfersApi {

    /**
     * Creates a new controller.
     *
     * @param transferService The repository service this controller will use.
     */
    public TransferController(final TransferService transferService) {
        super(transferService);
    }

    @Override
    public ResponseEntity<TransferDTO> findTransferById(final Long transferId) {
        return super.findById(transferId);
    }

    @Override
    public ResponseEntity<List<TransferDTO>> findTransferByTransferDayId(final Long transferDayId) {
        final List<TransferDTO> transfers = getRepositoryService().findByTransferDayId(transferDayId);
        return ResponseEntity.ok(transfers);
    }

    @Override
    public ResponseEntity<List<TransferDTO>> findTransferByPlayerId(final Long playerId) {
        final List<TransferDTO> transfers = getRepositoryService().findByPlayerId(playerId);
        return ResponseEntity.ok(transfers);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<InsertTransferResultDTO> insertTransfer(@Valid final InsertTransferDTO insertTransferDTO) {
        return ResponseEntity.ok(getRepositoryService().insertTransfer(insertTransferDTO));
    }

}
