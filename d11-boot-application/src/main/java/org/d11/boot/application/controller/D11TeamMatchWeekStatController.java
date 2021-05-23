package org.d11.boot.application.controller;

import org.d11.boot.api.D11TeamMatchWeekStatsApi;
import org.d11.boot.api.model.D11TeamMatchWeekStatDTO;
import org.d11.boot.application.service.D11TeamMatchWeekStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the D11TeamMatchWeekStatsApi and provides D11 team match week stat endpoints.
 */
@RestController
public class D11TeamMatchWeekStatController extends AbstractRepositoryServiceController<D11TeamMatchWeekStatDTO, D11TeamMatchWeekStatService>
        implements D11TeamMatchWeekStatsApi {

    /**
     * Creates a new controller.
     *
     * @param d11TeamMatchWeekStatService The repository service this controller will use.
     */
    @Autowired
    public D11TeamMatchWeekStatController(final D11TeamMatchWeekStatService d11TeamMatchWeekStatService) {
        super(d11TeamMatchWeekStatService);
    }

    @Override
    public ResponseEntity<D11TeamMatchWeekStatDTO> findD11TeamMatchWeekStatById(final Long d11TeamMatchWeekStatId) {
        return findById(d11TeamMatchWeekStatId);
    }

}
