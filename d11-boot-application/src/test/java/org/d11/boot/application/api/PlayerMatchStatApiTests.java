package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.api.service.PlayerMatchStatApiService;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.model.Lineup;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerMatchStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.PlayerMatchStatRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
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
     * Tests the findActivePlayerMatchStatByMatchIdAndTeamId API operation.
     */
    @Test
    public void findActivePlayerMatchStatByMatchIdAndTeamId() {
        for(final Match match : getRepository().findAll().stream().map(PlayerMatchStat::getMatch).collect(Collectors.toSet())) {
            final List<PlayerMatchStat> matchPlayerMatchStats = match.getPlayerMatchStats();
            final List<PlayerMatchStat> homeTeamPlayerMatchStats = matchPlayerMatchStats
                    .stream()
                    .filter(playerMatchStat ->
                            playerMatchStat.getTeam().equals(match.getHomeTeam()) && !playerMatchStat.getLineup().equals(Lineup.DID_NOT_PARTICIPATE))
                    .collect(Collectors.toList());

            final List<PlayerMatchStat> awayTeamPlayerMatchStats = matchPlayerMatchStats
                    .stream()
                    .filter(playerMatchStat ->
                            playerMatchStat.getTeam().equals(match.getAwayTeam()) && !playerMatchStat.getLineup().equals(Lineup.DID_NOT_PARTICIPATE))
                    .collect(Collectors.toList());

            final List<PlayerMatchStatDTO> homeTeamPlayerMatchStatDTOs
                    = getApiService().findActivePlayerMatchStatByMatchIdAndTeamId(match.getId(), match.getHomeTeam().getId());
            assertFalse(homeTeamPlayerMatchStatDTOs.isEmpty(), "Home team player match stats is empty.");
            assertEquals(map(homeTeamPlayerMatchStats, PlayerMatchStatDTO.class), homeTeamPlayerMatchStatDTOs,
                    "Active player match stats by match id and team id should equal active home team player match stats.");

            final List<PlayerMatchStatDTO> awayTeamPlayerMatchStatDTOs
                    = getApiService().findActivePlayerMatchStatByMatchIdAndTeamId(match.getId(), match.getAwayTeam().getId());
            assertFalse(awayTeamPlayerMatchStatDTOs.isEmpty(), "Away team player match stats is empty.");
            assertEquals(map(awayTeamPlayerMatchStats, PlayerMatchStatDTO.class), awayTeamPlayerMatchStatDTOs,
                    "Active player match stats by match id and team id should equal active away team player match stats.");
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
     * Tests the findActivePlayerMatchStatByMatchIdAndTeamId API operation.
     */
    @Test
    public void findActivePlayerMatchStatByD11MatchIdAndD11TeamId() {
        for(final D11Match d11Match : getRepository().findAll().stream().map(PlayerMatchStat::getD11Match).collect(Collectors.toSet())) {
            final List<PlayerMatchStat> d11MatchPlayerMatchStats = d11Match.getPlayerMatchStats();
            final List<PlayerMatchStat> homeD11TeamPlayerMatchStats = d11MatchPlayerMatchStats.stream()
                    .filter(playerMatchStat -> playerMatchStat.getD11Team().equals(d11Match.getHomeD11Team()))
                    .collect(Collectors.toList());

            final List<PlayerMatchStat> awayTeamPlayerMatchStats = d11MatchPlayerMatchStats.stream()
                    .filter(playerMatchStat -> playerMatchStat.getD11Team().equals(d11Match.getAwayD11Team()))
                    .collect(Collectors.toList());

            final List<PlayerMatchStatDTO> homeD11TeamPlayerMatchStatDTOs
                    = getApiService().findPlayerMatchStatByD11MatchIdAndD11TeamId(d11Match.getId(), d11Match.getHomeD11Team().getId());
            assertFalse(homeD11TeamPlayerMatchStatDTOs.isEmpty(), "Home D11 team player match stats is empty.");
            assertEquals(map(homeD11TeamPlayerMatchStats, PlayerMatchStatDTO.class), homeD11TeamPlayerMatchStatDTOs,
                    "Player match stats by D11 match id and D11 team id should equal home D11 team player match stats.");

            final List<PlayerMatchStatDTO> awayD11TeamPlayerMatchStatDTOs
                    = getApiService().findPlayerMatchStatByD11MatchIdAndD11TeamId(d11Match.getId(), d11Match.getAwayD11Team().getId());
            assertFalse(awayD11TeamPlayerMatchStatDTOs.isEmpty(), "Away D11 team player match stats is empty.");
            assertEquals(map(awayTeamPlayerMatchStats, PlayerMatchStatDTO.class), awayD11TeamPlayerMatchStatDTOs,
                    "Player match stats by D11 match id and D11 team id should equal away D11 team player match stats.");
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
            final Season season = playerMatchStat.getMatch().getMatchWeek().getSeason();
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

    /**
     * Tests the findTop5PlayerMatchStatByMatchWeek API operation.
     */
    @Test
    public void findTop5PlayerMatchStatByMatchWeek() {
        final Map<MatchWeek, List<PlayerMatchStat>> playerMatchStatMap = new HashMap<>();
        for(final PlayerMatchStat playerMatchStat : getEntities()) {
            if(playerMatchStat.getLineup() != Lineup.DID_NOT_PARTICIPATE) {
                final List<PlayerMatchStat> matchWeekList =
                        playerMatchStatMap.computeIfAbsent(playerMatchStat.getMatch().getMatchWeek(), matchWeek -> new ArrayList<>());
                matchWeekList.add(playerMatchStat);
            }
        }
        for(final Map.Entry<MatchWeek, List<PlayerMatchStat>> entry : playerMatchStatMap.entrySet()) {
            final List<PlayerMatchStat> matchWeekPlayerMatchStats = entry.getValue();
            Collections.sort(matchWeekPlayerMatchStats);

            final List<PlayerMatchStatDTO> playerMatchStats = getApiService().findTop5PlayerMatchStatByMatchWeek(entry.getKey().getId());

            assertEquals(matchWeekPlayerMatchStats.size(), playerMatchStats.size(),
                    "Top 5 match week player match stats and player match stats sizes are not equal. " +
                    "This means the top 5 match week player match stat test data is not set up properly.");

            assertEquals(map(matchWeekPlayerMatchStats, PlayerMatchStatDTO.class), playerMatchStats,
                    "Bottom 5 player match stats by match week should equal match week player match stats.");
        }
    }

    /**
     * Tests the findBottom5PlayerMatchStatByMatchWeek API operation.
     */
    @Test
    public void findBottom5PlayerMatchStatByMatchWeek() {
        final Map<MatchWeek, List<PlayerMatchStat>> playerMatchStatMap = new HashMap<>();
        for(final PlayerMatchStat playerMatchStat : getEntities()) {
            if(playerMatchStat.getLineup() != Lineup.DID_NOT_PARTICIPATE) {
                final List<PlayerMatchStat> matchWeekList =
                        playerMatchStatMap.computeIfAbsent(playerMatchStat.getMatch().getMatchWeek(), matchWeek -> new ArrayList<>());
                matchWeekList.add(playerMatchStat);
            }
        }
        for(final Map.Entry<MatchWeek, List<PlayerMatchStat>> entry : playerMatchStatMap.entrySet()) {
            final List<PlayerMatchStat> matchWeekPlayerMatchStats = entry.getValue();
            Collections.sort(matchWeekPlayerMatchStats);
            Collections.reverse(matchWeekPlayerMatchStats);

            final List<PlayerMatchStatDTO> playerMatchStats = getApiService().findBottom5PlayerMatchStatByMatchWeek(entry.getKey().getId());

            assertEquals(matchWeekPlayerMatchStats.size(), playerMatchStats.size(),
                    "Bottom 5 match week player match stats and player match stats sizes are not equal. " +
                    "This means the bottom 5 match week player match stat test data is not set up properly.");

            assertEquals(map(matchWeekPlayerMatchStats, PlayerMatchStatDTO.class), playerMatchStats,
                    "Top 5 player match stats by match week should equal match week player match stats.");
        }
    }

}
