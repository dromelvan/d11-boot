package org.d11.boot.spring.repository;


import org.d11.boot.spring.model.Goal;
import org.springframework.stereotype.Repository;

/**
 * Repository for Goal entities.
 */
@Repository
public interface GoalRepository extends D11EntityRepository<Goal> {

}
