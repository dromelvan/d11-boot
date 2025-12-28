package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TeamSeasonStat;
import org.d11.boot.spring.repository.TeamSeasonStatRepository;
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
                assertThrows(BadRequestException.class, () -> this.teamSeasonStatService.getBySeasonId(null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.teamSeasonStatService.getBySeasonId(-1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long seasonId = 1L;
        final List<TeamSeasonStat> teamSeasonStats = generateList(TeamSeasonStat.class);

        when(this.teamSeasonStatRepository.findBySeasonIdOrderByRanking(eq(seasonId))).thenReturn(teamSeasonStats);

        final List<TeamSeasonStat> result = this.teamSeasonStatService.getBySeasonId(seasonId);

        assertEquals(teamSeasonStats, result);

        verify(this.teamSeasonStatRepository, times(1)).findBySeasonIdOrderByRanking(eq(seasonId));
    }

    /**
     * Tests TeamSeasonStatService::getByTeamIdAndSeasonId.
     */
    @Test
    void testGetByTeamIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String teamIdProperty = "teamId";

        final BadRequestException nullTeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.teamSeasonStatService.getByTeamIdAndSeasonId(null, 1L));
        assertEquals(teamIdProperty, nullTeamIdException.getParameter());

        final BadRequestException invalidTeamIdIdException =
                assertThrows(BadRequestException.class,
                             () -> this.teamSeasonStatService.getByTeamIdAndSeasonId(-1L, 1L));
        assertEquals(teamIdProperty, invalidTeamIdIdException.getParameter());

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.teamSeasonStatService.getByTeamIdAndSeasonId(1L, null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.teamSeasonStatService.getByTeamIdAndSeasonId(1L, -1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long teamId = 1L;
        final long seasonId = 1L;
        final TeamSeasonStat teamSeasonStat = generate(TeamSeasonStat.class);

        when(this.teamSeasonStatRepository.findByTeamIdAndSeasonId(eq(teamId), eq(seasonId)))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                     () -> this.teamSeasonStatService.getByTeamIdAndSeasonId(teamId, seasonId));

        when(this.teamSeasonStatRepository.findByTeamIdAndSeasonId(eq(teamId), eq(seasonId)))
                .thenReturn(Optional.of(teamSeasonStat));

        final TeamSeasonStat result = this.teamSeasonStatService.getByTeamIdAndSeasonId(teamId, seasonId);

        assertEquals(teamSeasonStat, result);

        verify(this.teamSeasonStatRepository, times(2)).findByTeamIdAndSeasonId(eq(teamId), eq(seasonId));
    }

}
