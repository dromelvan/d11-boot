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
     * Must be positive error value.
     */
    private static final String MUST_BE_POSITIVE = "must be positive";

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
            throw new BadRequestException("matchId", MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByMatchIdOrderByPositionSortOrder(matchId);
    }

    /**
     * Get player match stats by D11 match id ordered by position sort order.
     *
     * @param d11MatchId The D11 match id.
     * @return Player match stats by D11 match id ordered by position sort order.
     */
    public List<PlayerMatchStat> getByD11MatchId(final Long d11MatchId) {
        if (d11MatchId == null || d11MatchId <= 0) {
            throw new BadRequestException("d11MatchId", MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByD11MatchIdOrderByPositionSortOrder(d11MatchId);
    }

}
