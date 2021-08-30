package org.d11.boot.application.controller;

import org.d11.boot.api.PlayersApi;
import org.d11.boot.api.model.InsertPlayerDTO;
import org.d11.boot.api.model.InsertPlayerResultDTO;
import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.api.model.UpdatePlayerDTO;
import org.d11.boot.api.model.UpdatePlayerResultDTO;
import org.d11.boot.application.service.api.PlayerAdminService;
import org.d11.boot.application.service.api.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller that implements the PlayersApi and provides player endpoints.
 */
@RestController
public class PlayerController extends AbstractRepositoryServiceController<PlayerDTO, PlayerService> implements PlayersApi {

    /**
     * The player admin service this controller will use.
     */
    private final PlayerAdminService playerAdminService;

    /**
     * Creates a new controller.
     *
     * @param playerService      The repository service this controller will use.
     * @param playerAdminService The player admin service this controller will use.
     */
    @Autowired
    public PlayerController(final PlayerService playerService, final PlayerAdminService playerAdminService) {
        super(playerService);
        this.playerAdminService = playerAdminService;
    }

    @Override
    public ResponseEntity<PlayerDTO> findPlayerById(final Long playerId) {
        return findById(playerId);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<InsertPlayerResultDTO> insertPlayer(@Valid final InsertPlayerDTO insertPlayerDTO) {
        return ResponseEntity.ok(this.playerAdminService.insertPlayer(insertPlayerDTO));
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UpdatePlayerResultDTO> updatePlayer(@Valid final UpdatePlayerDTO updatePlayerDTO) {
        return ResponseEntity.ok(this.playerAdminService.updatePlayer(updatePlayerDTO, false));
    }

}
