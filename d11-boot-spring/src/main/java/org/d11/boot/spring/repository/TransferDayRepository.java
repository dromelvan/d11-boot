package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferDay;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for TransferDay entities.
 */
@Repository
public interface TransferDayRepository extends D11EntityRepository<TransferDay> {

    /**
     * Finds the latest transfer day. This is the current one.
     *
     * @return The current transfer day.
     */
    Optional<TransferDay> findFirstByOrderByDatetimeDesc();

    /**
     * Finds transfer days by transfer window id ordered by date time descending.
     *
     * @param transferWindowId The transfer window id.
     * @return Transfer days for the transfer window ordered by date time descending.
     */
    List<TransferDay> findByTransferWindowIdOrderByDatetimeDesc(@Param("transferWindowId") Long transferWindowId);

}
