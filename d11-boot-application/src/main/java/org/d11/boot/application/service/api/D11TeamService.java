package org.d11.boot.application.service.api;

import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.api.model.D11TeamNameDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.repository.D11TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides D11 team services.
 */
@Service
public class D11TeamService extends ApiRepositoryService<D11Team, D11TeamDTO, D11TeamRepository> {

    /**
     * Creates a new service.
     *
     * @param d11TeamRepository The repository this service will use.
     */
    @Autowired
    public D11TeamService(final D11TeamRepository d11TeamRepository) {
        super(d11TeamRepository);
    }

    /**
     * Gets all D11 teams ordered by name.
     *
     * @return List of D11 team name DTOs.
     */
    public List<D11TeamNameDTO> findAllD11Teams() {
        final List<D11Team> d11Teams = getJpaRepository().findByOrderByName();
        return map(d11Teams, D11TeamNameDTO.class);
    }


    /**
     * Gets D11 teams participating in a specific season.
     *
     * @param seasonId Id for the season for which D11 teams will be looked up.
     * @return D11 team DTOs participating in the specified season.
     */
    public List<D11TeamDTO> findD11TeamBySeasonId(final long seasonId) {
        final List<D11Team> d11Teams = getJpaRepository().findByD11TeamSeasonStatSeasonIdOrderByName(seasonId);
        return map(d11Teams);
    }

}
