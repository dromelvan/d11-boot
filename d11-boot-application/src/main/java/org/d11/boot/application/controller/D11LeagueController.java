package org.d11.boot.application.controller;

import org.d11.boot.api.D11LeaguesApi;
import org.d11.boot.api.model.D11LeagueDTO;
import org.d11.boot.application.service.D11LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the D11LeaguesApi and provides D11 league endpoints.
 */
@RestController
public class D11LeagueController extends AbstractRepositoryServiceController<D11LeagueDTO, D11LeagueService> implements D11LeaguesApi {

    /**
     * Creates a new controller.
     *
     * @param d11LeagueService The service that will be used by this controller.
     */
    @Autowired
    public D11LeagueController(final D11LeagueService d11LeagueService) {
        super(d11LeagueService);
    }

    @Override
    public ResponseEntity<D11LeagueDTO> findD11LeagueById(final Long d11LeagueId) {
        return findById(d11LeagueId);
    }

}
