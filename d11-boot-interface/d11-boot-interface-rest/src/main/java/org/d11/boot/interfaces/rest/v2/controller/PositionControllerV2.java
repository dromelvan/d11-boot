package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.PositionApi;
import org.d11.boot.api.v2.model.PositionDTO;
import org.d11.boot.api.v2.model.PositionsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Position;
import org.d11.boot.spring.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Position API REST controller implementation.
 */
@RestController
public class PositionControllerV2 extends RepositoryServiceController<PositionService> implements PositionApi {

    /**
     * Create a new controller.
     *
     * @param positionService The service the controller will use.
     */
    @Autowired
    public PositionControllerV2(final PositionService positionService) {
        super(positionService);
    }

    @Override
    public ResponseEntity<PositionsResponseBodyDTO> getPositions() {
        final List<Position> positions = getRepositoryService().getPositions();

        return ResponseEntity.ok(new PositionsResponseBodyDTO()
                                         .positions(getMapper().map(positions, PositionDTO.class)));
    }

}
