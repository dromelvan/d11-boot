package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * D11 team service.
 */
@Service
public class D11TeamService extends RepositoryService<D11Team, D11TeamRepository> {

    /**
     * Creates a new team service.
     *
     * @param d11TeamRepository The repository the service will use.
     */
    @Autowired
    public D11TeamService(final D11TeamRepository d11TeamRepository) {
        super(D11Team.class, d11TeamRepository);
    }

    /**
     * Gets a list of all D11 teams ordered by name.
     *
     * @return List of all D11 teams ordered by name.
     */
    public List<D11Team> getD11Teams() {
        return getJpaRepository().findByOrderByName();
    }

}
