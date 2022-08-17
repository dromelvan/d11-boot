package org.d11.boot.application.service.api;

import org.d11.boot.api.model.DeleteTransferBidDTO;
import org.d11.boot.api.model.DeleteTransferBidResultDTO;
import org.d11.boot.api.model.InsertTransferBidDTO;
import org.d11.boot.api.model.InsertTransferBidResultDTO;
import org.d11.boot.api.model.TransferBidDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.D11TeamSeasonStat;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferBid;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.model.User;
import org.d11.boot.application.model.util.Current;
import org.d11.boot.application.model.validation.TransferFeeValidator;
import org.d11.boot.application.repository.TransferBidRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.util.BadRequestException;
import org.d11.boot.application.util.ForbiddenException;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides transfer bid services.
 */
@Service
public class TransferBidService extends ApiRepositoryService<TransferBid, TransferBidDTO, TransferBidRepository> {

    /**
     * Repository for looking up current transfer day.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Creates a new service.
     *
     * @param transferBidRepository The repository this service will use.
     * @param transferDayRepository Repository for looking up current transfer day.
     */
    @Autowired
    public TransferBidService(final TransferBidRepository transferBidRepository, final TransferDayRepository transferDayRepository) {
        super(transferBidRepository);
        this.transferDayRepository = transferDayRepository;
    }

    /**
     * Gets transfer bids for a specific transfer day, ordered by player ranking, active fee, descending and d11 team ranking.
     *
     * @param transferDayId Id for the transfer day for which transfer bids will be looked up.
     * @return Transfer bids for the transfer day.
     */
    public List<TransferBidDTO> findByTransferDayId(final long transferDayId) {
        final TransferDay transferDay = this.transferDayRepository.findById(transferDayId).orElseThrow(NotFoundException::new);
        final List<TransferBid> transferBids = getJpaRepository().findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDayId);

        if(!Status.FINISHED.equals(transferDay.getStatus())) {
            getCurrentUser().ifPresentOrElse(
                    user -> transferBids.removeIf(transferBid -> !transferBid.getD11Team().isAdministratedBy(user)),
                    transferBids::clear
            );
        }

        return map(transferBids);
    }

    /**
     * Inserts a new transfer bid, provided it is valid.
     *
     * @param insertTransferBidDTO DTO providing player id and fee.
     * @return Insert transfer bid result.
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public InsertTransferBidResultDTO insertTransferBid(final InsertTransferBidDTO insertTransferBidDTO) {
        if(!new TransferFeeValidator().isValid(insertTransferBidDTO.getFee())) {
            throw new BadRequestException("Invalid fee.");
        }

        // Get the current transfer day and check that it is active.
        final TransferDay transferDay = Current.getTransferDay();
        if(!Status.ACTIVE.equals(transferDay.getStatus())) {
            throw new BadRequestException("Current transfer day is not active. Transfer bids not allowed.");
        }

        final User user = getCurrentUser().orElseThrow(NotFoundException::new);

        final D11Team d11Team = user.getD11Team()
                .orElseThrow(() -> new BadRequestException("User does not own a D11 team."));

        final TransferListing transferListing = transferDay.getTransferListingByPlayerId(insertTransferBidDTO.getPlayerId())
                .orElseThrow(() -> new NotFoundException("Player is not available for transfer."));

        transferListing.getPlayer().getCurrentTransferBidByD11TeamId(d11Team.getId()).ifPresent(transferBid -> {
            throw new BadRequestException("A transfer bid already exists. Use update transfer bid instead.");
        });

        if(!d11Team.isBiddablePosition(transferListing.getPosition())) {
            throw new BadRequestException("D11 team not able to bid on position.");
        }

        if(insertTransferBidDTO.getFee() > d11Team.getMaxBid()) {
            throw new BadRequestException("Fee is higher than D11 team max bid.");
        }

        final D11TeamSeasonStat d11TeamSeasonStat = d11Team.getCurrentD11TeamSeasonStat()
                .orElseThrow(() -> new BadRequestException("D11 team season stats not found for current season."));

        TransferBid transferBid = new TransferBid();
        transferBid.setTransferDay(transferDay);
        transferBid.setD11Team(d11Team);
        transferBid.setD11TeamRanking(d11TeamSeasonStat.getRanking());
        transferBid.setPlayer(transferListing.getPlayer());
        transferBid.setPlayerRanking(transferListing.getRanking());
        transferBid.setFee(insertTransferBidDTO.getFee());

        transferBid = getJpaRepository().save(transferBid);

        return new InsertTransferBidResultDTO()
                .transferBidId(transferBid.getId())
                .playerId(transferBid.getPlayer().getId())
                .fee(transferBid.getFee())
                .transferDayId(transferDay.getId());
    }

    /**
     * Deletes a transfer bid.
     *
     * @param deleteTransferBidDTO The transfer bid that will be deleted.
     * @return Delete transfer bid result.
     */
    public DeleteTransferBidResultDTO deleteTransferBid(final DeleteTransferBidDTO deleteTransferBidDTO) {
        final TransferBid transferBid = getJpaRepository().findById(deleteTransferBidDTO.getTransferBidId())
                .orElseThrow(NotFoundException::new);

        if(!Status.ACTIVE.equals(transferBid.getTransferDay().getStatus())) {
            throw new BadRequestException("Transfer day is not active.");
        }

        final User user = getCurrentUser().orElseThrow(ForbiddenException::new);
        if(!transferBid.getD11Team().isAdministratedBy(user)) {
            throw new ForbiddenException();
        }

        getJpaRepository().delete(transferBid);

        return new DeleteTransferBidResultDTO()
                .transferBidId(transferBid.getId())
                .transferDayId(transferBid.getTransferDay().getId());
    }

}
