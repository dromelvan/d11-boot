package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerMatchStatApi;
import org.d11.boot.api.v2.model.PlayerMatchStatDTO;
import org.d11.boot.api.v2.model.PlayerMatchStatsResponseBodyDTO;
import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.model.Season;
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

    /**
     * Tests PlayerMatchStatController::getPlayerMatchStatsByD11MatchId.
     */
    @Test
    void testGetPlayerMatchStatsByD11MatchId() {
        final PlayerMatchStatApi playerMatchStatApi = getApi(PlayerMatchStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerMatchStatApi.getPlayerMatchStatsByD11MatchId(null),
                     "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId d11MatchId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerMatchStatApi.getPlayerMatchStatsByD11MatchId(-1L),
                     "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId d11MatchId negative throws");

        final List<PlayerMatchStat> playerMatchStats = this.playerMatchStatRepository.findAll();
        playerMatchStats.sort(Comparator.comparing(PlayerMatchStat::getPosition));

        final Set<D11Match> d11Matches = playerMatchStats.stream()
                .map(PlayerMatchStat::getD11Match)
                .collect(Collectors.toSet());

        assertTrue(d11Matches.size() > 1,
                   "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId D11 matches size > 0");

        for (final D11Match d11Match : d11Matches) {
            final PlayerMatchStatsResponseBodyDTO response =
                    playerMatchStatApi.getPlayerMatchStatsByD11MatchId(d11Match.getId());
            assertNotNull(response,
                          "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId response not null");

            final List<PlayerMatchStat> expected = playerMatchStats.stream()
                    .filter(playerMatchStat -> playerMatchStat.getD11Match().equals(d11Match))
                    .toList();

            assertTrue(expected.size() > 1,
                       "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId expected size > 1");

            final List<PlayerMatchStatDTO> result = response.getPlayerMatchStats();

            assertNotNull(result, "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId not null");
            assertFalse(result.isEmpty(), "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId empty");
            assertEquals(map(expected, PlayerMatchStatDTO.class), result,
                         "PlayerMatchStatController::getPlayerMatchStatsByD11MatchId equals");
        }
    }

    /**
     * Tests PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId.
     */
    @Test
    void testGetPlayerMatchStatsByPlayerIdAndSeasonId() {
        final PlayerMatchStatApi playerMatchStatApi = getApi(PlayerMatchStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerMatchStatApi.getPlayerMatchStatsByPlayerIdAndSeasonId(null, 1L),
                     "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId playerId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerMatchStatApi.getPlayerMatchStatsByPlayerIdAndSeasonId(-1L, 1L),
                     "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId playerId negative throws");

        final List<PlayerMatchStat> playerMatchStats = this.playerMatchStatRepository.findAll();
        playerMatchStats.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getMatch().getDatetime()));

        final Set<Player> players = playerMatchStats.stream()
                .map(PlayerMatchStat::getPlayer)
                .collect(Collectors.toSet());
        final Set<Season> seasons = playerMatchStats.stream()
                .map(playerMatchStat -> playerMatchStat.getMatch().getMatchWeek().getSeason())
                .collect(Collectors.toSet());

        assertTrue(players.size() > 1,
                   "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId players size > 1");
        assertFalse(seasons.isEmpty(),
                   "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId seasons empty");

        for (final Player player : players) {
            for (final Season season : seasons) {
                final PlayerMatchStatsResponseBodyDTO response =
                        playerMatchStatApi.getPlayerMatchStatsByPlayerIdAndSeasonId(player.getId(), season.getId());
                assertNotNull(response,
                              "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId response not null");

                final List<PlayerMatchStat> expected = playerMatchStats.stream()
                        .filter(playerMatchStat -> playerMatchStat.getPlayer().equals(player)
                                                   && playerMatchStat.getMatch().getMatchWeek().getSeason()
                                                                     .equals(season))
                        .toList();

                assertTrue(expected.size() > 1,
                           "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId expected size > 1");

                final List<PlayerMatchStatDTO> result = response.getPlayerMatchStats();

                assertNotNull(result, "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId not null");
                assertFalse(result.isEmpty(),
                            "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId empty");
                assertEquals(map(expected, PlayerMatchStatDTO.class), result,
                             "PlayerMatchStatController::getPlayerMatchStatsByPlayerIdAndSeasonId equals");
            }
        }
    }

}
