package org.d11.boot.application.controller;

import org.d11.boot.api.D11TeamD11MatchWeekStatsApi;
import org.d11.boot.api.model.D11TeamD11MatchWeekStatDTO;
import org.d11.boot.application.service.D11TeamD11MatchWeekStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the D11TeamD11MatchWeekStatsApi and provides D11 team D11 match week stat endpoints.
 */
@RestController
public class D11TeamD11MatchWeekStatController extends AbstractRepositoryServiceController<D11TeamD11MatchWeekStatDTO, D11TeamD11MatchWeekStatService>
        implements D11TeamD11MatchWeekStatsApi {

    /**
     * Creates a new controller.
     *
     * @param d11TeamD11MatchWeekStatService The repository service this controller will use.
     */
    @Autowired
    public D11TeamD11MatchWeekStatController(final D11TeamD11MatchWeekStatService d11TeamD11MatchWeekStatService) {
        super(d11TeamD11MatchWeekStatService);
    }

    @Override
    public ResponseEntity<D11TeamD11MatchWeekStatDTO> findD11TeamD11MatchWeekStatById(final Long d11TeamD11MatchWeekStatId) {
        return findById(d11TeamD11MatchWeekStatId);
    }

}
