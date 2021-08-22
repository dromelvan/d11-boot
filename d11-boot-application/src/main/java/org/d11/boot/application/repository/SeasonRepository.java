package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.model.jpa.projection.EntityId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for season entities.
 */
@Repository
public interface SeasonRepository extends D11EntityRepository<Season> {

    /**
     * Finds all season ids and orders them by date, descending.
     *
     * @return List of all season ids ordered by date, descending.
     */
    List<EntityId> findByOrderByDateDesc();

    /**
     * Finds the season with the latest start date. This is the current season.
     *
     * @return Optional with the current season or an empty optional if no seasons exist.
     */
    Optional<Season> findFirstByOrderByDateDesc();

}
