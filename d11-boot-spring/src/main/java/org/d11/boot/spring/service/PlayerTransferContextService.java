package org.d11.boot.spring.service;

import org.d11.boot.spring.model.PlayerTransferContext;
import org.d11.boot.spring.model.User;
import org.d11.boot.spring.repository.PlayerTransferContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Player transfer context service.
 */
@Service
public class PlayerTransferContextService extends D11BootService {

    /**
     * The repository the service will use.
     */
    private final PlayerTransferContextRepository repository;

    /**
     * Creates a new player transfer context service.
     *
     * @param repository The repository the service will use.
     */
    @Autowired
    public PlayerTransferContextService(final PlayerTransferContextRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets player transfer context for a player and the current user D11 team.
     *
     * @param playerId The player id.
     * @return Player transfer context for a player and the current user D11 team.
     */
    public PlayerTransferContext getByPlayerId(final Long playerId) {
        final Optional<User> optional = getCurrentUser();
        return optional
                .flatMap(user -> this.repository.findByPlayerIdAndOwnerId(playerId, user.getId()))
                .orElse(new PlayerTransferContext());
    }

}
