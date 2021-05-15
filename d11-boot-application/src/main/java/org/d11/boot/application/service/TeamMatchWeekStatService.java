package org.d11.boot.application.service;

import org.d11.boot.api.model.TeamMatchWeekStatDTO;
import org.d11.boot.application.model.TeamMatchWeekStat;
import org.d11.boot.application.repository.TeamMatchWeekStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides team match week stat services.
 */
@Service
public class TeamMatchWeekStatService extends AbstractRepositoryService<TeamMatchWeekStat, TeamMatchWeekStatDTO, TeamMatchWeekStatRepository> {

    /**
     * Creates a new service.
     *
     * @param teamMatchWeekStatRepository The repository this service will use.
     */
    @Autowired
    public TeamMatchWeekStatService(final TeamMatchWeekStatRepository teamMatchWeekStatRepository) {
        super(teamMatchWeekStatRepository);
    }

}
