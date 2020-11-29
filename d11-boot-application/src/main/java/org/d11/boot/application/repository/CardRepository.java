package org.d11.boot.application.repository;

import org.d11.boot.application.model.Card;
import org.springframework.stereotype.Repository;

/**
 * Repository for card entities.
 */
@Repository
public interface CardRepository extends D11EntityRepository<Card> {

}
