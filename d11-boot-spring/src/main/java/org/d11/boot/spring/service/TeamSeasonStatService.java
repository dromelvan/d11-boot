package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TeamSeasonStat;
import org.d11.boot.spring.repository.TeamSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.d11.boot.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Team season stat service.
 */
@Service
public class TeamSeasonStatService extends RepositoryService<TeamSeasonStat, TeamSeasonStatRepository> {

    /**
     * Season id property name.
     */
    private static final String SEASON_ID = "seasonId";

    /**
     * Must be positive error value.
     */
    private static final String MUST_BE_POSITIVE = "must be positive";

    /**
     * Creates a new team season stat service.
     *
     * @param teamSeasonStatRepository The repository the service will use.
     */
    @Autowired
    public TeamSeasonStatService(final TeamSeasonStatRepository teamSeasonStatRepository) {
        super(TeamSeasonStat.class, teamSeasonStatRepository);
    }

    /**
     * Get team season stats by season id ordered by ranking.
     *
     * @param seasonId The season id.
     * @return Team season stats by season id ordered by ranking.
     */
    public List<TeamSeasonStat> getBySeasonId(final Long seasonId) {
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        return getJpaRepository().findBySeasonIdOrderByRanking(seasonId);
    }

    /**
     * Get team season stat by team id and season id.
     *
     * @param teamId   The team id.
     * @param seasonId The season id.
     * @return Team season stat by team id and season id.
     */
    public TeamSeasonStat getByTeamIdAndSeasonId(final Long teamId, final Long seasonId) {
        if (teamId == null || teamId <= 0) {
            throw new BadRequestException("teamId", MUST_BE_POSITIVE);
        }
        if (seasonId == null || seasonId <= 0) {
            throw new BadRequestException(SEASON_ID, MUST_BE_POSITIVE);
        }

        return getJpaRepository().findByTeamIdAndSeasonId(teamId, seasonId)
                .orElseThrow(() -> new NotFoundException(teamId, TeamSeasonStat.class));
    }

}
