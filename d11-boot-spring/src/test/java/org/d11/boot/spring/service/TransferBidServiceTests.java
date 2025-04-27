package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.repository.TransferBidRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

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

}
