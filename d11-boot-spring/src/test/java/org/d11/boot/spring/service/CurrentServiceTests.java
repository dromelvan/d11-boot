package org.d11.boot.spring.service;

import org.d11.boot.spring.model.CurrentResult;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferWindow;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferWindowRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Current service tests.
 */
class CurrentServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked season repository.
     */
    @Mock
    private SeasonRepository seasonRepository;

    /**
     * Mocked match week repository.
     */
    @Mock
    private MatchWeekRepository matchWeekRepository;

    /**
     * Mocked transfer window repository.
     */
    @Mock
    private TransferWindowRepository transferWindowRepository;

    /**
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Current service.
     */
    @InjectMocks
    private CurrentService currentService;

    /**
     * Tests CurrentService::getCurrent when all resources exist.
     */
    @Test
    void testGetCurrentAllPresent() {
        final Season season = generate(Season.class);
        final MatchWeek matchWeek = generate(MatchWeek.class);
        final TransferWindow transferWindow = generate(TransferWindow.class);
        final TransferDay transferDay = generate(TransferDay.class);

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(eq(Status.PENDING)))
                .thenReturn(Optional.of(matchWeek));
        when(this.transferWindowRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(transferWindow));
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(transferDay));

        final CurrentResult result = this.currentService.getCurrent();

        assertNotNull(result);
        assertEquals(season, result.season());
        assertEquals(matchWeek, result.matchWeek());
        assertEquals(transferWindow, result.transferWindow());
        assertEquals(transferDay, result.transferDay());
    }

    /**
     * Tests CurrentService::getCurrent when no PENDING match week exists, falling back to date.
     */
    @Test
    void testGetCurrentMatchWeekFallback() {
        final MatchWeek matchWeek = generate(MatchWeek.class);

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.empty());
        when(this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(eq(Status.PENDING)))
                .thenReturn(Optional.empty());
        when(this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(any(LocalDate.class)))
                .thenReturn(Optional.of(matchWeek));
        when(this.transferWindowRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.empty());
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.empty());

        final CurrentResult result = this.currentService.getCurrent();

        assertNotNull(result);
        assertNull(result.season());
        assertEquals(matchWeek, result.matchWeek());
        assertNull(result.transferWindow());
        assertNull(result.transferDay());
    }

    /**
     * Tests CurrentService::getCurrent when no resources exist.
     */
    @Test
    void testGetCurrentAllAbsent() {
        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.empty());
        when(this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(eq(Status.PENDING)))
                .thenReturn(Optional.empty());
        when(this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(any(LocalDate.class)))
                .thenReturn(Optional.empty());
        when(this.transferWindowRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.empty());
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.empty());

        final CurrentResult result = this.currentService.getCurrent();

        assertNotNull(result);
        assertNull(result.season());
        assertNull(result.matchWeek());
        assertNull(result.transferWindow());
        assertNull(result.transferDay());
    }

}
