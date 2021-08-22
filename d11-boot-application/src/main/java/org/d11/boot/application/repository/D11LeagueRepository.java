package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.D11League;
import org.springframework.stereotype.Repository;

/**
 * Repository for D11 league entities.
 */
@Repository
public interface D11LeagueRepository extends D11EntityRepository<D11League> {

}
