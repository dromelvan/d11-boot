package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferApi;
import org.d11.boot.api.v2.model.CreateTransferRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferDTO;
import org.d11.boot.api.v2.model.TransferResponseBodyDTO;
import org.d11.boot.api.v2.model.TransfersResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdateTransferRequestBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferInput;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.service.TransferService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Transfer API REST controller implementation.
 */
@RestController
public class TransferControllerV2 extends RepositoryServiceController<TransferService> implements TransferApi {

    /**
     * REST controller mapper.
     */
    private final RestControllerMapperV2 mapper = Mappers.getMapper(RestControllerMapperV2.class);

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

    @Override
    @RoleAdmin
    public ResponseEntity<TransferResponseBodyDTO> createTransfer(
            final CreateTransferRequestBodyDTO createTransferRequestBodyDTO) {
        final TransferInput transferInput = this.mapper.mapToTransferInput(createTransferRequestBodyDTO.getTransfer());
        final Transfer transfer = getRepositoryService().createTransfer(transferInput);

        final TransferResponseBodyDTO responseBody = new TransferResponseBodyDTO()
                .transfer(this.mapper.mapToTransferDTO(transfer));

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<TransferResponseBodyDTO> updateTransfer(
            final Long transferId,
            final UpdateTransferRequestBodyDTO updateTransferRequestBodyDTO) {

        final TransferInput transferInput = this.mapper.mapToTransferInput(updateTransferRequestBodyDTO.getTransfer());
        final Transfer transfer = getRepositoryService().updateTransfer(transferId, transferInput);

        final TransferResponseBodyDTO responseBody = new TransferResponseBodyDTO()
                .transfer(this.mapper.mapToTransferDTO(transfer));

        return ResponseEntity.ok(responseBody);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<Void> deleteTransfer(final Long transferId) {
        getRepositoryService().deleteTransfer(transferId);
        return ResponseEntity.noContent().build();
    }

}
