package org.d11.boot.spring.service;

import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.TransferBid;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.TransferBidRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.NotFoundException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Transfer bid service.
 */
@Service
public class TransferBidService extends RepositoryService<TransferBid, TransferBidRepository> {

    /**
     * Fee property name.
     */
    private static final String FEE = "fee";

    /**
     * Transfer bid id property name.
     */
    private static final String TRANSFER_BID_ID = "transferBidId";

    /**
     * Repository for looking up current transfer day.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Service for looking up player transfer context.
     */
    private final PlayerTransferContextService playerTransferContextService;

    /**
     * Creates a new transfer bid service.
     *
     * @param transferBidRepository The transfer bid repository the service will use.
     * @param transferDayRepository The transfer day repository the service will use.
     * @param playerTransferContextService The player transfer context repository the service will use.
     */
    @Autowired
    public TransferBidService(final TransferBidRepository transferBidRepository,
                              final TransferDayRepository transferDayRepository,
                              final PlayerTransferContextService playerTransferContextService) {
        super(TransferBid.class, transferBidRepository);
        this.transferDayRepository = transferDayRepository;
        this.playerTransferContextService = playerTransferContextService;
    }

    /**
     * Get transfer bids by transfer day id ordered by ranking.
     *
     * @param transferDayId The transfer day id.
     * @return Transfer bids by transfer day id ordered by ranking in pages of size 25.
     */
    public List<TransferBid> getByTransferDayId(final Long transferDayId) {
        if (transferDayId == null || transferDayId <= 0) {
            throw new BadRequestException("transferDayId", "must be positive");
        }

        final List<TransferBid> transferBids = new ArrayList<>();

        final Optional<TransferDay> optional = this.transferDayRepository.findById(transferDayId);

        optional.ifPresent(transferDay -> {
            if (Status.FINISHED.equals(transferDay.getStatus())) {
                transferBids.addAll(getJpaRepository()
                        .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDayId));
            }
        });

        return transferBids;
    }

    /**
     * Creates a new transfer bid.
     *
     * @param playerId The player id.
     * @param fee The fee.
     * @return The created transfer bid.
     */
    @Transactional
    public TransferBid createTransferBid(final Long playerId, final Integer fee) {
        final PlayerTransferContext playerTransferContext = this.playerTransferContextService.getByPlayerId(playerId);

        if (playerTransferContext.getMaxBid() <= 0 || playerTransferContext.getActiveTransferBid() != null) {
            throw new ConflictException(ErrorCode.CONFLICT_TRANSFER_BID_NOT_ALLOWED);
        } else if (!playerTransferContext.isValidFee(fee)) {
            throw new BadRequestException(FEE, ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        final TransferBid transferBid = new TransferBid();
        transferBid.setTransferDay(playerTransferContext.getTransferDay());
        transferBid.setD11Team(playerTransferContext.getD11Team());
        transferBid.setD11TeamRanking(playerTransferContext.getRanking());
        transferBid.setPlayer(playerTransferContext.getPlayer());
        transferBid.setPlayerRanking(playerTransferContext.getTransferListing().getRanking());
        transferBid.setFee(fee);

        return getJpaRepository().save(transferBid);
    }

    /**
     * Updates the fee of an existing transfer bid.
     *
     * @param transferBidId The transfer bid id.
     * @param fee The new fee.
     * @return The updated transfer bid.
     */
    @Transactional
    public TransferBid updateTransferBidFee(final Long transferBidId, final Integer fee) {
        if (transferBidId == null) {
            throw new BadRequestException(TRANSFER_BID_ID, ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING);
        }

        final TransferBid transferBid = getJpaRepository().findById(transferBidId)
                .orElseThrow(() -> new NotFoundException(transferBidId, TransferBid.class));

        final PlayerTransferContext playerTransferContext = this.playerTransferContextService
                .getByPlayerId(transferBid.getPlayer().getId());

        if (!transferBid.equals(playerTransferContext.getActiveTransferBid())) {
            // This could let an owner know if there's a bid from at least one other owner on the player. We could
            // throw not found instead but for now let's put more importance on being consistent
            throw new ForbiddenException();
        }

        if (playerTransferContext.getMaxBid() <= 0) {
            throw new ConflictException(ErrorCode.CONFLICT_TRANSFER_BID_NOT_ALLOWED);
        } else if (!playerTransferContext.isValidFee(fee)) {
            throw new BadRequestException(FEE, ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        transferBid.setFee(fee);
        transferBid.setActiveFee(fee);

        return getJpaRepository().save(transferBid);
    }

    /**
     * Deletes a transfer bid.
     *
     * @param transferBidId The transfer bid id.
     */
    @Transactional
    public void deleteTransferBid(final Long transferBidId) {
        if (transferBidId == null) {
            throw new BadRequestException(TRANSFER_BID_ID, ErrorCode.BAD_REQUEST_PROPERTY_IS_MISSING);
        }

        final TransferBid transferBid = getJpaRepository().findById(transferBidId)
                .orElseThrow(() -> new NotFoundException(transferBidId, TransferBid.class));

        final User user = getCurrentUser().orElseThrow(UnauthorizedException::new);

        if (!transferBid.getD11Team().isAdministratedBy(user)) {
            throw new ForbiddenException();
        }

        if (!Status.ACTIVE.equals(transferBid.getTransferDay().getStatus())) {
            throw new ConflictException(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS);
        }

        getJpaRepository().delete(transferBid);
    }

}
