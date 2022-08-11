package org.d11.boot.application.repository;

import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferListing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for transfer listing entities.
 */
@Repository
public interface TransferListingRepository extends D11EntityRepository<TransferListing> {

    /**
     * Finds a transfer listing for a specific transfer day and player.
     *
     * @param transferDayId Id for the transfer day for which transfer listing will be looked up.
     * @param playerId Id for the player for which transfer listing will be looked up.
     * @return Transfer listing for the transfer day and player.
     */
    Optional<TransferListing> findByTransferDayIdAndPlayerId(@Param("transferDayId") Long transferDayId, @Param("playerId") Long playerId);

    /**
     * Finds transfer listings for a transfer day with a specific status and D11 team.
     *
     * @param status Status for the transfer day for which transfer listing will be looked up.
     * @param d11TeamId Id for the D11 team for which transfer listing will be looked up.
     * @return Transfer listings for the transfer day and D11 team.
     */
    List<TransferListing> findByTransferDayStatusAndD11TeamId(@Param("transferDayStatus") Status status, @Param("d11TeamId") Long d11TeamId);

    /**
     * Finds transfer listings for a specific transfer day ordered by ranking, descending.
     *
     * @param transferDayId Id for the transfer day for which transfer listings will be looked up.
     * @param pageable Pageable that defines page number, page size and sorting of the result.
     * @return Transfer listings for the transfer day.
     */
    List<TransferListing> findByTransferDayIdOrderByRanking(@Param("transferDayId") Long transferDayId, Pageable pageable);

    /**
     * Finds transfer listings for a specific season and player.
     *
     * @param seasonId Id for the season for which transfer listings will be looked up.
     * @param playerId Id for the player for which transfer listings will be looked up.
     * @return Transfer listings for the season and player.
     */
    List<TransferListing> findByTransferDayTransferWindowMatchWeekSeasonIdAndPlayerId(@Param("seasonId") Long seasonId, @Param("playerId") Long playerId);

    /**
     * Finds transfer listings for a specific season and D11 team.
     *
     * @param seasonId  Id for the season for which transfer listings will be looked up.
     * @param d11TeamId Id for the D11 team for which transfer listings will be looked up.
     * @return Transfer listings for the season and D11 team.
     */
    List<TransferListing> findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(@Param("seasonId") Long seasonId, @Param("d11TeamId") Long d11TeamId);

}
