package org.d11.boot.application.service;

import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.application.model.PlayerMatchStat;
import org.d11.boot.application.repository.PlayerMatchStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides player match stat services.
 */
@Service
public class PlayerMatchStatService extends AbstractRepositoryService<PlayerMatchStat, PlayerMatchStatDTO, PlayerMatchStatRepository> {

    /**
     * Creates a new service.
     *
     * @param playerMatchStatRepository The repository this service will use.
     */
    @Autowired
    public PlayerMatchStatService(final PlayerMatchStatRepository playerMatchStatRepository) {
        super(playerMatchStatRepository);
    }

    /**
     * Gets player match stats for a specific match ordered by player position sort order.
     *
     * @param matchId Id for the match for which player match stats will be looked up.
     * @return Player match stat DTOs for the match.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByMatchId(final long matchId) {
        final List<PlayerMatchStat> playerMatchStats = getJpaRepository().findByMatchIdOrderByPositionSortOrder(matchId);
        return map(playerMatchStats);
    }

    /**
     * Gets player match stats for a specific D11 match ordered by player position sort order.
     *
     * @param d11MatchId Id for the D11 match for which player match stats will be looked up.
     * @return Player match stat DTOs for the D11 match.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByD11MatchId(final long d11MatchId) {
        final List<PlayerMatchStat> playerMatchStats = getJpaRepository().findByD11MatchIdOrderByPositionSortOrder(d11MatchId);
        return map(playerMatchStats);
    }

    /**
     * Gets player match stats for a specific player and season ordered by match datetime.
     *
     * @param playerId Id for the player for which player match stats will be looked up.
     * @param seasonId Id for the season for which player match stats will be looked up.
     * @return Player match stat DTOs for the player and season.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByPlayerIdAndSeasonId(final long playerId, final long seasonId) {
        final List<PlayerMatchStat> playerMatchStats = getJpaRepository()
                .findByPlayerIdAndMatchMatchWeekPremierLeagueSeasonIdOrderByMatchDatetime(playerId, seasonId);
        return map(playerMatchStats);
    }

}
