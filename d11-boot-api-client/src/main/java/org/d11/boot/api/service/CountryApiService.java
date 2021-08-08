package org.d11.boot.api.service;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.client.api.CountryApi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

/**
 * Provides country API services.
 */
@Service
public class CountryApiService extends D11ApiService {

    /**
     * Finds a country with a specific id.
     *
     * @param countryId The id of the country that will be looked up.
     * @return Country DTO for the specified id or null if no country was found.
     */
    public CountryDTO findCountryById(final Long countryId) {
        try {
            final CountryApi countryApi = new CountryApi(getApiClient());
            return countryApi.findCountryById(countryId).block();
        } catch(WebClientResponseException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                return null;
            }
            throw translate(e);
        }
    }

    /**
     * Gets a list of all countries.
     *
     * @return List of all countries sorted by name.
     */
    public List<CountryDTO> findAllCountries() {
        final CountryApi countryApi = new CountryApi(getApiClient());
        return countryApi.findAllCountries().collectList().block();
    }

}
