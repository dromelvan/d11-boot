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

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByMatchId(null),
                             "PlayerMatchStatService::getByMatchId null matchId throws");
        assertEquals(matchIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerMatchStatService::getByMatchId property equals null matchId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.playerMatchStatService.getByMatchId(-1L),
                             "PlayerMatchStatService::getByMatchId invalid matchId throws");
        assertEquals(matchIdProperty, invalidSeasonIdException.getParameter(),
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

}
