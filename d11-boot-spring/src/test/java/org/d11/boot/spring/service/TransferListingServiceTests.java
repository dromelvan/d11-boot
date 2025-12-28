package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferListingRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer listing service tests.
 */
@SuppressWarnings("checkstyle:ClassFanOutComplexity")
class TransferListingServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked application context.
     */
    @Mock
    private ApplicationContext applicationContext;

    /**
     * Mocked authentication.
     */
    @Mock
    private Authentication authentication;

    /**
     * Mocked JWT.
     */
    @Mock
    private Jwt jwt;

    /**
     * Mocked user repository.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Mocked transfer listing repository.
     */
    @Mock
    private TransferListingRepository transferListingRepository;

    /**
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Mocked season repository.
     */
    @Mock
    private SeasonRepository seasonRepository;

    /**
     * Mocked player season stat repository.
     */
    @Mock
    private PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Transfer listing service.
     */
    @InjectMocks
    private TransferListingService transferListingService;

    // createTransferListing -------------------------------------------------------------------------------------------

    /**
     * Tests TransferListingService::createTransferListing for null playerId.
     */
    @Test
    void testCreateTransferListingPlayerIdNull() {
        final BadRequestException e =
                assertThrows(BadRequestException.class,
                             () -> this.transferListingService.createTransferListing(null));

        assertEquals("playerId", e.getParameter());
    }

    /**
     * Tests TransferListingService::createTransferListing for playerSeasonStat not found.
     */
    @Test
    void testCreateTransferListingPlayerSeasonStatNotFound() {
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);

        final Season season = generate(Season.class);
        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(any(), any())).thenReturn(Optional.empty());

        final ConflictException e =
                assertThrows(ConflictException.class,
                             () -> this.transferListingService.createTransferListing(1L));
        assertEquals(ErrorCode.CONFLICT_NO_PLAYER_SEASON_STAT, e.getErrorCode());
    }

    /**
     * Tests TransferListingService::createTransferListing for unauthorized.
     */
    @Test
    void testCreateTransferListingUnauthorized() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);

        final Season season = generate(Season.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));

        assertThrows(UnauthorizedException.class,
                     () -> this.transferListingService.createTransferListing(player.getId()));
    }

    /**
     * Tests TransferListingService::createTransferListing for forbidden.
     */
    @Test
    void testCreateTransferListingForbidden() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);

        final Season season = generate(Season.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();
        final User user = generate(User.class);
        user.setAdministrator(false);

        mockCurrentUser(user);
        playerSeasonStat.getD11Team().setOwner(generate(User.class));

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));

        assertThrows(ForbiddenException.class,
                     () -> this.transferListingService.createTransferListing(player.getId()));
    }

    /**
     * Tests TransferListingService::createTransferListing for D11 team max transfers.
     */
    @Test
    void testCreateTransferListingD11TeamMaxTransfers() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);

        final Season season = generate(Season.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();
        final D11Team d11Team = playerSeasonStat.getD11Team();

        mockCurrentUser(d11Team.getOwner());

        season.setD11TeamMaxTransfers(1);
        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));
        when(this.transferListingRepository
                     .findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), d11Team.getId()))
                .thenReturn(generateList(TransferListing.class, 5));

        final ConflictException e =
                assertThrows(ConflictException.class,
                             () -> this.transferListingService.createTransferListing(player.getId()));
        assertEquals(ErrorCode.CONFLICT_NO_REMAINING_D11_TEAM_TRANSFERS, e.getErrorCode());
    }

    /**
     * Tests TransferListingService::createTransferListing for transfer day invalid status.
     */
    @Test
    void testCreateTransferListingTransferDayStatusInvalid() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);
        when(this.applicationContext.getBean(eq(TransferDayRepository.class))).thenReturn(this.transferDayRepository);

        final Season season = generate(Season.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();
        final TransferDay transferDay = generate(TransferDay.class);
        transferDay.setStatus(Status.FINISHED);

        mockCurrentUser(playerSeasonStat.getD11Team().getOwner());

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(transferDay));

        final ConflictException e =
                assertThrows(ConflictException.class,
                             () -> this.transferListingService.createTransferListing(player.getId()));
        assertEquals(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS, e.getErrorCode());
    }

    /**
     * Tests TransferListingService::createTransferListing for player already transfer listed.
     */
    @Test
    void testCreateTransferListingPlayerAlreadyTransferListed() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);
        when(this.applicationContext.getBean(eq(TransferDayRepository.class))).thenReturn(this.transferDayRepository);

        final Season season = generate(Season.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();
        final TransferDay transferDay = generate(TransferDay.class);
        transferDay.setStatus(Status.PENDING);

        mockCurrentUser(playerSeasonStat.getD11Team().getOwner());

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(transferDay));
        when(this.transferListingRepository.findByTransferDayIdAndPlayerId(transferDay.getId(), player.getId()))
                .thenReturn(Optional.of(generate(TransferListing.class)));

        final ConflictException e =
                assertThrows(ConflictException.class,
                             () -> this.transferListingService.createTransferListing(player.getId()));
        assertEquals(ErrorCode.CONFLICT_NON_UNIQUE_TRANSFER_LISTING, e.getErrorCode());
    }

    /**
     * Tests TransferListingService::createTransferListing for user.
     */
    @Test
    void testCreateTransferListingUser() {
        final User user = generate(User.class);
        user.setAdministrator(false);

        testCreateTransferListing(user);
    }

    /**
     * Tests TransferListingService::createTransferListing for administrator.
     */
    @Test
    void testCreateTransferListingAdministrator() {
        final User user = generate(User.class);
        user.setAdministrator(true);

        testCreateTransferListing(user);
    }

    // deleteTransferListing -------------------------------------------------------------------------------------------

    /**
     * Tests TransferListingService::deleteTransferListing for null transferListingId.
     */
    @Test
    void testDeleteTransferListingTransferListingIdNull() {
        final BadRequestException e =
                assertThrows(BadRequestException.class,
                             () -> this.transferListingService.deleteTransferListing(null));

        assertEquals("transferListingId", e.getParameter());
    }

    /**
     * Tests TransferListingService::deleteTransferListing for transferListing not found.
     */
    @Test
    void testDeleteTransferListingTransferListingNotFound() {
        when(this.transferListingRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                     () -> this.transferListingService.deleteTransferListing(1L));
    }

    /**
     * Tests TransferListingService::deleteTransferListing for unauthorized.
     */
    @Test
    void testDeleteTransferListingUnauthorized() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final TransferListing transferListing = generate(TransferListing.class);

        when(this.transferListingRepository.findById(eq(transferListing.getId())))
                .thenReturn(Optional.of(transferListing));

        assertThrows(UnauthorizedException.class,
                     () -> this.transferListingService.deleteTransferListing(transferListing.getId()));
    }

    /**
     * Tests TransferListingService::deleteTransferListing for forbidden.
     */
    @Test
    void testDeleteTransferListingForbidden() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final TransferListing transferListing = generate(TransferListing.class);

        when(this.transferListingRepository.findById(eq(transferListing.getId())))
                .thenReturn(Optional.of(transferListing));

        final User user = generate(User.class);
        user.setAdministrator(false);
        mockCurrentUser(user);

        assertThrows(ForbiddenException.class,
                     () -> this.transferListingService.deleteTransferListing(transferListing.getId()));
    }

    /**
     * Tests TransferListingService::deleteTransferListing for transfer day invalid status as user.
     */
    @Test
    void testDeleteTransferListingTransferDayInvalidStatusUser() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final TransferListing transferListing = generate(TransferListing.class);
        transferListing.getTransferDay().setStatus(Status.FINISHED);

        when(this.transferListingRepository.findById(eq(transferListing.getId())))
                .thenReturn(Optional.of(transferListing));

        final User user = transferListing.getD11Team().getOwner();
        user.setAdministrator(false);
        mockCurrentUser(user);

        final ConflictException e =
                assertThrows(ConflictException.class,
                             () -> this.transferListingService.deleteTransferListing(transferListing.getId()));
        assertEquals(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS, e.getErrorCode());
    }

    /**
     * Tests TransferListingService::deleteTransferListing for transfer day invalid status as admin.
     */
    @Test
    void testDeleteTransferListingTransferDayInvalidStatusAdmin() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final TransferListing transferListing = generate(TransferListing.class);
        transferListing.getTransferDay().setStatus(Status.FINISHED);

        when(this.transferListingRepository.findById(eq(transferListing.getId())))
                .thenReturn(Optional.of(transferListing));

        final User user = generate(User.class);
        user.setAdministrator(true);
        mockCurrentUser(user);

        assertDoesNotThrow(() -> this.transferListingService.deleteTransferListing(transferListing.getId()));

        verify(this.transferListingRepository, times(1)).delete(eq(transferListing));
    }

    /**
     * Tests TransferListingService::deleteTransferListing as user.
     */
    @Test
    void testDeleteTransferListingUser() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final TransferListing transferListing = generate(TransferListing.class);
        transferListing.getTransferDay().setStatus(Status.PENDING);

        when(this.transferListingRepository.findById(eq(transferListing.getId())))
                .thenReturn(Optional.of(transferListing));

        final User user = transferListing.getD11Team().getOwner();
        user.setAdministrator(false);
        mockCurrentUser(user);

        assertDoesNotThrow(() -> this.transferListingService.deleteTransferListing(transferListing.getId()));

        verify(this.transferListingRepository, times(1)).delete(eq(transferListing));
    }

    /**
     * Tests TransferListingService::deleteTransferListing as admin.
     */
    @Test
    void testDeleteTransferListingAdmin() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final TransferListing transferListing = generate(TransferListing.class);
        transferListing.getTransferDay().setStatus(Status.PENDING);

        when(this.transferListingRepository.findById(eq(transferListing.getId())))
                .thenReturn(Optional.of(transferListing));

        final User user = generate(User.class);
        user.setAdministrator(true);
        mockCurrentUser(user);

        assertDoesNotThrow(() -> this.transferListingService.deleteTransferListing(transferListing.getId()));

        verify(this.transferListingRepository, times(1)).delete(eq(transferListing));
    }

    // getByTransferDayId ----------------------------------------------------------------------------------------------

    /**
     * Tests TransferListingService::getByTransferDayId.
     */
    @Test
    void testGetByTransferDayId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String transferDayIdProperty = "transferDayId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(null, 0));
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter());

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(-1L, 0));
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter());

        final BadRequestException invalidPageException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(1L, -1));
        assertEquals("page", invalidPageException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final TransferDay transferDay = generate(TransferDay.class);

        final List<TransferListing> transferListings = generateList(TransferListing.class);

        when(this.transferDayRepository.findById(eq(transferDay.getId() + 1))).thenReturn(Optional.empty());

        assertTrue(this.transferListingService.getByTransferDayId(transferDay.getId() + 1, 0).isEmpty());

        transferDay.setStatus(Status.PENDING);
        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));

        assertTrue(this.transferListingService.getByTransferDayId(transferDay.getId(), 0).isEmpty());

        transferDay.setStatus(Status.ACTIVE);
        when(this.transferListingRepository.findByTransferDayIdOrderByRanking(eq(transferDay.getId()),
                                                                              any(Pageable.class)))
                .thenReturn(transferListings);

        final List<TransferListing> result = this.transferListingService.getByTransferDayId(transferDay.getId(), 0);

        assertEquals(transferListings, result);

        verify(this.transferListingRepository, times(1)).findByTransferDayIdOrderByRanking(eq(transferDay.getId()),
                                                                                           any(Pageable.class));
    }

    /**
     * Tests TransferListingService::createTransferListing with a user that can be either administrator nor not.
     *
     * @param user The user.
     */
    private void testCreateTransferListing(final User user) {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);
        when(this.applicationContext.getBean(eq(TransferDayRepository.class))).thenReturn(this.transferDayRepository);

        final Season season = generate(Season.class);
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();
        final TransferDay transferDay = generate(TransferDay.class);
        transferDay.setStatus(Status.PENDING);

        if (!user.isAdministrator()) {
            playerSeasonStat.getD11Team().setOwner(user);
        }

        mockCurrentUser(user);

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(transferDay));
        when(this.transferListingRepository.findByTransferDayIdAndPlayerId(transferDay.getId(), player.getId()))
                .thenReturn(Optional.empty());
        when(this.transferListingRepository.save(any(TransferListing.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferListing result = this.transferListingService.createTransferListing(player.getId());

        assertResultEquals(playerSeasonStat, result);
    }

    /**
     * Sets a user as the current user in the mocked authentication.
     *
     * @param user The user that will be set as current user.
     */
    private void mockCurrentUser(final User user) {
        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.jwt.getClaimAsString(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(user.getEmail());
        when(this.userRepository.findByEmail(eq(user.getEmail()))).thenReturn(Optional.of(user));
    }

    /**
     * Checks a player season stat and a transfer listing for equality.
     *
     * @param playerSeasonStat The player season stat.
     * @param result The result.
     */
    private void assertResultEquals(final PlayerSeasonStat playerSeasonStat, final TransferListing result) {
        assertEquals(playerSeasonStat.getPlayer(), result.getPlayer());
        assertEquals(playerSeasonStat.getTeam(), result.getTeam());
        assertEquals(playerSeasonStat.getD11Team(), result.getD11Team());
        assertEquals(playerSeasonStat.getPosition(), result.getPosition());
        assertEquals(playerSeasonStat.getFormPoints(), result.getFormPoints());
        assertEquals(playerSeasonStat.getFormMatchPoints(), result.getFormMatchPoints());
        assertEquals(playerSeasonStat.getRanking(), result.getRanking());
        assertEquals(playerSeasonStat.getPointsPerAppearance(), result.getPointsPerAppearance());
        assertEquals(playerSeasonStat.getCleanSheets(), result.getCleanSheets());
        assertEquals(playerSeasonStat.getYellowCards(), result.getYellowCards());
        assertEquals(playerSeasonStat.getRedCards(), result.getRedCards());
        assertEquals(playerSeasonStat.getSubstitutionsOn(), result.getSubstitutionsOn());
        assertEquals(playerSeasonStat.getSubstitutionsOff(), result.getSubstitutionsOff());
        assertEquals(playerSeasonStat.getManOfTheMatch(), result.getManOfTheMatch());
        assertEquals(playerSeasonStat.getSharedManOfTheMatch(), result.getSharedManOfTheMatch());
        assertEquals(playerSeasonStat.getGamesStarted(), result.getGamesStarted());
        assertEquals(playerSeasonStat.getGamesSubstitute(), result.getGamesSubstitute());
        assertEquals(playerSeasonStat.getGamesDidNotParticipate(), result.getGamesDidNotParticipate());
        assertEquals(playerSeasonStat.getMinutesPlayed(), result.getMinutesPlayed());
        assertEquals(playerSeasonStat.getGoals(), result.getGoals());
        assertEquals(playerSeasonStat.getGoalAssists(), result.getGoalAssists());
        assertEquals(playerSeasonStat.getOwnGoals(), result.getOwnGoals());
        assertEquals(playerSeasonStat.getGoalsConceded(), result.getGoalsConceded());
        assertEquals(playerSeasonStat.getRating(), result.getRating());
        assertEquals(playerSeasonStat.getPoints(), result.getPoints());
    }

}
