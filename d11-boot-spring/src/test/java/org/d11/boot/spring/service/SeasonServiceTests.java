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
            assertNotNull(result, "SeasonService::getById not null");
            assertEquals(season, result, "SeasonService::getById");
        });

        assertThrows(NotFoundException.class,
                     () -> this.seasonService.getById(-1L),
                     "SeasonService::getById not found");
    }

    /**
     * Tests SeasonService::getSeasons.
     */
    @Test
    void testGetSeasons() {
        final List<Season> seasons = generateList(Season.class);
        when(this.seasonRepository.findByOrderByDateDesc()).thenReturn(seasons);

        final List<Season> result = this.seasonService.getSeasons();

        assertNotNull(result, "SeasonService::getSeasons not null");
        assertTrue(result.size() >= 2, "SeasonService::getSeasons size");
        assertEquals(seasons, result, "SeasonService::getSeasons");
    }

    /**
     * Tests SeasonService::getCurrentSeason.
     */
    @Test
    void testGetCurrentSeason() {
        final Season currentSeason = generate(Season.class);
        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(currentSeason));

        final Season result = this.seasonService.getCurrentSeason();
        assertNotNull(result, "SeasonService::getCurrentSeason not null");
        assertEquals(currentSeason, result, "SeasonService::getCurrentSeason");
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

        assertNotNull(result, "SeasonService::updateSeason not null");
        assertEquals(updatedSeason.getId(), result.getId(), "SeasonService::updateSeason id equals");
        assertEquals(updatedSeason.getName(), result.getName(), "SeasonService::updateSeason name equals");
        assertEquals(updatedSeason.getShortName(), result.getShortName(),
                     "SeasonService::updateSeason shortName equals");
        assertEquals(updatedSeason.getD11TeamBudget(), result.getD11TeamBudget(),
                     "SeasonService::updateSeason d11TeamBudget equals");
        assertEquals(updatedSeason.getD11TeamMaxTransfers(), result.getD11TeamMaxTransfers(),
                     "SeasonService::updateSeason d11TeamMaxTransfers equals");
        assertEquals(updatedSeason.getStatus(), result.getStatus(), "SeasonService::updateSeason status equals");
        assertEquals(updatedSeason.getDate(), result.getDate(), "SeasonService::updateSeason date equals");
        assertEquals(updatedSeason.isLegacy(), result.isLegacy(), "SeasonService::updateSeason legacy equals");

        verify(this.seasonRepository, times(1)).findById(eq(season.getId()));
        verify(this.seasonRepository, times(1)).save(eq(season));
    }

}
