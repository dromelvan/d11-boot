package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Position service.
 */
@Service
public class PositionService extends RepositoryService<Position, PositionRepository> {

    /**
     * Creates a new position service.
     *
     * @param positionRepository The repository the service will use.
     */
    @Autowired
    public PositionService(final PositionRepository positionRepository) {
        super(Position.class, positionRepository);
    }

    /**
     * Gets a list of all positions ordered by sort order.
     *
     * @return List of all positions ordered by sort order.
     */
    public List<Position> getPositions() {
        return getJpaRepository().findByOrderBySortOrder();
    }

}
