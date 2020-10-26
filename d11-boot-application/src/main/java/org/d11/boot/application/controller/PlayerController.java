package org.d11.boot.application.controller;

import org.d11.boot.api.PlayersApi;
import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.application.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the PlayersApi and provides player endpoints.
 */
@RestController
public class PlayerController extends AbstractRepositoryServiceController<PlayerDTO, PlayerService> implements PlayersApi {

    /**
     * Creates a new controller.
     *
     * @param playerService The repository service this controller will use.
     */
    @Autowired
    public PlayerController(final PlayerService playerService) {
        super(playerService);
    }

    @Override
    public ResponseEntity<PlayerDTO> findPlayerById(final Long playerId) {
        return findById(playerId);
    }

}
