package org.d11.boot.application.controller;

import org.d11.boot.api.D11MatchWeeksApi;
import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.application.service.D11MatchWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the D11MatchWeeksApi and provides match week endpoints.
 */
@RestController
public class D11MatchWeekController extends AbstractRepositoryServiceController<D11MatchWeekDTO, D11MatchWeekService> implements D11MatchWeeksApi {

    /**
     * Creates a new controller.
     *
     * @param d11MatchWeekService The service that will be used by this controller.
     */
    @Autowired
    public D11MatchWeekController(final D11MatchWeekService d11MatchWeekService) {
        super(d11MatchWeekService);
    }

    @Override
    public ResponseEntity<D11MatchWeekDTO> findD11MatchWeekById(final Long d11MatchWeekId) {
        return findById(d11MatchWeekId);
    }

    @Override
    public ResponseEntity<D11MatchWeekDTO> findCurrentD11MatchWeek() {
        final D11MatchWeekDTO d11MatchWeek = getRepositoryService().findCurrentD11MatchWeek();
        return ResponseEntity.ok(d11MatchWeek);
    }

}
