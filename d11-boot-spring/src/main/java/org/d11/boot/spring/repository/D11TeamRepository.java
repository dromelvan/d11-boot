package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for D11 Team entities.
 */
@Repository
public interface D11TeamRepository extends D11EntityRepository<D11Team> {

    /**
     * Finds a list of all D11 teams ordered by ascending name.
     *
     * @return List of all D11 teams ordered by ascending name.
     */
    List<D11Team> findByOrderByName();

}
