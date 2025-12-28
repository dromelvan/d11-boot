package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Match week service tests.
 */
class MatchWeekServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked match week repository.
     */
    @Mock
    private MatchWeekRepository matchWeekRepository;

    /**
     * Match week service.
     */
    @InjectMocks
    private MatchWeekService matchWeekService;

    /**
     * Tests MatchWeekService::getById.
     */
    @Test
    void testGetById() {
        final List<MatchWeek> matchWeeks = generateList(MatchWeek.class);
        when(this.matchWeekRepository.findById(anyLong())).thenReturn(Optional.empty());

        for (final MatchWeek matchWeek : matchWeeks) {
            when(this.matchWeekRepository.findById(matchWeek.getId())).thenReturn(Optional.of(matchWeek));

            final MatchWeek result = this.matchWeekService.getById(matchWeek.getId());
            assertNotNull(result);
            assertEquals(matchWeek, result);
        }

        assertThrows(NotFoundException.class, () -> this.matchWeekService.getById(-1L));
    }

    /**
     * Tests MatchWeekService::getCurrentMatchWeek.
     */
    @Test
    void testGetCurrentMatchWeek() {
        final MatchWeek current = generate(MatchWeek.class);
        final MatchWeek pending = generate(MatchWeek.class);

        when(this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(any(LocalDate.class)))
                .thenReturn(Optional.empty());
        when(this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(Status.PENDING))
                .thenReturn(Optional.empty());

        assertThrows(ConflictException.class, () -> this.matchWeekService.getCurrentMatchWeek());

        when(this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(any(LocalDate.class)))
                .thenReturn(Optional.of(current));

        assertEquals(current, this.matchWeekService.getCurrentMatchWeek());

        when(this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(Status.PENDING))
                .thenReturn(Optional.of(pending));

        assertEquals(pending, this.matchWeekService.getCurrentMatchWeek());

        verify(this.matchWeekRepository, times(3)).findFirstBySeasonStatusOrderByDateAsc(eq(Status.PENDING));
        verify(this.matchWeekRepository, times(2)).findFirstByDateLessThanEqualOrderByDateDesc(any(LocalDate.class));
    }

    /**
     * Tests MatchWeekService::getBySeasonId.
     */
    @Test
    void testGetBySeasonId() {
        final List<MatchWeek> matchWeeks = generateList(MatchWeek.class);
        when(this.matchWeekRepository.findBySeasonIdOrderByDate(any(Long.class))).thenReturn(matchWeeks);

        final List<MatchWeek> result = this.matchWeekService.getBySeasonId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(matchWeeks, result);
    }

}
