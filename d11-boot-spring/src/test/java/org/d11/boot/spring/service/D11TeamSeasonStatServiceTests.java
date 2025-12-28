package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.repository.D11TeamSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * D11 team season stat service tests.
 */
class D11TeamSeasonStatServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked D11 team season stat repository.
     */
    @Mock
    private D11TeamSeasonStatRepository d11TeamSeasonStatRepository;

    /**
     * D11 team season stat service.
     */
    @InjectMocks
    private D11TeamSeasonStatService d11TeamSeasonStatService;

    /**
     * Tests D11TeamSeasonStatService::getBySeasonId.
     */
    @Test
    void testGetBySeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11TeamSeasonStatService.getBySeasonId(null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11TeamSeasonStatService.getBySeasonId(-1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long seasonId = 1L;
        final List<D11TeamSeasonStat> d11TeamSeasonStats = generateList(D11TeamSeasonStat.class);

        when(this.d11TeamSeasonStatRepository.findBySeasonIdOrderByRanking(eq(seasonId)))
                .thenReturn(d11TeamSeasonStats);

        final List<D11TeamSeasonStat> result = this.d11TeamSeasonStatService.getBySeasonId(seasonId);

        assertEquals(d11TeamSeasonStats, result);

        verify(this.d11TeamSeasonStatRepository, times(1))
                .findBySeasonIdOrderByRanking(eq(seasonId));
    }

    /**
     * Tests D11TeamSeasonStatService::getByD11TeamIdAndSeasonId.
     */
    @Test
    void testGetByD11TeamIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String d11TeamIdProperty = "d11TeamId";

        final BadRequestException nullD11TeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.d11TeamSeasonStatService.getByD11TeamIdAndSeasonId(null, 1L));
        assertEquals(d11TeamIdProperty, nullD11TeamIdException.getParameter());

        final BadRequestException invalidD11TeamIdIdException =
                assertThrows(BadRequestException.class,
                             () -> this.d11TeamSeasonStatService.getByD11TeamIdAndSeasonId(-1L, 1L));
        assertEquals(d11TeamIdProperty, invalidD11TeamIdIdException.getParameter());

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.d11TeamSeasonStatService.getByD11TeamIdAndSeasonId(1L, null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.d11TeamSeasonStatService.getByD11TeamIdAndSeasonId(1L, -1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long d11TeamId = 1L;
        final long seasonId = 1L;
        final D11TeamSeasonStat d11TeamSeasonStat = generate(D11TeamSeasonStat.class);

        when(this.d11TeamSeasonStatRepository.findByD11TeamIdAndSeasonId(eq(d11TeamId), eq(seasonId)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                     () -> this.d11TeamSeasonStatService.getByD11TeamIdAndSeasonId(d11TeamId, seasonId));

        when(this.d11TeamSeasonStatRepository.findByD11TeamIdAndSeasonId(eq(d11TeamId), eq(seasonId)))
                .thenReturn(Optional.of(d11TeamSeasonStat));

        final D11TeamSeasonStat result = this.d11TeamSeasonStatService.getByD11TeamIdAndSeasonId(d11TeamId, seasonId);

        assertEquals(d11TeamSeasonStat, result);

        verify(this.d11TeamSeasonStatRepository, times(2)).findByD11TeamIdAndSeasonId(eq(d11TeamId), eq(seasonId));
    }

}
