package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Team service.
 */
@Service
public class TeamService extends RepositoryService<Team, TeamRepository> {

    /**
     * Creates a new team service.
     *
     * @param teamRepository The repository the service will use.
     */
    @Autowired
    public TeamService(final TeamRepository teamRepository) {
        super(Team.class, teamRepository);
    }

    /**
     * Gets a list of all teams ordered by name.
     *
     * @return List of all teams ordered by name.
     */
    public List<Team> getTeams() {
        return getJpaRepository().findByOrderByName();
    }

}
