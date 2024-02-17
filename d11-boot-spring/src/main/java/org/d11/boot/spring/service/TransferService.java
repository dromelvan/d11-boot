package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.repository.TransferRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Transfer service.
 */
@Service
public class TransferService extends RepositoryService<Transfer, TransferRepository> {

    /**
     * Message for invalid id parameter errors.
     */
    private static final String INVALID_ID_MESSAGE = "must be positive";

    /**
     * Creates a new transfer service.
     *
     * @param transferRepository The transfer repository the service will use.
     */
    @Autowired
    public TransferService(final TransferRepository transferRepository) {
        super(Transfer.class, transferRepository);
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

}
