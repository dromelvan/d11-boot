package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for position entities.
 */
@Repository
public interface PositionRepository extends D11EntityRepository<Position> {

    /**
     * Gets positions ordered by sort order.
     *
     * @return List of positions ordered by sort order.
     */
    List<Position> findByOrderBySortOrder();

}
