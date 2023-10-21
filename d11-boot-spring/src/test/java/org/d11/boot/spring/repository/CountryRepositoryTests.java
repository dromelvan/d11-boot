package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Country;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Country repository tests.
 */
class CountryRepositoryTests extends AbstractRepositoryTests<Country, CountryRepository> {

    /**
     * Tests CountryRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<Country> countries = getEntities();
        countries.sort(Comparator.comparing(Country::getName));

        final List<Country> result = getRepository().findByOrderByName();

        assertNotNull(result, "CountryRepository::findByOrderByName not null");
        assertEquals(countries, result, "CountryRepository::findByOrderByName");
    }

}
