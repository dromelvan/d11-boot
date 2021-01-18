package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.api.service.PlayerMatchStatApiService;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerMatchStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.PlayerMatchStatRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Player match stat API tests.
 */
public class PlayerMatchStatApiTests extends AbstractRepositoryApiTests<PlayerMatchStat, PlayerMatchStatRepository, PlayerMatchStatApiService> {

    /**
     * Tests the findPlayerMatchStatById API operation.
     */
    @Test
    public void findPlayerMatchStatById() {
        for(final PlayerMatchStat playerMatchStat : getEntities()) {
            final PlayerMatchStatDTO result = getApiService().findPlayerMatchStatById(playerMatchStat.getId());
            final PlayerMatchStatDTO playerMatchStatDTO = map(playerMatchStat, PlayerMatchStatDTO.class);

            assertNotNull(result, "Player match stat by id should not be null.");
            assertEquals(playerMatchStatDTO, result, "Player match stat by id should equal PlayerMatchStat.");
        }

        assertNull(getApiService().findPlayerMatchStatById(-1L), "Player match stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findPlayerMatchStatByMatchId API operation.
     */
    @Test
    public void findPlayerMatchStatByMatchId() {
        for(final Match match : getRepository().findAll().stream().map(PlayerMatchStat::getMatch).collect(Collectors.toSet())) {
            final List<PlayerMatchStat> matchPlayerMatchStats = match.getPlayerMatchStats();
            matchPlayerMatchStats.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()));
            assertFalse(matchPlayerMatchStats.isEmpty(), "Match player match stats is empty. " +
                                                         "This means the match player match stat test data is not set up properly.");

            final List<PlayerMatchStatDTO> playerMatchStats = getApiService().findPlayerMatchStatByMatchId(match.getId());
            assertFalse(playerMatchStats.isEmpty(), "Match player match stats is empty.");

            assertEquals(map(matchPlayerMatchStats, PlayerMatchStatDTO.class), playerMatchStats,
                         "Player match stats by match id should equal match player match stats.");
        }
    }

    /**
     * Tests the findPlayerMatchStatByD11MatchId API operation.
     */
    @Test
    public void findPlayerMatchStatByD11MatchId() {
        for(final D11Match d11Match : getRepository().findAll().stream().map(PlayerMatchStat::getD11Match).collect(Collectors.toSet())) {
            final List<PlayerMatchStat> d11MatchPlayerMatchStats = d11Match.getPlayerMatchStats();
            d11MatchPlayerMatchStats.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getPosition().getSortOrder()));
            assertFalse(d11MatchPlayerMatchStats.isEmpty(), "D11 match player match stats is empty. " +
                                                            "This means the D11 match player match stat test data is not set up properly.");

            final List<PlayerMatchStatDTO> playerMatchStats = getApiService().findPlayerMatchStatByD11MatchId(d11Match.getId());
            assertFalse(playerMatchStats.isEmpty(), "D11 match player match stats is empty.");

            assertEquals(map(d11MatchPlayerMatchStats, PlayerMatchStatDTO.class), playerMatchStats,
                         "Player match stats by D11 match id should equal D11 match player match stats.");
        }
    }

    /**
     * Tests the findPlayerMatchStatByPlayerIdAndSeasonId API operation.
     */
    @Test
    public void findPlayerMatchStatByPlayerIdAndSeasonId() {
        final Map<Player, Map<Season, List<PlayerMatchStat>>> playerMatchStatMap = new HashMap<>();
        for(final PlayerMatchStat playerMatchStat : getEntities()) {
            final Map<Season, List<PlayerMatchStat>> seasonMap = playerMatchStatMap.computeIfAbsent(playerMatchStat.getPlayer(), player -> new HashMap<>());
            final Season season = playerMatchStat.getMatch().getMatchWeek().getPremierLeague().getSeason();
            final List<PlayerMatchStat> playerMatchStats = seasonMap.computeIfAbsent(season, s -> new ArrayList<>());
            playerMatchStats.add(playerMatchStat);
        }

        assertFalse(playerMatchStatMap.isEmpty(), "Player match stat map is empty. " +
                                                  "This means the player match stat test data is not set up properly.");

        for(final Map.Entry<Player, Map<Season, List<PlayerMatchStat>>> playerEntry : playerMatchStatMap.entrySet()) {
            final Player player = playerEntry.getKey();
            final Map<Season, List<PlayerMatchStat>> seasonMap = playerEntry.getValue();
            for(final Map.Entry<Season, List<PlayerMatchStat>> seasonEntry : seasonMap.entrySet()) {
                final Season season = seasonEntry.getKey();
                final List<PlayerMatchStat> playerSeasonPlayerMatchStats = seasonEntry.getValue();
                playerSeasonPlayerMatchStats.sort(Comparator.comparing(playerMatchStat -> playerMatchStat.getMatch().getDatetime()));
                assertFalse(playerSeasonPlayerMatchStats.isEmpty(), "Player season player match stats is empty. " +
                                                                    "This means the player season player match stat test data is not set up properly.");

                final List<PlayerMatchStatDTO> playerMatchStats = getApiService().findPlayerMatchStatByPlayerIdAndSeasonId(player.getId(), season.getId());
                assertFalse(playerMatchStats.isEmpty(), "Player and season player match stats is empty.");

                assertEquals(map(playerSeasonPlayerMatchStats, PlayerMatchStatDTO.class), playerMatchStats,
                             "Player match stats by player id and season id should equal player season player match stats.");
            }
        }
    }

}
