package org.d11.boot.application.service.api;

import org.d11.boot.api.model.TeamDTO;
import org.d11.boot.api.model.TeamNameDTO;
import org.d11.boot.application.model.Team;
import org.d11.boot.application.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides team services.
 */
@Service
public class TeamService extends ApiRepositoryService<Team, TeamDTO, TeamRepository> {

    /**
     * Creates a new service.
     *
     * @param teamRepository The repository this service will use.
     */
    @Autowired
    public TeamService(final TeamRepository teamRepository) {
        super(teamRepository);
    }

    /**
     * Gets all teams ordered by name.
     *
     * @return List of team name DTOs.
     */
    public List<TeamNameDTO> findAllTeams() {
        final List<Team> teams = getJpaRepository().findByOrderByName();
        return map(teams, TeamNameDTO.class);
    }

}
