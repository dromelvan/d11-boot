package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TransferBidDTO;
import org.d11.boot.application.model.TransferBid;
import org.d11.boot.application.repository.TransferBidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides transfer bid services.
 */
@Service
public class TransferBidService extends ApiRepositoryService<TransferBid, TransferBidDTO, TransferBidRepository> {

    /**
     * Creates a new service.
     *
     * @param transferBidRepository The repository this service will use.
     */
    @Autowired
    public TransferBidService(final TransferBidRepository transferBidRepository) {
        super(transferBidRepository);
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

}
