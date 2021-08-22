package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.TransferListing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for transfer listing entities.
 */
@Repository
public interface TransferListingRepository extends D11EntityRepository<TransferListing> {

    /**
     * Finds transfer listings for a specific transfer day ordered by ranking, descending.
     *
     * @param transferDayId Id for the transfer day for which transfer listings will be looked up.
     * @param pageable Pageable that defines page number, page size and sorting of the result.
     * @return Transfer listings for the transfer day.
     */
    List<TransferListing> findByTransferDayIdOrderByRanking(@Param("transferDayId") Long transferDayId, Pageable pageable);

}
