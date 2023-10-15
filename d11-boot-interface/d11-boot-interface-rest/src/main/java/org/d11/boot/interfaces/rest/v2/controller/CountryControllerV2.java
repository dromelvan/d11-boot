package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.CountryApi;
import org.d11.boot.api.v2.model.CountriesResponseBodyDTO;
import org.d11.boot.api.v2.model.CountryDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Country;
import org.d11.boot.spring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Country API REST controller implementation.
 */
@RestController
public class CountryControllerV2 extends RepositoryServiceController<CountryService> implements CountryApi {

    /**
     * Create a new controller.
     *
     * @param countryService The service the controller will use.
     */
    @Autowired
    public CountryControllerV2(final CountryService countryService) {
        super(countryService);
    }

    @Override
    public ResponseEntity<CountriesResponseBodyDTO> getCountries() {
        final List<Country> countries = getRepositoryService().getCountries();

        return ResponseEntity.ok(new CountriesResponseBodyDTO()
                .countries(getMapper().map(countries, CountryDTO.class)));
    }

}
