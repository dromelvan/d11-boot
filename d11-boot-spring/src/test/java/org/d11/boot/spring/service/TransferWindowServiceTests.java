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

        assertThrows(ConflictException.class, () -> this.transferWindowService.getCurrentTransferWindow(),
                     "TransferWindowService::getCurrentTransferWindow conflict");

        when(this.transferWindowRepository.findCurrentTransferWindow())
                .thenReturn(Optional.of(current));

        assertEquals(current, this.transferWindowService.getCurrentTransferWindow(),
                     "TransferWindowService::getCurrentTransferWindow current");
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

        assertNotNull(result, "TransferWindowService::getBySeasonId not null");
        assertFalse(result.isEmpty(), "TransferWindowService::getBySeasonId isEmpty");
        assertEquals(transferWindows, result, "TransferWindowService::getBySeasonId equals");
    }

    /**
     * Tests TransferWindowService::createTransferWindow.
     */
    @Test
    void testCreateTransferWindow() {

        // Validation --------------------------------------------------------------------------------------------------

        final String datetimeProperty = "datetime";

        final BadRequestException nullDatetimeException =
                assertThrows(BadRequestException.class, () -> this.transferWindowService.createTransferWindow(null, 1),
                             "TransferWindowService::createTransferWindow null datetime throws");
        assertEquals(datetimeProperty, nullDatetimeException.getParameter(),
                     "TransferWindowService::createTransferWindow property equals null datetime");

        final BadRequestException invalidDatetimeException =
                assertThrows(BadRequestException.class,
                             () -> this.transferWindowService.createTransferWindow(LocalDateTime.now().minusDays(1), 1),
                             "TransferWindowService::createTransferWindow invalid datetime throws");
        assertEquals(datetimeProperty, invalidDatetimeException.getParameter(),
                     "TransferWindowService::createTransferWindow property equals invalid datetime");

        final LocalDateTime datetime = LocalDateTime.now().plusDays(1);

        final BadRequestException invalidTransferDayDelay =
                assertThrows(BadRequestException.class,
                             () -> this.transferWindowService.createTransferWindow(datetime, 0),
                             "TransferWindowService::createTransferWindow invalid transferDayDelay throws");
        assertEquals("transferDayDelay", invalidTransferDayDelay.getParameter(),
                     "TransferWindowService::createTransferWindow property equals invalid transferDayDelay");

        final TransferWindow currentTransferWindow = new TransferWindow();
        currentTransferWindow.setStatus(Status.PENDING);

        when(this.transferWindowRepository.findFirstByOrderByDatetimeDesc())
                .thenReturn(Optional.of(currentTransferWindow));

        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.createTransferWindow(datetime, 1),
                     "TransferWindowService::createTransferWindow current transferWindow invalid status throws");

        // Success -----------------------------------------------------------------------------------------------------

        currentTransferWindow.setStatus(Status.FINISHED);

        final MatchWeek matchWeek = new MatchWeek();
        when(this.matchWeekRepository.findFirstByDateGreaterThanOrderByDateAsc(eq(datetime.toLocalDate())))
                .thenReturn(Optional.of(matchWeek));
        when(this.transferWindowRepository.save(any(TransferWindow.class))).then(AdditionalAnswers.returnsFirstArg());

        final int transferDayDelay = 1;

        final TransferWindow transferWindow = this.transferWindowService.createTransferWindow(datetime,
                                                                                              transferDayDelay);

        assertNotNull(transferWindow, "TransferWindowService::createTransferWindow not null");
        assertEquals(currentTransferWindow.getTransferWindowNumber() + 1, transferWindow.getTransferWindowNumber(),
                     "TransferWindowService::createTransferWindow transferWindowNumber equals");
        assertFalse(transferWindow.isDraft(), "TransferWindowService::createTransferWindow draft");
        assertEquals(Status.PENDING, transferWindow.getStatus(),
                     "TransferWindowService::createTransferWindow status equals");
        assertEquals(datetime, transferWindow.getDatetime(),
                     "TransferWindowService::createTransferWindow datetime equals");
        assertEquals(matchWeek, transferWindow.getMatchWeek(),
                     "TransferWindowService::createTransferWindow matchWeek equals");

        assertEquals(1, transferWindow.getTransferDays().size(),
                     "TransferWindowService::createTransferWindow transferDays size equals");

        final TransferDay transferDay = transferWindow.getTransferDays().get(0);

        assertEquals(1, transferDay.getTransferDayNumber(),
                     "TransferWindowService::createTransferWindow transferDay transferDayNumber equals");
        assertEquals(Status.PENDING, transferWindow.getStatus(),
                     "TransferWindowService::createTransferWindow transferDay status equals");
        assertEquals(datetime.plusDays(transferDayDelay), transferDay.getDatetime(),
                     "TransferWindowService::createTransferWindow transferDay datetime equals");
        assertEquals(transferWindow, transferDay.getTransferWindow(),
                     "TransferWindowService::createTransferWindow transferDay transferWindow equals");
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
                     () -> this.transferWindowService.updateTransferWindow(1L, transferWindowInput),
                     "TransferWindowService::updateTransferWindow status FULL_TIME throws");
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
                     () -> this.transferWindowService.updateTransferWindow(1L, transferWindowInput),
                     "TransferWindowService::updateTransferWindow transfer window not found throws");

        assertEquals(e.getResource(), TransferWindow.class.getSimpleName(),
                     "TransferWindowService::updateTransferWindow transfer window not found error resource equals");
        assertEquals(e.getId(), 1L,
                     "TransferWindowService::updateTransferWindow transfer window not found error id equals");
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
                     () -> this.transferWindowService.updateTransferWindow(1L, transferWindowInput),
                     "TransferWindowService::updateTransferWindow match week not found throws");

        assertEquals(e.getResource(), MatchWeek.class.getSimpleName(),
                     "TransferWindowService::updateTransferWindow match week not found error resource equals");
        assertEquals(e.getId(), transferWindowInput.matchWeekId(),
                     "TransferWindowService::updateTransferWindow match week not found error id equals");
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

        assertEquals(transferWindowInput.transferWindowNumber(), transferWindow.getTransferWindowNumber(),
                     "TransferWindowService::updateTransferWindow result transferWindowNumber equals");
        assertEquals(transferWindowInput.draft(), transferWindow.isDraft(),
                     "TransferWindowService::updateTransferWindow result draft equals");
        assertEquals(transferWindowInput.status(), transferWindow.getStatus(),
                     "TransferWindowService::updateTransferWindow result status equals");
        assertEquals(transferWindowInput.datetime(), transferWindow.getDatetime(),
                     "TransferWindowService::updateTransferWindow result datetime equals");
        assertEquals(matchWeek, transferWindow.getMatchWeek(),
                     "TransferWindowService::updateTransferWindow result matchWeek equals");
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
                     () -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()),
                     "TransferWindowService::deleteTransferWindow not found throws");

        when(this.transferWindowRepository.findById(eq(transferWindow.getId())))
                .thenReturn(Optional.of(transferWindow));

        transferWindow.setStatus(Status.FINISHED);
        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()),
                     "TransferWindowService::deleteTransferWindow status FINISHED throws");

        transferWindow.setStatus(Status.ACTIVE);
        assertThrows(ConflictException.class,
                     () -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()),
                     "TransferWindowService::deleteTransferWindow status ACTIVE throws");

        transferWindow.setStatus(Status.PENDING);
        assertDoesNotThrow(() -> this.transferWindowService.deleteTransferWindow(transferWindow.getId()),
                           "TransferWindowService::deleteTransferWindow status PENDING does not throw");

        verify(this.transferWindowRepository, times(1)).delete(eq(transferWindow));
    }

}
