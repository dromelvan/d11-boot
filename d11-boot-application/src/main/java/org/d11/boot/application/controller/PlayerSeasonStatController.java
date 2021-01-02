package org.d11.boot.application.controller;

import org.d11.boot.api.PlayerSeasonStatsApi;
import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.application.service.PlayerSeasonStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the PlayerSeasonStatsApi and provides player season stat endpoints.
 */
@RestController
public class PlayerSeasonStatController extends AbstractRepositoryServiceController<PlayerSeasonStatDTO, PlayerSeasonStatService> implements PlayerSeasonStatsApi {

    /**
     * Creates a new controller.
     *
     * @param playerSeasonStatService The repository service this
     */
    public PlayerSeasonStatController(final PlayerSeasonStatService playerSeasonStatService) {
        super(playerSeasonStatService);
    }

    @Override
    public ResponseEntity<PlayerSeasonStatDTO> findPlayerSeasonStatById(final Long playerSeasonStatId) {
        return findById(playerSeasonStatId);
    }

}
