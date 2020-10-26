package org.d11.boot.application.repository;

import org.d11.boot.application.model.Player;
import org.springframework.stereotype.Repository;

/**
 * Repository for player entities.
 */
@Repository
public interface PlayerRepository extends D11EntityRepository<Player> {

}
