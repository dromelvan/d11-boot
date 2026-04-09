package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferListing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for TransferListing entities.
 */
@Repository
public interface TransferListingRepository extends D11EntityRepository<TransferListing> {

    /**
     * Finds a transfer listing by transfer day id and player id.
     *
     * @param transferDayId The transfer day id.
     * @param playerId The player id.
     * @return Optional with transfer listing for the transfer day and the player or empty optional if none was found.
     */
    Optional<TransferListing> findByTransferDayIdAndPlayerId(@Param("transferDayId") Long transferDayId,
                                                             @Param("playerId") Long playerId);

    /**
     * Finds transfer listings by transfer day ordered by ranking, paged.
     *
     * @param transferDayId The transfer day id.
     * @param dummy         Null for all, true for dummy D11 team only, false for real D11 team only.
     * @param pageable      Pageable that defines page number, page size and sorting of the result.
     * @return Transfer listings for the transfer day ordered by ranking, paged.
     */
    @Query("""
            SELECT tl FROM TransferListing tl
            WHERE tl.transferDay.id = :transferDayId
              AND (:dummy IS NULL OR tl.d11Team.dummy = :dummy)
            """)
    List<TransferListing> findByTransferDayIdOrderByRanking(@Param("transferDayId") Long transferDayId,
                                                            @Param("dummy") Boolean dummy,
                                                            Pageable pageable);

    /**
     * Finds transfer listings by season id and D11 team id.
     *
     * @param seasonId The season id.
     * @param d11TeamId The D11 teams id.
     * @return Transfer listings for the season and D11 team.
     */
    List<TransferListing> findByTransferDayTransferWindowMatchWeekSeasonIdAndD11TeamId(
            @Param("seasonId") Long seasonId,
            @Param("d11TeamId") Long d11TeamId);

}
