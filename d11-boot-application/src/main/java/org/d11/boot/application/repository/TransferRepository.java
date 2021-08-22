package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.Transfer;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for transfer entities.
 */
@Repository
public interface TransferRepository extends D11EntityRepository<Transfer> {

    /**
     * Finds transfers for a specific transfer day ordered by D11 team name and fee.
     *
     * @param transferDayId Id for the transfer day for which transfers will be looked up.
     * @return Transfers for the transfer day.
     */
    List<Transfer> findByTransferDayIdOrderByD11TeamNameAscFeeDesc(@Param("transferDayId") Long transferDayId);

    /**
     * Finds transfers for a specific player ordered by transfer day datetime, descending.
     *
     * @param playerId Id for the player for which transfers will be looked up.
     * @return Transfers for the player.
     */
    List<Transfer> findByPlayerIdOrderByTransferDayDatetimeDesc(@Param("playerId") Long playerId);

    /**
     * Finds a transfer for a specific player and a specific transfer day.
     *
     * @param playerId Id for the player for which a transfer will be looked up.
     * @param transferDayId Id for the transfer day for which a transfer will be looked up.
     * @return Transfer for the player and transfer day.
     */
    Optional<Transfer> findByPlayerIdAndTransferDayId(@Param("playerId") Long playerId, @Param("transferDayId") Long transferDayId);

}
