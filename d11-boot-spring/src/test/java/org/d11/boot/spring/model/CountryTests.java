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

        assertTrue(country.isValid(), "Country::isValid");

        country.setName("");
        assertFalse(country.isValid(), "Country::isValid name empty");
        country.setName(null);
        assertFalse(country.isValid(), "Country::isValid name null");
        country.setName("Name");

        country.setIso("");
        assertFalse(country.isValid(), "Country::isValid ISO empty");
        //noinspection DataFlowIssue
        country.setIso(null);
        assertFalse(country.isValid(), "Country::isValid ISO null");
        country.setIso("A");
        assertFalse(country.isValid(), "Country::isValid ISO too short");
        country.setIso("AAA");
        assertFalse(country.isValid(), "Country::isValid ISO too long");
        country.setIso("AA");

        assertTrue(country.isValid(), "Country::isValid valid");
    }

}
