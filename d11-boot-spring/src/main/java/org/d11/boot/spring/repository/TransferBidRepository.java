package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferBid;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * Finds a transfer bid by transfer day id, player id and D11 team id.
     *
     * @param transferDayId The transfer day id.
     * @param playerId The player id.
     * @param d11TeamId The D11 team id.
     * @return Optional with transfer bid for the transfer day, player and D11 team or empty optional if none was found.
     */
    Optional<TransferBid> findByTransferDayIdAndPlayerIdAndD11TeamId(
            @Param("transferDayId") Long transferDayId,
            @Param("playerId") Long playerId,
            @Param("d11TeamId") Long d11TeamId
    );

    /**
     * Finds transfer bids by transfer day id and player id ordered by player ranking, active fee descending and
     * d11 team ranking.
     *
     * @param transferDayId The transfer day id.
     * @param playerId The player id.
     * @return Transfer bids for the transfer day and player ordered by player ranking, active fee descending and
     *         d11 team ranking.
     */
    List<TransferBid> findByTransferDayIdAndPlayerIdOrderByPlayerRankingAscActiveFeeDescD11TeamRankingDesc(
            @Param("transferDayId") Long transferDayId, @Param("playerId") Long playerId
    );

}
