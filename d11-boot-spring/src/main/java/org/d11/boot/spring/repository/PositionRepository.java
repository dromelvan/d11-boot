package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Position;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Position entities.
 */
@Repository
public interface PositionRepository extends D11EntityRepository<Position> {

    /**
     * Finds a positions by code.
     *
     * @param code The position code.
     * @return Positions with provided code.
     */
    Optional<Position> findByCode(@Param("code") String code);

    /**
     * Finds a list of all positions ordered by ascending sort order.
     *
     * @return list of all positions ordered by ascending sort order.
     */
    List<Position> findByOrderBySortOrder();

}
