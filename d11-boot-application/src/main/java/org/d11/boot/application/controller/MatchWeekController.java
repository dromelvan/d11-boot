package org.d11.boot.application.controller;

import org.d11.boot.api.MatchWeeksApi;
import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.application.service.MatchWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the MatchWeeksApi and provides match week endpoints.
 */
@RestController
public class MatchWeekController extends AbstractRepositoryServiceController<MatchWeekDTO, MatchWeekService> implements MatchWeeksApi {

    /**
     * Creates a new controller.
     *
     * @param matchWeekService The service that will be used by this controller.
     */
    @Autowired
    public MatchWeekController(final MatchWeekService matchWeekService) {
        super(matchWeekService);
    }

    @Override
    public ResponseEntity<MatchWeekDTO> findMatchWeekById(final Long matchWeekId) {
        return findById(matchWeekId);
    }

    @Override
    public ResponseEntity<MatchWeekDTO> findCurrentMatchWeek() {
        final MatchWeekDTO matchWeekDTO = getRepositoryService().findCurrentMatchWeek();
        return ResponseEntity.ok(matchWeekDTO);
    }

}
