package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferApi;
import org.d11.boot.api.v2.model.TransferDTO;
import org.d11.boot.api.v2.model.TransfersResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Transfer API REST controller implementation.
 */
@RestController
public class TransferControllerV2 extends RepositoryServiceController<TransferService> implements TransferApi {

    /**
     * Create a new controller.
     *
     * @param transferService The service the controller will use.
     */
    @Autowired
    public TransferControllerV2(final TransferService transferService) {
        super(transferService);
    }

    @Override
    public ResponseEntity<TransfersResponseBodyDTO> getTransfersByTransferDayId(final Long transferDayId) {
        final List<Transfer> transfers = getRepositoryService().getByTransferDayId(transferDayId);
        return ResponseEntity.ok(new TransfersResponseBodyDTO()
                .transfers(map(transfers, TransferDTO.class)));
    }

    @Override
    public ResponseEntity<TransfersResponseBodyDTO> getTransfersByPlayerId(final Long playerId) {
        final List<Transfer> transfers = getRepositoryService().getByPlayerId(playerId);
        return ResponseEntity.ok(new TransfersResponseBodyDTO()
                .transfers(map(transfers, TransferDTO.class)));
    }

}
