package org.d11.boot.application.repository;

import org.d11.boot.application.model.jpa.PremierLeague;
import org.springframework.stereotype.Repository;

/**
 * Repository for premier league entities.
 */
@Repository
public interface PremierLeagueRepository extends D11EntityRepository<PremierLeague> {

}
