package org.d11.boot.application.api;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.api.model.TeamPlayerSeasonStatsDTO;
import org.d11.boot.api.service.PlayerSeasonStatApiService;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player season stat API tests.
 */
public class PlayerSeasonStatApiTests extends AbstractRepositoryApiTests<PlayerSeasonStat, PlayerSeasonStatRepository, PlayerSeasonStatApiService> {

    /**
     * Tests the findPlayerSeasonStatById API operation.
     */
    @Test
    public void findPlayerSeasonStatById() {
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final PlayerSeasonStatDTO result = getApiService().findPlayerSeasonStatById(playerSeasonStat.getId());
            final PlayerSeasonStatDTO playerSeasonStatDTO = map(playerSeasonStat, PlayerSeasonStatDTO.class);

            assertNotNull(result, "Player season stat by id should not be null.");
            assertEquals(playerSeasonStatDTO, result, "Player season stat by id should equal PlayerSeasonStat.");
        }

        assertNull(getApiService().findPlayerSeasonStatById(-1L), "Player season stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findPlayerSeasonStatByPlayerId API operation.
     */
    @Test
    public void findPlayerSeasonStatByPlayerId() {
        final Map<Player, List<PlayerSeasonStat>> playerMap = new HashMap<>();
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final List<PlayerSeasonStat> playerSeasonStats = playerMap.computeIfAbsent(playerSeasonStat.getPlayer(), p -> new ArrayList<>());
            playerSeasonStats.add(playerSeasonStat);
        }

        for(final Map.Entry<Player, List<PlayerSeasonStat>> entry : playerMap.entrySet()) {
            final Player player = entry.getKey();
            final List<PlayerSeasonStat> playerSeasonStats = entry.getValue();

            playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getSeason,
                    (season1, season2) -> (int) (season2.getId() - season1.getId())));

            final List<PlayerSeasonStatDTO> result = getApiService().findPlayerSeasonStatByPlayerId(player.getId());

