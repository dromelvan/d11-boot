package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerMatchStatApi;
import org.d11.boot.api.v2.model.PlayerMatchStatDTO;
import org.d11.boot.api.v2.model.PlayerMatchStatsResponseBodyDTO;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.repository.PlayerMatchStatRepository;
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
 * Player match stat controller tests.
 */
class PlayerMatchStatControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Player match stat repository.
     */
    @Autowired
    private PlayerMatchStatRepository playerMatchStatRepository;

    /**
     * Tests PlayerMatchStatController::getPlayerMatchStatsByMatchId.
     */
    @Test
    void testGetPlayerMatchStatsByMatchId() {
        final PlayerMatchStatApi playerMatchStatApi = getApi(PlayerMatchStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerMatchStatApi.getPlayerMatchStatsByMatchId(null),
                     "PlayerMatchStatController::getPlayerMatchStatsByMatchId matchId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerMatchStatApi.getPlayerMatchStatsByMatchId(-1L),
                     "PlayerMatchStatController::getPlayerMatchStatsByMatchId matchId negative throws");

        final List<PlayerMatchStat> playerMatchStats = this.playerMatchStatRepository.findAll();
        playerMatchStats.sort(Comparator.comparing(PlayerMatchStat::getPosition));

        final Set<Match> matches = playerMatchStats.stream()
                .map(PlayerMatchStat::getMatch)
                .collect(Collectors.toSet());

        assertTrue(matches.size() > 1, "PlayerMatchStatController::getPlayerMatchStatsByMatchId matches size > 0");

        for (final Match match : matches) {
            final PlayerMatchStatsResponseBodyDTO response =
                    playerMatchStatApi.getPlayerMatchStatsByMatchId(match.getId());
            assertNotNull(response,
                          "PlayerMatchStatController::getPlayerMatchStatsByMatchId response not null");

            final List<PlayerMatchStat> expected = playerMatchStats.stream()
                    .filter(playerMatchStat -> playerMatchStat.getMatch().equals(match))
                    .toList();

            assertTrue(expected.size() > 1,
                       "PlayerMatchStatController::getPlayerMatchStatsByMatchId expected size > 1");

            final List<PlayerMatchStatDTO> result = response.getPlayerMatchStats();

            assertNotNull(result, "PlayerMatchStatController::getPlayerMatchStatsByMatchId not null");
            assertFalse(result.isEmpty(), "PlayerMatchStatController::getPlayerMatchStatsByMatchId empty");
            assertEquals(map(expected, PlayerMatchStatDTO.class), result,
                         "PlayerMatchStatController::getPlayerMatchStatsByMatchId equals");
        }
    }

}
