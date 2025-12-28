package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.model.TransferWindowInput;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer window service tests.
 */
class TransferWindowServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked application context.
     */
    @Mock
    private ApplicationContext applicationContext;

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
     * Tests TransferWindowService::getCurrentTransferWindow.
     */
    @Test
    void testGetTransferWindow() {
        final TransferWindow current = generate(TransferWindow.class);

        when(this.transferWindowRepository.findCurrentTransferWindow()).thenReturn(Optional.empty());

        assertThrows(ConflictException.class, () -> this.transferWindowService.getCurrentTransferWindow());

        when(this.transferWindowRepository.findCurrentTransferWindow())
                .thenReturn(Optional.of(current));

        assertEquals(current, this.transferWindowService.getCurrentTransferWindow());
    }

    /**
     * Tests TransferWindowService::getBySeasonId.
     */
    @Test
    void testGetBySeasonId() {
        final List<TransferWindow> transferWindows = generateList(TransferWindow.class);
        when(this.transferWindowRepository.findByMatchWeekSeasonIdOrderByDatetimeDesc(any(Long.class)))
                .thenReturn(transferWindows);

        final List<TransferWindow> result = this.transferWindowService.getBySeasonId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(transferWindows, result);
    }

    /**
     * Tests TransferWindowService::createTransferWindow.
     */
    @Test
    void testCreateTransferWindow() {

        // Validation --------------------------------------------------------------------------------------------------

        final String datetimeProperty = "datetime";

        final BadRequestException nullDatetimeException =
                assertThrows(BadRequestException.class, () -> this.transferWindowService.createTransferWindow(null, 1));
        assertEquals(datetimeProperty, nullDatetimeException.getParameter());

        final BadRequestException invalidDatetimeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferWindowService.createTransferWindow(LocalDateTime.now().minusDays(1),
                                                                                   1));
        assertEquals(datetimeProperty, invalidDatetimeException.getParameter());

        final LocalDateTime datetime = LocalDateTime.now().plusDays(1);

        final BadRequestException invalidTransferDayDelay =
                assertThrows(BadRequestException.class,
                             () -> this.transferWindowService.createTransferWindow(datetime, 0));
        assertEquals("transferDayDelay", invalidTransferDayDelay.getParameter());

        final TransferWindow currentTransferWindow = new TransferWindow();
        currentTransferWindow.setStatus(Status.PENDING);

        when(this.transferWindowRepository.findFirstByOrderByDatetimeDesc())
                .thenReturn(Optional.of(currentTransferWindow));

        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.createTransferWindow(datetime, 1));

        // Success -----------------------------------------------------------------------------------------------------

        currentTransferWindow.setStatus(Status.FINISHED);

        final MatchWeek matchWeek = new MatchWeek();
        when(this.matchWeekRepository.findFirstByDateGreaterThanOrderByDateAsc(eq(datetime.toLocalDate())))
                .thenReturn(Optional.of(matchWeek));
        when(this.transferWindowRepository.save(any(TransferWindow.class))).then(AdditionalAnswers.returnsFirstArg());

        final int transferDayDelay = 1;

        final TransferWindow transferWindow = this.transferWindowService.createTransferWindow(datetime,
                                                                                              transferDayDelay);

        assertNotNull(transferWindow);
        assertEquals(currentTransferWindow.getTransferWindowNumber() + 1, transferWindow.getTransferWindowNumber());
        assertFalse(transferWindow.isDraft());
        assertEquals(Status.PENDING, transferWindow.getStatus());
        assertEquals(datetime, transferWindow.getDatetime());
        assertEquals(matchWeek, transferWindow.getMatchWeek());

        assertEquals(1, transferWindow.getTransferDays().size());

        final TransferDay transferDay = transferWindow.getTransferDays().get(0);

        assertEquals(1, transferDay.getTransferDayNumber());
        assertEquals(Status.PENDING, transferWindow.getStatus());
        assertEquals(datetime.plusDays(transferDayDelay), transferDay.getDatetime());
        assertEquals(transferWindow, transferDay.getTransferWindow());
    }

    // updateTransferDay -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferWindowService::updateTransferWindow for bad request.
     */
    @Test
    void testUpdateTransferDayBadRequest() {
        final TransferWindowInput transferWindowInput =
                new TransferWindowInput(1, false, Status.FULL_TIME, LocalDateTime.now(), 1L);

        assertThrows(BadRequestException.class,
                     () -> this.transferWindowService.updateTransferWindow(1L, transferWindowInput));
    }

    /**
     * Tests TransferWindowService::updateTransferWindow for transfer window not found.
     */
    @Test
    void testUpdateTransferWindowTransferWindowNotFound() {
        final TransferWindowInput transferWindowInput =
                new TransferWindowInput(1, false, Status.FINISHED, LocalDateTime.now(), 1L);

        when(this.transferWindowRepository.findById(eq(1L))).thenReturn(Optional.empty());

        final NotFoundException e = assertThrows(NotFoundException.class,
                     () -> this.transferWindowService.updateTransferWindow(1L, transferWindowInput));

        assertEquals(e.getResource(), TransferWindow.class.getSimpleName());
        assertEquals(1L, e.getId());
    }

    /**
     * Tests TransferWindowService::updateTransferWindow for match week not found.
     */
    @Test
    void testUpdateTransferWindowMatchWeekNotFound() {
        final TransferWindowInput transferWindowInput =
                new TransferWindowInput(1, false, Status.FINISHED, LocalDateTime.now(), 1L);

        when(this.transferWindowRepository.findById(eq(1L))).thenReturn(Optional.of(generate(TransferWindow.class)));
        when(this.matchWeekRepository.findById(eq(transferWindowInput.matchWeekId())))
                .thenReturn(Optional.empty());
        when(this.applicationContext.getBean(eq(MatchWeekRepository.class))).thenReturn(this.matchWeekRepository);

        final NotFoundException e = assertThrows(NotFoundException.class,
                     () -> this.transferWindowService.updateTransferWindow(1L, transferWindowInput));

        assertEquals(e.getResource(), MatchWeek.class.getSimpleName());
        assertEquals(e.getId(), transferWindowInput.matchWeekId());
    }

    /**
     * Tests TransferWindowService::updateTransferWindow.
     */
    @Test
    void testUpdateTransferWindow() {
        final TransferWindowInput transferWindowInput =
                new TransferWindowInput(1, false, Status.FINISHED, LocalDateTime.now(), 1L);

        final MatchWeek matchWeek = generate(MatchWeek.class);

        when(this.transferWindowRepository.findById(eq(1L))).thenReturn(Optional.of(generate(TransferWindow.class)));
        when(this.matchWeekRepository.findById(eq(transferWindowInput.matchWeekId())))
                .thenReturn(Optional.of(matchWeek));
        when(this.applicationContext.getBean(eq(MatchWeekRepository.class))).thenReturn(this.matchWeekRepository);
        when(this.transferWindowRepository.save(any(TransferWindow.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferWindow transferWindow = this.transferWindowService.updateTransferWindow(1L, transferWindowInput);

        assertEquals(transferWindowInput.transferWindowNumber(), transferWindow.getTransferWindowNumber());
        assertEquals(transferWindowInput.draft(), transferWindow.isDraft());
        assertEquals(transferWindowInput.status(), transferWindow.getStatus());
        assertEquals(transferWindowInput.datetime(), transferWindow.getDatetime());
        assertEquals(matchWeek, transferWindow.getMatchWeek());
    }

    // deleteTransferDay -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferWindowService::deleteTransferWindow.
     */
    @Test
    void testDeleteTransferWindow() {
        final TransferWindow transferWindow = new TransferWindow();
        transferWindow.setId(1L);

        when(this.transferWindowRepository.findById(eq(transferWindow.getId()))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                     () -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()));

        when(this.transferWindowRepository.findById(eq(transferWindow.getId())))
                .thenReturn(Optional.of(transferWindow));

        transferWindow.setStatus(Status.FINISHED);
        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()));

        transferWindow.setStatus(Status.ACTIVE);
        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()));

        transferWindow.setStatus(Status.PENDING);
        assertDoesNotThrow(() -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()));

        verify(this.transferWindowRepository, times(1)).delete(eq(transferWindow));
    }

}
