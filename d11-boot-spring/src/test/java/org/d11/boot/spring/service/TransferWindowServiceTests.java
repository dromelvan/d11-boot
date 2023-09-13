package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Transfer window service tests.
 */
class TransferWindowServiceTests extends D11BootServiceTests {

    /**
     * Mocked transfer window repository.
     */
    @Mock
    private TransferWindowRepository transferWindowRepository;

    /**
     * Mocked match week repository.
     */
    @Mock
    private MatchWeekRepository matchWeekRepository;

    /**
     * Transfer window service.
     */
    @InjectMocks
    private TransferWindowService transferWindowService;

    /**
     * Tests TransferWindowService::insertTransferWindow.
     */
    @Test
    void testInsertTransferWindow() {

        // Validation --------------------------------------------------------------------------------------------------

        final String datetimeProperty = "datetime";

        final BadRequestException nullDatetimeException =
                assertThrows(BadRequestException.class, () -> this.transferWindowService.insertTransferWindow(null, 1),
                             "TransferWindowService::insertTransferWindow null datetime throws");
        assertEquals(datetimeProperty, nullDatetimeException.getParameter(),
                     "TransferWindowService::insertTransferWindow property equals null datetime");

        final BadRequestException invalidDatetimeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferWindowService.insertTransferWindow(LocalDateTime.now().minusDays(1), 1),
                             "TransferWindowService::insertTransferWindow invalid datetime throws");
        assertEquals(datetimeProperty, invalidDatetimeException.getParameter(),
                     "TransferWindowService::insertTransferWindow property equals invalid datetime");

        final LocalDateTime datetime = LocalDateTime.now().plusDays(1);

        final BadRequestException invalidTransferDayDelay =
                assertThrows(BadRequestException.class,
                             () -> this.transferWindowService.insertTransferWindow(datetime, 0),
                             "TransferWindowService::insertTransferWindow invalid transferDayDelay throws");
        assertEquals("transferDayDelay", invalidTransferDayDelay.getParameter(),
                     "TransferWindowService::insertTransferWindow property equals invalid transferDayDelay");

        final TransferWindow currentTransferWindow = new TransferWindow();
        currentTransferWindow.setStatus(Status.PENDING);

        when(this.transferWindowRepository.findFirstByOrderByDatetimeDesc())
                .thenReturn(Optional.of(currentTransferWindow));

        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.insertTransferWindow(datetime, 1),
                     "TransferWindowService::insertTransferWindow current transferWindow invalid status throws");

        // Success -----------------------------------------------------------------------------------------------------

        currentTransferWindow.setStatus(Status.FINISHED);

        final MatchWeek matchWeek = new MatchWeek();
        when(this.matchWeekRepository.findFirstByDateGreaterThanOrderByDateAsc(eq(datetime.toLocalDate())))
                .thenReturn(Optional.of(matchWeek));
        when(this.transferWindowRepository.save(any(TransferWindow.class))).then(AdditionalAnswers.returnsFirstArg());

        final int transferDayDelay = 1;

        final TransferWindow transferWindow = this.transferWindowService.insertTransferWindow(datetime,
                                                                                              transferDayDelay);

        assertNotNull(transferWindow, "TransferWindowService::insertTransferWindow not null");
        assertEquals(currentTransferWindow.getTransferWindowNumber() + 1, transferWindow.getTransferWindowNumber(),
                     "TransferWindowService::insertTransferWindow transferWindowNumber equals");
        assertFalse(transferWindow.isDraft(), "TransferWindowService::insertTransferWindow draft");
        assertEquals(Status.PENDING, transferWindow.getStatus(),
                     "TransferWindowService::insertTransferWindow status equals");
        assertEquals(datetime, transferWindow.getDatetime(),
                     "TransferWindowService::insertTransferWindow datetime equals");
        assertEquals(matchWeek, transferWindow.getMatchWeek(),
                     "TransferWindowService::insertTransferWindow matchWeek equals");

        assertEquals(1, transferWindow.getTransferDays().size(),
                     "TransferWindowService::insertTransferWindow transferDays size equals");

        final TransferDay transferDay = transferWindow.getTransferDays().get(0);

        assertEquals(1, transferDay.getTransferDayNumber(),
                     "TransferWindowService::insertTransferWindow transferDay transferDayNumber equals");
        assertEquals(Status.PENDING, transferWindow.getStatus(),
                     "TransferWindowService::insertTransferWindow transferDay status equals");
        assertEquals(datetime.plusDays(transferDayDelay), transferDay.getDatetime(),
                     "TransferWindowService::insertTransferWindow transferDay datetime equals");
        assertEquals(transferWindow, transferDay.getTransferWindow(),
                     "TransferWindowService::insertTransferWindow transferDay transferWindow equals");
    }

}
