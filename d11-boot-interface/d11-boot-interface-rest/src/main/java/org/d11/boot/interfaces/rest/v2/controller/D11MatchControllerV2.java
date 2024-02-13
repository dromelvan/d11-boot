package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.D11MatchApi;
import org.d11.boot.api.v2.model.D11MatchBaseDTO;
import org.d11.boot.api.v2.model.D11MatchDTO;
import org.d11.boot.api.v2.model.D11MatchResponseBodyDTO;
import org.d11.boot.api.v2.model.D11MatchesResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.service.D11MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * D11 match API REST controller implementation.
 */
@RestController
public class D11MatchControllerV2 extends RepositoryServiceController<D11MatchService> implements D11MatchApi {

    /**
     * Create a new controller.
     *
     * @param d11MatchService The service the controller will use.
     */
    @Autowired
    public D11MatchControllerV2(final D11MatchService d11MatchService) {
        super(d11MatchService);
    }

    @Override
    public ResponseEntity<D11MatchResponseBodyDTO> getD11MatchById(final Long d11MatchId) {
        final D11Match d11Match = getRepositoryService().getById(d11MatchId);

        return ResponseEntity.ok(new D11MatchResponseBodyDTO()
                .d11Match(getMapper().map(d11Match, D11MatchDTO.class)));
    }

    @Override
    public ResponseEntity<D11MatchesResponseBodyDTO> getD11MatchesByMatchWeekId(final Long matchWeekId) {
        final List<D11Match> d11Matches = getRepositoryService().getByMatchWeekId(matchWeekId);
        return ResponseEntity.ok(new D11MatchesResponseBodyDTO()
                .d11Matches(getMapper().map(d11Matches, D11MatchBaseDTO.class)));
    }

}
