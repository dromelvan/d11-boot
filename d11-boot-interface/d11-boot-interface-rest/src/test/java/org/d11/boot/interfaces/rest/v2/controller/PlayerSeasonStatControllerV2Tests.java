package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerSeasonStatApi;
import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputDTO;
import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputRequestBodyDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatResponseBodyDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatsResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdatePlayerSeasonStatInputDTO;
import org.d11.boot.api.v2.model.UpdatePlayerSeasonStatInputRequestBodyDTO;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.spring.repository.PositionRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TeamRepository;
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
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
class PlayerSeasonStatControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Player season stat repository.
     */
    @Autowired
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Player repository.
     */
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Team repository.
     */
    @Autowired
    private TeamRepository teamRepository;

    /**
     * D11 team repository.
     */
    @Autowired
    private D11TeamRepository d11TeamRepository;

    /**
     * Poseidon repository.
     */
    @Autowired
    private PositionRepository positionRepository;

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsByPlayerId.
     */
    @Test
    void testGetD11TeamSeasonStatsByPlayerId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class, () -> playerSeasonStatApi.getPlayerSeasonStatsByPlayerId(null));

        assertThrows(FeignException.BadRequest.class, () -> playerSeasonStatApi.getPlayerSeasonStatsByPlayerId(-1L));

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getId).reversed());

        final Set<Player> players = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getPlayer)
                .collect(Collectors.toSet());

        assertTrue(players.size() > 1);

        for (final Player player : players) {
            final PlayerSeasonStatsResponseBodyDTO response =
                    playerSeasonStatApi.getPlayerSeasonStatsByPlayerId(player.getId());
            assertNotNull(response);

            final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getPlayer().equals(player))
                    .toList();

            assertTrue(expected.size() > 1);

            final List<PlayerSeasonStatDTO> result = response.getPlayerSeasonStats();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(map(expected, PlayerSeasonStatDTO.class), result);
        }

    }

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsBySeasonId.
     */
    @Test
    void testGetPlayerSeasonStatsBySeasonId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(null, 0));

        assertThrows(FeignException.BadRequest.class, () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(-1L, 0));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(1L, null));

        assertThrows(FeignException.BadRequest.class, () -> playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(1L, -1));

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getRanking));

        final Set<Season> seasons = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1);

        for (final Season season : seasons) {
            final PlayerSeasonStatsResponseBodyDTO playerSeasonStatsBySeasonId =
                    playerSeasonStatApi.getPlayerSeasonStatsBySeasonId(season.getId(), 0);
            assertNotNull(playerSeasonStatsBySeasonId);

            final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1);

            final List<PlayerSeasonStatDTO> result = playerSeasonStatsBySeasonId.getPlayerSeasonStats();

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(map(expected, PlayerSeasonStatDTO.class), result);
        }
    }

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId.
     */
    @Test
    void testGetPlayerSeasonStatsByTeamIdAndSeasonId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(null, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(-1L, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(1L, (Long) null));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(1L, -1L));

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getPosition)
                                         .thenComparing(PlayerSeasonStat::getRanking));

        final Set<Team> teams = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getTeam)
                .collect(Collectors.toSet());

        final Set<Season> seasons = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertFalse(teams.isEmpty());
        assertFalse(seasons.isEmpty());

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final PlayerSeasonStatsResponseBodyDTO response =
                        playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(team.getId(), season.getId());
                assertNotNull(response);

                final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getTeam().equals(team)
                                                && playerSeasonStat.getSeason().equals(season))
                    .toList();

                assertFalse(expected.isEmpty());

                final List<PlayerSeasonStatDTO> result = response.getPlayerSeasonStats();

                assertNotNull(result);
                assertFalse(result.isEmpty());
                assertEquals(map(expected, PlayerSeasonStatDTO.class), result);
            }
        }
    }

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId.
     */
    @Test
    @SuppressWarnings("checkstyle:LineLength")
    void testGetPlayerSeasonStatsByD11TeamIdAndSeasonId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(null, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(-1L, 1L));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(1L, (Long) null));

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(1L, -1L));

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getPosition)
                                         .thenComparing(PlayerSeasonStat::getRanking));

        final Set<D11Team> d11Teams = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getD11Team)
                .collect(Collectors.toSet());

        final Set<Season> seasons = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertFalse(d11Teams.isEmpty());
        assertFalse(seasons.isEmpty());

        for (final D11Team d11Team : d11Teams) {
            for (final Season season : seasons) {
                final PlayerSeasonStatsResponseBodyDTO response =
                        playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());
                assertNotNull(response);

                final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                        .filter(playerSeasonStat -> playerSeasonStat.getD11Team().equals(d11Team)
                                                    && playerSeasonStat.getSeason().equals(season))
                        .toList();

                assertFalse(expected.isEmpty());

                final List<PlayerSeasonStatDTO> result = response.getPlayerSeasonStats();

                assertNotNull(result);
                assertFalse(result.isEmpty());
                assertEquals(map(expected, PlayerSeasonStatDTO.class), result);
            }
        }
    }

    /**
     * Tests PlayerSeasonStatController::createPlayerSeasonStat.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testCreatePlayerSeasonStat() {
        final Player player = this.playerRepository.findById(6L).orElseThrow(RuntimeException::new);
        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(RuntimeException::new);
        final Team team = this.teamRepository.findById(1L).orElseThrow(RuntimeException::new);
        final Position position = this.positionRepository.findById(1L).orElseThrow(RuntimeException::new);

        final CreatePlayerSeasonStatInputDTO input = new CreatePlayerSeasonStatInputDTO()
                .playerId(player.getId())
                .teamId(team.getId())
                .positionId(position.getId());

        final CreatePlayerSeasonStatInputRequestBodyDTO request = new CreatePlayerSeasonStatInputRequestBodyDTO()
                .playerSeasonStat(input);

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class,
                     () -> getApi(PlayerSeasonStatApi.class).createPlayerSeasonStat(request));

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(PlayerSeasonStatApi.class).createPlayerSeasonStat(request));

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final PlayerSeasonStatApi playerSeasonStatApi = getAdministratorApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.createPlayerSeasonStat(new CreatePlayerSeasonStatInputRequestBodyDTO()
                                                            .playerSeasonStat(new CreatePlayerSeasonStatInputDTO())));

        // 404 Not Found -----------------------------------------------------------------------------------------------

        request.getPlayerSeasonStat().playerId(123_456L);
        assertThrows(FeignException.NotFound.class, () -> playerSeasonStatApi.createPlayerSeasonStat(request));
        request.getPlayerSeasonStat().playerId(player.getId());

        request.getPlayerSeasonStat().teamId(123_456L);
        assertThrows(FeignException.NotFound.class, () -> playerSeasonStatApi.createPlayerSeasonStat(request));
        request.getPlayerSeasonStat().teamId(team.getId());

        request.getPlayerSeasonStat().positionId(123_456L);
        assertThrows(FeignException.NotFound.class, () -> playerSeasonStatApi.createPlayerSeasonStat(request));
        request.getPlayerSeasonStat().positionId(position.getId());

        // 409 Conflict ------------------------------------------------------------------------------------------------

        request.getPlayerSeasonStat().playerId(1L);
        assertThrows(FeignException.Conflict.class, () -> playerSeasonStatApi.createPlayerSeasonStat(request));
        request.getPlayerSeasonStat().playerId(player.getId());

        // 201 Created -------------------------------------------------------------------------------------------------

        final PlayerSeasonStatResponseBodyDTO response = playerSeasonStatApi.createPlayerSeasonStat(request);
        final PlayerSeasonStatDTO result = response.getPlayerSeasonStat();

        assertEquals(player.getId(), result.getPlayer().getId());
        assertEquals(season.getId(), result.getSeason().getId());
        assertEquals(team.getId(), result.getTeam().getId());
        assertEquals(D11Team.DEFAULT_D11_TEAM_ID, result.getD11Team().getId());
        assertEquals(position.getId(), result.getPosition().getId());
    }

    /**
     * Tests PlayerSeasonStatController::updatePlayerSeasonStat.
     */
    @Test
    void testUpdatePlayerSeasonStat() {
        final PlayerSeasonStat playerSeasonStat = this.playerSeasonStatRepository.findById(1L)
                .orElseThrow(RuntimeException::new);
        final Team team = this.teamRepository.findById(1L).orElseThrow(RuntimeException::new);
        final D11Team d11Team = this.d11TeamRepository.findById(1L).orElseThrow(RuntimeException::new);
        final Position position = this.positionRepository.findById(1L).orElseThrow(RuntimeException::new);

        final UpdatePlayerSeasonStatInputDTO input = new UpdatePlayerSeasonStatInputDTO()
                .teamId(team.getId())
                .d11TeamId(d11Team.getId())
                .positionId(position.getId());

        final UpdatePlayerSeasonStatInputRequestBodyDTO request = new UpdatePlayerSeasonStatInputRequestBodyDTO()
                .playerSeasonStat(input);

        // 401 Unauthorized --------------------------------------------------------------------------------------------

        assertThrows(FeignException.Unauthorized.class,
                () -> getApi(PlayerSeasonStatApi.class).updatePlayerSeasonStat(playerSeasonStat.getId(), request));

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                () -> getUserApi(PlayerSeasonStatApi.class).updatePlayerSeasonStat(playerSeasonStat.getId(), request));

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final PlayerSeasonStatApi playerSeasonStatApi = getAdministratorApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                () -> playerSeasonStatApi.updatePlayerSeasonStat(
                        playerSeasonStat.getId(),
                        new UpdatePlayerSeasonStatInputRequestBodyDTO()
                                .playerSeasonStat(new UpdatePlayerSeasonStatInputDTO())));

        // 404 Not Found -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.NotFound.class,
                     () -> playerSeasonStatApi.updatePlayerSeasonStat(123_456L, request));

        request.getPlayerSeasonStat().teamId(123_456L);
        assertThrows(FeignException.NotFound.class,
                () -> playerSeasonStatApi.updatePlayerSeasonStat(playerSeasonStat.getId(), request));
        request.getPlayerSeasonStat().teamId(team.getId());

        request.getPlayerSeasonStat().d11TeamId(123_456L);
        assertThrows(FeignException.NotFound.class,
                () -> playerSeasonStatApi.updatePlayerSeasonStat(playerSeasonStat.getId(), request));
        request.getPlayerSeasonStat().d11TeamId(d11Team.getId());

        request.getPlayerSeasonStat().positionId(123_456L);
        assertThrows(FeignException.NotFound.class,
                () -> playerSeasonStatApi.updatePlayerSeasonStat(playerSeasonStat.getId(), request));
        request.getPlayerSeasonStat().positionId(position.getId());

        // 200 Created -------------------------------------------------------------------------------------------------

        final PlayerSeasonStatResponseBodyDTO response =
                playerSeasonStatApi.updatePlayerSeasonStat(playerSeasonStat.getId(), request);
        final PlayerSeasonStatDTO result = response.getPlayerSeasonStat();

        assertEquals(playerSeasonStat.getId(), result.getId());
        assertEquals(team.getId(), result.getTeam().getId());
        assertEquals(d11Team.getId(), result.getD11Team().getId());
        assertEquals(position.getId(), result.getPosition().getId());
    }

}
