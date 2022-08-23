package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TransferDayDTO;
import org.d11.boot.api.model.UpdateTransferDayResultDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.D11TeamTransferProperties;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.Transfer;
import org.d11.boot.application.model.TransferBid;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.TransferListing;
import org.d11.boot.application.model.TransferWindow;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TransferBidRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.repository.TransferRepository;
import org.d11.boot.application.repository.TransferWindowRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
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

            final Map<Long, TransferListing> transferListingMap = transferDay.getTransferListings().stream()
                    .collect(Collectors.toMap(transferListing -> transferListing.getPlayer().getId(),
                                              transferListing -> transferListing));
            final Season season = transferWindow.getMatchWeek().getSeason();
            final D11Team dummyD11team = getDummyD11Team();

            final List<TransferListing> transferListings = this.playerSeasonStatRepository.findBySeasonId(season.getId()).stream()
                    .filter(playerSeasonStat -> playerSeasonStat.getD11Team().isDummy()
                                                || transferListingMap.containsKey(playerSeasonStat.getPlayer().getId()))
                    .map(playerSeasonStat -> {
                        final TransferListing transferListing;
                        if(transferListingMap.containsKey(playerSeasonStat.getPlayer().getId())) {
                            transferListing = transferListingMap.get(playerSeasonStat.getPlayer().getId());
                            transferListing.init(playerSeasonStat);
                            playerSeasonStat.setD11Team(dummyD11team);
                        } else {
                            transferListing = map(playerSeasonStat, TransferListing.class);
                            transferListing.setId(null);
                            transferListing.setTransferDay(transferDay);
                        }
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

    /**
     * Finishes a transfer day and transfers all players with successful bids.
     *
     * @param transferDayId Id for the transfer day that will be finished.
     * @return Result of the finishing with id of the updated entity if successful and list of errors if not.
     */
    @Transactional
    @SuppressWarnings({"checkstyle:CyclomaticComplexity", "checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS",
                       "checkstyle:NestedIfDepth", "PMD.ExcessiveMethodLength", "PMD.ConfusingTernary"})
    public UpdateTransferDayResultDTO finishTransferDayByTransferDayId(final long transferDayId) {
        final List<String> errors = new ArrayList<>();
        final TransferDay transferDay = getJpaRepository().findById(transferDayId).orElseThrow(NotFoundException::new);

        if(transferDay.getStatus() == Status.ACTIVE) {
            final Season season = getCurrentSeason();
            final Map<Long, D11TeamTransferProperties> d11TeamTransferPropertiesMap =
                    getRepository(D11TeamRepository.class).findByD11TeamSeasonStatSeasonIdOrderByName(season.getId()).stream()
                            .map(D11TeamTransferProperties::new)
                            .collect(Collectors.toMap(D11TeamTransferProperties::getId, Function.identity()));

            final List<TransferBid> transferBids = getRepository(TransferBidRepository.class)
                    .findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(transferDayId);

            final Map<Integer, List<TransferBid>> playerBidMap = new TreeMap<>();
            final Map<Long, List<TransferBid>> d11TeamBidMap = new HashMap<>();

            transferBids.forEach(transferBid -> {
                final List<TransferBid> playerBids = playerBidMap.computeIfAbsent(transferBid.getPlayerRanking(), key -> new ArrayList<>());
                playerBids.add(transferBid);
                final List<TransferBid> d11TeamBids = d11TeamBidMap.computeIfAbsent(transferBid.getD11Team().getId(), key -> new ArrayList<>());
                d11TeamBids.add(transferBid);
            });

            final int minBid = 5;

            playerBidMap.keySet().forEach(playerRanking -> {
                final List<TransferBid> playerBids = playerBidMap.get(playerRanking);
                playerBids.sort(Comparator.comparing(TransferBid::getActiveFee)
                               .thenComparing(TransferBid::getD11TeamRanking).reversed());

                for(int i = 0; i < playerBids.size(); ++i) {
                    final TransferBid transferBid = playerBids.get(i);
                    if(transferBid.getActiveFee() > 0) {
                        transferBid.setSuccessful(true);
                        if(i < playerBids.size() - 2) {
                            final TransferBid nextTransferBid = playerBids.get(i + 1);
                            if(transferBid.getActiveFee() > nextTransferBid.getActiveFee()) {
                                transferBid.setActiveFee(playerBids.get(i + 1).getActiveFee() + minBid);
                            }
                        } else {
                            transferBid.setActiveFee(minBid);
                        }

                        final PlayerSeasonStat playerSeasonStat =
                                this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(transferBid.getPlayer().getId(), season.getId())
                                        .orElseThrow(NotFoundException::new);

                        final D11TeamTransferProperties d11TeamTransferProperties = d11TeamTransferPropertiesMap.get(transferBid.getD11Team().getId());

                        d11TeamTransferProperties.decrementMaxBid(transferBid.getActiveFee());
                        d11TeamTransferProperties.incrementPositionCount(playerSeasonStat.getPosition());

                        d11TeamBidMap.get(transferBid.getD11Team().getId()).forEach(d11TeamTransferBid -> {
                            if(d11TeamTransferBid.getPlayerRanking() > transferBid.getPlayerRanking()) {
                                final PlayerSeasonStat playerSeasonStat2 =
                                        this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(d11TeamTransferBid.getPlayer().getId(), season.getId())
                                                .orElseThrow(NotFoundException::new);

                                if(!d11TeamTransferProperties.isBiddablePosition(playerSeasonStat2.getPosition())) {
                                    d11TeamTransferBid.setActiveFee(0);
                                } else if(d11TeamTransferBid.getActiveFee() > d11TeamTransferProperties.getMaxBid()) {
                                    d11TeamTransferBid.setActiveFee(d11TeamTransferProperties.getMaxBid());
                                }
                            }
                        });

                        playerSeasonStat.setD11Team(transferBid.getD11Team());
                        playerSeasonStat.setValue(transferBid.getActiveFee());

                        final Transfer transfer = new Transfer();
                        transfer.setTransferDay(transferDay);
                        transfer.setPlayer(playerSeasonStat.getPlayer());
                        transfer.setD11Team(playerSeasonStat.getD11Team());
                        transfer.setFee(playerSeasonStat.getValue());
                        getRepository(TransferRepository.class).save(transfer);

                        break;
                    }
                }
            });

            transferDay.setStatus(Status.FINISHED);

            TransferDay newTransferDay = null;
            for(final D11TeamTransferProperties d11TeamTransferProperties : d11TeamTransferPropertiesMap.values()) {
                if(!d11TeamTransferProperties.isFull()) {
                    newTransferDay = new TransferDay();
                    newTransferDay.setTransferWindow(transferDay.getTransferWindow());
                    newTransferDay.setStatus(Status.PENDING);
                    newTransferDay.setTransferDayNumber(transferDay.getTransferDayNumber() + 1);
                    newTransferDay.setDatetime(transferDay.getDatetime().plus(1, ChronoUnit.DAYS));
                    break;
                }
            }

            if(newTransferDay == null) {
                transferDay.getTransferWindow().setStatus(Status.FINISHED);
            } else {
                newTransferDay = getJpaRepository().save(newTransferDay);
                activateTransferDayByTransferDayId(newTransferDay.getId());
            }
        } else {
            errors.add("Transfer day status is " + transferDay.getStatus() + ".");
            rollback();
        }

        return new UpdateTransferDayResultDTO()
                .transferDayId(transferDay.getId())
                .errors(errors);
    }

    /**
     * Updates the status of the current transfer day. Only intended for use in the integration tests.
     *
     * @param status The status the current transfer day will be given.
     */
    public void updateCurrentTransferDayStatusForTest(final Status status) {
        final TransferDay transferDay = getCurrentSeason().getCurrentTransferDay();
        transferDay.setStatus(status);
        getJpaRepository().save(transferDay);
    }

}
