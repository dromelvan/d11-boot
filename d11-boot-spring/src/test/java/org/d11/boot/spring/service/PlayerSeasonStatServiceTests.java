package org.d11.boot.spring.service;

import org.d11.boot.spring.model.CreatePlayerSeasonStatInput;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.model.UpdatePlayerSeasonStatInput;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.spring.repository.PositionRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TeamRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
class PlayerSeasonStatServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked application context.
     */
    @Mock
    private ApplicationContext applicationContext;

    /**
     * Mocked player season stat repository.
     */
    @Mock
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Mocked player repository.
     */
    @Mock
    private PlayerRepository playerRepository;

    /**
     * Mocked season repository.
     */
    @Mock
    private SeasonRepository seasonRepository;

    /**
     * Mocked team repository.
     */
    @Mock
    private TeamRepository teamRepository;

    /**
     * Mocked D11 team repository.
     */
    @Mock
    private D11TeamRepository d11TeamRepository;

    /**
     * Mocked position repository.
     */
    @Mock
    private PositionRepository positionRepository;

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

    /**
     * Tests PlayerSeasonStatService::getByTeamIdAndSeasonId.
     */
    @Test
    void testGetByTeamIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String teamIdProperty = "teamId";

        final BadRequestException nullTeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(null, 1L),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId null teamId throws");
        assertEquals(teamIdProperty, nullTeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals null teamId");

        final BadRequestException invalidTeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(-1L, 1L),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId invalid teamId throws");
        assertEquals(teamIdProperty, invalidTeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals invalid teamId");

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, null),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, -1L),
                             "PlayerSeasonStatService::getByTeamIdAndSeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByTeamIdAndSeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long teamId = 1L;
        final long seasonId = 1L;
        final List<PlayerSeasonStat> playerSeasonStats = generateList(PlayerSeasonStat.class);

        when(this.playerSeasonStatRepository
                     .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(teamId), eq(seasonId)))
                .thenReturn(playerSeasonStats);

        final List<PlayerSeasonStat> result = this.playerSeasonStatService.getByTeamIdAndSeasonId(teamId, seasonId);

        assertEquals(playerSeasonStats, result, "PlayerSeasonStatService::getByTeamIdAndSeasonId result equals");

        verify(this.playerSeasonStatRepository, times(1))
                .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(teamId), eq(seasonId));
    }

    /**
     * Tests PlayerSeasonStatService::getByD11TeamIdAndSeasonId.
     */
    @Test
    void testGetByD11TeamIdAndSeasonId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String d11TeamIdProperty = "d11TeamId";

        final BadRequestException nullD11TeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByD11TeamIdAndSeasonId(null, 1L),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId null d11TeamId throws");
        assertEquals(d11TeamIdProperty, nullD11TeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals null d11TeamId");

        final BadRequestException invalidD11TeamIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByD11TeamIdAndSeasonId(-1L, 1L),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId invalid d11TeamId throws");
        assertEquals(d11TeamIdProperty, invalidD11TeamIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals invalid d11TeamId");

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, null),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class,
                             () -> this.playerSeasonStatService.getByTeamIdAndSeasonId(1L, -1L),
                             "PlayerSeasonStatService::getByD11TeamIdAndSeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "PlayerSeasonStatService::getByD11TeamIdAndSeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final long d11TeamId = 1L;
        final long seasonId = 1L;
        final List<PlayerSeasonStat> playerSeasonStats = generateList(PlayerSeasonStat.class);

        when(this.playerSeasonStatRepository
                     .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(d11TeamId), eq(seasonId)))
                .thenReturn(playerSeasonStats);

        final List<PlayerSeasonStat> result =
                this.playerSeasonStatService.getByD11TeamIdAndSeasonId(d11TeamId, seasonId);

        assertEquals(playerSeasonStats, result, "PlayerSeasonStatService::getByD11TeamIdAndSeasonId result equals");

        verify(this.playerSeasonStatRepository, times(1))
                .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(eq(d11TeamId), eq(seasonId));
    }

    /**
     * Tests PlayerSeasonStatService::createPlayerSeasonStat.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testCreatePlayerSeasonStat() {
        when(this.applicationContext.getBean(eq(PlayerRepository.class))).thenReturn(this.playerRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(TeamRepository.class))).thenReturn(this.teamRepository);
        when(this.applicationContext.getBean(eq(D11TeamRepository.class))).thenReturn(this.d11TeamRepository);
        when(this.applicationContext.getBean(eq(PositionRepository.class))).thenReturn(this.positionRepository);
        this.playerSeasonStatService.setApplicationContext(this.applicationContext);

        final BadRequestException e = assertThrows(
                BadRequestException.class,
                () -> this.playerSeasonStatService.createPlayerSeasonStat(new CreatePlayerSeasonStatInput(-1, -1, -1)),
                "PlayerSeasonStatService::createPlayerSeasonStat invalid input throws");

        final List<String> properties = Arrays.asList("playerId", "positionId", "teamId");
        assertEquals(properties, e.getValidationErrors().stream().map(ValidationError::property).toList(),
                     "PlayerSeasonStatService::createPlayerSeasonStat validation error properties equals");

        final Player player = generate(Player.class);
        final Season season = generate(Season.class);
        final Team team = generate(Team.class);
        final D11Team d11Team = generate(D11Team.class);
        final Position position = generate(Position.class);

        final long invalidId = 999L;

        when(this.playerRepository.findById(eq(player.getId()))).thenReturn(Optional.of(player));
        when(this.playerRepository.findById(eq(invalidId))).thenThrow(new NotFoundException(invalidId, Player.class));
        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.teamRepository.findById(eq(team.getId()))).thenReturn(Optional.of(team));
        when(this.teamRepository.findById(eq(invalidId))).thenThrow(new NotFoundException(invalidId, Team.class));
        when(this.d11TeamRepository.findById(eq(D11Team.DEFAULT_D11_TEAM_ID))).thenReturn(Optional.of(d11Team));
        when(this.positionRepository.findById(eq(position.getId()))).thenReturn(Optional.of(position));
        when(this.positionRepository.findById(eq(invalidId))).thenThrow(new NotFoundException(invalidId,
                                                                                              Position.class));

        assertThrows(NotFoundException.class,
                     () -> this.playerSeasonStatService.createPlayerSeasonStat(
                             new CreatePlayerSeasonStatInput(invalidId, team.getId(), position.getId())),
                     "PlayerSeasonStatService::createPlayerSeasonStat player not found throws");

        assertThrows(NotFoundException.class,
                     () -> this.playerSeasonStatService.createPlayerSeasonStat(
                             new CreatePlayerSeasonStatInput(player.getId(), invalidId, position.getId())),
                     "PlayerSeasonStatService::createPlayerSeasonStat team not found throws");

        assertThrows(NotFoundException.class,
                     () -> this.playerSeasonStatService.createPlayerSeasonStat(
                             new CreatePlayerSeasonStatInput(player.getId(), team.getId(), invalidId)),
                     "PlayerSeasonStatService::createPlayerSeasonStat position not found throws");

        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(new PlayerSeasonStat()));

        assertThrows(ConflictException.class,
                     () -> this.playerSeasonStatService.createPlayerSeasonStat(
                             new CreatePlayerSeasonStatInput(player.getId(), team.getId(), invalidId)),
                     "PlayerSeasonStatService::createPlayerSeasonStat player season stat exists throws");

        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.empty());
        when(this.playerSeasonStatRepository.save(any(PlayerSeasonStat.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        final CreatePlayerSeasonStatInput input =
                new CreatePlayerSeasonStatInput(player.getId(), team.getId(), position.getId());
        final PlayerSeasonStat result = this.playerSeasonStatService.createPlayerSeasonStat(input);

        assertEquals(player, result.getPlayer(),
                     "PlayerSeasonStatService::createPlayerSeasonStat result player equals");
        assertEquals(season, result.getSeason(),
                     "PlayerSeasonStatService::createPlayerSeasonStat result season equals");
        assertEquals(team, result.getTeam(),
                     "PlayerSeasonStatService::createPlayerSeasonStat result team equals");
        assertEquals(d11Team, result.getD11Team(),
                     "PlayerSeasonStatService::createPlayerSeasonStat result D11 team equals");
        assertEquals(position, result.getPosition(),
                     "PlayerSeasonStatService::createPlayerSeasonStat result position equals");

        verify(this.playerSeasonStatRepository, times(1)).save(eq(result));
    }

    /**
     * Tests PlayerSeasonStatService::updatePlayerSeasonStat.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testUpdatePlayerSeasonStat() {
        when(this.applicationContext.getBean(eq(TeamRepository.class))).thenReturn(this.teamRepository);
        when(this.applicationContext.getBean(eq(D11TeamRepository.class))).thenReturn(this.d11TeamRepository);
        when(this.applicationContext.getBean(eq(PositionRepository.class))).thenReturn(this.positionRepository);

        final BadRequestException badRequestException = assertThrows(
                BadRequestException.class,
                () -> this.playerSeasonStatService.updatePlayerSeasonStat(
                        -1L,
                        new UpdatePlayerSeasonStatInput(-1, -1, -1)
                ),
                "PlayerSeasonStatService::updatePlayerSeasonStat invalid input throws");

        final List<String> properties = Arrays.asList("d11TeamId", "positionId", "teamId");
        assertEquals(properties, badRequestException.getValidationErrors().stream()
                        .map(ValidationError::property).toList(),
                     "PlayerSeasonStatService::updatePlayerSeasonStat validation error properties equals");

        final Team team = generate(Team.class);
        final D11Team d11Team = generate(D11Team.class);
        final Position position = generate(Position.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);

        final long invalidId = 999L;

        when(this.teamRepository.findById(eq(team.getId()))).thenReturn(Optional.of(team));
        when(this.teamRepository.findById(eq(invalidId))).thenThrow(new NotFoundException(invalidId, Team.class));
        when(this.d11TeamRepository.findById(eq(d11Team.getId()))).thenReturn(Optional.of(d11Team));
        when(this.d11TeamRepository.findById(eq(invalidId))).thenThrow(new NotFoundException(invalidId, D11Team.class));
        when(this.positionRepository.findById(eq(position.getId()))).thenReturn(Optional.of(position));
        when(this.positionRepository.findById(eq(invalidId))).thenThrow(new NotFoundException(invalidId,
                                                                                              Position.class));
        when(this.playerSeasonStatRepository.findById(eq(invalidId))).thenReturn(Optional.empty());
        when(this.playerSeasonStatRepository.findById(eq(playerSeasonStat.getId())))
                .thenReturn(Optional.of(playerSeasonStat));

        NotFoundException e = assertThrows(NotFoundException.class,
                () -> this.playerSeasonStatService.updatePlayerSeasonStat(
                        invalidId,
                        new UpdatePlayerSeasonStatInput(team.getId(), d11Team.getId(), position.getId())),
                "PlayerSeasonStatService::updatePlayerSeasonStat playerSeasonStat not found throws");
        assertEquals(invalidId, e.getId(),
                     "PlayerSeasonStatService::updatePlayerSeasonStat playerSeasonStat not found id");
        assertEquals(PlayerSeasonStat.class.getSimpleName(), e.getResource(),
                     "PlayerSeasonStatService::updatePlayerSeasonStat playerSeasonStat not found class");

        e = assertThrows(NotFoundException.class,
                () -> this.playerSeasonStatService.updatePlayerSeasonStat(
                        playerSeasonStat.getId(),
                        new UpdatePlayerSeasonStatInput(invalidId, d11Team.getId(), position.getId())),
                "PlayerSeasonStatService::updatePlayerSeasonStat team not found throws");
        assertEquals(invalidId, e.getId(), "PlayerSeasonStatService::updatePlayerSeasonStat team not found id");
        assertEquals(Team.class.getSimpleName(), e.getResource(),
                "PlayerSeasonStatService::updatePlayerSeasonStat team not found class");

        e = assertThrows(NotFoundException.class,
                () -> this.playerSeasonStatService.updatePlayerSeasonStat(
                        playerSeasonStat.getId(),
                        new UpdatePlayerSeasonStatInput(team.getId(), invalidId, position.getId())),
                "PlayerSeasonStatService::updatePlayerSeasonStat D11 team not found throws");
        assertEquals(invalidId, e.getId(), "PlayerSeasonStatService::updatePlayerSeasonStat D11 team not found id");
        assertEquals(D11Team.class.getSimpleName(), e.getResource(),
                "PlayerSeasonStatService::updatePlayerSeasonStat D11 team not found class");

        e = assertThrows(NotFoundException.class,
                () -> this.playerSeasonStatService.updatePlayerSeasonStat(
                        playerSeasonStat.getId(),
                        new UpdatePlayerSeasonStatInput(team.getId(), d11Team.getId(), invalidId)),
                "PlayerSeasonStatService::updatePlayerSeasonStat position not found throws");
        assertEquals(invalidId, e.getId(), "PlayerSeasonStatService::updatePlayerSeasonStat position not found id");
        assertEquals(Position.class.getSimpleName(), e.getResource(),
                "PlayerSeasonStatService::updatePlayerSeasonStat position not found class");

        when(this.playerSeasonStatRepository.save(any(PlayerSeasonStat.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        final PlayerSeasonStat result = this.playerSeasonStatService.updatePlayerSeasonStat(
                playerSeasonStat.getId(),
                new UpdatePlayerSeasonStatInput(team.getId(), d11Team.getId(), position.getId())
        );

        assertEquals(playerSeasonStat, result, "PlayerSeasonStatService::updatePlayerSeasonStat result equals");
        assertEquals(team, result.getTeam(),
                "PlayerSeasonStatService::updatePlayerSeasonStat result team equals");
        assertEquals(d11Team, result.getD11Team(),
                "PlayerSeasonStatService::updatePlayerSeasonStat result D11 team equals");
        assertEquals(position, result.getPosition(),
                "PlayerSeasonStatService::updatePlayerSeasonStat result position equals");

        verify(this.playerSeasonStatRepository, times(1)).save(eq(playerSeasonStat));
    }

}
