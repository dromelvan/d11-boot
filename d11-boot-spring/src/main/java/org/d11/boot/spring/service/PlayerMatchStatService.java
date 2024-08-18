package org.d11.boot.spring.service;

import org.d11.boot.spring.model.PlayerMatchStat;
import org.d11.boot.spring.repository.PlayerMatchStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Player match stat service.
 */
@Service
public class PlayerMatchStatService extends RepositoryService<PlayerMatchStat, PlayerMatchStatRepository> {

    /**
     * Creates a new player match stat service.
     *
     * @param playerMatchStatRepository The repository the service will use.
     */
    @Autowired
    public PlayerMatchStatService(final PlayerMatchStatRepository playerMatchStatRepository) {
        super(PlayerMatchStat.class, playerMatchStatRepository);
    }

    /**
     * Get player match stats by match id ordered by position sort order.
     *
     * @param matchId The match id.
     * @return Player match stats by match id ordered by position sort order.
     */
    public List<PlayerMatchStat> getByMatchId(final Long matchId) {
        if (matchId == null || matchId <= 0) {
            throw new BadRequestException("matchId", "must be positive");
        }

        return getJpaRepository().findByMatchIdOrderByPositionSortOrder(matchId);
    }

}
