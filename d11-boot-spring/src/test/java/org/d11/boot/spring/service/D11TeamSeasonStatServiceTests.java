package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.repository.D11TeamSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

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
                assertThrows(BadRequestException.class, () -> this.d11TeamSeasonStatService.getBySeasonId(null),
                             "D11TeamSeasonStatService::getBySeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "D11TeamSeasonStatService::getBySeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11TeamSeasonStatService.getBySeasonId(-1L),
                             "D11TeamSeasonStatService::getBySeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "D11TeamSeasonStatService::getBySeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long seasonId = 1L;
        final List<D11TeamSeasonStat> d11TeamSeasonStats = generateList(D11TeamSeasonStat.class);

        when(this.d11TeamSeasonStatRepository.findBySeasonIdOrderByRanking(eq(seasonId)))
                .thenReturn(d11TeamSeasonStats);

        final List<D11TeamSeasonStat> result = this.d11TeamSeasonStatService.getBySeasonId(seasonId);

        assertEquals(d11TeamSeasonStats, result, "D11TeamSeasonStatService::getBySeasonId result equals");

        verify(this.d11TeamSeasonStatRepository, times(1))
                .findBySeasonIdOrderByRanking(eq(seasonId));
    }

}
