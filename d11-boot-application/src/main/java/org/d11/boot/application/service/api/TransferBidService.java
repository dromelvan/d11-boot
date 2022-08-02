package org.d11.boot.application.service.api;

import org.d11.boot.api.model.InsertTransferBidDTO;
import org.d11.boot.api.model.InsertTransferBidResultDTO;
import org.d11.boot.api.model.TransferBidDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.D11TeamSeasonStat;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.Transfer;
import org.d11.boot.application.model.TransferBid;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TransferBidRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.util.BadRequestException;
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
     * Repository for looking up transfer listings.
     */
    private final TransferListingRepository transferListingRepository;
    /**
     * Repository for looking up player season stats.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;
    /**
     * Repository for looking up D11 team season stats.
     */
    private final D11TeamSeasonStatRepository d11TeamSeasonStatRepository;

    /**
     * Creates a new service.
     *
     * @param transferBidRepository       The repository this service will use.
     * @param transferDayRepository       Repository for looking up current transfer day.
     * @param transferListingRepository   Repository for looking up transfer listings.
     * @param playerSeasonStatRepository  Repository for looking up player season stats.
     * @param d11TeamSeasonStatRepository Repository for looking up D11 team season stats.
     */
    @Autowired
    public TransferBidService(final TransferBidRepository transferBidRepository,
                              final TransferDayRepository transferDayRepository,
                              final TransferListingRepository transferListingRepository,
                              final PlayerSeasonStatRepository playerSeasonStatRepository,
                              final D11TeamSeasonStatRepository d11TeamSeasonStatRepository) {
        super(transferBidRepository);
        this.transferDayRepository = transferDayRepository;
        this.transferListingRepository = transferListingRepository;
        this.playerSeasonStatRepository = playerSeasonStatRepository;
        this.d11TeamSeasonStatRepository = d11TeamSeasonStatRepository;
    }

    /**
     * Gets transfer bids for a specific transfer day, ordered by player ranking, active fee, descending and d11 team ranking.
     *
     * @param transferDayId Id for the transfer day for which transfer bids will be looked up.
     * @return Transfer bids for the transfer day.
     */
    public List<TransferBidDTO> findByTransferDayId(final long transferDayId) {
        final List<TransferBid> transferBids = getJpaRepository().findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDayId);
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
        // Get the current transfer day and check that it is pending.
        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc().orElseThrow(NotFoundException::new);
        if(!Status.PENDING.equals(transferDay.getStatus())) {
            throw new BadRequestException("Current transfer day is not pending. Transfer listings not allowed.");
        }

        final User user = getCurrentUser();
        final D11Team d11Team = user.getOwnedD11Team() == null ? user.getCoOwnedD11Team() : user.getOwnedD11Team();
        if(d11Team == null) {
            throw new BadRequestException("User does not own a D11 team.");
        }

        final TransferListing transferListing =
                this.transferListingRepository.findByTransferDayIdAndPlayerId(transferDay.getId(), insertTransferBidDTO.getPlayerId())
                        .orElseThrow(() -> new BadRequestException("Player is not available for transfer."));

        final Player player = transferListing.getPlayer();
        getJpaRepository().findByPlayerIdAndD11TeamIdAndTransferDayId(player.getId(), d11Team.getId(), transferDay.getId()).ifPresent(transferBid -> {
            throw new BadRequestException("A transfer bid for " + player.getName() + " already exists. Use update transfer bid instead.");
        });

        final Season season = getCurrentSeason();
        final List<PlayerSeasonStat> playerSeasonStats =
                this.playerSeasonStatRepository.findByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());

        final int positionCount = (int) playerSeasonStats.stream()
                        .filter(playerSeasonStat -> playerSeasonStat.getPosition().equals(transferListing.getPosition()))
                        .count();
        if(positionCount >= transferListing.getPosition().getMaxCount()) {
            throw new BadRequestException("D11 team not able to bid on position " + transferListing.getPosition().getName() + ".");
        }

        final int neededPlayers = 10 - playerSeasonStats.size();
        final int total = playerSeasonStats.stream().mapToInt(PlayerSeasonStat::getValue).sum();
        final int maxBid = neededPlayers > 0 ? season.getD11TeamBudget() - total - Transfer.FEE_DIVISOR * neededPlayers : 0;
        if(insertTransferBidDTO.getFee() > maxBid) {
            throw new BadRequestException("Fee is higher than D11 team max bid.");
        }

        final D11TeamSeasonStat d11TeamSeasonStat = this.d11TeamSeasonStatRepository.findByD11TeamIdAndSeasonId(d11Team.getId(), season.getId())
                .orElseThrow(BadRequestException::new);

        TransferBid transferBid = new TransferBid();
        transferBid.setD11Team(d11Team);
        transferBid.setTransferDay(transferDay);
        transferBid.setD11TeamRanking(d11TeamSeasonStat.getRanking());
        transferBid.setPlayer(transferListing.getPlayer());
        transferBid.setPlayerRanking(transferListing.getRanking());
        transferBid.setFee(insertTransferBidDTO.getFee());

        if(!transferBid.isValid()) {
            throw new BadRequestException("Invalid fee.");
        }

        transferBid = getJpaRepository().save(transferBid);

        return new InsertTransferBidResultDTO()
                .transferBidId(transferBid.getId())
                .playerId(player.getId())
                .fee(transferBid.getFee());
    }

}
