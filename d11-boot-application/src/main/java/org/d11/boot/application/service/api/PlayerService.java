package org.d11.boot.application.service.api;

import org.d11.boot.api.model.PlayerDTO;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides player services.
 */
@Service
public class PlayerService extends ApiRepositoryService<Player, PlayerDTO, PlayerRepository> {

    /**
     * Creates a new service.
     *
     * @param playerRepository The repository this service will use.
     */
    @Autowired
    public PlayerService(final PlayerRepository playerRepository) {
        super(playerRepository);
    }

}
