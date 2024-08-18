package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferDayApi;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferDayResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferDaysResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /**
     * Tests TransferDayController::getCurrentTransferDay.
     */
    @Test
    void testGetCurrentTransferDay() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final Optional<TransferDay> optional = this.transferDayRepository.findFirstByOrderByDatetimeDesc();

        assertFalse(optional.isEmpty(), "TransferDayController::getCurrentTransferDay transferDay present");

        optional.ifPresent(transferDay -> {
            final TransferDayResponseBodyDTO result = transferDayApi.getCurrentTransferDay();
            assertNotNull(result, "TransferDayController::getCurrentTransferDay not null");
            assertEquals(getMapper().map(transferDay, TransferDayDTO.class), result.getTransferDay(),
                         "TransferDayController::getCurrentTransferDay equals");
            assertEquals(getMapper().map(transferDay.getTransferWindow(), TransferWindowDTO.class),
                         result.getTransferWindow(),
                         "TransferDayController::getCurrentTransferDay transfer window equals");
            assertEquals(getMapper().map(transferDay.getTransferWindow().getMatchWeek(), MatchWeekDTO.class),
                         result.getMatchWeek(),
                         "TransferDayController::getCurrentTransferDay match week equals");
        });
    }

    /**
     * Tests TransferDayController::getTransferDaysByTransferWindowId.
     */
    @Test
    void testGetTransferDaysByTransferWindowId() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferDayApi.getTransferDaysByTransferWindowId((Long) null),
                     "TransferDayController::getTransferDaysByTransferWindowId transferWindowId null throws");
        assertThrows(FeignException.BadRequest.class,
                     () -> transferDayApi.getTransferDaysByTransferWindowId(-1L),
                     "TransferDayController::getTransferDaysByTransferWindowId transferWindowId negative throws");

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();
        transferDays.sort(Comparator.comparing(TransferDay::getDatetime).reversed());

        final Set<TransferWindow> transferWindows = transferDays.stream()
                .map(TransferDay::getTransferWindow)
                .collect(Collectors.toSet());

        for (final TransferWindow transferWindow : transferWindows) {
            final TransferDaysResponseBodyDTO transferDaysResponseBodyDTO =
                    transferDayApi.getTransferDaysByTransferWindowId(transferWindow.getId());
            assertNotNull(transferDaysResponseBodyDTO,
                          "TransferDayController::getTransferDaysByTransferWindowId response not null");

            final List<TransferDay> expected = transferDays.stream()
                    .filter(transferDay -> transferDay.getTransferWindow().equals(transferWindow))
                    .toList();

            assertTrue(expected.size() > 1,
                       "TransferDayController::getTransferDaysByTransferWindowId expected size > 1");

            final List<TransferDayDTO> result = transferDaysResponseBodyDTO.getTransferDays();

            assertNotNull(result, "TransferDayController::getTransferDaysByTransferWindowId not null ");
            assertFalse(result.isEmpty(), "TransferDayController::getTransferDaysByTransferWindowId empty");
            assertEquals(map(expected, TransferDayDTO.class), result,
                         "TransferDayController::getTransferDaysByTransferWindowId equals");
        }
    }

}

