package org.d11.boot.application.service;

import org.d11.boot.api.model.PlayerSeasonStatDTO;
import org.d11.boot.api.model.TeamPlayerSeasonStatsDTO;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.util.NotFoundException;
import org.d11.boot.application.util.PlayerSeasonStatsToTeamPlayerSeasonStatsMapConverter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Provides player season stat services.
 */
@Service
public class PlayerSeasonStatService extends AbstractRepositoryService<PlayerSeasonStat, PlayerSeasonStatDTO, PlayerSeasonStatRepository> {

    /**
     * Page size for when getting player season stats for a season.
     */
    public static final int PAGE_SIZE = 25;

    /**
     * Creates a new service.
     *
     * @param playerSeasonStatRepository The repository this service will use.
     */
    @Autowired
    public PlayerSeasonStatService(final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(playerSeasonStatRepository);
    }

    /**
     * Gets player season stats for a player.
     *
     * @param playerId Id for the player for which player season stats will be looked up.
     * @return Player season stats for the player.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByPlayerId(final long playerId) {
        final List<PlayerSeasonStat> playerSeasonStats = getJpaRepository().findByPlayerIdOrderBySeasonIdDesc(playerId);
        return map(playerSeasonStats);
    }

    /**
     * Gets player season stats for a season.
     *
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @param page     Page number (25 per page) for the search result page that will be returned.
     * @return Player season stats for the season, in pages of size 25.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatBySeasonId(final long seasonId, final int page) {
        final Pageable pageable = PageRequest.of(page, PlayerSeasonStatService.PAGE_SIZE, Sort.by("ranking"));
        final List<PlayerSeasonStat> playerSeasonStats = getJpaRepository().findBySeasonId(seasonId, pageable);
        return map(playerSeasonStats);
    }

    /**
     * Gets a player season stat for a specific player and a specific season.
     *
     * @param playerId Id for the player for which a player season stat will be looked up.
     * @param seasonId Id for the season for which a player season stat will be looked up.
     * @return Player season stat for the player and the season.
     */
    public PlayerSeasonStatDTO findPlayerSeasonStatByPlayerIdAndSeasonId(final long playerId, final long seasonId) {
        final Optional<PlayerSeasonStat> optional = getJpaRepository().findByPlayerIdAndSeasonId(playerId, seasonId);
        return mapIfFound(optional.orElse(null));
    }

    /**
     * Gets player season stats for a specific team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, descending.
     *
     * @param teamId   Id for the team for which player season stats will be looked up.
     * @param seasonId Id for the season for which player season stats will be looked up.
     * @return Player season stat DTOs for the team and season.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByTeamIdAndSeasonId(final long teamId, final long seasonId) {
        final List<PlayerSeasonStat> playerSeasonStats = getJpaRepository()
                .findByTeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDesc(teamId, seasonId);
        return map(playerSeasonStats);
    }

    /**
     * Gets player season stats for a specific D11 team and a specific season ordered by player position sort order,
     * games started, descending, substitutions on, descending and games as substitute, descending.
     *
     * @param d11TeamId Id for the D11 team for which player season stats will be looked up.
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @return Player season stat DTOs for the D11 team and season.
     */
    public List<PlayerSeasonStatDTO> findPlayerSeasonStatByD11TeamIdAndSeasonId(final long d11TeamId, final long seasonId) {
        final List<PlayerSeasonStat> playerSeasonStats = getJpaRepository()
                .findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscGamesStartedDescSubstitutionsOnDescGamesSubstituteDescPointsDesc(d11TeamId, seasonId);
        return map(playerSeasonStats);
    }

    /**
     * Gets players grouped by team for a specific season.
     *
     * @param seasonId  Id for the season for which player season stats will be looked up.
     * @param available If true, only available players will be included.
     * @return Players form the season grouped by team.
     */
    public List<TeamPlayerSeasonStatsDTO> findTeamPlayerSeasonStatsBySeasonIdAndAvailable(final long seasonId, final boolean available) {
        final List<PlayerSeasonStat> playerSeasonStats = available
                ? getJpaRepository().findBySeasonIdAndTeamDummyAndD11TeamDummyOrderByTeamNameAscPositionSortOrderAscPlayerLastNameAsc(seasonId, false, true)
                : getJpaRepository().findBySeasonIdAndTeamDummyOrderByTeamNameAscPositionSortOrderAscPlayerLastNameAsc(seasonId, false);

        final List<Map<String, Object>> teamPlayerSeasonStatsMap = new PlayerSeasonStatsToTeamPlayerSeasonStatsMapConverter().convert(playerSeasonStats);
        if(teamPlayerSeasonStatsMap == null) {
            throw new NotFoundException();
        }

        // Fix updatePlayer so that it uses strict and everything else uses standard instead.
        setMatchingStrategy(MatchingStrategies.STANDARD);
        final List<TeamPlayerSeasonStatsDTO> teamPlayerSeasonStatsDTOs = map(teamPlayerSeasonStatsMap, TeamPlayerSeasonStatsDTO.class);
        setMatchingStrategy(MatchingStrategies.STRICT);
        return teamPlayerSeasonStatsDTOs;
    }

}
