package org.d11.boot.application.service;

import org.d11.boot.api.model.TeamTableStatDTO;
import org.d11.boot.application.model.TeamTableStat;
import org.d11.boot.application.repository.TeamTableStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides team table stat services.
 */
@Service
public class TeamTableStatService extends AbstractRepositoryService<TeamTableStat, TeamTableStatDTO, TeamTableStatRepository> {

    /**
     * Creates a new service.
     *
     * @param teamTableStatRepository The repository this service will use.
     */
    @Autowired
    public TeamTableStatService(final TeamTableStatRepository teamTableStatRepository) {
        super(teamTableStatRepository);
    }

    /**
     * Gets the main team table stats for a Premier League ordered by ranking.
     * This will be the current league table standings.
     *
     * @param premierLeagueId Id for the Premier League for which team table stats will be looked up.
     * @return The current league table standings for the Premier League.
     */
    public List<TeamTableStatDTO> findTeamTableStatByPremierLeagueId(final Long premierLeagueId) {
        final List<TeamTableStat> teamTableStats =
                getJpaRepository().findByPremierLeagueIdAndMainOrderByMatchWeekIdDescRanking(premierLeagueId, true);
        return map(teamTableStats);
    }

}
