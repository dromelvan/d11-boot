package org.d11.boot.application.controller;

import org.d11.boot.api.D11MatchesApi;
import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.api.model.D11MatchesByDateDTO;
import org.d11.boot.application.service.D11MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the D11MatchesApi and provides D11 match endpoints.
 */
@RestController
public class D11MatchController extends AbstractRepositoryServiceController<D11MatchDTO, D11MatchService> implements D11MatchesApi {

    /**
     * Creates a new controller.
     *
     * @param d11MatchService The service that will be used by this controller.
     */
    @Autowired
    public D11MatchController(final D11MatchService d11MatchService) {
        super(d11MatchService);
    }

    @Override
    public ResponseEntity<D11MatchDTO> findD11MatchById(final Long d11MatchId) {
        return findById(d11MatchId);
    }

    @Override
    public ResponseEntity<List<Long>> findD11MatchByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        final List<Long> d11MatchIds = getRepositoryService().findD11MatchByD11TeamIdAndSeasonId(d11TeamId, seasonId);
        return ResponseEntity.ok(d11MatchIds);
    }

    @Override
    public ResponseEntity<D11MatchesByDateDTO> findCurrentD11Matches() {
        final D11MatchesByDateDTO d11MatchesByDateDTO = getRepositoryService().findCurrentD11Matches();
        return ResponseEntity.ok(d11MatchesByDateDTO);
    }

}
