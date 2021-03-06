package org.d11.boot.application.service;

import org.d11.boot.api.model.D11TeamDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.repository.D11TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides D11 team services.
 */
@Service
public class D11TeamService extends AbstractRepositoryService<D11Team, D11TeamDTO, D11TeamRepository> {

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
