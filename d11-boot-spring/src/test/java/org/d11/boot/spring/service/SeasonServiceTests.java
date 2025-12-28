package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Season service tests.
 */
class SeasonServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked season repository.
     */
    @Mock
    private SeasonRepository seasonRepository;

    /**
     * Season service.
     */
    @InjectMocks
    private SeasonService seasonService;

    /**
     * Tests SeasonService::getById.
     */
    @Test
    void testGetById() {
        final List<Season> seasons = generateList(Season.class);
        when(this.seasonRepository.findById(anyLong())).thenReturn(Optional.empty());
        seasons.forEach(season -> when(this.seasonRepository.findById(season.getId())).thenReturn(Optional.of(season)));

        seasons.forEach(season -> {
            final Season result = this.seasonService.getById(season.getId());
            assertNotNull(result);
            assertEquals(season, result);
        });

        assertThrows(NotFoundException.class, () -> this.seasonService.getById(-1L));
    }

    /**
     * Tests SeasonService::getSeasons.
     */
    @Test
    void testGetSeasons() {
        final List<Season> seasons = generateList(Season.class);
        when(this.seasonRepository.findByOrderByDateDesc()).thenReturn(seasons);

        final List<Season> result = this.seasonService.getSeasons();

        assertNotNull(result);
        assertTrue(result.size() >= 2);
        assertEquals(seasons, result);
    }

    /**
     * Tests SeasonService::getCurrentSeason.
     */
    @Test
    void testGetCurrentSeason() {
        final Season currentSeason = generate(Season.class);
        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(currentSeason));

        final Season result = this.seasonService.getCurrentSeason();
        assertNotNull(result);
        assertEquals(currentSeason, result);
    }

    /**
     * Tests SeasonService::updateSeason.
     */
    @Test
    void testUpdateSeason() {
        final Season season = generate(Season.class);
        when(this.seasonRepository.findById(eq(season.getId()))).thenReturn(Optional.of(season));
        when(this.seasonRepository.save(any(Season.class))).then(AdditionalAnswers.returnsFirstArg());

        final Season updatedSeason = generate(Season.class);
        updatedSeason.setId(season.getId());

        final Season result = this.seasonService.updateSeason(updatedSeason);

        assertNotNull(result);
        assertEquals(updatedSeason.getId(), result.getId());
        assertEquals(updatedSeason.getName(), result.getName());
        assertEquals(updatedSeason.getShortName(), result.getShortName());
        assertEquals(updatedSeason.getD11TeamBudget(), result.getD11TeamBudget());
        assertEquals(updatedSeason.getD11TeamMaxTransfers(), result.getD11TeamMaxTransfers());
        assertEquals(updatedSeason.getStatus(), result.getStatus());
        assertEquals(updatedSeason.getDate(), result.getDate());
        assertEquals(updatedSeason.isLegacy(), result.isLegacy());

        verify(this.seasonRepository, times(1)).findById(eq(season.getId()));
        verify(this.seasonRepository, times(1)).save(eq(season));
    }

}
