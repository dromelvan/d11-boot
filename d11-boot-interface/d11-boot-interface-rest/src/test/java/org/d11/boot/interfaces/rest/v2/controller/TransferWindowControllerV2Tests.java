package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.TransferWindowApi;
import org.d11.boot.api.v2.model.InsertTransferWindowRequestBodyDTO;
import org.d11.boot.api.v2.model.TransferWindowDTO;
import org.d11.boot.api.v2.model.TransferWindowResponseBodyDTO;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
     * Tests TransferWindowController::insertTransferWindow.
     */
    @Test
    @SuppressWarnings({ "checkstyle:ExecutableStatementCount", "PMD.ExcessiveMethodLength" })
    void testInsertTransferWindow() {
        final InsertTransferWindowRequestBodyDTO requestBody = new InsertTransferWindowRequestBodyDTO()
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(1);

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class,
                     () -> getApi(TransferWindowApi.class).insertTransferWindow(requestBody),
                     "TransferWindowController::insertTransferWindow unauthorized throws");

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(TransferWindowApi.class).insertTransferWindow(requestBody),
                     "TransferWindowController::insertTransferWindow user throws");

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final TransferWindowApi transferWindowApi = getAdministratorApi(TransferWindowApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.insertTransferWindow(new InsertTransferWindowRequestBodyDTO()),
                     "TransferWindowController::insertTransferWindow request body invalid throws");

        requestBody
                .datetime(LocalDateTime.now().minusDays(1));

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.insertTransferWindow(requestBody),
                     "TransferWindowController::insertTransferWindow datetime invalid throws");

        requestBody
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(0);

        assertThrows(FeignException.BadRequest.class,
                     () -> transferWindowApi.insertTransferWindow(requestBody),
                     "TransferWindowController::insertTransferWindow transferDayDelay invalid throws");

        requestBody.transferDayDelay(1);

        // 409 Conflict ------------------------------------------------------------------------------------------------

        assertThrows(FeignException.Conflict.class,
                     () -> transferWindowApi.insertTransferWindow(requestBody),
                     "TransferWindowController::insertTransferWindow current transfer window status pending throws");

        // 200 OK ------------------------------------------------------------------------------------------------------

        final TransferWindow currentTransferWindow = this.transferWindowRepository.findFirstByOrderByDatetimeDesc()
                .orElseThrow(NotFoundException::new);

        currentTransferWindow.setStatus(Status.FINISHED);
        this.transferWindowRepository.save(currentTransferWindow);

        final TransferWindowResponseBodyDTO responseBody = transferWindowApi.insertTransferWindow(requestBody);
        final TransferWindowDTO transferWindowDTO = responseBody.getTransferWindow();

        assertNotNull(transferWindowDTO, "TransferWindowController::insertTransferWindow transferWindow DTO not null");
        assertNotNull(responseBody.getMatchWeek(),
                      "TransferWindowController::insertTransferWindow matchWeek DTO not null");
        assertNotNull(responseBody.getTransferDays(),
                      "TransferWindowController::insertTransferWindow transferDay DTOs not null");
        assertEquals(1, responseBody.getTransferDays().size(),
                     "TransferWindowController::insertTransferWindow transferDay DTOs size equals");

        final TransferWindow transferWindow = this.transferWindowRepository.findById(transferWindowDTO.getId())
                .orElseThrow(NotFoundException::new);
        final MatchWeek matchWeek = this.matchWeekRepository
                .findFirstByDateGreaterThanOrderByDateAsc(requestBody.getDatetime().toLocalDate())
                        .orElseThrow(NotFoundException::new);

        assertEquals(currentTransferWindow.getTransferWindowNumber() + 1, transferWindow.getTransferWindowNumber(),
                     "TransferWindowController::insertTransferWindow transferWindow transferWindowNumber equals");
        assertFalse(transferWindow.isDraft(),
                    "TransferWindowController::insertTransferWindow transferWindow draft");
        assertEquals(Status.PENDING, transferWindow.getStatus(),
                     "TransferWindowController::insertTransferWindow transferWindow status equals");
        assertEquals(requestBody.getDatetime(), transferWindow.getDatetime(),
                     "TransferWindowController::insertTransferWindow transferWindow datetime equals");
        assertEquals(matchWeek, transferWindow.getMatchWeek(),
                     "TransferWindowController::insertTransferWindow transferWindow matchWeek equals");

        final List<TransferDay> transferDays = transferWindow.getTransferDays();

        assertEquals(1, transferDays.size(),
                     "TransferWindowController::insertTransferWindow transferWindow transferDays size equals");

        final TransferDay transferDay = transferDays.get(0);

        assertEquals(1, transferDay.getTransferDayNumber(),
                     "TransferWindowController::insertTransferWindow transferDay transferDayNumber equals");
        assertEquals(Status.PENDING, transferDay.getStatus(),
                     "TransferWindowController::insertTransferWindow transferDay status equals");
        assertEquals(requestBody.getDatetime().plusDays(requestBody.getTransferDayDelay()), transferDay.getDatetime(),
                     "TransferWindowController::insertTransferWindow transferDay datetime equals");

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
                .orElseThrow(NotFoundException::new);

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

        final InsertTransferWindowRequestBodyDTO requestBody = new InsertTransferWindowRequestBodyDTO()
                .datetime(LocalDateTime.now().plusDays(1))
                .transferDayDelay(1);
        final TransferWindowResponseBodyDTO responseBody = transferWindowApi.insertTransferWindow(requestBody);

        final TransferWindow transferWindow = this.transferWindowRepository
                .findById(responseBody.getTransferWindow().getId())
                .orElseThrow(NotFoundException::new);

        assertDoesNotThrow(() -> transferWindowApi.deleteTransferWindow(transferWindow.getId()),
                           "TransferWindowController::deleteTransferWindow does not throw");

        final Optional<TransferWindow> optional = this.transferWindowRepository.findById(transferWindow.getId());

        assertFalse(optional.isPresent(),
                    "TransferWindowController::deleteTransferWindow transferWindow deleted present");

        // Rollback the changes just in case
        currentTransferWindow.setStatus(Status.PENDING);
        this.transferWindowRepository.save(currentTransferWindow);
    }

}
