package org.d11.boot.application.controller;

import org.d11.boot.api.PremierLeaguesApi;
import org.d11.boot.api.model.PremierLeagueDTO;
import org.d11.boot.application.service.PremierLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the PremierLeaguesApi and provides premier league endpoints.
 */
@RestController
public class PremierLeagueController extends RepositoryController<PremierLeagueDTO, PremierLeagueService> implements PremierLeaguesApi {

    /**
     * Creates a new controller.
     *
     * @param premierLeagueService The service that will be used by this controller.
     */
    @Autowired
    public PremierLeagueController(PremierLeagueService premierLeagueService) {
        super(premierLeagueService);
    }

    @Override
    public ResponseEntity<PremierLeagueDTO> findPremierLeagueById(Long premierLeagueId) {
        return findById(premierLeagueId);
    }

}
