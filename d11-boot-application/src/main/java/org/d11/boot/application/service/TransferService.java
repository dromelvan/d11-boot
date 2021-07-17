package org.d11.boot.application.service;

import org.d11.boot.api.model.TransferDTO;
import org.d11.boot.application.model.Transfer;
import org.d11.boot.application.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides transfer services.
 */
@Service
public class TransferService extends AbstractRepositoryService<Transfer, TransferDTO, TransferRepository> {

    /**
     * Creates a new service.
     *
     * @param transferRepository The repository this service will use.
     */
    @Autowired
    public TransferService(final TransferRepository transferRepository) {
        super(transferRepository);
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

}
