package org.d11.boot.application.controller;

import org.d11.boot.api.CountriesApi;
import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the CountriesApi and provides country endpoints.
 */
@RestController
public class CountryController extends AbstractRepositoryServiceController<CountryDTO, CountryService> implements CountriesApi {

    /**
     * Creates a new controller.
     *
     * @param countryService The repository service this controller will use.
     */
    @Autowired
    public CountryController(final CountryService countryService) {
        super(countryService);
    }

    @Override
    public ResponseEntity<CountryDTO> findCountryById(final Long countryId) {
        return findById(countryId);
    }

}
