package org.d11.boot.application.service.api;

import org.d11.boot.api.model.InsertTransferListingDTO;
import org.d11.boot.api.model.InsertTransferListingResultDTO;
import org.d11.boot.api.model.TransferListingDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.model.User;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.util.BadRequestException;
import org.d11.boot.application.util.ForbiddenException;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Provides transfer day services.
 */
@Service
public class TransferListingService extends ApiRepositoryService<TransferListing, TransferListingDTO, TransferListingRepository> {

    /**
     * Page size for when getting transfer listings for a transfer day.
     */
    public static final int PAGE_SIZE = 25;
    /**
     * Repository for looking up current transfer day.
     */
    private final TransferDayRepository transferDayRepository;
    /**
     * Repository for looking up player season stats.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Creates a new service.
     *
     * @param transferListingRepository  The repository this service will use.
     * @param transferDayRepository      Repository for looking up current transfer day.
     * @param playerSeasonStatRepository Repository for looking up player season stats.
     */
    @Autowired
    public TransferListingService(final TransferListingRepository transferListingRepository,
                                  final TransferDayRepository transferDayRepository,
                                  final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(transferListingRepository);
        this.transferDayRepository = transferDayRepository;
        this.playerSeasonStatRepository = playerSeasonStatRepository;
    }

    /**
     * Gets transfer listings for a specific transfer day.
     *
     * @param transferDayId Id for the transfer day for which transfer listings will be looked up.
     * @param page Page number (25 per page) for the search result page that will be returned.
     * @return Transfer listings for the transfer day, in pages of size 25.
     */
    public List<TransferListingDTO> findByTransferDayId(final long transferDayId, final int page) {
        final Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("ranking"));
        final List<TransferListing> transferListings = getJpaRepository().findByTransferDayIdOrderByRanking(transferDayId, pageable);
        return map(transferListings);
    }

    /**
     * Inserts a new transfer listing, provided it is valid.
     *
     * @param insertTransferListingDTO DTO providing id of the player that will be transfer listed.
     * @return Insert transfer listing result with remaining transfer count.
     */
    @Transactional
    public InsertTransferListingResultDTO insertTransferListing(final InsertTransferListingDTO insertTransferListingDTO) {
        // Get the current transfer day and check that it is pending.
        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc().orElseThrow(NotFoundException::new);
        if(!Status.PENDING.equals(transferDay.getStatus())) {
            throw new BadRequestException("Current transfer day is not pending. Transfer listings not allowed.");
        }

        final Season season = getCurrentSeason();
        final PlayerSeasonStat playerSeasonStat = this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(
                    insertTransferListingDTO.getPlayerId(),
                    season.getId())
                .orElseThrow(NotFoundException::new);
        final D11Team d11Team = playerSeasonStat.getD11Team();
        final User user = getCurrentUser();

        // Check that the current user is authorized to transfer list the player.
        if(user.isAdministrator() || user.equals(d11Team.getOwner()) || user.equals(d11Team.getCoOwner())) {
            // Check that the D11 team still has remaining transfers for the season.
            final List<TransferListing> transferListings =
                    getJpaRepository().findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), d11Team.getId());
            if(transferListings.size() >= season.getMaxTransfers()) {
                throw new BadRequestException("D11 team has no remaining transfer listings.");
            }

            // Check that the player is not already transfer listed.
            getJpaRepository().findByTransferDayIdAndPlayerId(transferDay.getId(), insertTransferListingDTO.getPlayerId())
                    .ifPresent(transferListing -> {
                        throw new BadRequestException("Player is already transfer listed.");
                    });

            // Create and save new transfer listing.
            final TransferListing transferListing = new TransferListing();
            transferListing.init(playerSeasonStat);
            transferListing.setTransferDay(transferDay);
            getJpaRepository().save(transferListing);

            return new InsertTransferListingResultDTO()
                    .playerId(transferListing.getPlayer().getId())
                    .remainingTransfers(season.getMaxTransfers() - transferListings.size() - 1);
        }

        throw new ForbiddenException("User is not owner of player D11 team.");
    }

}
