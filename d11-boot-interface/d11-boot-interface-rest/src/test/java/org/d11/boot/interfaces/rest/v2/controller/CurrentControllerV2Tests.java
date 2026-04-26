package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.client.CurrentApi;
import org.d11.boot.api.v2.model.CurrentResponseBodyDTO;
import org.d11.boot.api.v2.model.MatchWeekBaseDTO;
import org.d11.boot.api.v2.model.SeasonBaseDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferWindowBaseDTO;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Current controller tests.
 */
class CurrentControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Match week repository.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Transfer window repository.
     */
    @Autowired
    private TransferWindowRepository transferWindowRepository;

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Tests CurrentController::getCurrent.
     */
    @Test
    void testGetCurrent() {
        final CurrentApi currentApi = getApi(CurrentApi.class);

        final CurrentResponseBodyDTO result = currentApi.getCurrent();
        assertNotNull(result);

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow();
        assertEquals(getMapper().map(season, SeasonBaseDTO.class), result.getSeason());

        final MatchWeek matchWeek =
                this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(Status.PENDING)
                .or(() -> this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(LocalDate.now()))
                .orElseThrow();
        assertEquals(getMapper().map(matchWeek, MatchWeekBaseDTO.class), result.getMatchWeek());

        final TransferWindow transferWindow =
                this.transferWindowRepository.findFirstByOrderByDatetimeDesc().orElseThrow();
        assertEquals(getMapper().map(transferWindow, TransferWindowBaseDTO.class), result.getTransferWindow());

        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc().orElseThrow();
        assertEquals(getMapper().map(transferDay, TransferDayDTO.class), result.getTransferDay());
    }

}
