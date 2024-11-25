package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferWindowApi;
import org.d11.boot.api.v2.model.CreateTransferWindowRequestBodyDTO;
import org.d11.boot.api.v2.model.MatchWeekBaseDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.api.v2.model.TransferWindowResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowsResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdateTransferWindowRequestBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.model.TransferWindowInput;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.service.TransferWindowService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Transfer window API REST controller implementation.
 */
@RestController
public class TransferWindowControllerV2 extends RepositoryServiceController<TransferWindowService>
        implements TransferWindowApi {

    /**
     * REST controller mapper.
     */
    private final RestControllerMapperV2 mapper = Mappers.getMapper(RestControllerMapperV2.class);

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
                .matchWeek(map(transferWindow.getMatchWeek(), MatchWeekBaseDTO.class))
                .transferDays(map(transferWindow.getTransferDays(), TransferDayDTO.class));

        return ResponseEntity.ok(responseBody);
    }

    @Override
    public ResponseEntity<TransferWindowsResponseBodyDTO> getTransferWindowsBySeasonId(final Long seasonId) {
        final List<TransferWindow> transferWindows = getRepositoryService().getBySeasonId(seasonId);

        return ResponseEntity.ok(new TransferWindowsResponseBodyDTO()
                .transferWindows(getMapper().map(transferWindows, TransferWindowDTO.class)));
    }

    @Override
    public ResponseEntity<TransferWindowResponseBodyDTO> getCurrentTransferWindow() {
        final TransferWindow transferWindow = getRepositoryService().getCurrentTransferWindow();

        return ResponseEntity.ok(new TransferWindowResponseBodyDTO()
                .transferWindow(getMapper().map(transferWindow, TransferWindowDTO.class))
                .matchWeek(getMapper().map(transferWindow.getMatchWeek(), MatchWeekBaseDTO.class))
                .transferDays(map(transferWindow.getTransferDays(), TransferDayDTO.class)));
    }

    @Override
    @RoleAdmin
    public ResponseEntity<TransferWindowResponseBodyDTO> createTransferWindow(
            final CreateTransferWindowRequestBodyDTO requestBody) {

        final TransferWindow transferWindow =
                getRepositoryService().createTransferWindow(requestBody.getDatetime(),
                                                            requestBody.getTransferDayDelay());

        final TransferWindowResponseBodyDTO responseBody = new TransferWindowResponseBodyDTO()
                .transferWindow(map(transferWindow, TransferWindowDTO.class))
                .matchWeek(map(transferWindow.getMatchWeek(), MatchWeekBaseDTO.class))
                .transferDays(map(transferWindow.getTransferDays(), TransferDayDTO.class));

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<TransferWindowResponseBodyDTO> updateTransferWindow(
            final Long transferWindowId,
            final UpdateTransferWindowRequestBodyDTO updateTransferWindowRequestBodyDTO) {
        final TransferWindowInput transferWindowInput =
                this.mapper.mapToTransferWindowInput(updateTransferWindowRequestBodyDTO.getTransferWindow());

        final TransferWindow transferWindow =
                getRepositoryService().updateTransferWindow(transferWindowId, transferWindowInput);

        final TransferWindowResponseBodyDTO responseBody = new TransferWindowResponseBodyDTO()
                .transferWindow(map(transferWindow, TransferWindowDTO.class))
                .matchWeek(map(transferWindow.getMatchWeek(), MatchWeekBaseDTO.class))
                .transferDays(map(transferWindow.getTransferDays(), TransferDayDTO.class));

        return ResponseEntity.ok(responseBody);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<Void> deleteTransferWindow(final Long transferWindowId) {
        getRepositoryService().deleteTransferWindow(transferWindowId);
        return ResponseEntity.noContent().build();
    }

}
