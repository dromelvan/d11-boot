package org.d11.boot.application.controller;

import org.d11.boot.api.TransferWindowsApi;
import org.d11.boot.api.model.TransferWindowDTO;
import org.d11.boot.application.service.api.TransferWindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the TransferWindowsApi and provides transfer window endpoints.
 */
@RestController
public class TransferWindowController extends AbstractRepositoryServiceController<TransferWindowDTO, TransferWindowService> implements TransferWindowsApi {

    /**
     * Creates a new controller.
     *
     * @param transferWindowService The repository service this controller will use.
     */
    @Autowired
    public TransferWindowController(final TransferWindowService transferWindowService) {
        super(transferWindowService);
    }

    @Override
    public ResponseEntity<TransferWindowDTO> findTransferWindowById(final Long transferWindowId) {
        return super.findById(transferWindowId);
    }

    @Override
    public ResponseEntity<TransferWindowDTO> findCurrentTransferWindow() {
        final TransferWindowDTO transferWindowDTO = getRepositoryService().findCurrentTransferWindow();
        return ResponseEntity.ok(transferWindowDTO);
    }

    @Override
    public ResponseEntity<List<TransferWindowDTO>> findTransferWindowBySeasonId(final Long seasonId) {
        final List<TransferWindowDTO> transferWindows = getRepositoryService().findBySeasonId(seasonId);
        return ResponseEntity.ok(transferWindows);
    }

}
