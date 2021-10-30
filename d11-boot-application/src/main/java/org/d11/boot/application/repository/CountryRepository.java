package org.d11.boot.application.repository;

import org.d11.boot.application.model.Country;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for country entities.
 */
@Repository
public interface CountryRepository extends D11EntityRepository<Country> {

    /**
     * Finds a country with a specific name.
     *
     * @param name Name of the country that will be looked up.
     * @return Country with the specified name.
     */
    Optional<Country> findByName(@Param("name") String name);

    /**
     * Finds all countries.
     *
     * @return List of all countries, ordered by name.
     */
    List<Country> findByOrderByName();

}
