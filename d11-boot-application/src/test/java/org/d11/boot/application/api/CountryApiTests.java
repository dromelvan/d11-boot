package org.d11.boot.application.api;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.api.service.CountryApiService;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.repository.CountryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Country API tests.
 */
public class CountryApiTests extends AbstractApiTests<Country, CountryRepository, CountryApiService> {

    /**
     * Tests the findCountryById API operation.
     */
    @Test
    public void findCountryById() {
        for(final Country country : getEntities()) {
            final CountryDTO result = getApiService().findCountryById(country.getId());
            final CountryDTO countryDTO = map(country, CountryDTO.class);

            assertNotNull(result, "Country by id should not be null.");
            assertEquals(countryDTO, result, "Country by id should equal Country.");
        }

        assertNull(getApiService().findCountryById(-1L), "Country not found should return null.");
        assertBadRequest(get("countries", "BAD_REQUEST"));
    }

}
