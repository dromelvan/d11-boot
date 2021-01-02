package org.d11.boot.application.service;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides player season stat services.
 */
@Service
public class PlayerSeasonStatService extends AbstractRepositoryService<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatRepository> {

    /**
     * Creates a new service.
     *
     * @param playerSeasonStatRepository The repository this service will use.
     */
    @Autowired
    public PlayerSeasonStatService(final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(playerSeasonStatRepository);
    }

}
