package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferDayApi;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.StatusDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferDayInputDTO;
import org.d11.boot.api.v2.model.TransferDayResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferDayStatusInputDTO;
import org.d11.boot.api.v2.model.TransferDaysResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.api.v2.model.UpdateTransferDayRequestBodyDTO;
import org.d11.boot.api.v2.model.UpdateTransferDayStatusRequestBodyDTO;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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

        assertFalse(transferDays.isEmpty());

        for (final TransferDay transferDay : transferDays) {
            final TransferDayResponseBodyDTO result = transferDayApi.getTransferDayById(transferDay.getId());
            assertNotNull(result);

            assertEquals(getMapper().map(transferDay, TransferDayDTO.class), result.getTransferDay());
            assertEquals(getMapper().map(transferDay.getTransferWindow(), TransferWindowDTO.class),
                         result.getTransferWindow());
            assertEquals(getMapper().map(transferDay.getTransferWindow().getMatchWeek(), MatchWeekDTO.class),
                         result.getMatchWeek());
        }

        assertThrows(FeignException.NotFound.class, () -> transferDayApi.getTransferDayById(0L));
    }

    /**
     * Tests TransferDayController::getCurrentTransferDay.
     */
    @Test
    void testGetCurrentTransferDay() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final Optional<TransferDay> optional = this.transferDayRepository.findFirstByOrderByDatetimeDesc();

        assertFalse(optional.isEmpty());

        optional.ifPresent(transferDay -> {
            final TransferDayResponseBodyDTO result = transferDayApi.getCurrentTransferDay();
            assertNotNull(result);
            assertEquals(getMapper().map(transferDay, TransferDayDTO.class), result.getTransferDay());
            assertEquals(getMapper().map(transferDay.getTransferWindow(), TransferWindowDTO.class),
                         result.getTransferWindow());
            assertEquals(getMapper().map(transferDay.getTransferWindow().getMatchWeek(), MatchWeekDTO.class),
                         result.getMatchWeek());
        });
    }

    /**
     * Tests TransferDayController::getTransferDaysByTransferWindowId.
     */
    @Test
    void testGetTransferDaysByTransferWindowId() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferDayApi.getTransferDaysByTransferWindowId((Long) null));
        assertThrows(FeignException.BadRequest.class,
                     () -> transferDayApi.getTransferDaysByTransferWindowId(-1L));

        final List<TransferDay> transferDays = this.transferDayRepository.findAll();
        transferDays.sort(Comparator.comparing(TransferDay::getDatetime).reversed());

        final Set<TransferWindow> transferWindows = transferDays.stream()
                .map(TransferDay::getTransferWindow)
                .collect(Collectors.toSet());

        for (final TransferWindow transferWindow : transferWindows) {
            final TransferDaysResponseBodyDTO transferDaysResponseBodyDTO =
                    transferDayApi.getTransferDaysByTransferWindowId(transferWindow.getId());
            assertNotNull(transferDaysResponseBodyDTO);

            final List<TransferDay> expected = transferDays.stream()
                    .filter(transferDay -> transferDay.getTransferWindow().equals(transferWindow))
                    .toList();

            assertTrue(expected.size() > 1);

            final List<TransferDayDTO> result = transferDaysResponseBodyDTO.getTransferDays();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(map(expected, TransferDayDTO.class), result);
        }
    }

    // updateTransferDay -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferDayController::updateTransferDay for null transfer day number.
     */
    @Test
    void testUpdateTransferDayTransferDayNumberNull() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .status(StatusDTO.FINISHED)
                                     .datetime(LocalDateTime.now()));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferDayApi.updateTransferDay(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferDay.transferDayNumber"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferDayController::updateTransferDay for null status.
     */
    @Test
    void testUpdateTransferDayStatusNull() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .transferDayNumber(1)
                                     .datetime(LocalDateTime.now()));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferDayApi.updateTransferDay(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferDay.status"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferDayController::updateTransferDay for invalid status.
     */
    @Test
    void testUpdateTransferDayStatusInvalid() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .transferDayNumber(1)
                                     .status(StatusDTO.FULL_TIME)
                                     .datetime(LocalDateTime.now()));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferDayApi.updateTransferDay(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferDay.status"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()));
    }

    /**
     * Tests TransferDayController::updateTransferDay for null datetime.
     */
    @Test
    void testUpdateTransferDayDatetimeNull() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .transferDayNumber(1)
                                     .status(StatusDTO.FINISHED));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferDayApi.updateTransferDay(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferDay.datetime"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferDayController::updateTransferDay for unauthorized.
     */
    @Test
    void testUpdateTransferDayUnauthorized() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .transferDayNumber(1)
                                     .status(StatusDTO.FINISHED)
                                     .datetime(LocalDateTime.now()));

        assertThrows(FeignException.Unauthorized.class, () -> transferDayApi.updateTransferDay(1L, requestBodyDTO));
    }

    /**
     * Tests TransferDayController::updateTransferDay for forbidden.
     */
    @Test
    void testUpdateTransferDayForbidden() {
        final TransferDayApi transferDayApi = getUserApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .transferDayNumber(1)
                                     .status(StatusDTO.FINISHED)
                                     .datetime(LocalDateTime.now()));

        assertThrows(FeignException.Forbidden.class, () -> transferDayApi.updateTransferDay(1L, requestBodyDTO));
    }

    /**
     * Tests TransferDayController::updateTransferDay for not found.
     */
    @Test
    void testUpdateTransferDayNotFound() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(new TransferDayInputDTO()
                                     .transferDayNumber(1)
                                     .status(StatusDTO.FINISHED)
                                     .datetime(LocalDateTime.now()));

        assertThrows(FeignException.NotFound.class, () -> transferDayApi.updateTransferDay(-1L, requestBodyDTO));
    }

    /**
     * Tests TransferDayController::updateTransferDay.
     */
    @Test
    void testUpdateTransferDay() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final TransferDayInputDTO transferDayInputDTO = new TransferDayInputDTO()
                .transferDayNumber(99)
                .status(StatusDTO.FINISHED)
                .datetime(LocalDateTime.now());

        final UpdateTransferDayRequestBodyDTO requestBodyDTO = new UpdateTransferDayRequestBodyDTO()
                .transferDay(transferDayInputDTO);

        final TransferDayResponseBodyDTO response = transferDayApi.updateTransferDay(1L, requestBodyDTO);

        assertEquals(transferDayInputDTO.getTransferDayNumber(), response.getTransferDay().getTransferDayNumber());
        assertEquals(transferDayInputDTO.getStatus().name(), response.getTransferDay().getStatus().name());
        assertEquals(transferDayInputDTO.getDatetime(), response.getTransferDay().getDatetime());
    }

    /**
     * Tests TransferDayController::updateTransferDayStatus for invalid status.
     */
    @Test
    void testUpdateTransferDayStatusStatusInvalid() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final UpdateTransferDayStatusRequestBodyDTO requestBodyDTO = new UpdateTransferDayStatusRequestBodyDTO()
                .transferDay(new TransferDayStatusInputDTO()
                                     .status(StatusDTO.FULL_TIME)
                                     .process(false));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferDayApi.updateTransferDayStatus(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferDay.status"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()));
    }

    /**
     * Tests TransferDayController::updateTransferDayStatus for unauthorized.
     */
    @Test
    void testUpdateTransferDayStatusUnauthorized() {
        final TransferDayApi transferDayApi = getApi(TransferDayApi.class);

        final UpdateTransferDayStatusRequestBodyDTO requestBodyDTO = new UpdateTransferDayStatusRequestBodyDTO()
                .transferDay(new TransferDayStatusInputDTO()
                                     .status(StatusDTO.FINISHED));

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferDayApi.updateTransferDayStatus(1L, requestBodyDTO));
    }

    /**
     * Tests TransferDayController::updateTransferDayStatus for forbidden.
     */
    @Test
    void testUpdateTransferDayStatusForbidden() {
        final TransferDayApi transferDayApi = getUserApi(TransferDayApi.class);

        final UpdateTransferDayStatusRequestBodyDTO requestBodyDTO = new UpdateTransferDayStatusRequestBodyDTO()
                .transferDay(new TransferDayStatusInputDTO()
                                     .status(StatusDTO.FINISHED));

        assertThrows(FeignException.Forbidden.class, () -> transferDayApi.updateTransferDayStatus(1L, requestBodyDTO));
    }

    /**
     * Tests TransferDayController::updateTransferDayStatus for not found.
     */
    @Test
    void testUpdateTransferDayStatusNotFound() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final UpdateTransferDayStatusRequestBodyDTO requestBodyDTO = new UpdateTransferDayStatusRequestBodyDTO()
                .transferDay(new TransferDayStatusInputDTO()
                                     .status(StatusDTO.FINISHED));

        assertThrows(FeignException.NotFound.class, () -> transferDayApi.updateTransferDayStatus(-1L, requestBodyDTO));
    }

    /**
     * Tests TransferDayController::updateTransferDayStatus for invalid status transition.
     */
    @Test
    void testUpdateTransferDayStatusInvalidStatusTransition() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final UpdateTransferDayStatusRequestBodyDTO requestBodyDTO = new UpdateTransferDayStatusRequestBodyDTO()
                .transferDay(new TransferDayStatusInputDTO()
                                     .status(StatusDTO.PENDING)
                                     .process(true));

        final FeignException.Conflict e =
                assertThrows(FeignException.Conflict.class,
                             () -> transferDayApi.updateTransferDayStatus(1L, requestBodyDTO));
        assertTrue(e.getMessage().contains(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS.getMessage()));
    }

    /**
     * Tests TransferDayController::updateTransferDayStatus.
     */
    @Test
    void testUpdateTransferDayStatus() {
        final TransferDayApi transferDayApi = getAdministratorApi(TransferDayApi.class);

        final TransferDayStatusInputDTO transferDayInputDTO = new TransferDayStatusInputDTO().status(StatusDTO.PENDING);

        final UpdateTransferDayStatusRequestBodyDTO requestBodyDTO = new UpdateTransferDayStatusRequestBodyDTO()
                .transferDay(transferDayInputDTO);

        final TransferDayResponseBodyDTO response = transferDayApi.updateTransferDayStatus(1L, requestBodyDTO);

        assertEquals(transferDayInputDTO.getStatus().name(), response.getTransferDay().getStatus().name());

        transferDayInputDTO.setStatus(StatusDTO.FINISHED);
        transferDayApi.updateTransferDayStatus(1L, requestBodyDTO);
    }

}

