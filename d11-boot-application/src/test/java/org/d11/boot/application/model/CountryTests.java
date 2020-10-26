package org.d11.boot.application.model;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.mock.D11EasyRandom;
import org.d11.boot.application.util.D11BootModelMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Country tests.
 */
public class CountryTests {

    /**
     * Random stadium generator.
     */
    private final D11EasyRandom d11EasyRandom = new D11EasyRandom();

    /**
     * Tests stadium validity.
     */
    @Test
    public void isValid() {
        final Country country = this.d11EasyRandom.nextObject(Country.class);

        assertTrue(country.isValid(), "New country should be valid.");

        country.setName("");
        assertFalse(country.isValid(), "Empty name should not be valid.");
        country.setName(null);
        assertFalse(country.isValid(), "Null name should not be valid.");
        country.setName("Name");

        country.setIso("");
        assertFalse(country.isValid(), "Empty ISO should not be valid.");
        country.setIso(null);
        assertFalse(country.isValid(), "Null ISO should not be valid.");
        country.setIso("A");
        assertFalse(country.isValid(), "Too short ISO should not be valid.");
        country.setIso("AAA");
        assertFalse(country.isValid(), "Too long ISO should not be valid.");
        country.setIso("AA");

        assertTrue(country.isValid(), "Country should be valid.");
    }

    /**
     * Tests mapping between Country and CountryDTO.
     */
    @Test
    public void map() {
        final Country country = this.d11EasyRandom.nextObject(Country.class);

        final ModelMapper modelMapper = new D11BootModelMapper();

        final CountryDTO countryDTO = modelMapper.map(country, CountryDTO.class);
        final Country mappedCountry = modelMapper.map(countryDTO, Country.class);

        assertEquals(country, mappedCountry, "Country should equal mapped country.");
    }

}
