package org.d11.boot.application.service.api;

import org.d11.boot.api.model.CountryDTO;
import org.d11.boot.application.model.Country;
import org.d11.boot.application.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides country services.
 */
@Service
public class CountryService extends ApiRepositoryService<Country, CountryDTO, CountryRepository> {

    /**
     * Creates a new service.
     *
     * @param countryRepository The repository this service will use.
     */
    @Autowired
    public CountryService(final CountryRepository countryRepository) {
        super(countryRepository);
    }

    /**
     * Gets all countries ordered by name.
     *
     * @return List of country DTOs.
     */
    public List<CountryDTO> findAllCountries() {
        final List<Country> countries = getJpaRepository().findByOrderByName();
        return map(countries, CountryDTO.class);
    }

}
