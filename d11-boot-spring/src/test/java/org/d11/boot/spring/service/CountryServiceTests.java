package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Country;
import org.d11.boot.spring.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Country service tests.
 */
class CountryServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked country repository.
     */
    @Mock
    private CountryRepository countryRepository;

    /**
     * Country service.
     */
    @InjectMocks
    private CountryService countryService;

    /**
     * Tests CountryService::getCountries.
     */
    @Test
    void testGetCountries() {
        final List<Country> countries = generateList(Country.class);
        when(this.countryRepository.findByOrderByName()).thenReturn(countries);

        final List<Country> result = this.countryService.getCountries();

        assertNotNull(result);
        assertTrue(result.size() >= 2);
        assertEquals(countries, result);
    }

}
