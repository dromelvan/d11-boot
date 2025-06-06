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
        assertFalse(transferWindows.isEmpty(), "TransferWindowController::getTransferWindowById transferWindows empty");

        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        // 404 Not Found -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class,
                     () -> transferWindowApi.getTransferWindowById(0L),
                     "TransferWindowController::getTransferWindowById not found throws");

        // 200 OK ------------------------------------------------------------------------------------------------------

        for (final TransferWindow transferWindow : transferWindows) {
            final TransferWindowResponseBodyDTO responseBody =
                    transferWindowApi.getTransferWindowById(transferWindow.getId());

            assertEquals(transferWindow.getId(), responseBody.getTransferWindow().getId(),
                         "TransferWindowController::getTransferWindowById transferWindow id equals");
            assertEquals(transferWindow.getMatchWeek().getId(), responseBody.getMatchWeek().getId(),
                         "TransferWindowController::getTransferWindowById matchWeek id equals");
            assertEquals(transferWindow.getTransferDays().size(), responseBody.getTransferDays().size(),
                         "TransferWindowController::getTransferWindowById transferDays size equals");

            for (int i = 0; i < transferWindow.getTransferDays().size(); ++i) {
                assertEquals(transferWindow.getTransferDays().get(i).getId(),
                             responseBody.getTransferDays().get(i).getId(),
                             "TransferWindowController::getTransferWindowById transferDay id equals");
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

        assertFalse(optional.isEmpty(), "TransferWindowController::getCurrentTransferWindow transferWindow present");

        optional.ifPresent(transferWindow -> {
            final TransferWindowResponseBodyDTO result = transferWindowApi.getCurrentTransferWindow();
            assertNotNull(result, "TransferWindowController::getCurrentTransferWindow not null");
            assertEquals(getMapper().map(transferWindow, TransferWindowDTO.class),
                         result.getTransferWindow(),
                         "TransferWindowController::getCurrentTransferWindow equals");

            assertNotNull(result.getMatchWeek(), "TransferWindowController::getCurrentTransferWindow matchWeek null");
            assertEquals(getMapper().map(transferWindow.getMatchWeek(), MatchWeekBaseDTO.class),
                         result.getMatchWeek(),
                         "TransferWindowController::getCurrentTransferWindow matchWeek equals");

            assertNotNull(result.getTransferDays(),
                          "TransferWindowController::getCurrentTransferWindow transferDays null");
            assertFalse(result.getTransferDays().isEmpty(),
                        "TransferWindowController::getCurrentTransferWindow transferDays empty");
            assertEquals(getMapper().map(transferWindow.getTransferDays(), TransferDayDTO.class),
                         result.getTransferDays(),
                         "TransferWindowController::getCurrentTransferWindow transferDays equals");
        });
    }

    /**
     * Tests TransferWindowController::getTransferWindowsBySeasonId.
     */
    @Test
    void testGetTransferWindowsBySeasonId() {
        final TransferWindowApi transferWindowApi = getApi(TransferWindowApi.class);

        assertThrows(FeignException.BadRequest.class, () -> transferWindowApi.getTransferWindowsBySeasonId((Long) null),
                "TransferWindowController::getTransferWindowsBySeasonId seasonId null throws");

        final List<TransferWindow> transferWindows = this.transferWindowRepository.findAll();
        transferWindows.sort(Comparator.comparing(TransferWindow::getDatetime).reversed());

        final Set<Season> seasons = transferWindows.stream()
                .map(transferWindow -> transferWindow.getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1, "TransferWindowController::getTransferWindowsBySeasonId seasons size > 0");

        for (final Season season : seasons) {
            final TransferWindowsResponseBodyDTO transferWindowsResponseBodyDTO =
                    transferWindowApi.getTransferWindowsBySeasonId(season.getId());
            assertNotNull(transferWindowsResponseBodyDTO,
                          "TransferWindowController::getTransferWindowsBySeasonId response not null");

            final List<TransferWindow> expected = transferWindows.stream()
                    .filter(transferWindow -> transferWindow.getMatchWeek().getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1, "TransferWindowController::getTransferWindowsBySeasonId expected size > 1");

            final List<TransferWindowDTO> result = transferWindowsResponseBodyDTO.getTransferWindows();

            assertNotNull(result, "TransferWindowController::getTransferWindowsBySeasonId not null ");
            assertFalse(result.isEmpty(), "TransferWindowController::getTransferWindowsBySeasonId empty");
            assertEquals(map(expected, TransferWindowDTO.class), result,
                    "TransferWindowController::getTransferWindowsBySeasonId equals");
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
                     () -> getApi(TransferWindowApi.class).createTransferWindow(requestBody),
                     "TransferWindowController::createTransferWindow unauthorized throws");

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(TransferWindowApi.class).createTransferWindow(requestBody),
                     "TransferWindowController::createTransferWindow user throws");

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.createTransferWindow(new CreateTransferWindowRequestBodyDTO()),
                     "TransferWindowController::createTransferWindow request body invalid throws");

        requestBody
                .datetime(LocalDateTime.now().minusDays(1));

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.createTransferWindow(requestBody),
                     "TransferWindowController::createTransferWindow datetime invalid throws");

        requestBody
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(0);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.createTransferWindow(requestBody),
                     "TransferWindowController::createTransferWindow transferDayDelay invalid throws");

        requestBody.transferDayDelay(1);

        // 409 Conflict ------------------------------------------------------------------------------------------------

        assertThrows(FeignException.Conflict.class,
                     () -> transferWindowApi.createTransferWindow(requestBody),
                     "TransferWindowController::createTransferWindow current transfer window status pending throws");

        // 201 Created -------------------------------------------------------------------------------------------------

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findCurrentTransferWindow()
                .orElse(null);
        assertNotNull(currentTransferWindow,
                      "TransferWindowController::createTransferWindow currentTransferWindow not null");

        currentTransferWindow.setStatus(Status.FINISHED);
        this.transferWindowRepository.save(currentTransferWindow);

        final TransferWindowResponseBodyDTO responseBody = transferWindowApi.createTransferWindow(requestBody);
        final TransferWindowDTO transferWindowDTO = responseBody.getTransferWindow();

        assertNotNull(transferWindowDTO, "TransferWindowController::createTransferWindow transferWindow DTO not null");
        assertNotNull(responseBody.getMatchWeek(),
                      "TransferWindowController::createTransferWindow matchWeek DTO not null");
        assertNotNull(responseBody.getTransferDays(),
                      "TransferWindowController::createTransferWindow transferDay DTOs not null");
        assertEquals(1, responseBody.getTransferDays().size(),
                     "TransferWindowController::createTransferWindow transferDay DTOs size equals");

        final TransferWindow transferWindow = this.transferWindowRepository.findById(transferWindowDTO.getId())
                .orElse(null);
        final MatchWeek matchWeek = this.matchWeekRepository
                .findFirstByDateGreaterThanOrderByDateAsc(requestBody.getDatetime().toLocalDate()).orElse(null);

        assertNotNull(transferWindow,
                      "TransferWindowController::createTransferWindow transferDay not null");
        assertNotNull(matchWeek,
                     "TransferWindowController::createTransferWindow matchWeek not null");

        assertEquals(currentTransferWindow.getTransferWindowNumber() + 1, transferWindow.getTransferWindowNumber(),
                     "TransferWindowController::createTransferWindow transferWindow transferWindowNumber equals");
        assertFalse(transferWindow.isDraft(),
                    "TransferWindowController::createTransferWindow transferWindow draft");
        assertEquals(Status.PENDING, transferWindow.getStatus(),
                     "TransferWindowController::createTransferWindow transferWindow status equals");
        assertEquals(requestBody.getDatetime(), transferWindow.getDatetime(),
                     "TransferWindowController::createTransferWindow transferWindow datetime equals");
        assertEquals(matchWeek, transferWindow.getMatchWeek(),
                     "TransferWindowController::createTransferWindow transferWindow matchWeek equals");

        final List<TransferDay> transferDays = transferWindow.getTransferDays();

        assertEquals(1, transferDays.size(),
                     "TransferWindowController::createTransferWindow transferWindow transferDays size equals");

        final TransferDay transferDay = transferDays.get(0);

        assertEquals(1, transferDay.getTransferDayNumber(),
                     "TransferWindowController::createTransferWindow transferDay transferDayNumber equals");
        assertEquals(Status.PENDING, transferDay.getStatus(),
                     "TransferWindowController::createTransferWindow transferDay status equals");
        assertEquals(requestBody.getDatetime().plusDays(requestBody.getTransferDayDelay()), transferDay.getDatetime(),
                     "TransferWindowController::createTransferWindow transferDay datetime equals");

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
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                             "TransferWindowControllerV2::updateTransferWindow transferWindowNumber null throws");

        assertTrue(e.getMessage().contains("transferWindow.transferWindowNumber"),
                   "TransferWindowControllerV2::updateTransferWindow transferWindowNumber null property equals");
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()),
                   "TransferWindowControllerV2::updateTransferWindow transferWindowNumber null message contains");
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
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                             "TransferWindowControllerV2::updateTransferWindow status null throws");

        assertTrue(e.getMessage().contains("transferWindow.status"),
                   "TransferWindowControllerV2::updateTransferWindow status null property equals");
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()),
                   "TransferWindowControllerV2::updateTransferWindow status null message contains");
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
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                             "TransferWindowControllerV2::updateTransferWindow status invalid throws");

        assertTrue(e.getMessage().contains("transferWindow.status"),
                   "TransferWindowControllerV2::updateTransferWindow status invalid property equals");
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_INVALID_PARAMETER.getMessage()),
                   "TransferWindowControllerV2::updateTransferWindow status invalid message contains");
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
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                             "TransferWindowControllerV2::updateTransferWindow datetime null throws");

        assertTrue(e.getMessage().contains("transferWindow.datetime"),
                   "TransferWindowControllerV2::updateTransferWindow datetime null property equals");
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()),
                   "TransferWindowControllerV2::updateTransferWindow datetime null message contains");
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
                             () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                             "TransferWindowControllerV2::updateTransferWindow matchWeekId null throws");

        assertTrue(e.getMessage().contains("transferWindow.matchWeekId"),
                   "TransferWindowControllerV2::updateTransferWindow matchWeekId null property equals");
        assertTrue(e.getMessage().contains(ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING.getMessage()),
                   "TransferWindowControllerV2::updateTransferWindow matchWeekId null message contains");
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
                     () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                     "TransferWindowControllerV2::updateTransferWindow unauthorized throws");
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

        assertThrows(FeignException.Forbidden.class,
                     () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                     "TransferWindowControllerV2::updateTransferWindow forbidden throws");
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

        assertThrows(FeignException.NotFound.class,
                     () -> transferWindowApi.updateTransferWindow(-1L, requestBodyDTO),
                     "TransferWindowControllerV2::updateTransferWindow transfer window not found throws");
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

        assertThrows(FeignException.NotFound.class,
                     () -> transferWindowApi.updateTransferWindow(1L, requestBodyDTO),
                     "TransferWindowControllerV2::updateTransferWindow transfer window not found throws");
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
                     response.getTransferWindow().getTransferWindowNumber(),
                     "TransferWindowControllerV2::updateTransferWindow result transferDayNumber equals");
        assertEquals(transferWindowInputDTO.isDraft(), response.getTransferWindow().isDraft(),
                     "TransferWindowControllerV2::updateTransferWindow result draft equals");
        assertEquals(transferWindowInputDTO.getStatus().name(), response.getTransferWindow().getStatus().name(),
                     "TransferWindowControllerV2::updateTransferWindow result status equals");
        assertEquals(transferWindowInputDTO.getDatetime(), response.getTransferWindow().getDatetime(),
                     "TransferWindowControllerV2::updateTransferWindow result datetime equals");
        assertEquals(transferWindowInputDTO.getMatchWeekId(), response.getMatchWeek().getId(),
                     "TransferWindowControllerV2::updateTransferWindow result matchWeekId equals");

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

        assertThrows(FeignException.Unauthorized.class,
                     () -> getApi(TransferWindowApi.class).deleteTransferWindow(1L),
                     "TransferWindowController::deleteTransferWindow unauthorized throws");

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(TransferWindowApi.class).deleteTransferWindow(1L),
                     "TransferWindowController::deleteTransferWindow user throws");

        // 404 Not Found -----------------------------------------------------------------------------------------------

        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        assertThrows(FeignException.NotFound.class,
                     () -> transferWindowApi.deleteTransferWindow(0L),
                     "TransferWindowController::deleteTransferWindow not found throws");

        // 409 Conflict ------------------------------------------------------------------------------------------------

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findCurrentTransferWindow()
                .orElse(null);
        assertNotNull(currentTransferWindow,
                      "TransferWindowController::deleteTransferWindow currentTransferWindow not null");

        currentTransferWindow.setStatus(Status.ACTIVE);
        this.transferWindowRepository.save(currentTransferWindow);

        assertThrows(FeignException.Conflict.class,
                     () -> transferWindowApi.deleteTransferWindow(currentTransferWindow.getId()),
                     "TransferWindowController::deleteTransferWindow status active throws");

        currentTransferWindow.setStatus(Status.FINISHED);
        this.transferWindowRepository.save(currentTransferWindow);

        assertThrows(FeignException.Conflict.class,
                     () -> transferWindowApi.deleteTransferWindow(currentTransferWindow.getId()),
                     "TransferWindowController::deleteTransferWindow status finished throws");

        // 204 No Content ----------------------------------------------------------------------------------------------

        final CreateTransferWindowRequestBodyDTO requestBody = new CreateTransferWindowRequestBodyDTO()
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(1);
        final TransferWindowResponseBodyDTO responseBody = transferWindowApi.createTransferWindow(requestBody);

        final TransferWindow transferWindow = this.transferWindowRepository
                .findById(responseBody.getTransferWindow().getId())
                .orElse(null);
        assertNotNull(transferWindow, "TransferWindowController::deleteTransferWindow transferWindow not null");

        assertDoesNotThrow(() -> transferWindowApi.deleteTransferWindow(transferWindow.getId()),
                           "TransferWindowController::deleteTransferWindow does not throw");

        final Optional<TransferWindow> optional = this.transferWindowRepository.findById(transferWindow.getId());

        assertFalse(optional.isPresent(),
                    "TransferWindowController::deleteTransferWindow transferWindow deleted present");

        for (final TransferDay transferDay : transferWindow.getTransferDays()) {
            assertFalse(this.transferDayRepository.findById(transferDay.getId()).isPresent(),
                        "TransferWindowController::deleteTransferWindow transferDay deleted present");
        }

        // Rollback the changes just in case
        currentTransferWindow.setStatus(Status.PENDING);
        this.transferWindowRepository.save(currentTransferWindow);
    }

}
