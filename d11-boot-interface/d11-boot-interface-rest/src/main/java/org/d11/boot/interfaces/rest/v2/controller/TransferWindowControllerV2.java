package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferWindowApi;
import org.d11.boot.api.v2.model.InsertTransferWindowRequestBodyDTO;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.api.v2.model.TransferWindowResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.service.TransferWindowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Transfer window API REST controller implementation.
 */
@RestController
public class TransferWindowControllerV2
        extends RepositoryServiceController<TransferWindowService> implements TransferWindowApi {

    /**
     * Create a new controller.
     *
     * @param transferWindowService The service the controller will use.
     */
    public TransferWindowControllerV2(final TransferWindowService transferWindowService) {
        super(transferWindowService);
    }

    @Override
    public ResponseEntity<TransferWindowResponseBodyDTO> getTransferWindowById(final Long transferWindowId) {
        final TransferWindow transferWindow = getRepositoryService().getById(transferWindowId);

        final TransferWindowResponseBodyDTO responseBody = new TransferWindowResponseBodyDTO()
                .transferWindow(map(transferWindow, TransferWindowDTO.class))
                .matchWeek(map(transferWindow.getMatchWeek(), MatchWeekDTO.class))
                .transferDays(map(transferWindow.getTransferDays(), TransferDayDTO.class));

        return ResponseEntity.ok(responseBody);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<TransferWindowResponseBodyDTO> insertTransferWindow(
            final InsertTransferWindowRequestBodyDTO requestBody) {

        final TransferWindow transferWindow =
                getRepositoryService().insertTransferWindow(requestBody.getDatetime(),
                                                            requestBody.getTransferDayDelay());

        final TransferWindowResponseBodyDTO responseBody = new TransferWindowResponseBodyDTO()
                .transferWindow(map(transferWindow, TransferWindowDTO.class))
                .matchWeek(map(transferWindow.getMatchWeek(), MatchWeekDTO.class))
                .transferDays(map(transferWindow.getTransferDays(), TransferDayDTO.class));

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<Void> deleteTransferWindow(final Long transferWindowId) {
        getRepositoryService().deleteTransferWindow(transferWindowId);
        return ResponseEntity.noContent().build();
    }

}
