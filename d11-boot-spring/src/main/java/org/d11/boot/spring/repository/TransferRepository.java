package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Transfer;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Transfer entities.
 */
@Repository
public interface TransferRepository extends D11EntityRepository<Transfer> {

    /**
     * Finds transfers by season id and D11 team id.
     *
     * @param seasonId The season id.
     * @param d11TeamId The D11 team id.
     * @return Transfers for the season and D11 team id.
     */
    List<Transfer> findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(@Param("seasonId") Long seasonId,
                                                                                @Param("d11TeamId") Long d11TeamId);

    /**
     * Finds transfers by transfer day id ordered by D11 team name and fee descending.
     *
     * @param transferDayId The transfer day id.
     * @return Transfers for the transfer day ordered by D11 team name and fee descending.
     */
    List<Transfer> findByTransferDayIdOrderByD11TeamNameAscFeeDesc(@Param("transferDayId") Long transferDayId);

    /**
     * Finds transfers by player id ordered by transfer day datetime descending.
     *
     * @param playerId The player id.
     * @return Transfers for the player ordered by transfer day datetime descending.
     */
    List<Transfer> findByPlayerIdOrderByTransferDayDatetimeDesc(@Param("playerId") Long playerId);

    /**
     * Finds a transfer by player id and transfer day id.
     *
     * @param playerId The player id.
     * @param transferDayId The transfer id.
     * @return Optional with transfer for the player and transfer day or empty optional if none was found.
     */
    Optional<Transfer> findByPlayerIdAndTransferDayId(@Param("playerId") Long playerId,
                                                      @Param("transferDayId") Long transferDayId);

}
