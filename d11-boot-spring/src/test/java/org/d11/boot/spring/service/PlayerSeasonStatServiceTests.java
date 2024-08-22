package org.d11.boot.spring.service;

import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Player season stat service tests.
 */
class PlayerSeasonStatServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked player season stat repository.
     */
    @Mock
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Player season stat service.
     */
    @InjectMocks
    private PlayerSeasonStatService playerSeasonStatService;

    /**
     * Tests PlayerSeasonStatService::getByPlayerId.
     */
    @Test
    void testGetByPlayerId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String playerIdProperty = "playerId";

        final BadRequestException nullPlayerIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByPlayerId(null),
                             "PlayerSeasonStatService::getByPlayerId null playerId throws");
        assertEquals(playerIdProperty, nullPlayerIdException.getParameter(),
                     "PlayerSeasonStatService::getByPlayerId property equals null playerId");

        final BadRequestException invalidPlayerIdException =
                assertThrows(BadRequestException.class, () -> this.playerSeasonStatService.getByPlayerId(-1L),
                             "PlayerSeasonStatService::getByPlayerId invalid playerId throws");
        assertEquals(playerIdProperty, invalidPlayerIdException.getParameter(),
                     "PlayerSeasonStatService::getByPlayerId property equals invalid playerId");

        // Success -----------------------------------------------------------------------------------------------------

        final long playerId = 1L;
        final List<PlayerSeasonStat> playerSeasonStats = generateList(PlayerSeasonStat.class);

        when(this.playerSeasonStatRepository.findByPlayerIdOrderBySeasonIdDesc(eq(playerId)))
                .thenReturn(playerSeasonStats);

        final List<PlayerSeasonStat> result = this.playerSeasonStatService.getByPlayerId(playerId);

        assertEquals(playerSeasonStats, result, "PlayerSeasonStatService::getByPlayerId result equals");

        verify(this.playerSeasonStatRepository, times(1)).findByPlayerIdOrderBySeasonIdDesc(eq(playerId));
    }

    /**
     * Tests PlayerSeasonStatService::getBySeasonId.
     */
    @Test
    void testGetBySeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getBySeasonId(null, 0),
                             "PlayerSeasonStatService::getBySeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getBySeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.playerSeasonStatService.getBySeasonId(-1L, 0),
                             "PlayerSeasonStatService::getBySeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getBySeasonId property equals invalid seasonId");

        final BadRequestException invalidPageException =
                assertThrows(BadRequestException.class, () -> this.playerSeasonStatService.getBySeasonId(1L, -1),
                             "PlayerSeasonStatService::getBySeasonId invalid page throws");
        assertEquals("page", invalidPageException.getParameter(),
                     "PlayerSeasonStatService::getBySeasonId property equals invalid page");

        // Success -----------------------------------------------------------------------------------------------------

        final long seasonId = 1L;
        final List<PlayerSeasonStat> playerSeasonStats = generateList(PlayerSeasonStat.class);

        when(this.playerSeasonStatRepository.findBySeasonId(eq(seasonId), any(Pageable.class)))
                .thenReturn(playerSeasonStats);

        final List<PlayerSeasonStat> result = this.playerSeasonStatService.getBySeasonId(seasonId, 0);

        assertEquals(playerSeasonStats, result, "PlayerSeasonStatService::getBySeasonId result equals");

        verify(this.playerSeasonStatRepository, times(1)).findBySeasonId(eq(seasonId), any(Pageable.class));
    }

    /**
     * Tests PlayerSeasonStatService::getByTeamIdAndSeasonId.
     */
    @Test
    void testGetByTeamIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String teamIdProperty = "teamId";

        final BadRequestException nullTeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(null, 1L),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId null teamId throws");
        assertEquals(teamIdProperty, nullTeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals null teamId");

        final BadRequestException invalidTeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(-1L, 1L),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId invalid teamId throws");
        assertEquals(teamIdProperty, invalidTeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals invalid teamId");

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, null),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, -1L),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long teamId = 1L;
        final long seasonId = 1L;
        final List<PlayerSeasonStat> playerSeasonStats = generateList(PlayerSeasonStat.class);

        when(this.playerSeasonStatRepository
                     .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(teamId), eq(seasonId)))
                .thenReturn(playerSeasonStats);

        final List<PlayerSeasonStat> result = this.playerSeasonStatService.getByTeamIdAndSeasonId(teamId, seasonId);

        assertEquals(playerSeasonStats, result, "PlayerSeasonStatService::getByTeamIdAndSeasonId result equals");

        verify(this.playerSeasonStatRepository, times(1))
                .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(teamId), eq(seasonId));
    }

    /**
     * Tests PlayerSeasonStatService::getByD11TeamIdAndSeasonId.
     */
    @Test
    void testGetByD11TeamIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String d11TeamIdProperty = "d11TeamId";

        final BadRequestException nullD11TeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByD11TeamIdAndSeasonId(null, 1L),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId null d11TeamId throws");
        assertEquals(d11TeamIdProperty, nullD11TeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals null d11TeamId");

        final BadRequestException invalidD11TeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByD11TeamIdAndSeasonId(-1L, 1L),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId invalid d11TeamId throws");
        assertEquals(d11TeamIdProperty, invalidD11TeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals invalid d11TeamId");

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, null),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, -1L),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long d11TeamId = 1L;
        final long seasonId = 1L;
        final List<PlayerSeasonStat> playerSeasonStats = generateList(PlayerSeasonStat.class);

        when(this.playerSeasonStatRepository
                     .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(d11TeamId), eq(seasonId)))
                .thenReturn(playerSeasonStats);

        final List<PlayerSeasonStat> result =
                this.playerSeasonStatService.getByD11TeamIdAndSeasonId(d11TeamId, seasonId);

        assertEquals(playerSeasonStats, result, "PlayerSeasonStatService::getByD11TeamIdAndSeasonId result equals");

        verify(this.playerSeasonStatRepository, times(1))
                .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(d11TeamId), eq(seasonId));
    }

}
