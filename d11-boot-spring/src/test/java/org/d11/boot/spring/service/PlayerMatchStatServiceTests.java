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
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByMatchId(null),
                             "PlayerMatchStatService::getByMatchId null matchId throws");
        assertEquals(matchIdProperty, nullMatchIdException.getParameter(),
                     "PlayerMatchStatService::getByMatchId property equals null matchId");

        final BadRequestException invalidMatchIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByMatchId(-1L),
                             "PlayerMatchStatService::getByMatchId invalid matchId throws");
        assertEquals(matchIdProperty, invalidMatchIdException.getParameter(),
                     "PlayerMatchStatService::getByMatchId property equals invalid matchId");

        // Success -----------------------------------------------------------------------------------------------------

        final long matchId = 1L;
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        when(this.playerMatchStatRepository.findByMatchIdOrderByPositionSortOrder(eq(matchId)))
                .thenReturn(playerMatchStats);

        final List<PlayerMatchStat> result = this.playerMatchStatService.getByMatchId(matchId);

        assertEquals(playerMatchStats, result, "PlayerMatchStatService::getByMatchId result equals");

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
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByD11MatchId(null),
                             "PlayerMatchStatService::getByD11MatchId null d11MatchId throws");
        assertEquals(d11MatchIdProperty, nullD11MatchIdException.getParameter(),
                     "PlayerMatchStatService::getByD11MatchId property equals null d11MatchId");

        final BadRequestException invalidD11MatchIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByD11MatchId(-1L),
                             "PlayerMatchStatService::getByD11MatchId invalid d11MatchId throws");
        assertEquals(d11MatchIdProperty, invalidD11MatchIdException.getParameter(),
                     "PlayerMatchStatService::getByD11MatchId property equals invalid d11MatchId");

        // Success -----------------------------------------------------------------------------------------------------

        final long d11MatchId = 1L;
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        when(this.playerMatchStatRepository.findByD11MatchIdOrderByPositionSortOrder(eq(d11MatchId)))
                .thenReturn(playerMatchStats);

        final List<PlayerMatchStat> result = this.playerMatchStatService.getByD11MatchId(d11MatchId);

        assertEquals(playerMatchStats, result, "PlayerMatchStatService::getByD11MatchId result equals");

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
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(null, 1L),
                             "PlayerMatchStatService::getByPlayerIdAndSeasonId null playerId throws");
        assertEquals(playerIdProperty, nullPlayerIdException.getParameter(),
                     "PlayerMatchStatService::getByPlayerIdAndSeasonId property equals null playerId");

        final BadRequestException invalidPlayerIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(-1L, 1L),
                             "PlayerMatchStatService::getByPlayerIdAndSeasonId invalid playerId throws");
        assertEquals(playerIdProperty, invalidPlayerIdException.getParameter(),
                     "PlayerMatchStatService::getByPlayerIdAndSeasonId property equals invalid playerId");

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(1L, null),
                             "PlayerMatchStatService::getByPlayerIdAndSeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerMatchStatService::getByPlayerIdAndSeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerMatchStatService.getByPlayerIdAndSeasonId(1L, -1L),
                             "PlayerMatchStatService::getByPlayerIdAndSeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "PlayerMatchStatService::getByPlayerIdAndSeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long playerId = 1L;
        final long seasonId = 1L;
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        when(this.playerMatchStatRepository
                     .findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(eq(playerId), eq(seasonId)))
                .thenReturn(playerMatchStats);

        final List<PlayerMatchStat> result = this.playerMatchStatService.getByPlayerIdAndSeasonId(playerId, seasonId);

        assertEquals(playerMatchStats, result, "getByPlayerIdAndSeasonId::getByPlayerIdAndSeasonId result equals");

        verify(this.playerMatchStatRepository, times(1))
                .findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(eq(playerId), eq(seasonId));
    }

}
