package org.d11.boot.interfaces.rest.v2.controller;

import lombok.RequiredArgsConstructor;
import org.d11.boot.api.v2.CurrentApi;
import org.d11.boot.api.v2.model.CurrentResponseBodyDTO;
import org.d11.boot.api.v2.model.MatchWeekBaseDTO;
import org.d11.boot.api.v2.model.SeasonBaseDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferWindowBaseDTO;
import org.d11.boot.interfaces.rest.D11BootRestController;
import org.d11.boot.spring.model.CurrentResult;
import org.d11.boot.spring.service.CurrentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Current API REST controller implementation.
 */
@RestController
@RequiredArgsConstructor
public class CurrentControllerV2 extends D11BootRestController implements CurrentApi {

    /**
     * Current service.
     */
    private final CurrentService currentService;

    @Override
    public ResponseEntity<CurrentResponseBodyDTO> getCurrent() {
        final CurrentResult result = this.currentService.getCurrent();

        final CurrentResponseBodyDTO responseBody = new CurrentResponseBodyDTO();
        if (result.season() != null) {
            responseBody.season(map(result.season(), SeasonBaseDTO.class));
        }
        if (result.matchWeek() != null) {
            responseBody.matchWeek(map(result.matchWeek(), MatchWeekBaseDTO.class));
        }
        if (result.transferWindow() != null) {
            responseBody.transferWindow(map(result.transferWindow(), TransferWindowBaseDTO.class));
        }
        if (result.transferDay() != null) {
            responseBody.transferDay(map(result.transferDay(), TransferDayDTO.class));
        }
        return ResponseEntity.ok(responseBody);
    }

}
