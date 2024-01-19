package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferWindow;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for TransferWindow entities.
 */
@Repository
public interface TransferWindowRepository extends D11EntityRepository<TransferWindow> {

    /**
     * Overrides parent method with TransferWindow.TRANSFER_WINDOW_ASSOCIATIONS named entity graph applied.
     *
     * @param id Transfer window id.
     * @return Optional with the transfer window or empty optional if none was found.
     */
    @Override
    @EntityGraph(TransferWindow.TRANSFER_WINDOW_ASSOCIATIONS)
    Optional<TransferWindow> findById(Long id);

    /**
     * Finds the current transfer window and its match week and transfer days..
     *
     * @return The current transfer window.
     */
    @EntityGraph(TransferWindow.TRANSFER_WINDOW_ASSOCIATIONS)
    @Query("SELECT tw FROM TransferWindow tw ORDER BY tw.datetime DESC LIMIT 1")
    Optional<TransferWindow> findCurrentTransferWindow();

    /**
     * Finds the latest transfer window. This is the current one.
     *
     * @return The current transfer window.
     */
    Optional<TransferWindow> findFirstByOrderByDatetimeDesc();

    /**
     * Finds transfer windows by match week season id ordered by datetime desc.
     *
     * @param seasonId The season id.
     * @return Transfer windows for the season ordered by datetime desc.
     */
    List<TransferWindow> findByMatchWeekSeasonIdOrderByDatetimeDesc(@Param("seasonId") Long seasonId);

}
