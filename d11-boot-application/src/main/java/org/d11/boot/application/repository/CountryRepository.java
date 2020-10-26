package org.d11.boot.application.repository;

import org.d11.boot.application.model.Country;
import org.springframework.stereotype.Repository;

/**
 * Repository for country entities.
 */
@Repository
public interface CountryRepository extends D11EntityRepository<Country> {

}
