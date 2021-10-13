package org.d11.boot.application.repository;

import org.d11.boot.application.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for country entities.
 */
@Repository
public interface CountryRepository extends D11EntityRepository<Country> {

    /**
     * Finds all countries.
     *
     * @return List of all countries, ordered by name.
     */
    List<Country> findByOrderByName();

}
