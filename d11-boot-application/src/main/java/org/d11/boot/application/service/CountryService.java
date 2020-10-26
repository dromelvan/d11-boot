package org.d11.boot.application.service;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides country services.
 */
@Service
public class CountryService extends AbstractRepositoryService<Country, CountryDTO, CountryRepository> {

    /**
     * Creates a new service.
     *
     * @param countryRepository The repository this service will use.
     */
    @Autowired
    public CountryService(final CountryRepository countryRepository) {
        super(countryRepository);
    }

}
