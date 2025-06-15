package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.model.TransferDay;
import org.d11.boot.spring.model.TransferInput;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.d11.boot.spring.repository.PlayerRepository;
import org.d11.boot.spring.repository.TransferDayRepository;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.ErrorCode;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Transfer service.
 */
@Service
public class TransferService extends RepositoryService<Transfer, TransferRepository> {

    /**
     * Fee property name.
     */
    private static final String FEE = "fee";

    /**
     * Message for invalid id parameter errors.
     */
    private static final String INVALID_ID_MESSAGE = "must be positive";

    /**
     * Transfer day repository.
     */
    private final TransferDayRepository transferDayRepository;

    /**
     * Player repository.
     */
    private final PlayerRepository playerRepository;

    /**
     * D11 team repository.
     */
    private final D11TeamRepository d11TeamRepository;

    /**
     * Creates a new transfer service.
     *
     * @param transferRepository    The transfer repository the service will use.
     * @param transferDayRepository The transfer day repository the service will use.
     * @param playerRepository      The player repository the service will use.
     * @param d11TeamRepository     The D11 team repository the service will use.
     */
    @Autowired
    public TransferService(final TransferRepository transferRepository,
                           final TransferDayRepository transferDayRepository,
                           final PlayerRepository playerRepository,
                           final D11TeamRepository d11TeamRepository) {
        super(Transfer.class, transferRepository);
        this.transferDayRepository = transferDayRepository;
        this.playerRepository = playerRepository;
        this.d11TeamRepository = d11TeamRepository;
    }

    /**
     * Get transfers by transfer day id ordered by D11 team name and fee, descending.
     *
     * @param transferDayId The transfer day id.
     * @return Transfers by transfer day id ordered by D11 team name and fee, descending.
     */
    public List<Transfer> getByTransferDayId(final Long transferDayId) {
        if (transferDayId == null || transferDayId <= 0) {
            throw new BadRequestException("transferDayId", INVALID_ID_MESSAGE);
        }

        return getJpaRepository().findByTransferDayIdOrderByD11TeamNameAscFeeDesc(transferDayId);
    }

    /**
     * Get transfers by player id ordered by transfer day date time, descending.
     *
     * @param playerId The player id.
     * @return Transfers by player id ordered by transfer day date time, descending.
     */
    public List<Transfer> getByPlayerId(final Long playerId) {
        if (playerId == null || playerId <= 0) {
            throw new BadRequestException("playerId", INVALID_ID_MESSAGE);
        }

        return getJpaRepository().findByPlayerIdOrderByTransferDayDatetimeDesc(playerId);
    }

    /**
     * Creates a new transfer. This is pretty lenient as it should only be used by admins.
     *
     * @param transferInput Transfer properties.
     * @return New transfer.
     */
    @Transactional
    public Transfer createTransfer(final TransferInput transferInput) {
        if (transferInput.fee() <= 0 || transferInput.fee() % Transfer.FEE_DIVISOR != 0) {
            throw new BadRequestException(FEE, ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        final TransferDay transferDay = this.transferDayRepository.findById(transferInput.transferDayId())
                .orElseThrow(() -> new NotFoundException(transferInput.transferDayId(), TransferDay.class));

        final Player player = this.playerRepository.findById(transferInput.playerId())
                .orElseThrow(() -> new NotFoundException(transferInput.playerId(), Player.class));

        final D11Team d11Team = this.d11TeamRepository.findById(transferInput.d11TeamId())
                .orElseThrow(() -> new NotFoundException(transferInput.d11TeamId(), D11Team.class));

        final Transfer transfer = getServiceMapper().mapToTransfer(transferInput);
        transfer.setTransferDay(transferDay);
        transfer.setPlayer(player);
        transfer.setD11Team(d11Team);

        return getJpaRepository().save(transfer);
    }

    /**
     * Updates an existing transfer. This is pretty lenient as it should only be used by admins.
     *
     * @param transferId Transfer id.
     * @param transferInput Transfer properties.
     * @return Updated transfer.
     */
    @Transactional
    public Transfer updateTransfer(final Long transferId, final TransferInput transferInput) {
        if (transferId == null) {
            throw new BadRequestException("transferId", ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        if (transferInput.fee() <= 0 || transferInput.fee() % Transfer.FEE_DIVISOR != 0) {
            throw new BadRequestException(FEE, ErrorCode.BAD_REQUEST_INVALID_PARAMETER);
        }

        final Transfer transfer = getById(transferId);

        final TransferDay transferDay = this.transferDayRepository.findById(transferInput.transferDayId())
                .orElseThrow(() -> new NotFoundException(transferInput.transferDayId(), TransferDay.class));

        final Player player = this.playerRepository.findById(transferInput.playerId())
                .orElseThrow(() -> new NotFoundException(transferInput.playerId(), Player.class));

        final D11Team d11Team = this.d11TeamRepository.findById(transferInput.d11TeamId())
                .orElseThrow(() -> new NotFoundException(transferInput.d11TeamId(), D11Team.class));

        transfer.setFee(transferInput.fee());
        transfer.setTransferDay(transferDay);
        transfer.setPlayer(player);
        transfer.setD11Team(d11Team);

        return getJpaRepository().save(transfer);
    }

    /**
     * Deletes a transfer. This is pretty lenient as it should only be used by admins.
     *
     * @param transferId Transfer id.
     */
    @Transactional
    public void deleteTransfer(final Long transferId) {
        final Transfer transfer = getById(transferId);

        getJpaRepository().delete(transfer);
    }

}
