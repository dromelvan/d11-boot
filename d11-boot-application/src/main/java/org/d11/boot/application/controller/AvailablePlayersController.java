package org.d11.boot.application.controller;

import org.d11.boot.api.AvailablePlayersApi;
import org.d11.boot.api.model.AvailablePlayersTeamDTO;
import org.d11.boot.application.service.AvailablePlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller that implements the AvailablePlayersApi and provides available player endpoints.
 */
@RestController
public class AvailablePlayersController extends D11BootController implements AvailablePlayersApi {

    /**
     * The available players service that will be used by this controller.
     */
    private final AvailablePlayersService availablePlayersService;

    /**
     * Creates a new controller.
     *
     * @param availablePlayersService The available players service that will be used by this controller.
     */
    @Autowired
    public AvailablePlayersController(final AvailablePlayersService availablePlayersService) {
        this.availablePlayersService = availablePlayersService;
    }

    @Override
    public ResponseEntity<List<AvailablePlayersTeamDTO>> findAvailablePlayers() {
        final List<AvailablePlayersTeamDTO> availablePlayersTeams = this.availablePlayersService.findAvailablePlayers();
        return ResponseEntity.ok(availablePlayersTeams);
    }

}
