package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.client.api.CountryApi;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Country API tests.
 */
public class CountryApiTests extends AbstractRepositoryApiTests<Country, CountryRepository> {

    /**
     * Tests the findCountryById API operation.
     */
    @Test
    public void findCountryById() {
        final CountryApi countryApi = getApi(CountryApi.class);
        for(final Country country : getEntities()) {
            final CountryDTO result = countryApi.findCountryById(country.getId());
            final CountryDTO countryDTO = map(country, CountryDTO.class);

            assertNotNull(result, "Country by id should not be null.");
            assertEquals(countryDTO, result, "Country by id should equal Country.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> countryApi.findCountryById(-1L),
                     "Country not found should throw NotFound exception.");
    }

    /**
     * Tests the findAllCountries API operation.
     */
    @Test
    public void findAllCountries() {
        final CountryApi countryApi = getApi(CountryApi.class);
        final List<CountryDTO> result = countryApi.findAllCountries();

        final List<CountryDTO> countryDTOs = map(getEntities(), CountryDTO.class);
        countryDTOs.sort(Comparator.comparing(CountryDTO::getName));

        assertNotNull(result, "All countries should not be null.");
        assertEquals(countryDTOs, result, "All countries should equal countries.");
    }

}
