package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.TransferDayApi;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferDayResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferDaysResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.service.TransferDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Transfer day API REST controller implementation.
 */
@RestController
public class TransferDayControllerV2 extends RepositoryServiceController<TransferDayService> implements TransferDayApi {

    /**
     * Create a new controller.
     *
     * @param transferDayService The service the controller will use.
     */
    @Autowired
    public TransferDayControllerV2(final TransferDayService transferDayService) {
        super(transferDayService);
    }

    @Override
    public ResponseEntity<TransferDayResponseBodyDTO> getTransferDayById(final Long transferDayId) {
        final TransferDay transferDay = getRepositoryService().getById(transferDayId);

        return ResponseEntity.ok(new TransferDayResponseBodyDTO()
                                         .transferDay(getMapper().map(transferDay, TransferDayDTO.class))
                                         .transferWindow(getMapper().map(transferDay.getTransferWindow(),
                                                                         TransferWindowDTO.class))
                                         .matchWeek(getMapper().map(transferDay.getTransferWindow().getMatchWeek(),
                                                                    MatchWeekDTO.class)));
    }

    @Override
    public ResponseEntity<TransferDayResponseBodyDTO> getCurrentTransferDay() {
        final TransferDay transferDay = getRepositoryService().getCurrentTransferDay();

        return ResponseEntity.ok(new TransferDayResponseBodyDTO()
                .transferDay(getMapper().map(transferDay, TransferDayDTO.class))
                .transferWindow(getMapper().map(transferDay.getTransferWindow(),
                                                TransferWindowDTO.class))
                .matchWeek(getMapper().map(transferDay.getTransferWindow().getMatchWeek(),
                                           MatchWeekDTO.class)));
    }

    @Override
    public ResponseEntity<TransferDaysResponseBodyDTO> getTransferDaysByTransferWindowId(final Long transferWindowId) {
        final List<TransferDay> transferDays = getRepositoryService().getByTransferWindowId(transferWindowId);

        return ResponseEntity.ok(new TransferDaysResponseBodyDTO()
                .transferDays(getMapper().map(transferDays, TransferDayDTO.class)));
    }

}