package org.d11.boot.application.controller;

import org.d11.boot.api.D11TeamTableStatsApi;
import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.d11.boot.application.service.D11TeamTableStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the D11TeamTableStatsApi and provides D11 team table stat endpoints.
 */
@RestController
public class D11TeamTableStatController extends AbstractRepositoryServiceController<D11TeamTableStatDTO, D11TeamTableStatService>
        implements D11TeamTableStatsApi {

    /**
     * Creates a new controller.
     *
     * @param d11TeamTableStatService The repository service this controller will use.
     */
    @Autowired
    public D11TeamTableStatController(final D11TeamTableStatService d11TeamTableStatService) {
        super(d11TeamTableStatService);
    }

    @Override
    public ResponseEntity<D11TeamTableStatDTO> findD11TeamTableStatById(final Long d11TeamTableStatId) {
        return findById(d11TeamTableStatId);
    }

}