            assertNotNull(result, "Player season stat by player id should not be null.");
            assertEquals(map(playerSeasonStats, PlayerSeasonStatDTO.class), result,
                    "Player season stats by player id should equal player season stat.");
        }

        assertTrue(getApiService().findPlayerSeasonStatByPlayerId(-1L).isEmpty(),
                "Player season stat by player id not found should be empty.");
    }

    /**
     * Tests the findPlayerSeasonStatBySeasonId API operation.
     */
    @Test
    public void findPlayerSeasonStatBySeasonId() {
        final Map<Season, List<PlayerSeasonStat>> seasonMap = new HashMap<>();
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final List<PlayerSeasonStat> playerSeasonStats = seasonMap.computeIfAbsent(playerSeasonStat.getSeason(), s -> new ArrayList<>());
            playerSeasonStats.add(playerSeasonStat);
        }

        for(final Map.Entry<Season, List<PlayerSeasonStat>> entry : seasonMap.entrySet()) {
            final Season season = entry.getKey();
            final List<PlayerSeasonStat> playerSeasonStats = entry.getValue();

            playerSeasonStats.sort(Comparator.comparingInt(PlayerSeasonStat::getRanking));

            final List<PlayerSeasonStatDTO> result = getApiService().findPlayerSeasonStatBySeasonId(season.getId(), 0);

            assertNotNull(result, "Player season stat by season id should not be null.");
            assertEquals(map(playerSeasonStats, PlayerSeasonStatDTO.class), result,
                    "Player season stats by season id should equal player season stat.");
            assertTrue(getApiService().findPlayerSeasonStatBySeasonId(season.getId(), 1).isEmpty(),
                    "Player season stat by season id and too high page number should be empty.");
        }
    }

    /**
     * Tests the findPlayerSeasonStatByPlayerIdAndSeasonId API operation.
     */
    @Test
    public void findPlayerSeasonStatByPlayerIdAndSeasonId() {
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final PlayerSeasonStatDTO result = getApiService().findPlayerSeasonStatByPlayerIdAndSeasonId(
                    playerSeasonStat.getPlayer().getId(),
                    playerSeasonStat.getSeason().getId()
            );
            assertNotNull(result, "Player season stat by player id and season id should not be null.");
            assertEquals(map(playerSeasonStat, PlayerSeasonStatDTO.class), result,
                    "Player season stat by player id and season id should equal player season stat.");
        }

        assertNull(getApiService().findPlayerSeasonStatByPlayerIdAndSeasonId(-1L, -1L),
                "Player season stat by player id and season id not found should return null.");
    }

    /**
     * Tests the findPlayerSeasonStatByTeamIdAndSeasonId API operation.
     */
    @Test
    public void findPlayerSeasonStatByTeamIdAndSeasonId() {
        final Map<Team, Map<Season, List<PlayerSeasonStat>>> playerSeasonStatMap = new HashMap<>();
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final Map<Season, List<PlayerSeasonStat>> seasonMap = playerSeasonStatMap.computeIfAbsent(playerSeasonStat.getTeam(), t -> new HashMap<>());
            final List<PlayerSeasonStat> playerSeasonStats = seasonMap.computeIfAbsent(playerSeasonStat.getSeason(), s -> new ArrayList<>());
            playerSeasonStats.add(playerSeasonStat);
        }

        final Comparator<PlayerSeasonStat> comparator = Comparator.comparing(PlayerSeasonStat::getPosition)
                .thenComparing(Comparator.comparing(PlayerSeasonStat::getFormPoints).reversed());

        for(final Map.Entry<Team, Map<Season, List<PlayerSeasonStat>>> teamEntry : playerSeasonStatMap.entrySet()) {
            final Team team = teamEntry.getKey();
            for(final Map.Entry<Season, List<PlayerSeasonStat>> seasonEntry : teamEntry.getValue().entrySet()) {
                final Season season = seasonEntry.getKey();
                final List<PlayerSeasonStat> playerSeasonStats = seasonEntry.getValue();
                playerSeasonStats.sort(comparator);

                final List<PlayerSeasonStatDTO> result = getApiService().findPlayerSeasonStatByTeamIdAndSeasonId(team.getId(), season.getId());

                assertNotNull(result, "Player season stats by team id and season id should not be null.");
                assertFalse(result.isEmpty(), "Player season stats by team id and season id should not be empty.");

                assertEquals(map(playerSeasonStats, PlayerSeasonStatDTO.class), result,
                        "Player season stats by team id and season id should equal player season stats.");
            }
        }
    }

    /**
     * Tests the findPlayerSeasonStatByD11TeamIdAndSeasonId API operation.
     */
    @Test
    public void findPlayerSeasonStatByD11TeamIdAndSeasonId() {
        final Map<D11Team, Map<Season, List<PlayerSeasonStat>>> playerSeasonStatMap = new HashMap<>();
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final Map<Season, List<PlayerSeasonStat>> seasonMap = playerSeasonStatMap.computeIfAbsent(playerSeasonStat.getD11Team(), d -> new HashMap<>());
            final List<PlayerSeasonStat> playerSeasonStats = seasonMap.computeIfAbsent(playerSeasonStat.getSeason(), s -> new ArrayList<>());
            playerSeasonStats.add(playerSeasonStat);
        }

        final Comparator<PlayerSeasonStat> comparator = Comparator.comparing(PlayerSeasonStat::getPosition)
                .thenComparing(Comparator.comparing(PlayerSeasonStat::getFormPoints).reversed());

        for(final Map.Entry<D11Team, Map<Season, List<PlayerSeasonStat>>> d11TeamEntry : playerSeasonStatMap.entrySet()) {
            final D11Team d11Team = d11TeamEntry.getKey();
            for(final Map.Entry<Season, List<PlayerSeasonStat>> seasonEntry : d11TeamEntry.getValue().entrySet()) {
                final Season season = seasonEntry.getKey();
                final List<PlayerSeasonStat> playerSeasonStats = seasonEntry.getValue();
                playerSeasonStats.sort(comparator);

                final List<PlayerSeasonStatDTO> result = getApiService().findPlayerSeasonStatByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());

                assertNotNull(result, "Player season stats by D11 team id and season id should not be null.");
                assertFalse(result.isEmpty(), "Player season stats by D11 team id and season id should not be empty.");

                assertEquals(map(playerSeasonStats, PlayerSeasonStatDTO.class), result,
                        "Player season stats by D11 team id and season id should equal player season stats.");
            }
        }
    }

    /**
     * Tests the findAvailablePlayerSeasonStatBySeasonId API operation.
     */
    @Test
    public void findAvailablePlayerSeasonStatBySeasonId() {
        for(final PlayerSeasonStat playerSeasonStat : getEntities()) {
            final List<TeamPlayerSeasonStatsDTO> teamPlayerSeasonStats = getApiService().findAvailablePlayerSeasonStatBySeasonId(
                    playerSeasonStat.getSeason().getId()
            );
            assertNotNull(teamPlayerSeasonStats, "Team player season stats by season id should not be null.");
            assertFalse(teamPlayerSeasonStats.isEmpty(), "Team player season stats by season id should not be empty.");
        }

    }

}
