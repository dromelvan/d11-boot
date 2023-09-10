package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Country;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Country repository tests.
 */
class CountryRepositoryTests extends D11BootRepositoryTests<Country, CountryRepository> {

    /**
     * Creates new Country repository tests.
     */
    CountryRepositoryTests() {
        super(Country.class);
    }

    /**
     * Tests CountryRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<Country> countries = getEntities();
        countries.sort(Comparator.comparing(Country::getName));

        final List<Country> result = getRepository().findByOrderByName();

        assertNotNull(result, "CountryRepository::findByOrderByName not null");
        assertTrue(result.size() >= 2, "CountryRepository::findByOrderByName size");
        assertEquals(countries, result, "CountryRepository::findByOrderByName");
    }

}
