package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferWindowApi;
import org.d11.boot.api.v2.model.CreateTransferWindowRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.api.v2.model.TransferWindowResponseBodyDTO;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findFirstByOrderByDatetimeDesc()
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

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findFirstByOrderByDatetimeDesc()
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
