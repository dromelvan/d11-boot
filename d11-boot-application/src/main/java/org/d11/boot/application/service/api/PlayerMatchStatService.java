package org.d11.boot.application.service.api;

import org.d11.boot.api.model.PlayerMatchStatDTO;
import org.d11.boot.application.model.jpa.Lineup;
import org.d11.boot.application.model.jpa.PlayerMatchStat;
import org.d11.boot.application.repository.PlayerMatchStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Provides player match stat services.
 */
@Service
public class PlayerMatchStatService extends ApiRepositoryService<PlayerMatchStat, PlayerMatchStatDTO, PlayerMatchStatRepository> {

    /**
     * A set of active lineups to use when finding active players for a match.
     */
    private final Set<Lineup> activeLineups = Set.of(Lineup.STARTING_LINEUP, Lineup.SUBSTITUTE);

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
     * Gets active (starting lineup or substitute) player match stats for a specific match and team ordered by player
     * position sort order and descending lineup.
     *
     * @param matchId Id for the match for which player match stats will be looked up.
     * @param teamId Id for the team for which player match stats will be looked up.
     * @return Active player match stat DTOs for the match and team.
     */
    public List<PlayerMatchStatDTO> findActivePlayerMatchStatByMatchIdAndTeamId(final long matchId, final long teamId) {
        final List<PlayerMatchStat> playerMatchStats =
                getJpaRepository().findByMatchIdAndTeamIdAndLineupInOrderByPositionSortOrderAscLineupDesc(matchId, teamId, this.activeLineups);
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
     * Gets player match stats for a specific D11 match and a specific D11 team ordered by player position sort order.
     *
     * @param d11MatchId Id for the D11 match for which player match stats will be looked up.
     * @param d11TeamId Id for the D11 team for which player match stats will be looked up.
     * @return Player match stat DTOs for the D11 match.
     */
    public List<PlayerMatchStatDTO> findPlayerMatchStatByD11MatchIdAndD11TeamId(final long d11MatchId,
                                                                                final long d11TeamId) {
        final List<PlayerMatchStat> playerMatchStats =
                getJpaRepository().findByD11MatchIdAndD11TeamIdOrderByPositionSortOrderAscLineupDesc(d11MatchId, d11TeamId);
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
                .findByPlayerIdAndMatchMatchWeekSeasonIdOrderByMatchDatetime(playerId, seasonId);
        return map(playerMatchStats);
    }

    /**
     * Gets the top 5 player match stats for a match week ordered by points and rating.
     *
     * @param matchWeekId Id for the match week for which player match stats will be looked up.
     * @return The top 5 player match stats for the match week.
     */
    public List<PlayerMatchStatDTO> findTop5PlayerMatchStatByMatchWeek(final long matchWeekId) {
        final List<PlayerMatchStat> playerMatchStats = getJpaRepository()
                .findTop5ByMatchMatchWeekIdAndLineupInOrderByPointsDescRatingDesc(matchWeekId, this.activeLineups);
        return map(playerMatchStats);
    }

    /**
     * Gets the bottom 5 player match stats for a match week ordered by points and rating.
     *
     * @param matchWeekId Id for the match week for which player match stats will be looked up.
     * @return The bottom 5 player match stats for the match week.
     */
    public List<PlayerMatchStatDTO> findBottom5PlayerMatchStatByMatchWeek(final long matchWeekId) {
        final List<PlayerMatchStat> playerMatchStats = getJpaRepository()
                .findTop5ByMatchMatchWeekIdAndLineupInOrderByPointsAscRatingAsc(matchWeekId, this.activeLineups);
        return map(playerMatchStats);
    }

}
