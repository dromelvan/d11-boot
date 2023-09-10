package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Country entities.
 */
@Repository
public interface CountryRepository extends D11EntityRepository<Country> {

    /**
     * Finds a list of all countries ordered by ascending name.
     *
     * @return List of all countries ordered by ascending name.
     */
    List<Country> findByOrderByName();

}
