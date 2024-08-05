package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferDayApi;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferDayResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Transfer day controller tests.
 */
class TransferDayControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Tests TransferDayController::getTransferDayById.
     */
    @Test
    void testGetTransferDayById() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);
        final List<TransferDay> transferDays = this.transferDayRepository.findAll();

        assertFalse(transferDays.isEmpty(), "TransferDayController::getTransferDayById empty");

        for (final TransferDay transferDay : transferDays) {
            final TransferDayResponseBodyDTO result = transferDayApi.getTransferDayById(transferDay.getId());
            assertNotNull(result, "TransferDayController::getTransferDayById not null");

            assertEquals(getMapper().map(transferDay, TransferDayDTO.class),
                         result.getTransferDay(),
                         "TransferDayController::getTransferDayById equals");
            assertEquals(getMapper().map(transferDay.getTransferWindow(), TransferWindowDTO.class),
                         result.getTransferWindow(),
                         "TransferDayController::getTransferDayById transferWindow equals");
            assertEquals(getMapper().map(transferDay.getTransferWindow().getMatchWeek(),
                                         MatchWeekDTO.class),
                         result.getMatchWeek(),
                         "TransferDayController::getTransferDayById matchWeek equals");
        }

        assertThrows(FeignException.NotFound.class, () -> transferDayApi.getTransferDayById(0L),
                     "TransferDayController::getTransferDayById not found");
    }

}

