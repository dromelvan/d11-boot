package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.PlayerSeasonStatApi;
import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputDTO;
import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputRequestBodyDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatResponseBodyDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatsResponseBodyDTO;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
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
    void testGetPlayerSeasonStatsBySeasonId() {
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

    /**
     * Tests PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId.
     */
    @Test
    void testGetPlayerSeasonStatsByTeamIdAndSeasonId() {
        final PlayerSeasonStatApi playerSeasonStatApi = getApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(null, 1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId teamId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(-1L, 1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId teamId negative throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(1L, (Long) null),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId seasonId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(1L, -1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId seasonId negative throws");

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getPosition)
                                         .thenComparing(PlayerSeasonStat::getRanking));

        final Set<Team> teams = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getTeam)
                .collect(Collectors.toSet());

        final Set<Season> seasons = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertFalse(teams.isEmpty(),
                    "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId teams empty");
        assertFalse(seasons.isEmpty(),
                    "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId seasons empty");

        for (final Team team : teams) {
            for (final Season season : seasons) {
                final PlayerSeasonStatsResponseBodyDTO response =
                        playerSeasonStatApi.getPlayerSeasonStatsByTeamIdAndSeasonId(team.getId(), season.getId());
                assertNotNull(response,
                              "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId response not null");

                final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getTeam().equals(team)
                                                && playerSeasonStat.getSeason().equals(season))
                    .toList();

                assertFalse(expected.isEmpty(),
                           "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId expected empty");

                final List<PlayerSeasonStatDTO> result = response.getPlayerSeasonStats();

                assertNotNull(result, "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId not null ");
                assertFalse(result.isEmpty(),
                            "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId empty");
                assertEquals(map(expected, PlayerSeasonStatDTO.class), result,
                             "PlayerSeasonStatController::getPlayerSeasonStatsByTeamIdAndSeasonId equals");
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
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(null, 1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId d11TeamId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(-1L, 1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId d11TeamId negative throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(1L, (Long) null),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId seasonId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(1L, -1L),
                     "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId seasonId negative throws");

        final List<PlayerSeasonStat> playerSeasonStats = this.playerSeasonStatRepository.findAll();
        playerSeasonStats.sort(Comparator.comparing(PlayerSeasonStat::getPosition)
                                         .thenComparing(PlayerSeasonStat::getRanking));

        final Set<D11Team> d11Teams = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getD11Team)
                .collect(Collectors.toSet());

        final Set<Season> seasons = playerSeasonStats.stream()
                .map(PlayerSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertFalse(d11Teams.isEmpty(),
                    "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId D11 teams empty");
        assertFalse(seasons.isEmpty(),
                    "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId seasons empty");

        for (final D11Team d11Team : d11Teams) {
            for (final Season season : seasons) {
                final PlayerSeasonStatsResponseBodyDTO response =
                        playerSeasonStatApi.getPlayerSeasonStatsByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());
                assertNotNull(response,
                              "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId response not null");

                final List<PlayerSeasonStat> expected = playerSeasonStats.stream()
                        .filter(playerSeasonStat -> playerSeasonStat.getD11Team().equals(d11Team)
                                                    && playerSeasonStat.getSeason().equals(season))
                        .toList();

                assertFalse(expected.isEmpty(),
                            "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId expected empty");

                final List<PlayerSeasonStatDTO> result = response.getPlayerSeasonStats();

                assertNotNull(result,
                              "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId not null ");
                assertFalse(result.isEmpty(),
                            "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId empty");
                assertEquals(map(expected, PlayerSeasonStatDTO.class), result,
                             "PlayerSeasonStatController::getPlayerSeasonStatsByD11TeamIdAndSeasonId equals");
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
                     () -> getApi(PlayerSeasonStatApi.class).createPlayerSeasonStat(request),
                     "PlayerSeasonStatController::createPlayerSeasonStat unauthorized throws");

        // 403 Forbidden -----------------------------------------------------------------------------------------------

        assertThrows(FeignException.Forbidden.class,
                     () -> getUserApi(PlayerSeasonStatApi.class).createPlayerSeasonStat(request),
                     "PlayerSeasonStatController::createPlayerSeasonStat user throws");

        // 400 Bad Request ---------------------------------------------------------------------------------------------

        final PlayerSeasonStatApi playerSeasonStatApi = getAdministratorApi(PlayerSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> playerSeasonStatApi.createPlayerSeasonStat(new CreatePlayerSeasonStatInputRequestBodyDTO()
                                                            .playerSeasonStat(new CreatePlayerSeasonStatInputDTO())),
                     "PlayerSeasonStatController::createPlayerSeasonStat request body invalid throws");

        // 404 Not Found -----------------------------------------------------------------------------------------------

        request.getPlayerSeasonStat().playerId(123_456L);
        assertThrows(FeignException.NotFound.class,
                     () -> playerSeasonStatApi.createPlayerSeasonStat(request),
                     "PlayerSeasonStatController::createPlayerSeasonStat request body player not found throws");
        request.getPlayerSeasonStat().playerId(player.getId());

        request.getPlayerSeasonStat().teamId(123_456L);
        assertThrows(FeignException.NotFound.class,
                     () -> playerSeasonStatApi.createPlayerSeasonStat(request),
                     "PlayerSeasonStatController::createPlayerSeasonStat request body team not found throws");
        request.getPlayerSeasonStat().teamId(team.getId());

        request.getPlayerSeasonStat().positionId(123_456L);
        assertThrows(FeignException.NotFound.class,
                     () -> playerSeasonStatApi.createPlayerSeasonStat(request),
                     "PlayerSeasonStatController::createPlayerSeasonStat request body position not found throws");
        request.getPlayerSeasonStat().positionId(position.getId());

        // 409 Conflict ------------------------------------------------------------------------------------------------

        request.getPlayerSeasonStat().playerId(1L);
        assertThrows(FeignException.Conflict.class,
                     () -> playerSeasonStatApi.createPlayerSeasonStat(request),
                     "PlayerSeasonStatController::createPlayerSeasonStat request body already exists throws");
        request.getPlayerSeasonStat().playerId(player.getId());

        // 201 Created -------------------------------------------------------------------------------------------------

        final PlayerSeasonStatResponseBodyDTO response = playerSeasonStatApi.createPlayerSeasonStat(request);
        final PlayerSeasonStatDTO result = response.getPlayerSeasonStat();

        assertEquals(player.getId(), result.getPlayer().getId(),
                     "PlayerSeasonStatController::createPlayerSeasonStat result player id equals");
        assertEquals(season.getId(), result.getSeason().getId(),
                     "PlayerSeasonStatController::createPlayerSeasonStat result season id equals");
        assertEquals(team.getId(), result.getTeam().getId(),
                     "PlayerSeasonStatController::createPlayerSeasonStat result team id equals");
        assertEquals(D11Team.DEFAULT_D11_TEAM_ID, result.getD11Team().getId(),
                     "PlayerSeasonStatController::createPlayerSeasonStat result D11 team id equals");
        assertEquals(position.getId(), result.getPosition().getId(),
                     "PlayerSeasonStatController::createPlayerSeasonStat result position id equals");
    }

}
