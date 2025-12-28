package org.d11.boot.spring.service;

import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.repository.PlayerMatchStatRepository;
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
 * Player match stat service tests.
 */
class PlayerMatchStatServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked player match stat repository.
     */
    @Mock
    private PlayerMatchStatRepository playerMatchStatRepository;

    /**
     * Player match stat service.
     */
    @InjectMocks
    private PlayerMatchStatService playerMatchStatService;

    /**
     * Tests PlayerMatchStatService::getByMatchId.
     */
    @Test
    void testGetByMatchId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String matchIdProperty = "matchId";

        final BadRequestException nullMatchIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByMatchId(null));
        assertEquals(matchIdProperty, nullMatchIdException.getParameter());

        final BadRequestException invalidMatchIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByMatchId(-1L));
        assertEquals(matchIdProperty, invalidMatchIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long matchId = 1L;
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        when(this.playerMatchStatRepository.findByMatchIdOrderByPositionSortOrder(eq(matchId)))
                .thenReturn(playerMatchStats);

        final List<PlayerMatchStat> result = this.playerMatchStatService.getByMatchId(matchId);

        assertEquals(playerMatchStats, result);

        verify(this.playerMatchStatRepository, times(1)).findByMatchIdOrderByPositionSortOrder(eq(matchId));
    }

    /**
     * Tests PlayerMatchStatService::getByD11MatchId.
     */
    @Test
    void testGetByD11MatchId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String d11MatchIdProperty = "d11MatchId";

        final BadRequestException nullD11MatchIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByD11MatchId(null));
        assertEquals(d11MatchIdProperty, nullD11MatchIdException.getParameter());

        final BadRequestException invalidD11MatchIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByD11MatchId(-1L));
        assertEquals(d11MatchIdProperty, invalidD11MatchIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long d11MatchId = 1L;
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        when(this.playerMatchStatRepository.findByD11MatchIdOrderByPositionSortOrder(eq(d11MatchId)))
                .thenReturn(playerMatchStats);

        final List<PlayerMatchStat> result = this.playerMatchStatService.getByD11MatchId(d11MatchId);

        assertEquals(playerMatchStats, result);

        verify(this.playerMatchStatRepository, times(1)).findByD11MatchIdOrderByPositionSortOrder(eq(d11MatchId));
    }

    /**
     * Tests PlayerMatchStatService::getByPlayerIdAndSeasonId.
     */
    @Test
    void testGetByPlayerIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String playerIdProperty = "playerId";

        final BadRequestException nullPlayerIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(null, 1L));
        assertEquals(playerIdProperty, nullPlayerIdException.getParameter());

        final BadRequestException invalidPlayerIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(-1L, 1L));
        assertEquals(playerIdProperty, invalidPlayerIdException.getParameter());

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(1L, null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(1L, -1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long playerId = 1L;
        final long seasonId = 1L;
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        when(this.playerMatchStatRepository
                     .findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(eq(playerId), eq(seasonId)))
                .thenReturn(playerMatchStats);

        final List<PlayerMatchStat> result = this.playerMatchStatService.getByPlayerIdAndSeasonId(playerId, seasonId);

        assertEquals(playerMatchStats, result);

        verify(this.playerMatchStatRepository, times(1))
                .findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(eq(playerId), eq(seasonId));
    }

}
