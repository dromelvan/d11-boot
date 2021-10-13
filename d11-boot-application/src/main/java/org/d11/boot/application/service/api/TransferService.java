package org.d11.boot.application.service.api;

import org.d11.boot.api.model.InsertTransferDTO;
import org.d11.boot.api.model.InsertTransferResultDTO;
import org.d11.boot.api.model.TransferDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Transfer;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.validation.TransferValidator;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides transfer services.
 */
@Service
public class TransferService extends ApiRepositoryService<Transfer, TransferDTO, TransferRepository> {

    /**
     * The player repository the service will use.
     */
    private final PlayerRepository playerRepository;
    /**
     * The player season stat repository the service will use.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;
    /**
     * The season repository the service will use.
     */
    private final SeasonRepository seasonRepository;
    /**
     * The D11 team repository the service will use.
     */
    private final D11TeamRepository d11TeamRepository;
    /**
     * The transfer day repository the service will use.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Creates a new service.
     *
     * @param transferRepository         The transfer repository this service will use.
     * @param playerRepository           The player repository this service will use.
     * @param playerSeasonStatRepository The player season stat repository this service will use.
     * @param seasonRepository           The season repository this service will use.
     * @param d11TeamRepository          The D11 team repository this service will use.
     * @param transferDayRepository      The transfer day repository this service will use.
     */
    @Autowired
    public TransferService(final TransferRepository transferRepository,
                           final PlayerRepository playerRepository,
                           final PlayerSeasonStatRepository playerSeasonStatRepository,
                           final SeasonRepository seasonRepository,
                           final D11TeamRepository d11TeamRepository,
                           final TransferDayRepository transferDayRepository) {
        super(transferRepository);
        this.playerRepository = playerRepository;
        this.playerSeasonStatRepository = playerSeasonStatRepository;
        this.seasonRepository = seasonRepository;
        this.d11TeamRepository = d11TeamRepository;
        this.transferDayRepository = transferDayRepository;
    }

    /**
     * Gets transfers for a specific transfer day, ordered by D11 team name and fee.
     *
     * @param transferDayId Id for the transfer day for which transfers will be looked up.
     * @return Transfers for the transfer day.
     */
    public List<TransferDTO> findByTransferDayId(final long transferDayId) {
        final List<Transfer> transfers = getJpaRepository().findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDayId);
        return map(transfers);
    }

    /**
     * Gets transfers for a specific player, ordered by transfer day datetime descending.
     *
     * @param playerId If for the player for which transfers will be looked up.
     * @return Transfers for the player.
     */
    public List<TransferDTO> findByPlayerId(final long playerId) {
        final List<Transfer> transfers = getJpaRepository().findByPlayerIdOrderByTransferDayDatetimeDesc(playerId);
        return map(transfers);
    }

    /**
     * Inserts a transfer for the current transfer day.
     *
     * @param insertTransferDTO Details for the transfer that will be inserted.
     * @return Result of the insert with ids of created entities if successful and list of errors if not.
     */
    @Transactional
    public InsertTransferResultDTO insertTransfer(final InsertTransferDTO insertTransferDTO) {
        final Player player = this.playerRepository.findById(insertTransferDTO.getPlayerId()).orElseThrow(NotFoundException::new);
        final D11Team d11Team = this.d11TeamRepository.findById(insertTransferDTO.getD11TeamId()).orElseThrow(NotFoundException::new);
        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);
        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc().orElseThrow(NotFoundException::new);
        final PlayerSeasonStat playerSeasonStat = this.playerSeasonStatRepository.findByPlayerIdAndSeasonId(player.getId(), season.getId())
                .orElseThrow(NotFoundException::new);

        Transfer transfer = getJpaRepository().findByPlayerIdAndTransferDayId(player.getId(), transferDay.getId())
                .orElse(new Transfer());

        final List<String> errors = new ArrayList<>();

        // If we're transferring a player to the dummy team it means we want to undo an existing transfer.
        if(d11Team.isDummy()) {
            if(transfer.getId() != null) {
                getJpaRepository().delete(transfer);
            }
            playerSeasonStat.setD11Team(d11Team);
            playerSeasonStat.setValue(0);
            this.playerSeasonStatRepository.save(playerSeasonStat);
        } else {
            final List<PlayerSeasonStat> playerSeasonStats =
                    this.playerSeasonStatRepository.findByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());
            final TransferValidator transferValidator = new TransferValidator(season, playerSeasonStats);
            errors.addAll(transferValidator.validate(insertTransferDTO.getFee(), playerSeasonStat.getPosition()));

            transfer.setPlayer(player);
            transfer.setD11Team(d11Team);
            transfer.setTransferDay(transferDay);
            transfer.setFee(insertTransferDTO.getFee());

            playerSeasonStat.setD11Team(d11Team);
            playerSeasonStat.setValue(insertTransferDTO.getFee());

            errors.addAll(getValidationErrors(transfer, playerSeasonStat));
            if(errors.isEmpty()) {
                transfer = getJpaRepository().save(transfer);
                this.playerSeasonStatRepository.save(playerSeasonStat);
            } else {
                rollback();
            }
        }
        return new InsertTransferResultDTO()
                .playerId(player.getId())
                .transferId(transfer.getId())
                .errors(errors);
    }

}
