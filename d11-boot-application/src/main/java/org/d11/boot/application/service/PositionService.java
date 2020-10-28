package org.d11.boot.application.service;

import org.d11.boot.api.model.PositionDTO;
import org.d11.boot.application.model.Position;
import org.d11.boot.application.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides position services.
 */
@Service
public class PositionService extends AbstractRepositoryService<Position, PositionDTO, PositionRepository> {

    /**
     * Creates a new service.
     *
     * @param positionRepository The repository this service will use.
     */
    @Autowired
    public PositionService(final PositionRepository positionRepository) {
        super(positionRepository);
    }

    /**
     * Gets all positions ordered by sort order.
     *
     * @return List of position DTOs.
     */
    public List<PositionDTO> findAllPositions() {
        final List<Position> positions = getJpaRepository().findByOrderBySortOrder();
        return map(positions);
    }

}
