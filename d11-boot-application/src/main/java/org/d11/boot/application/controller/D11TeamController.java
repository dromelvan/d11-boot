package org.d11.boot.application.controller;

import org.d11.boot.api.D11TeamsApi;
import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.api.model.D11TeamNameDTO;
import org.d11.boot.application.service.api.D11TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the D11TeamsApi and provides D11 team endpoints.
 */
@RestController
public class D11TeamController extends AbstractRepositoryServiceController<D11TeamDTO, D11TeamService> implements D11TeamsApi {

    /**
     * Creates a new controller.
     *
     * @param d11TeamService The repository service this controller will use.
     */
    @Autowired
    public D11TeamController(final D11TeamService d11TeamService) {
        super(d11TeamService);
    }

    @Override
    public ResponseEntity<D11TeamDTO> findD11TeamById(final Long d11TeamId) {
        return findById(d11TeamId);
    }

    @Override
    public ResponseEntity<List<D11TeamNameDTO>> findAllD11Teams() {
        final List<D11TeamNameDTO> d11TeamNameDTOs = getRepositoryService().findAllD11Teams();
        return ResponseEntity.ok(d11TeamNameDTOs);
    }

    @Override
    public ResponseEntity<List<D11TeamDTO>> findD11TeamBySeasonId(final Long seasonId) {
        final List<D11TeamDTO> d11Teams = getRepositoryService().findD11TeamBySeasonId(seasonId);
        return ResponseEntity.ok(d11Teams);
    }

}
