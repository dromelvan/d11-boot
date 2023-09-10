package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Position entities.
 */
@Repository
public interface PositionRepository extends D11EntityRepository<Position> {

    /**
     * Finds a list of all positions ordered by ascending sort order.
     *
     * @return list of all positions ordered by ascending sort order.
     */
    List<Position> findByOrderBySortOrder();

}
