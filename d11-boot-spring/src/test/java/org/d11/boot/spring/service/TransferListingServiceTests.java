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
import org.d11.boot.util.exception.ForbiddenException;
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

    /**
     * Tests TransferListingService::createTransferListing.
     */
    @Test
    @SuppressWarnings({"checkstyle:JavaNCSS",
                       "checkstyle:ExecutableStatementCount",
                       "checkstyle:MethodLength",
                       "PMD.ExcessiveMethodLength",
                       "PMD.NcssCount"})
    void testCreateTransferListing() {
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.applicationContext.getBean(eq(SeasonRepository.class))).thenReturn(this.seasonRepository);
        when(this.applicationContext.getBean(eq(PlayerSeasonStatRepository.class)))
                .thenReturn(this.playerSeasonStatRepository);
        when(this.applicationContext.getBean(eq(TransferDayRepository.class))).thenReturn(this.transferDayRepository);

        // Validation --------------------------------------------------------------------------------------------------

        final BadRequestException badRequestException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.createTransferListing(null),
                             "TransferListingService::createTransferListing null playerId throws");
        assertEquals("playerId", badRequestException.getParameter(),
                     "TransferListingService::createTransferListing property equals null playerId");

        final Season season = generate(Season.class);

        when(this.seasonRepository.findFirstByOrderByDateDesc()).thenReturn(Optional.of(season));
        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(any(), any())).thenReturn(Optional.empty());

        Exception e = assertThrows(ConflictException.class,
                                   () -> this.transferListingService.createTransferListing(1L),
                                   "TransferListingService::createTransferListing playerSeasonStat not found throws");
        assertEquals(TransferListingService.INACTIVE_PLAYER_MESSAGE, e.getMessage(),
                     "TransferListingService::createTransferListing playerSeasonStat not found message equals");

        // Unauthorized ------------------------------------------------------------------------------------------------

        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final Player player = playerSeasonStat.getPlayer();
        final D11Team d11Team = playerSeasonStat.getD11Team();

        when(this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(eq(player.getId()), eq(season.getId())))
                .thenReturn(Optional.of(playerSeasonStat));

        assertThrows(UnauthorizedException.class,
                     () -> this.transferListingService.createTransferListing(player.getId()),
                     "TransferListingService::createTransferListing unauthorized throws");

        // Forbidden ---------------------------------------------------------------------------------------------------

        final User user = generate(User.class);
        user.setAdministrator(false);

        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.jwt.getClaimAsString(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(user.getEmail());
        when(this.userRepository.findByEmail(eq(user.getEmail()))).thenReturn(Optional.of(user));

        assertThrows(ForbiddenException.class,
                     () -> this.transferListingService.createTransferListing(player.getId()),
                     "TransferListingService::createTransferListing forbidden throws");

        playerSeasonStat.getD11Team().setOwner(user);

        // Conflict ----------------------------------------------------------------------------------------------------

        season.setD11TeamMaxTransfers(1);
        when(this.transferListingRepository
                     .findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), d11Team.getId()))
                .thenReturn(generateList(TransferListing.class, 5));

        e = assertThrows(ConflictException.class,
                         () -> this.transferListingService.createTransferListing(player.getId()),
                         "TransferListingService::createTransferListing D11 team max transfers throws");
        assertEquals(TransferListingService.MAX_TRANSFERS_MESSAGE, e.getMessage(),
                 "TransferListingService::createTransferListing D11 team max transfers max transfers message equals");
        season.setD11TeamMaxTransfers(10);

        final TransferDay transferDay = generate(TransferDay.class);
        transferDay.setStatus(Status.FINISHED);
        when(this.transferDayRepository.findFirstByOrderByDatetimeDesc()).thenReturn(Optional.of(transferDay));

        e = assertThrows(ConflictException.class,
                         () -> this.transferListingService.createTransferListing(player.getId()),
                         "TransferListingService::createTransferListing transfer day invalid status throws");
        assertEquals(TransferListingService.INVALID_TRANSFER_DAY_STATUS, e.getMessage(),
                     "TransferListingService::createTransferListing transfer day invalid status message equals");

        transferDay.setStatus(Status.PENDING);

        when(this.transferListingRepository.findByTransferDayIdAndPlayerId(transferDay.getId(), player.getId()))
                .thenReturn(Optional.of(generate(TransferListing.class)));

        e = assertThrows(ConflictException.class,
                         () -> this.transferListingService.createTransferListing(player.getId()),
                         "TransferListingService::createTransferListing player already transfer listed throws");
        assertEquals(TransferListingService.PLAYER_ALREADY_TRANSFER_LISTED_MESSAGE, e.getMessage(),
                     "TransferListingService::createTransferListing player already transfer listed message equals");

        // Success -----------------------------------------------------------------------------------------------------
        when(this.transferListingRepository.findByTransferDayIdAndPlayerId(transferDay.getId(), player.getId()))
                .thenReturn(Optional.empty());
        when(this.transferListingRepository.save(any(TransferListing.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferListing userResult = this.transferListingService.createTransferListing(player.getId());

        assertEquals(player, userResult.getPlayer(),
                     "TransferListingService::createTransferListing user result player equals");
        assertEquals(playerSeasonStat.getTeam(), userResult.getTeam(),
                     "TransferListingService::createTransferListing user result team equals");
        assertEquals(playerSeasonStat.getD11Team(), userResult.getD11Team(),
                     "TransferListingService::createTransferListing user result position equals");
        assertEquals(playerSeasonStat.getPosition(), userResult.getPosition(),
                     "TransferListingService::createTransferListing user result position equals");
        assertEquals(playerSeasonStat.getFormPoints(), userResult.getFormPoints(),
                     "TransferListingService::createTransferListing user result formPoints equals");
        assertEquals(playerSeasonStat.getFormMatchPoints(), userResult.getFormMatchPoints(),
                     "TransferListingService::createTransferListing user result formMatchPoints equals");
        assertEquals(playerSeasonStat.getRanking(), userResult.getRanking(),
                     "TransferListingService::createTransferListing user result ranking equals");
        assertEquals(playerSeasonStat.getPointsPerAppearance(), userResult.getPointsPerAppearance(),
                     "TransferListingService::createTransferListing user result pointsPerAppearance equals");
        assertEquals(playerSeasonStat.getCleanSheets(), userResult.getCleanSheets(),
                     "TransferListingService::createTransferListing user result cleanSheets equals");
        assertEquals(playerSeasonStat.getYellowCards(), userResult.getYellowCards(),
                     "TransferListingService::createTransferListing user result yellowCards equals");
        assertEquals(playerSeasonStat.getRedCards(), userResult.getRedCards(),
                     "TransferListingService::createTransferListing user result redCards equals");
        assertEquals(playerSeasonStat.getSubstitutionsOn(), userResult.getSubstitutionsOn(),
                     "TransferListingService::createTransferListing user result substitutionsOn equals");
        assertEquals(playerSeasonStat.getSubstitutionsOff(), userResult.getSubstitutionsOff(),
                     "TransferListingService::createTransferListing user result substitutionsOff equals");
        assertEquals(playerSeasonStat.getManOfTheMatch(), userResult.getManOfTheMatch(),
                     "TransferListingService::createTransferListing user result manOfTheMatch equals");
        assertEquals(playerSeasonStat.getSharedManOfTheMatch(), userResult.getSharedManOfTheMatch(),
                     "TransferListingService::createTransferListing user result sharedManOfTheMatch equals");
        assertEquals(playerSeasonStat.getGamesStarted(), userResult.getGamesStarted(),
                     "TransferListingService::createTransferListing user result gamesStarted equals");
        assertEquals(playerSeasonStat.getGamesSubstitute(), userResult.getGamesSubstitute(),
                     "TransferListingService::createTransferListing user result gamesSubstitute equals");
        assertEquals(playerSeasonStat.getGamesDidNotParticipate(), userResult.getGamesDidNotParticipate(),
                     "TransferListingService::createTransferListing user result gamesDidNotParticipate equals");
        assertEquals(playerSeasonStat.getMinutesPlayed(), userResult.getMinutesPlayed(),
                     "TransferListingService::createTransferListing user result minutesPlayed equals");
        assertEquals(playerSeasonStat.getGoals(), userResult.getGoals(),
                     "TransferListingService::createTransferListing user result goals equals");
        assertEquals(playerSeasonStat.getGoalAssists(), userResult.getGoalAssists(),
                     "TransferListingService::createTransferListing user result goalAssists equals");
        assertEquals(playerSeasonStat.getOwnGoals(), userResult.getOwnGoals(),
                     "TransferListingService::createTransferListing user result ownGoals equals");
        assertEquals(playerSeasonStat.getGoalsConceded(), userResult.getGoalsConceded(),
                     "TransferListingService::createTransferListing user result goalsConceded equals");
        assertEquals(playerSeasonStat.getRating(), userResult.getRating(),
                     "TransferListingService::createTransferListing user result rating equals");
        assertEquals(playerSeasonStat.getPoints(), userResult.getPoints(),
                     "TransferListingService::createTransferListing user result points equals");

        d11Team.setOwner(generate(User.class));
        user.setAdministrator(true);

        final TransferListing administratorResult = this.transferListingService.createTransferListing(player.getId());

        assertEquals(player, administratorResult.getPlayer(),
                     "TransferListingService::createTransferListing administrator result player equals");
        assertEquals(playerSeasonStat.getTeam(), administratorResult.getTeam(),
                     "TransferListingService::createTransferListing administrator result team equals");
        assertEquals(playerSeasonStat.getD11Team(), administratorResult.getD11Team(),
                     "TransferListingService::createTransferListing administrator result position equals");
        assertEquals(playerSeasonStat.getPosition(), administratorResult.getPosition(),
                     "TransferListingService::createTransferListing administrator result position equals");
        assertEquals(playerSeasonStat.getFormPoints(), administratorResult.getFormPoints(),
                     "TransferListingService::createTransferListing administrator result formPoints equals");
        assertEquals(playerSeasonStat.getFormMatchPoints(), administratorResult.getFormMatchPoints(),
                     "TransferListingService::createTransferListing administrator result formMatchPoints equals");
        assertEquals(playerSeasonStat.getRanking(), administratorResult.getRanking(),
                     "TransferListingService::createTransferListing administrator result ranking equals");
        assertEquals(playerSeasonStat.getPointsPerAppearance(), administratorResult.getPointsPerAppearance(),
                     "TransferListingService::createTransferListing administrator result pointsPerAppearance equals");
        assertEquals(playerSeasonStat.getCleanSheets(), administratorResult.getCleanSheets(),
                     "TransferListingService::createTransferListing administrator result cleanSheets equals");
        assertEquals(playerSeasonStat.getYellowCards(), administratorResult.getYellowCards(),
                     "TransferListingService::createTransferListing administrator result yellowCards equals");
        assertEquals(playerSeasonStat.getRedCards(), administratorResult.getRedCards(),
                     "TransferListingService::createTransferListing administrator result redCards equals");
        assertEquals(playerSeasonStat.getSubstitutionsOn(), administratorResult.getSubstitutionsOn(),
                     "TransferListingService::createTransferListing administrator result substitutionsOn equals");
        assertEquals(playerSeasonStat.getSubstitutionsOff(), administratorResult.getSubstitutionsOff(),
                     "TransferListingService::createTransferListing administrator result substitutionsOff equals");
        assertEquals(playerSeasonStat.getManOfTheMatch(), administratorResult.getManOfTheMatch(),
                     "TransferListingService::createTransferListing administrator result manOfTheMatch equals");
        assertEquals(playerSeasonStat.getSharedManOfTheMatch(), administratorResult.getSharedManOfTheMatch(),
                     "TransferListingService::createTransferListing administrator result sharedManOfTheMatch equals");
        assertEquals(playerSeasonStat.getGamesStarted(), administratorResult.getGamesStarted(),
                     "TransferListingService::createTransferListing administrator result gamesStarted equals");
        assertEquals(playerSeasonStat.getGamesSubstitute(), administratorResult.getGamesSubstitute(),
                     "TransferListingService::createTransferListing administrator result gamesSubstitute equals");
        assertEquals(playerSeasonStat.getGamesDidNotParticipate(), administratorResult.getGamesDidNotParticipate(),
                 "TransferListingService::createTransferListing administrator result gamesDidNotParticipate equals");
        assertEquals(playerSeasonStat.getMinutesPlayed(), administratorResult.getMinutesPlayed(),
                     "TransferListingService::createTransferListing administrator result minutesPlayed equals");
        assertEquals(playerSeasonStat.getGoals(), administratorResult.getGoals(),
                     "TransferListingService::createTransferListing administrator result goals equals");
        assertEquals(playerSeasonStat.getGoalAssists(), administratorResult.getGoalAssists(),
                     "TransferListingService::createTransferListing administrator result goalAssists equals");
        assertEquals(playerSeasonStat.getOwnGoals(), administratorResult.getOwnGoals(),
                     "TransferListingService::createTransferListing administrator result ownGoals equals");
        assertEquals(playerSeasonStat.getGoalsConceded(), administratorResult.getGoalsConceded(),
                     "TransferListingService::createTransferListing administrator result goalsConceded equals");
        assertEquals(playerSeasonStat.getRating(), administratorResult.getRating(),
                     "TransferListingService::createTransferListing administrator result rating equals");
        assertEquals(playerSeasonStat.getPoints(), administratorResult.getPoints(),
                     "TransferListingService::createTransferListing administrator result points equals");
    }

    /**
     * Tests TransferListingService::getByTransferDayId.
     */
    @Test
    void testGetByTransferDayId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String transferDayIdProperty = "transferDayId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(null, 0),
                             "TransferListingService::getByTransferDayId null transferDayId throws");
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter(),
                     "TransferListingService::getByTransferDayId property equals null transferDayId");

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(-1L, 0),
                             "TransferListingService::getByTransferDayId invalid transferDayId throws");
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter(),
                     "TransferListingService::getByTransferDayId property equals invalid transferDayId");

        final BadRequestException invalidPageException =
                assertThrows(BadRequestException.class, () -> this.transferListingService.getByTransferDayId(1L, -1),
                             "TransferListingService::getByTransferDayId invalid page throws");
        assertEquals("page", invalidPageException.getParameter(),
                     "TransferListingService::getByTransferDayId property equals invalid page");

        // Success -----------------------------------------------------------------------------------------------------

        final TransferDay transferDay = generate(TransferDay.class);

        final List<TransferListing> transferListings = generateList(TransferListing.class);

        when(this.transferDayRepository.findById(eq(transferDay.getId() + 1))).thenReturn(Optional.empty());

        assertTrue(this.transferListingService.getByTransferDayId(transferDay.getId() + 1, 0).isEmpty(),
                   "TransferListingService::getByTransferDayId invalid transferDayId isEmpty");

        transferDay.setStatus(Status.PENDING);
        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));

        assertTrue(this.transferListingService.getByTransferDayId(transferDay.getId(), 0).isEmpty(),
                   "TransferListingService::getByTransferDayId transfer day pending isEmpty");

        transferDay.setStatus(Status.ACTIVE);
        when(this.transferListingRepository.findByTransferDayIdOrderByRanking(eq(transferDay.getId()),
                                                                              any(Pageable.class)))
                .thenReturn(transferListings);

        final List<TransferListing> result = this.transferListingService.getByTransferDayId(transferDay.getId(), 0);

        assertEquals(transferListings, result, "TransferListingService::getByTransferDayId result equals");

        verify(this.transferListingRepository, times(1)).findByTransferDayIdOrderByRanking(eq(transferDay.getId()),
                                                                                           any(Pageable.class));
    }

}
