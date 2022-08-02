package org.d11.boot.application.repository;

import org.d11.boot.application.model.TransferBid;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for transfer bid entities.
 */
@Repository
public interface TransferBidRepository extends D11EntityRepository<TransferBid> {

    /**
     * Finds a transfer bid for a specific player, D11 team and transfer day.
     *
     * @param playerId Id for the player for which a transfer bid will be looked up.
     * @param d11TeamId Id for the D11 team for which a transfer bid will be looked up.
     * @param transferDayId Id for the transfer day for which a transfer bid will be looked up.
     * @return Optional with the transfer bid or an empty optional if no such transfer bid exists.
     */
    Optional<TransferBid> findByPlayerIdAndD11TeamIdAndTransferDayId(@Param("playerId") Long playerId,
                                                                     @Param("d11TeamId") Long d11TeamId,
                                                                     @Param("transferDayId") Long transferDayId);

    /**
     * Finds transfer bids for a specific transfer day ordered by player ranking, active fee, descending and d11 team ranking.
     *
     * @param transferDayId Id for the transfer day for which transfer bids will be looked up.
     * @return Transfer bids for the transfer day.
     */
    List<TransferBid> findByTransferDayIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(@Param("transferDayId") Long transferDayId);

}
