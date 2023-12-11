package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TeamSeasonStat;
import org.d11.boot.spring.repository.TeamSeasonStatRepository;
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
 * Team season stat service tests.
 */
class TeamSeasonStatServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked team season stat repository.
     */
    @Mock
    private TeamSeasonStatRepository teamSeasonStatRepository;

    /**
     * Team season stat service.
     */
    @InjectMocks
    private TeamSeasonStatService teamSeasonStatService;

    /**
     * Tests TeamSeasonStatService::getBySeasonId.
     */
    @Test
    void testGetBySeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.teamSeasonStatService.getBySeasonId(null),
                             "TeamSeasonStatService::getBySeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "TeamSeasonStatService::getBySeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.teamSeasonStatService.getBySeasonId(-1L),
                             "TeamSeasonStatService::getBySeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "TeamSeasonStatService::getBySeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long seasonId = 1L;
        final List<TeamSeasonStat> teamSeasonStats = generateList(TeamSeasonStat.class);

        when(this.teamSeasonStatRepository.findBySeasonIdOrderByRanking(eq(seasonId))).thenReturn(teamSeasonStats);

        final List<TeamSeasonStat> result = this.teamSeasonStatService.getBySeasonId(seasonId);

        assertEquals(teamSeasonStats, result, "TeamSeasonStatService::getBySeasonId result equals");

        verify(this.teamSeasonStatRepository, times(1)).findBySeasonIdOrderByRanking(eq(seasonId));
    }

}
