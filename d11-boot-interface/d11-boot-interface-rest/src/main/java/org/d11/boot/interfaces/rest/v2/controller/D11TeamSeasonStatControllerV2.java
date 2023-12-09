package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.D11TeamSeasonStatApi;
import org.d11.boot.api.v2.model.D11TeamSeasonStatDTO;
import org.d11.boot.api.v2.model.D11TeamSeasonStatsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.service.D11TeamSeasonStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * D11 team season stat API REST controller implementation.
 */
@RestController
public class D11TeamSeasonStatControllerV2 extends RepositoryServiceController<D11TeamSeasonStatService>
        implements D11TeamSeasonStatApi {

    /**
     * Create a new controller.
     *
     * @param d11TeamSeasonStatService The service the controller will use.
     */
    @Autowired
    public D11TeamSeasonStatControllerV2(final D11TeamSeasonStatService d11TeamSeasonStatService) {
        super(d11TeamSeasonStatService);
    }

    @Override
    public ResponseEntity<D11TeamSeasonStatsResponseBodyDTO> getD11TeamSeasonStatsBySeasonId(final Long seasonId) {
        final List<D11TeamSeasonStat> d11TeamSeasonStats = getRepositoryService().getBySeasonId(seasonId);

        return ResponseEntity.ok(new D11TeamSeasonStatsResponseBodyDTO()
                                         .d11TeamSeasonStats(getMapper().map(d11TeamSeasonStats,
                                                                             D11TeamSeasonStatDTO.class)));
    }

}
