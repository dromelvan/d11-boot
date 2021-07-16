package org.d11.boot.application.repository;

import org.d11.boot.application.model.TransferBid;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for transfer bid entities.
 */
@Repository
public interface TransferBidRepository extends D11EntityRepository<TransferBid> {

    /**
     * Finds transfer bids for a specific transfer day ordered by player ranking, active fee, descending and d11 team ranking.
     *
     * @param transferDayId Id for the transfer day for which transfer bids will be looked up.
     * @return Transfer listings for the transfer day.
     */
    List<TransferBid> findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(@Param("transferDayId") Long transferDayId);

}
