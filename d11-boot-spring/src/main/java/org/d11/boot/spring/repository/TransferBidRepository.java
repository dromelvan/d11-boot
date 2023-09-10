package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferBid;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for TransferBid entities.
 */
@Repository
public interface TransferBidRepository extends D11EntityRepository<TransferBid> {

    /**
     * Finds transfer bids by transfer day id ordered by player ranking, active fee descending and d11 team ranking.
     *
     * @param transferDayId The transfer day id.
     * @return Transfer bids for the transfer day ordered by player ranking, active fee descending and d11 team ranking.
     */
    List<TransferBid> findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(
            @Param("transferDayId") Long transferDayId
    );

}
