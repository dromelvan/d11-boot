package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.client.CountryApi;
import org.d11.boot.api.v2.model.CountriesResponseBodyDTO;
import org.d11.boot.api.v2.model.CountryDTO;
import org.d11.boot.spring.model.Country;
import org.d11.boot.spring.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Country controller tests.
 */
class CountryControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Country repository.
     */
    @Autowired
    private CountryRepository countryRepository;

    /**
     * Tests CountryController::getCountries.
     */
    @Test
    void testGetCountries() {
        final CountryApi countryApi = getApi(CountryApi.class);

        final List<Country> countries = this.countryRepository.findByOrderByName();
        assertFalse(countries.isEmpty(), "CountryController::getCountries not empty");

        final CountriesResponseBodyDTO countriesResponseBodyDTO = countryApi.getCountries();

        assertNotNull(countriesResponseBodyDTO, "CountryController::getCountries not null");

        final List<CountryDTO> result = countriesResponseBodyDTO.getCountries();

        assertEquals(countries.size(), result.size(), "CountryController::getCountries size");

        for (int i = 0; i < countries.size(); ++i) {
            final Country country = countries.get(i);
            final CountryDTO countryDTO = result.get(i);

            assertEquals(map(country, CountryDTO.class), countryDTO, "CountryController::getCountries equals");
        }
    }

}
