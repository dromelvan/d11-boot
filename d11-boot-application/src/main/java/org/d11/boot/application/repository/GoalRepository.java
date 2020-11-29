package org.d11.boot.application.repository;

import org.d11.boot.application.model.Goal;
import org.springframework.stereotype.Repository;

/**
 * Repository for goal entities.
 */
@Repository
public interface GoalRepository extends D11EntityRepository<Goal> {

}
