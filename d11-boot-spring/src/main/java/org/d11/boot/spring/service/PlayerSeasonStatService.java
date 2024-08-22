package org.d11.boot.spring.service;

import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.repository.PlayerSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Player season stat service.
 */
@Service
public class PlayerSeasonStatService extends RepositoryService<PlayerSeasonStat, PlayerSeasonStatRepository> {

    /**
     * Player season stat list page size.
     */
    public static final int PAGE_SIZE = 25;

    /**
     * Season id property name.
     */
    private static final String SEASON_ID = "seasonId";

    /**
     * Must be positive error value.
     */
    private static final String MUST_BE_POSITIVE = "must be positive";

    /**
     * Creates a new player season stat service.
     *
     * @param playerSeasonStatRepository The repository the service will use.
     */
    @Autowired
    public PlayerSeasonStatService(final PlayerSeasonStatRepository playerSeasonStatRepository) {
        super(PlayerSeasonStat.class, playerSeasonStatRepository);
    }

    /**
     * Get player season stats by player id ordered by season id, descending.
     *
     * @param playerId The player id.
     * @return Player season stats by player id ordered by season id, descending.
     */
    public List<PlayerSeasonStat> getByPlayerId(final Long playerId) {
        if (playerId == null || playerId <= 0) {
            throw new BadRequestException("playerId", MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByPlayerIdOrderBySeasonIdDesc(playerId);
    }

    /**
     * Get player season stats by season id ordered by ranking.
     *
     * @param seasonId The season id.
     * @param page     Page number (25 per page) for the search result page that will be returned.
     * @return Player season stats by season id ordered by ranking in pages of size 25.
     */
    public List<PlayerSeasonStat> getBySeasonId(final Long seasonId, final int page) {
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        if (page < 0) {
            throw new BadRequestException("page", "must be non-negative");
        }

        final Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("ranking"));
        return getJpaRepository().findBySeasonId(seasonId, pageable);
    }

    /**
     * Get player season stats by team id and season ordered by position and ranking.
     *
     * @param teamId  The team id.
     * @param seasonId The season id.
     * @return Player season stats by team id and season id ordered by position and ranking.
     */
    public List<PlayerSeasonStat> getByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        if (teamId == null || teamId <= 0) {
            throw new BadRequestException("teamId", MUST_BE_POSITIVE);
        }
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByTeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(teamId, seasonId);
    }

    /**
     * Get player season stats by D11 team id and season ordered by position and ranking.
     *
     * @param d11TeamId  The D11 team id.
     * @param seasonId   The season id.
     * @return Player season stats by D11 team id and season id ordered by position and ranking.
     */
    public List<PlayerSeasonStat> getByD11TeamIdAndSeasonId(final Long d11TeamId, final Long seasonId) {
        if (d11TeamId == null || d11TeamId <= 0) {
            throw new BadRequestException("d11TeamId", MUST_BE_POSITIVE);
        }
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByD11TeamIdAndSeasonIdOrderByPositionSortOrderAscRanking(d11TeamId, seasonId);
    }

}
