package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Country tests.
 */
class CountryTests extends EasyRandomTests {

    /**
     * Tests Country::isValid.
     */
    @Test
    void testIsValid() {
        final Country country = generate(Country.class);

        assertTrue(country.isValid());

        country.setName("");
        assertFalse(country.isValid());
        country.setName(null);
        assertFalse(country.isValid());
        country.setName("Name");

        country.setIso("");
        assertFalse(country.isValid());
        country.setIso(null);
        assertFalse(country.isValid());
        country.setIso("A");
        assertFalse(country.isValid());
        country.setIso("AAA");
        assertFalse(country.isValid());
        country.setIso("AA");

        assertTrue(country.isValid());
    }

}
