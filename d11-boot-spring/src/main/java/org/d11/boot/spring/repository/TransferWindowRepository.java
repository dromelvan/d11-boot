package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.TransferWindow;
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
