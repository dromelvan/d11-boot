package org.d11.boot.application.api;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.model.Country;
import org.d11.boot.client.api.CountryApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Country API tests.
 */
public class CountryApiTests extends AbstractApiTests<Country> {

    /**
     * Sets up mocked countries for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getCountryRepository().findAll());
    }

    /**
     * Tests the findCountryById API operation.
     */
    @Test
    public void findCountryById() {
        final CountryApi countryApi = new CountryApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Countries should not be empty.");

        for(final Country country : getEntities()) {
            final CountryDTO result = countryApi.findCountryById(country.getId()).block();
            final CountryDTO countryDTO = map(country, CountryDTO.class);

            assertNotNull(result, "Country by id should not be null.");
            assertEquals(countryDTO, result, "Country by id should equal Country.");
        }

        assertNotFound(countryApi.findCountryById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

    /**
     * Correct resource string for 'country'.
     *
     * @return "countries".
     */
    @Override
    protected String getResourceString() {
        return "countries";
    }

}
