package org.d11.boot.application.controller;

import org.d11.boot.api.TransferDaysApi;
import org.d11.boot.api.model.StatusDTO;
import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayResultDTO;
import org.d11.boot.application.service.api.TransferDayService;
import org.d11.boot.application.util.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller that implements the TransferDaysApi and provides transfer day endpoints.
 */
@RestController
public class TransferDayController extends AbstractRepositoryServiceController<TransferDayDTO, TransferDayService> implements TransferDaysApi {

    /**
     * Creates a new controller.
     *
     * @param transferDayService The repository service this controller will use.
     */
    public TransferDayController(final TransferDayService transferDayService) {
        super(transferDayService);
    }

    @Override
    public ResponseEntity<TransferDayDTO> findTransferDayById(final Long transferDayId) {
        return super.findById(transferDayId);
    }

    @Override
    public ResponseEntity<TransferDayDTO> findCurrentTransferDay() {
        final TransferDayDTO transferDayDTO = getRepositoryService().findCurrentTransferDay();
        return ResponseEntity.ok(transferDayDTO);
    }

    @Override
    public ResponseEntity<List<TransferDayDTO>> findTransferDayByTransferWindowId(final Long transferWindowId) {
        final List<TransferDayDTO> transferDays = getRepositoryService().findByTransferWindowId(transferWindowId);
        return ResponseEntity.ok(transferDays);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UpdateTransferDayResultDTO> updateTransferDay(@Valid final UpdateTransferDayDTO updateTransferDayDTO) {
        // Later on we might want to flip to other statuses.
        if(StatusDTO.ACTIVE.equals(updateTransferDayDTO.getStatus())) {
            return ResponseEntity.ok(getRepositoryService().activateTransferDayByTransferDayId(updateTransferDayDTO.getId()));
        } else if(StatusDTO.FINISHED.equals(updateTransferDayDTO.getStatus())) {
            return ResponseEntity.ok(getRepositoryService().finishTransferDayByTransferDayId(updateTransferDayDTO.getId()));
        }
        throw new BadRequestException();
    }

}
