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
            throw new BadRequestException("seasonId", MUST_BE_POSITIVE);
        }

        if (page < 0) {
            throw new BadRequestException("page", "must be non-negative");
        }

        final Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("ranking"));
        return getJpaRepository().findBySeasonId(seasonId, pageable);
    }

}
