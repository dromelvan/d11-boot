package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.MatchWeekApi;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.MatchWeekResponseBodyDTO;
import org.d11.boot.api.v2.model.MatchWeeksResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.service.MatchWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Match week API REST controller implementation.
 */
@RestController
public class MatchWeekControllerV2 extends RepositoryServiceController<MatchWeekService> implements MatchWeekApi {

    /**
     * Create a new controller.
     *
     * @param matchWeekService The service the controller will use.
     */
    @Autowired
    public MatchWeekControllerV2(final MatchWeekService matchWeekService) {
        super(matchWeekService);
    }

    @Override
    public ResponseEntity<MatchWeeksResponseBodyDTO> getMatchWeeksBySeasonId(final Long seasonId) {
        final List<MatchWeek> matchWeeks = getRepositoryService().getBySeasonId(seasonId);

        return ResponseEntity.ok(new MatchWeeksResponseBodyDTO()
                .matchWeeks(getMapper().map(matchWeeks, MatchWeekDTO.class)));
    }

    @Override
    public ResponseEntity<MatchWeekResponseBodyDTO> getMatchWeekById(final Long matchWeekId) {
        final MatchWeek matchWeek = getRepositoryService().getById(matchWeekId);

        return ResponseEntity.ok(new MatchWeekResponseBodyDTO()
                .matchWeek(getMapper().map(matchWeek, MatchWeekDTO.class)));
    }

    @Override
    public ResponseEntity<MatchWeekResponseBodyDTO> getCurrentMatchWeek() {
        final MatchWeek matchWeek = getRepositoryService().getCurrentMatchWeek();

        return ResponseEntity.ok(new MatchWeekResponseBodyDTO()
                .matchWeek(getMapper().map(matchWeek, MatchWeekDTO.class)));
    }

}
