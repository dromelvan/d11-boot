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

}
