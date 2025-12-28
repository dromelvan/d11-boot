package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferWindowApi;
import org.d11.boot.api.v2.model.CreateTransferWindowRequestBodyDTO;
import org.d11.boot.api.v2.model.MatchWeekBaseDTO;
import org.d11.boot.api.v2.model.StatusDTO;
import org.d11.boot.api.v2.model.TransferDayDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.api.v2.model.TransferWindowInputDTO;
import org.d11.boot.api.v2.model.TransferWindowResponseBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowsResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdateTransferWindowRequestBodyDTO;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer window controller tests.
 */
class TransferWindowControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Transfer window repository.
     */
    @Autowired
    private TransferWindowRepository transferWindowRepository;

    /**
     * Match week repository.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Transfer day repository.
     */
    @Autowired
    private TransferDayRepository transferDayRepository;

    /**
     * Tests TransferWindowController::getTransferWindowById.
     */
    @Test
    @Transactional
    void testGetTransferWindowById() {
        final List<TransferWindow> transferWindows = this.transferWindowRepository.findAll();

        // Check to see that the test data is set up correctly
        assertFalse(transferWindows.isEmpty());

        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        // 404 Not Found -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class, () -> transferWindowApi.getTransferWindowById(0L));

        // 200 OK ------------------------------------------------------------------------------------------------------

        for (final TransferWindow transferWindow : transferWindows) {
            final TransferWindowResponseBodyDTO responseBody =
                    transferWindowApi.getTransferWindowById(transferWindow.getId());

            assertEquals(transferWindow.getId(), responseBody.getTransferWindow().getId());
            assertEquals(transferWindow.getMatchWeek().getId(), responseBody.getMatchWeek().getId());
            assertEquals(transferWindow.getTransferDays().size(), responseBody.getTransferDays().size());

            for (int i = 0; i < transferWindow.getTransferDays().size(); ++i) {
                assertEquals(transferWindow.getTransferDays().get(i).getId(),
                             responseBody.getTransferDays().get(i).getId());
            }
        }
    }

    /**
     * Tests TransferWindowController::getCurrentTransferWindow.
     */
    @Test
    void testGetCurrentTransferWindow() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        final Optional<TransferWindow> optional = this.transferWindowRepository.findCurrentTransferWindow();

        assertFalse(optional.isEmpty());

        optional.ifPresent(transferWindow -> {
            final TransferWindowResponseBodyDTO result = transferWindowApi.getCurrentTransferWindow();
            assertNotNull(result);
            assertEquals(getMapper().map(transferWindow, TransferWindowDTO.class), result.getTransferWindow());

            assertNotNull(result.getMatchWeek());
            assertEquals(getMapper().map(transferWindow.getMatchWeek(), MatchWeekBaseDTO.class), result.getMatchWeek());

            assertNotNull(result.getTransferDays());
            assertFalse(result.getTransferDays().isEmpty());
            assertEquals(getMapper().map(transferWindow.getTransferDays(), TransferDayDTO.class),
                         result.getTransferDays());
        });
    }

    /**
     * Tests TransferWindowController::getTransferWindowsBySeasonId.
     */
    @Test
    void testGetTransferWindowsBySeasonId() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.getTransferWindowsBySeasonId((Long) null));

        final List<TransferWindow> transferWindows = this.transferWindowRepository.findAll();
        transferWindows.sort(Comparator.comparing(TransferWindow::getDatetime).reversed());

        final Set<Season> seasons = transferWindows.stream()
                .map(transferWindow -> transferWindow.getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1);

        for (final Season season : seasons) {
            final TransferWindowsResponseBodyDTO transferWindowsResponseBodyDTO =
                    transferWindowApi.getTransferWindowsBySeasonId(season.getId());
            assertNotNull(transferWindowsResponseBodyDTO);

            final List<TransferWindow> expected = transferWindows.stream()
                    .filter(transferWindow -> transferWindow.getMatchWeek().getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1);

            final List<TransferWindowDTO> result = transferWindowsResponseBodyDTO.getTransferWindows();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(map(expected, TransferWindowDTO.class), result);
        }
    }

    /**
     * Tests TransferWindowController::createTransferWindow.
     */
    @Test
    @SuppressWarnings({ "checkstyle:ExecutableStatementCount", "PMD.ExcessiveMethodLength" })
    void testCreateTransferWindow() {
        final CreateTransferWindowRequestBodyDTO requestBody = new CreateTransferWindowRequestBodyDTO()
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(1);

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class,
                     () -> getApi(TransferWindowApi.class).createTransferWindow(requestBody));

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(TransferWindowApi.class).createTransferWindow(requestBody));

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.createTransferWindow(new CreateTransferWindowRequestBodyDTO()));

        requestBody
                .datetime(LocalDateTime.now().minusDays(1));

        assertThrows(FeignException.BadRequest.class, () -> transferWindowApi.createTransferWindow(requestBody));

        requestBody
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(0);

        assertThrows(FeignException.BadRequest.class, () -> transferWindowApi.createTransferWindow(requestBody));

        requestBody.transferDayDelay(1);

        // 409 Conflict ------------------------------------------------------------------------------------------------

        assertThrows(FeignException.Conflict.class, () -> transferWindowApi.createTransferWindow(requestBody));

        // 201 Created -------------------------------------------------------------------------------------------------

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findCurrentTransferWindow()
                .orElse(null);
        assertNotNull(currentTransferWindow);

        currentTransferWindow.setStatus(Status.FINISHED);
        this.transferWindowRepository.save(currentTransferWindow);

        final TransferWindowResponseBodyDTO responseBody = transferWindowApi.createTransferWindow(requestBody);
        final TransferWindowDTO transferWindowDTO = responseBody.getTransferWindow();

        assertNotNull(transferWindowDTO);
        assertNotNull(responseBody.getMatchWeek());
        assertNotNull(responseBody.getTransferDays());
        assertEquals(1, responseBody.getTransferDays().size());

        final TransferWindow transferWindow = this.transferWindowRepository.findById(transferWindowDTO.getId())
                .orElse(null);
        final MatchWeek matchWeek = this.matchWeekRepository
                .findFirstByDateGreaterThanOrderByDateAsc(requestBody.getDatetime().toLocalDate()).orElse(null);

        assertNotNull(transferWindow);
        assertNotNull(matchWeek);

        assertEquals(currentTransferWindow.getTransferWindowNumber() + 1, transferWindow.getTransferWindowNumber());
        assertFalse(transferWindow.isDraft());
        assertEquals(Status.PENDING, transferWindow.getStatus());
        assertEquals(requestBody.getDatetime(), transferWindow.getDatetime());
        assertEquals(matchWeek, transferWindow.getMatchWeek());

        final List<TransferDay> transferDays = transferWindow.getTransferDays();

        assertEquals(1, transferDays.size());

        final TransferDay transferDay = transferDays.get(0);

        assertEquals(1, transferDay.getTransferDayNumber());
        assertEquals(Status.PENDING, transferDay.getStatus());
        assertEquals(requestBody.getDatetime().plusDays(requestBody.getTransferDayDelay()), transferDay.getDatetime());

        // Rollback the changes just in case
        this.transferWindowRepository.delete(transferWindow);
        currentTransferWindow.setStatus(Status.PENDING);
        this.transferWindowRepository.save(currentTransferWindow);
    }

    // updateTransferWindow --------------------------------------------------------------------------------------------

    /**
     * Tests TransferWindowController::updateTransferWindow for null transfer window number.
     */
    @Test
    void testUpdateTransferWindowTransferWindowNumberNull() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(1L));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferWindow.transferWindowNumber"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferWindowController::updateTransferWindow for null status.
     */
    @Test
    void testUpdateTransferWindowStatusNull() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(1L));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferWindow.status"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferWindowController::updateTransferWindow for invalid status.
     */
    @Test
    void testUpdateTransferWindowStatusInvalid() {
        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FULL_TIME)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(1L));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferWindow.status"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()));
    }

    /**
     * Tests TransferWindowController::updateTransferWindow for null datetime.
     */
    @Test
    void testUpdateTransferWindowDatetimeNull() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .matchWeekId(1L));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferWindow.datetime"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferWindowController::updateTransferWindow for null matchWeekId.
     */
    @Test
    void testUpdateTransferWindowMatchWeekIdNull() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .datetime(LocalDateTime.now()));

        final FeignException.BadRequest e =
                assertThrows(FeignException.BadRequest.class,
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));

        assertTrue(e.getMessage().contains("transferWindow.matchWeekId"));
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()));
    }

    /**
     * Tests TransferWindowControllerV2::updateTransferWindow for unauthorized.
     */
    @Test
    void testUpdateTransferDayUnauthorized() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(1L));

        assertThrows(FeignException.Unauthorized.class,
                     () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));
    }

    /**
     * Tests TransferWindowControllerV2::updateTransferWindow for forbidden.
     */
    @Test
    void testUpdateTransferDayForbidden() {
        final TransferWindowApi transferWindowApi = getUserApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(1L));

        assertThrows(FeignException.Forbidden.class, () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));
    }

    /**
     * Tests TransferWindowControllerV2::updateTransferWindow for transfer window not found.
     */
    @Test
    void testUpdateTransferWindowTransferWindowNotFound() {
        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(1L));

        assertThrows(FeignException.NotFound.class, () -> transferWindowApi.updateTransferWindow(-1L, requestBodyDTO));
    }

    /**
     * Tests TransferWindowControllerV2::updateTransferWindow for match week not found.
     */
    @Test
    void testUpdateTransferWindowMatchWeekNotFound() {
        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(new TransferWindowInputDTO()
                                        .transferWindowNumber(1)
                                        .draft(false)
                                        .status(StatusDTO.FINISHED)
                                        .datetime(LocalDateTime.now())
                                        .matchWeekId(-1L));

        assertThrows(FeignException.NotFound.class, () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO));
    }

    /**
     * Tests TransferWindowControllerV2::updateTransferWindow.
     */
    @Test
    void testUpdateTransferWindow() {
        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        final TransferWindow transferWindow = this.transferWindowRepository.findById(1L)
                .orElseThrow(RuntimeException::new);

        final TransferWindowInputDTO transferWindowInputDTO = new TransferWindowInputDTO()
                .transferWindowNumber(1)
                .draft(false)
                .status(StatusDTO.FINISHED)
                .datetime(LocalDateTime.now())
                .matchWeekId(1L);

        final UpdateTransferWindowRequestBodyDTO requestBodyDTO = new UpdateTransferWindowRequestBodyDTO()
                .transferWindow(transferWindowInputDTO);

        final TransferWindowResponseBodyDTO response = transferWindowApi.updateTransferWindow(1L, requestBodyDTO);

        assertEquals(transferWindowInputDTO.getTransferWindowNumber(),
                     response.getTransferWindow().getTransferWindowNumber());
        assertEquals(transferWindowInputDTO.isDraft(), response.getTransferWindow().isDraft());
        assertEquals(transferWindowInputDTO.getStatus().name(), response.getTransferWindow().getStatus().name());
        assertEquals(transferWindowInputDTO.getDatetime(), response.getTransferWindow().getDatetime());
        assertEquals(transferWindowInputDTO.getMatchWeekId(), response.getMatchWeek().getId());

        // Rollback changes just in case
        this.transferWindowRepository.save(transferWindow);
    }

    // deleteTransferWindow --------------------------------------------------------------------------------------------

    /**
     * Tests TransferWindowController::deleteTransferWindow.
     */
    @Test
    void testDeleteTransferWindow() {

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class, () -> getApi(TransferWindowApi.class).deleteTransferWindow(1L));

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(TransferWindowApi.class).deleteTransferWindow(1L));

        // 404 Not Found -----------------------------------------------------------------------------------------------

        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        assertThrows(FeignException.NotFound.class, () -> transferWindowApi.deleteTransferWindow(0L));

        // 409 Conflict ------------------------------------------------------------------------------------------------

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findCurrentTransferWindow()
                .orElse(null);
        assertNotNull(currentTransferWindow);

        currentTransferWindow.setStatus(Status.ACTIVE);
        this.transferWindowRepository.save(currentTransferWindow);

        assertThrows(FeignException.Conflict.class,
                     () -> transferWindowApi.deleteTransferWindow(currentTransferWindow.getId()));

        currentTransferWindow.setStatus(Status.FINISHED);
        this.transferWindowRepository.save(currentTransferWindow);

        assertThrows(FeignException.Conflict.class,
                     () -> transferWindowApi.deleteTransferWindow(currentTransferWindow.getId()));

        // 204 No Content ----------------------------------------------------------------------------------------------

        final CreateTransferWindowRequestBodyDTO requestBody = new CreateTransferWindowRequestBodyDTO()
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(1);
        final TransferWindowResponseBodyDTO responseBody = transferWindowApi.createTransferWindow(requestBody);

        final TransferWindow transferWindow = this.transferWindowRepository
                .findById(responseBody.getTransferWindow().getId())
                .orElse(null);
        assertNotNull(transferWindow);

        assertDoesNotThrow(() -> transferWindowApi.deleteTransferWindow(transferWindow.getId()));

        final Optional<TransferWindow> optional = this.transferWindowRepository.findById(transferWindow.getId());

        assertFalse(optional.isPresent());

        for (final TransferDay transferDay : transferWindow.getTransferDays()) {
            assertFalse(this.transferDayRepository.findById(transferDay.getId()).isPresent());
        }

        // Rollback the changes just in case
        currentTransferWindow.setStatus(Status.PENDING);
        this.transferWindowRepository.save(currentTransferWindow);
    }

}
