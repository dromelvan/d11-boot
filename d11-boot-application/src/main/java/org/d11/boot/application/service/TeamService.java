package org.d11.boot.application.service;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides team services.
 */
@Service
public class TeamService extends AbstractRepositoryService<Team, TeamDTO, TeamRepository> {

    /**
     * Creates a new service.
     *
     * @param teamRepository The repository this service will use.
     */
    @Autowired
    public TeamService(final TeamRepository teamRepository) {
        super(teamRepository);
    }

}
