package org.d11.boot.application.service;

import org.d11.boot.api.model.TeamTableStatDTO;
import org.d11.boot.application.model.TeamTableStat;
import org.d11.boot.application.repository.TeamTableStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
