package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.TransferBidRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.UserRepository;
import org.d11.boot.spring.security.JwtBuilder;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Transfer bid service tests.
 */
class TransferBidServiceTests extends BaseD11BootServiceTests {

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
     * Mocked application context.
     */
    @Mock
    private ApplicationContext applicationContext;

    /**
     * Mocked transfer bid repository.
     */
    @Mock
    private TransferBidRepository transferBidRepository;

    /**
     * Mocked transfer day repository.
     */
    @Mock
    private TransferDayRepository transferDayRepository;

    /**
     * Mocked user repository.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * Player transfer context service.
     */
    @Mock
    private PlayerTransferContextService playerTransferContextService;

    /**
     * Transfer listing service.
     */
    @InjectMocks
    private TransferBidService transferBidService;

    /**
     * Tests TransferBidService::getByTransferDayId.
     */
    @Test
    void testGetByTransferDayId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String transferDayIdProperty = "transferDayId";

        final BadRequestException nullTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferBidService.getByTransferDayId(null),
                             "TransferBidService::getByTransferDayId null transferDayId throws");
        assertEquals(transferDayIdProperty, nullTransferDayIdException.getParameter(),
                     "TransferBidService::getByTransferDayId property equals null transferDayId");

        final BadRequestException invalidTransferDayIdException =
                assertThrows(BadRequestException.class, () -> this.transferBidService.getByTransferDayId(-1L),
                             "TransferBidService::getByTransferDayId invalid transferDayId throws");
        assertEquals(transferDayIdProperty, invalidTransferDayIdException.getParameter(),
                     "TransferBidService::getByTransferDayId property equals invalid transferDayId");

        // Success -----------------------------------------------------------------------------------------------------

        final TransferDay transferDay = generate(TransferDay.class);

        final List<TransferBid> transferBids = generateList(TransferBid.class);

        when(this.transferDayRepository.findById(eq(transferDay.getId() + 1))).thenReturn(Optional.empty());

        assertTrue(this.transferBidService.getByTransferDayId(transferDay.getId() + 1).isEmpty(),
                   "TransferBidService::getByTransferDayId invalid transferDayId isEmpty");

        transferDay.setStatus(Status.PENDING);
        when(this.transferDayRepository.findById(eq(transferDay.getId()))).thenReturn(Optional.of(transferDay));

        assertTrue(this.transferBidService.getByTransferDayId(transferDay.getId()).isEmpty(),
                   "TransferBidService::getByTransferDayId transfer day pending isEmpty");

        transferDay.setStatus(Status.ACTIVE);
        assertTrue(this.transferBidService.getByTransferDayId(transferDay.getId()).isEmpty(),
                   "TransferBidService::getByTransferDayId transfer day active isEmpty");

        transferDay.setStatus(Status.FINISHED);
        when(this.transferBidRepository
                     .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(
                             eq(transferDay.getId()))
                     )
                .thenReturn(transferBids);

        final List<TransferBid> result = this.transferBidService.getByTransferDayId(transferDay.getId());

        assertEquals(transferBids, result, "TransferBidService::getByTransferDayId result equals");

        verify(this.transferBidRepository, times(1))
                .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(eq(transferDay.getId()));
    }

    // createTransferBid -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidService::createTransferBid with no user logged in.
     */
    @Test
    void testCreateTransferBidNoUser() {
        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(new PlayerTransferContext());

        assertThrows(ConflictException.class, () -> this.transferBidService.createTransferBid(1L, 5),
                     "TransferBidService::createTransferBid no user throws");
    }

    /**
     * Tests TransferBidService::createTransferBid with max bid zero.
     */
    @Test
    void testCreateTransferBidMaxBidZero() {
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(0);

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(ConflictException.class, () -> this.transferBidService.createTransferBid(1L, 5),
                     "TransferBidService::createTransferBid maxBid 0 throws");
    }

    /**
     * Tests TransferBidService::createTransferBid with existing bid.
     */
    @Test
    void testCreateTransferBidExistingBid() {
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(5);
        when(playerTransferContext.getActiveTransferBid()).thenReturn(generate(TransferBid.class));

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(ConflictException.class, () -> this.transferBidService.createTransferBid(1L, 5),
                     "TransferBidService::createTransferBid existing transferBid throws");
    }

    /**
     * Tests TransferBidService::createTransferBid with null fee.
     */
    @Test
    void testCreateTransferBidNullFee() {
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(5);

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(BadRequestException.class, () -> this.transferBidService.createTransferBid(1L, null),
                     "TransferBidService::createTransferBid null fee throws");
    }

    /**
     * Tests TransferBidService::createTransferBid with non-positive fee.
     */
    @Test
    void testCreateTransferBidNonPositiveFee() {
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(5);

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(BadRequestException.class, () -> this.transferBidService.createTransferBid(1L, 0),
                     "TransferBidService::createTransferBid non-positive fee throws");
    }

    /**
     * Tests TransferBidService::createTransferBid with fee greater than max bid.
     */
    @Test
    void testCreateTransferBidFeeGreaterThanMaxBid() {
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(5);

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(BadRequestException.class, () -> this.transferBidService.createTransferBid(1L, 10),
                     "TransferBidService::createTransferBid fee greater than maxBid throws");
    }

    /**
     * Tests TransferBidService::createTransferBid with invalid fee.
     */
    @Test
    void testCreateTransferBidInvalidFee() {
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(5);

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(BadRequestException.class, () -> this.transferBidService.createTransferBid(1L, 1),
                     "TransferBidService::createTransferBid invalid fee throws");
    }

    /**
     * Tests TransferBidService::createTransferBid.
     */
    @Test
    void testCreateTransferBid() {
        final TransferDay transferDay = generate(TransferDay.class);
        final D11Team d11Team = generate(D11Team.class);
        final Player player = generate(Player.class);
        final TransferListing transferListing = generate(TransferListing.class);
        final int fee = Transfer.FEE_DIVISOR;
        final int ranking = 1;

        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(fee);
        when(playerTransferContext.getTransferDay()).thenReturn(transferDay);
        when(playerTransferContext.getD11Team()).thenReturn(d11Team);
        when(playerTransferContext.getPlayer()).thenReturn(player);
        when(playerTransferContext.getTransferListing()).thenReturn(transferListing);
        when(playerTransferContext.getRanking()).thenReturn(ranking);
        when(playerTransferContext.isValidFee(eq(fee))).thenReturn(true);

        when(this.playerTransferContextService.getByPlayerId(eq(player.getId()))).thenReturn(playerTransferContext);

        when(this.transferBidRepository.save(any(TransferBid.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferBid transferBid = this.transferBidService.createTransferBid(player.getId(), fee);

        assertNotNull(transferBid, "TransferBidService::createTransferBid transferBid not null");
        assertEquals(transferDay, transferBid.getTransferDay(),
                     "TransferBidService::createTransferBid transferDay equals");
        assertEquals(d11Team, transferBid.getD11Team(),
                     "TransferBidService::createTransferBid d11Team equals");
        assertEquals(ranking, transferBid.getD11TeamRanking(), "TransferBidService::createTransferBid ranking equals");
        assertEquals(player, transferBid.getPlayer(), "TransferBidService::createTransferBid player equals");
        assertEquals(transferListing.getRanking(), transferBid.getPlayerRanking(),
                     "TransferBidService::createTransferBid playerRanking equals");
        assertEquals(fee, transferBid.getFee(),
                     "TransferBidService::createTransferBid fee equals");

        verify(this.playerTransferContextService, times(1)).getByPlayerId(eq(player.getId()));
        verify(this.transferBidRepository, times(1)).save(any(TransferBid.class));
    }

    // updateTransferBidFee --------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidService::updateTransferBidFee with invalid transfer bid id.
     */
    @Test
    void testUpdateTransferBidFeeInvalidTransferBidId() {
        assertThrows(BadRequestException.class, () -> this.transferBidService.updateTransferBidFee(null, 0),
                     "TransferBidService::updateTransferBidFee transferBidId invalid throws");
    }

    /**
     * Tests TransferBidService::updateTransferBidFee invalid fee.
     */
    @Test
    void testUpdateTransferBidFeeInvalidFee() {
        final TransferBid transferBid = generate(TransferBid.class);
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        final int maxBid = 50;
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(maxBid);
        when(playerTransferContext.getActiveTransferBid()).thenReturn(transferBid);
        when(playerTransferContext.isValidFee(any(Integer.class))).thenReturn(false);

        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(BadRequestException.class, () -> this.transferBidService.updateTransferBidFee(1L, 5),
                     "TransferBidService::updateTransferBidFee invalid fee throws");
    }

    /**
     * Tests TransferBidService::updateTransferBidFee with transfer bid not found.
     */
    @Test
    void testUpdateTransferBidFeeNotFound() {
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.transferBidService.updateTransferBidFee(1L, 0),
                     "TransferBidService::updateTransferBidFee transferBid not found throws");
    }

    /**
     * Tests TransferBidService::updateTransferBidFee for forbidden.
     */
    @Test
    void testUpdateTransferBidFeeForbidden() {
        final TransferBid transferBid = generate(TransferBid.class);
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(ForbiddenException.class, () -> this.transferBidService.updateTransferBidFee(1L, 0),
                     "TransferBidService::updateTransferBidFee transferBid forbidden throws");
    }

    /**
     * Tests TransferBidService::updateTransferBidFee for transfer bid not allowed.
     */
    @Test
    void testUpdateTransferBidFeeTransferBidNotAllowed() {
        final TransferBid transferBid = generate(TransferBid.class);
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(0);
        when(playerTransferContext.getActiveTransferBid()).thenReturn(transferBid);
        when(this.playerTransferContextService.getByPlayerId(any(Long.class))).thenReturn(playerTransferContext);

        assertThrows(ConflictException.class, () -> this.transferBidService.updateTransferBidFee(1L, 0),
                     "TransferBidService::updateTransferBidFee transferBid not allowed throws");
    }

    /**
     * Tests TransferBidService::updateTransferBidFee.
     */
    @Test
    void testUpdateTransferBidFee() {
        final TransferBid transferBid = generate(TransferBid.class);
        when(this.transferBidRepository.findById(eq(transferBid.getId()))).thenReturn(Optional.of(transferBid));

        final int fee = 5;
        final int maxBid = 50;
        final PlayerTransferContext playerTransferContext = mock(PlayerTransferContext.class);
        when(playerTransferContext.getMaxBid()).thenReturn(maxBid);
        when(playerTransferContext.getActiveTransferBid()).thenReturn(transferBid);
        when(playerTransferContext.isValidFee(eq(fee))).thenReturn(true);
        when(this.playerTransferContextService.getByPlayerId(eq(transferBid.getPlayer().getId())))
                .thenReturn(playerTransferContext);

        when(this.transferBidRepository.save(any(TransferBid.class))).then(AdditionalAnswers.returnsFirstArg());

        final TransferBid updatedTransferBid = this.transferBidService.updateTransferBidFee(transferBid.getId(), fee);

        assertNotNull(updatedTransferBid, "TransferBidService::updateTransferBidFee updatedTransferBid not null");
        assertEquals(fee, updatedTransferBid.getFee(),
                     "TransferBidService::updateTransferBidFee updatedTransferBid fee equals");
        assertEquals(fee, updatedTransferBid.getActiveFee(),
                     "TransferBidService::updateTransferBidFee updatedTransferBid activeFee equals");

        verify(this.transferBidRepository, times(1)).findById(eq(transferBid.getId()));
        verify(this.playerTransferContextService, times(1)).getByPlayerId(eq(transferBid.getPlayer().getId()));
        verify(this.transferBidRepository, times(1)).save(eq(transferBid));
    }

    // deleteTransferBid -----------------------------------------------------------------------------------------------

    /**
     * Tests TransferBidService::deleteTransferBid with invalid transfer bid id.
     */
    @Test
    void testDeleteTransferBidInvalidTransferBidId() {
        assertThrows(BadRequestException.class, () -> this.transferBidService.deleteTransferBid(null),
                     "TransferBidService::deleteTransferBid transferBidId invalid throws");
    }

    /**
     * Tests TransferBidService::deleteTransferBid with transfer bid not found.
     */
    @Test
    void testDeleteTransferBidNotFound() {
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.transferBidService.deleteTransferBid(1L),
                     "TransferBidService::deleteTransferBid transferBid not found throws");
    }

    /**
     * Tests TransferBidService::deleteTransferBid for unauthorized.
     */
    @Test
    void testDeleteTransferBidUnauthorized() {
        final TransferBid transferBid = generate(TransferBid.class);
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        SecurityContextHolder.getContext().setAuthentication(null);

        assertThrows(UnauthorizedException.class, () -> this.transferBidService.deleteTransferBid(1L),
                     "TransferBidService::deleteTransferBid transferBid unauthorized throws");
    }

    /**
     * Tests TransferBidService::deleteTransferBid for forbidden.
     */
    @Test
    void testDeleteTransferBidForbidden() {
        final TransferBid transferBid = generate(TransferBid.class);
        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        final User owner = generate(User.class);
        owner.setAdministrator(false);

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.jwt.getClaimAsString(JwtBuilder.USERNAME_CLAIM)).thenReturn(JwtBuilder.USERNAME_CLAIM);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.userRepository.findByEmail(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(Optional.of(owner));

        assertThrows(ForbiddenException.class, () -> this.transferBidService.deleteTransferBid(1L),
                     "TransferBidService::deleteTransferBid transferBid forbidden throws");
    }

    /**
     * Tests TransferBidService::deleteTransferBid for invalid transfer day status.
     */
    @Test
    void testDeleteTransferBidInvalidTransferDayStatus() {
        final TransferBid transferBid = generate(TransferBid.class);
        transferBid.getTransferDay().setStatus(Status.PENDING);

        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        final User owner = generate(User.class);
        owner.setAdministrator(true);

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.jwt.getClaimAsString(JwtBuilder.USERNAME_CLAIM)).thenReturn(JwtBuilder.USERNAME_CLAIM);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);
        when(this.userRepository.findByEmail(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(Optional.of(owner));

        assertThrows(ConflictException.class, () -> this.transferBidService.deleteTransferBid(1L),
                     "TransferBidService::deleteTransferBid transferBid forbidden throws");
    }

    /**
     * Tests TransferBidService::deleteTransferBid.
     */
    @Test
    void testDeleteTransferBid() {
        final TransferBid transferBid = generate(TransferBid.class);
        transferBid.getTransferDay().setStatus(Status.ACTIVE);

        when(this.transferBidRepository.findById(any(Long.class))).thenReturn(Optional.of(transferBid));

        SecurityContextHolder.getContext().setAuthentication(this.authentication);
        when(this.authentication.getPrincipal()).thenReturn(this.jwt);
        when(this.jwt.getClaimAsString(JwtBuilder.USERNAME_CLAIM)).thenReturn(JwtBuilder.USERNAME_CLAIM);
        when(this.applicationContext.getBean(eq(UserRepository.class))).thenReturn(this.userRepository);

        final User owner = generate(User.class);
        transferBid.getD11Team().setOwner(owner);

        when(this.userRepository.findByEmail(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(Optional.of(owner));

        assertDoesNotThrow(() -> this.transferBidService.deleteTransferBid(transferBid.getId()),
                           "TransferBidService::deleteTransferBid user does not throw");

        final User administrator = generate(User.class);
        administrator.setAdministrator(true);

        when(this.userRepository.findByEmail(eq(JwtBuilder.USERNAME_CLAIM))).thenReturn(Optional.of(administrator));

        assertDoesNotThrow(() -> this.transferBidService.deleteTransferBid(transferBid.getId()),
                           "TransferBidService::deleteTransferBid administrator does not throw");

        verify(this.transferBidRepository, times(2)).delete(transferBid);
    }

}
