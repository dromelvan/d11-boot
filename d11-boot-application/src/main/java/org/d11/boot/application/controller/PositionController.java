package org.d11.boot.application.controller;

import org.d11.boot.api.PositionsApi;
import org.d11.boot.api.model.PositionDTO;
import org.d11.boot.application.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the PositionsApi and provides position endpoints.
 */
@RestController
public class PositionController extends AbstractRepositoryServiceController<PositionDTO, PositionService> implements PositionsApi {

    /**
     * Creates a new controller.
     *
     * @param positionService The service that will be used by this controller.
     */
    @Autowired
    public PositionController(final PositionService positionService) {
        super(positionService);
    }

    @Override
    public ResponseEntity<PositionDTO> findPositionById(final Long positionId) {
        return findById(positionId);
    }

    @Override
    public ResponseEntity<List<PositionDTO>> findAllPositions() {
        final List<PositionDTO> positionDTOs = getRepositoryService().findAllPositions();
        return ResponseEntity.ok(positionDTOs);
    }

}
