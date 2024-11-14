package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferListing;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferListingRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ConflictException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.ForbiddenException;
import org.d11.boot.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Transfer listing service.
 */
@Service
public class TransferListingService extends RepositoryService<TransferListing, TransferListingRepository> {

    /**
     * Transfer listing list page size.
     */
    public static final int PAGE_SIZE = 25;

    /**
     * Repository for looking up current transfer day.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Creates a new transfer listing service.
     *
     * @param transferListingRepository The transfer listing repository the service will use.
     * @param transferDayRepository     The transfer day repository the service will use.
     */
    @Autowired
    public TransferListingService(final TransferListingRepository transferListingRepository,
                                  final TransferDayRepository transferDayRepository) {
        super(TransferListing.class, transferListingRepository);
        this.transferDayRepository = transferDayRepository;
    }

    /**
     * Creates a new transfer listing for a player in the current transfer window.
     *
     * @param playerId The player id.
     * @return The created transfer listing.
     */
    public TransferListing createTransferListing(final Long playerId) {
        if (playerId == null) {
            throw new BadRequestException("playerId", "is missing");
        }

        final Season season = getCurrentSeason();

        final PlayerSeasonStat playerSeasonStat = getRepository(PlayerSeasonStatRepository.class)
                .findByPlayerIdAndSeasonId(playerId, season.getId())
                .orElseThrow(() -> new ConflictException(ErrorCode.CONFLICT_NO_PLAYER_SEASON_STAT));
        final D11Team d11Team = playerSeasonStat.getD11Team();

        getCurrentUser().ifPresentOrElse(
                user -> {
                    if (!d11Team.isAdministratedBy(user)) {
                        throw new ForbiddenException();
                    }
                },
                () -> {
                    throw new UnauthorizedException();
                }
        );

        final Collection<TransferListing> transferListings = getJpaRepository()
                .findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(season.getId(), d11Team.getId());
        if (transferListings.size() >= season.getD11TeamMaxTransfers()) {
            throw new ConflictException(ErrorCode.CONFLICT_NO_REMAINING_D11_TEAM_TRANSFERS);
        }

        final TransferDay transferDay = getRepository(TransferDayRepository.class).findFirstByOrderByDatetimeDesc()
                .orElseThrow(() -> new ConflictException(ErrorCode.CONFLICT_NO_CURRENT_TRANSFER_DAY));
        if (!Status.PENDING.equals(transferDay.getStatus())) {
            throw new ConflictException(ErrorCode.CONFLICT_INVALID_TRANSFER_DAY_STATUS);
        }

        getJpaRepository().findByTransferDayIdAndPlayerId(transferDay.getId(), playerId).ifPresent(transferListing -> {
            throw new ConflictException(ErrorCode.CONFLICT_NON_UNIQUE_TRANSFER_LISTING);
        });

        final TransferListing transferListing = new TransferListing();
        transferListing.init(playerSeasonStat);
        transferListing.setTransferDay(transferDay);

        return getJpaRepository().save(transferListing);
    }

    /**
     * Get transfer listings by transfer day id ordered by ranking.
     *
     * @param transferDayId The transfer day id.
     * @param page          Page number (25 per page) for the search result page that will be returned.
     * @return Transfer listings by transfer day id ordered by ranking in pages of size 25.
     */
    public List<TransferListing> getByTransferDayId(final Long transferDayId, final int page) {
        if (transferDayId == null || transferDayId <= 0) {
            throw new BadRequestException("transferDayId", "must be positive");
        }

        if (page < 0) {
            throw new BadRequestException("page", "must be non-negative");
        }

        final List<TransferListing> transferListings = new ArrayList<>();

        final Optional<TransferDay> optional = this.transferDayRepository.findById(transferDayId);

        optional.ifPresent(transferDay -> {
            if (!Status.PENDING.equals(transferDay.getStatus())) {
                final Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("ranking"));
                transferListings.addAll(getJpaRepository().findByTransferDayIdOrderByRanking(transferDayId, pageable));
            }
        });

        return transferListings;
    }

}
