package org.d11.boot.spring.service;

import org.d11.boot.spring.model.TeamSeasonStat;
import org.d11.boot.spring.repository.TeamSeasonStatRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Team season stat service.
 */
@Service
public class TeamSeasonStatService extends RepositoryService<TeamSeasonStat, TeamSeasonStatRepository> {

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
            throw new BadRequestException("seasonId", "must be positive");
        }

        return getJpaRepository().findBySeasonIdOrderByRanking(seasonId);
    }

}
