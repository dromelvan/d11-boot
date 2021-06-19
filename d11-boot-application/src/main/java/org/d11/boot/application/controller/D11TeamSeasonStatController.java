package org.d11.boot.application.controller;

import org.d11.boot.api.D11TeamSeasonStatsApi;
import org.d11.boot.api.model.D11TeamSeasonStatDTO;
import org.d11.boot.application.service.D11TeamSeasonStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the D11TeamSeasonStatsApi and provides D11 team season stat endpoints.
 */
@RestController
public class D11TeamSeasonStatController extends AbstractRepositoryServiceController<D11TeamSeasonStatDTO, D11TeamSeasonStatService> implements D11TeamSeasonStatsApi {

    /**
     * Creates a new controller.
     *
     * @param d11TeamSeasonStatService The repository service this controller will use.
     */
    @Autowired
    public D11TeamSeasonStatController(final D11TeamSeasonStatService d11TeamSeasonStatService) {
        super(d11TeamSeasonStatService);
    }

    @Override
    public ResponseEntity<D11TeamSeasonStatDTO> findD11TeamSeasonStatById(final Long d11TeamSeasonStatId) {
        return findById(d11TeamSeasonStatId);
    }

    @Override
    public ResponseEntity<List<D11TeamSeasonStatDTO>> findD11TeamSeasonStatBySeasonId(final Long seasonId) {
        final List<D11TeamSeasonStatDTO> d11TeamSeasonStats = getRepositoryService().findD11TeamSeasonStatBySeasonId(seasonId);
        return ResponseEntity.ok(d11TeamSeasonStats);
    }

    @Override
    public ResponseEntity<D11TeamSeasonStatDTO> findD11TeamSeasonStatByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        final D11TeamSeasonStatDTO d11TeamSeasonStatDTO = getRepositoryService().findD11TeamSeasonStatByD11TeamIdAndSeasonId(d11TeamId, seasonId);
        return ResponseEntity.ok(d11TeamSeasonStatDTO);
    }

}
