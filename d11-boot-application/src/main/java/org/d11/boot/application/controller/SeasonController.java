package org.d11.boot.application.controller;

import org.d11.boot.api.SeasonsApi;
import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.api.model.SeasonSummaryDTO;
import org.d11.boot.application.service.api.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the SeasonsApi and provides season endpoints.
 */
@RestController
public class SeasonController extends AbstractRepositoryServiceController<SeasonDTO, SeasonService> implements SeasonsApi {

    /**
     * Creates a new controller.
     *
     * @param seasonService The service that will be used by this controller.
     */
    @Autowired
    public SeasonController(final SeasonService seasonService) {
        super(seasonService);
    }

    @Override
    public ResponseEntity<List<Long>> findAllSeasons() {
        final List<Long> seasonIds = getRepositoryService().findAllSeasons();
        return ResponseEntity.ok(seasonIds);
    }

    @Override
    public ResponseEntity<SeasonDTO> findSeasonById(final Long seasonId) {
        return findById(seasonId);
    }

    @Override
    public ResponseEntity<SeasonSummaryDTO> findSeasonSummaryById(final Long seasonId) {
        final SeasonSummaryDTO seasonSummaryDTO = getRepositoryService().findSeasonSummaryById(seasonId);
        return ResponseEntity.ok(seasonSummaryDTO);
    }

    @Override
    public ResponseEntity<SeasonDTO> findCurrentSeason() {
        final SeasonDTO seasonDTO = getRepositoryService().findCurrentSeason();
        return ResponseEntity.ok(seasonDTO);
    }

}
