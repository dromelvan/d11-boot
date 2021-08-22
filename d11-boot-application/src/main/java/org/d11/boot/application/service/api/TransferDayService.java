package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayResultDTO;
import org.d11.boot.application.model.jpa.PlayerSeasonStat;
import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.model.jpa.Status;
import org.d11.boot.application.model.jpa.TransferDay;
import org.d11.boot.application.model.jpa.TransferListing;
import org.d11.boot.application.model.jpa.TransferWindow;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.repository.TransferWindowRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provides transfer day services.
 */
@Service
public class TransferDayService extends ApiRepositoryService<TransferDay, TransferDayDTO, TransferDayRepository> {

    /**
     * The transfer window repository this service will use.
     */
    private final TransferWindowRepository transferWindowRepository;
    /**
     * The transfer listing repository this service will use.
     */
    private final TransferListingRepository transferListingRepository;
    /**
     * The player season stat repository this service will use.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Creates a new service.
     *
     * @param transferDayRepository      The transfer day repository this service will use.
     * @param transferWindowRepository   The transfer window repository this service will use.
     * @param transferListingRepository  The transfer listing repository this service will use.
     * @param playerSeasonStatRepository The player season stat repository this service will use.
     */
    @Autowired
    public TransferDayService(final TransferDayRepository transferDayRepository,
                              final TransferWindowRepository transferWindowRepository,
                              final TransferListingRepository transferListingRepository,
                              final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(transferDayRepository);
        this.transferWindowRepository = transferWindowRepository;
        this.transferListingRepository = transferListingRepository;
        this.playerSeasonStatRepository = playerSeasonStatRepository;
    }

    /**
     * Gets the current transfer day.
     *
     * @return The current transfer day DTO.
     */
    public TransferDayDTO findCurrentTransferDay() {
        final Optional<TransferDay> optional = getJpaRepository().findFirstByOrderByDatetimeDesc();
        return mapIfFound(optional.orElse(null));
    }

    /**
     * Gets transfer days for a specific transfer window.
     *
     * @param transferWindowId Id for the transfer window for which transfer days will be looked up.
     * @return Transfer days for the transfer window.
     */
    public List<TransferDayDTO> findByTransferWindowId(final long transferWindowId) {
        final List<TransferDay> transferDays = getJpaRepository().findByTransferWindowIdOrderByDatetimeDesc(transferWindowId);
        return map(transferDays);
    }

    /**
     * Activates a transfer day and inserts transfer listings for all players who don't belong to a D11 team.
     *
     * @param transferDayId Id for the transfer day that will be activated.
     * @return Result of the activation with id of the updated entity if successful and list of errors if not.
     */
    @Transactional
    public UpdateTransferDayResultDTO activateTransferDayByTransferDayId(final long transferDayId) {
        final TransferDay transferDay = getJpaRepository().findById(transferDayId).orElseThrow(NotFoundException::new);
        final TransferWindow transferWindow = transferDay.getTransferWindow();

        final List<String> errors = new ArrayList<>();

        if(transferDay.getStatus() == Status.PENDING) {
            transferDay.setStatus(Status.ACTIVE);
            transferWindow.setStatus(Status.ACTIVE);

            final Season season = transferWindow.getMatchWeek().getSeason();
            final List<PlayerSeasonStat> playerSeasonStats =
                    this.playerSeasonStatRepository.findBySeasonIdAndD11TeamDummyOrderByRankingAsc(season.getId(), true);

            final List<TransferListing> transferListings = playerSeasonStats
                    .stream()
                    .map(playerSeasonStat -> {
                        final TransferListing transferListing = map(playerSeasonStat, new TransferListing());
                        transferListing.setId(null);
                        transferListing.setTransferDay(transferDay);
                        return transferListing;
                    })
                    .collect(Collectors.toList());

            getJpaRepository().save(transferDay);
            this.transferWindowRepository.save(transferWindow);
            this.transferListingRepository.saveAll(transferListings);
        } else {
            errors.add("Transfer day status is " + transferDay.getStatus() + ".");
            rollback();
        }

        return new UpdateTransferDayResultDTO()
                .transferDayId(transferDay.getId())
                .errors(errors);
    }

}
