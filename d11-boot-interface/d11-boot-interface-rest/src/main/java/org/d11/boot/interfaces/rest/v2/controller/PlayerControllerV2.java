package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.PlayerApi;
import org.d11.boot.api.v2.model.PlayerDTO;
import org.d11.boot.api.v2.model.PlayerRequestBodyDTO;
import org.d11.boot.api.v2.model.PlayerResponseBodyDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultDTO;
import org.d11.boot.api.v2.model.PlayerSearchResultsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Player;
import org.d11.boot.spring.model.PlayerSearchResult;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Player API REST controller implementation.
 */
@RestController
public class PlayerControllerV2 extends RepositoryServiceController<PlayerService> implements PlayerApi {

    /**
     * Create a new controller.
     *
     * @param playerService The service the controller will use.
     */
    @Autowired
    public PlayerControllerV2(final PlayerService playerService) {
        super(playerService);
    }

    @Override
    public ResponseEntity<PlayerResponseBodyDTO> getPlayerById(final Long playerId) {
        final Player player = getRepositoryService().getById(playerId);

        return ResponseEntity.ok(new PlayerResponseBodyDTO()
                .player(getMapper().map(player, PlayerDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSearchResultsResponseBodyDTO> searchPlayersByName(final String name) {
        final List<PlayerSearchResult> playerSearchResults = getRepositoryService().searchByName(name);
        return ResponseEntity.ok(new PlayerSearchResultsResponseBodyDTO()
                .players(getMapper().map(playerSearchResults, PlayerSearchResultDTO.class)));
    }

    @Override
    @RoleAdmin
    public ResponseEntity<PlayerResponseBodyDTO> updatePlayer(final Long playerId,
                                                              final PlayerRequestBodyDTO playerRequestBodyDTO) {
        final Player player = map(playerRequestBodyDTO.getPlayer(), Player.class);
        final Player result = getRepositoryService().updatePlayer(playerId, player);

        return ResponseEntity.ok(new PlayerResponseBodyDTO()
                .player(getMapper().map(result, PlayerDTO.class)));
    }

}
