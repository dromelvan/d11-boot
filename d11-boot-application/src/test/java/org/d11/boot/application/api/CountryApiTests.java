package org.d11.boot.application.api;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.client.api.CountryApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Country API tests.
 */
public class CountryApiTests extends AbstractApiTests {

    /**
     * Country repository.
     */
    @Autowired
    private CountryRepository countryRepository;
    /**
     * List of countries.
     */
    private List<Country> countries;

    /**
     * Sets up mocked countries for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.countries = this.countryRepository.findAll();
    }

    /**
     * Tests the findCountryById API operation.
     */
    @Test
    public void findCountryById() {
        final CountryApi countryApi = new CountryApi(getApiClient());

        for(final Country country : this.countries) {
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
