package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.TransferWindow;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for transfer window entities.
 */
@Repository
public interface TransferWindowRepository extends D11EntityRepository<TransferWindow> {

    /**
     * Finds the latest transfer window. This is the current one.
     *
     * @return The current transfer window.
     */
    Optional<TransferWindow> findFirstByOrderByDatetimeDesc();

    /**
     * Finds transfer windows for a specific season.
     *
     * @param seasonId Id for the season for which transfer windows will be looked up.
     * @return Transfer windows for the season.
     */
    List<TransferWindow> findByMatchWeekSeasonIdOrderByDatetimeDesc(@Param("seasonId") Long seasonId);

}
