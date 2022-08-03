package org.d11.boot.application.repository;

import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferDay;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for transfer day entities.
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
     * Finds transfer days for a specific transfer window.
     *
     * @param transferWindowId Id for the transfer window for which transfer days will be looked up.
     * @return Transfer days for the transfer window.
     */
    List<TransferDay> findByTransferWindowIdOrderByDatetimeDesc(@Param("transferWindowId") Long transferWindowId);

    /**
     * Upates the status of a transfer day.
     *
     * @param transferDayId Id of the transfer day that will be updated.
     * @param status        The status the transfer day will be given.
     */
    @Modifying
    @Query("update TransferDay transferDay set transferDay.status = ?2 where transferDay.id = ?1")
    void updateStatus(long transferDayId, Status status);

}
