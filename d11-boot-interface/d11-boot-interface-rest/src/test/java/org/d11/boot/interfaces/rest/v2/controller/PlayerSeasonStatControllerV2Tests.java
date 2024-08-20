package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerSeasonStatApi;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatsResponseBodyDTO;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player season stat controller tests.
 */
class PlayerSeasonStatControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Player season stat repository.
     */
    @Autowired
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId.
     */
    @Test
    void testGetD11TeamSeasonStatsByPlayerId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByPlayerId(null),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId playerId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByPlayerId(-1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId playerId negative throws");

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getId).reversed());

        final Set<Player> players = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getPlayer)
                .collect(Collectors.toSet());

        assertTrue(players.size() > 1, "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId players size > 0");

        for (final Player player : players) {
            final PlayerSeasonStatsResponseBodyDTO response =
                    playerSeasonStatApi.getPlayerSeasonStatsByPlayerId(player.getId());
            assertNotNull(response, "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId response not null");

            final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getPlayer().equals(player))
                    .toList();

            assertTrue(expected.size() > 1,
                       "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId expected size > 1");

            final List<PlayerSeasonStatDTO> result = response.getPlayerSeasonStats();

            assertNotNull(result, "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId not null ");
            assertFalse(result.isEmpty(), "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId empty");
            assertEquals(map(expected, PlayerSeasonStatDTO.class), result,
                         "PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId equals");
        }

    }

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId.
     */
    @Test
    void testGetD11TeamSeasonStatsBySeasonId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(null, 0),
                     "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId seasonId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(-1L, 0),
                     "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId seasonId negative throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(1L, null),
                     "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId page null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(1L, -1),
                     "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId page negative throws");

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getRanking));

        final Set<Season> seasons = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1, "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId seasons size > 0");

        for (final Season season : seasons) {
            final PlayerSeasonStatsResponseBodyDTO playerSeasonStatsBySeasonId =
                    playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(season.getId(), 0);
            assertNotNull(playerSeasonStatsBySeasonId,
                          "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId response not null");

            final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1,
                       "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId expected size > 1");

            final List<PlayerSeasonStatDTO> result = playerSeasonStatsBySeasonId.getPlayerSeasonStats();

            assertNotNull(result, "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId not null ");
            assertFalse(result.isEmpty(), "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId empty");
            assertEquals(map(expected, PlayerSeasonStatDTO.class), result,
                         "PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId equals");
        }
    }

}
