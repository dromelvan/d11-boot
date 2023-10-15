package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Country;
import org.d11.boot.spring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Country service.
 */
@Service
public class CountryService extends RepositoryService<Country, CountryRepository> {

    /**
     * Creates a new country service.
     *
     * @param countryRepository The repository the service will use.
     */
    @Autowired
    public CountryService(final CountryRepository countryRepository) {
        super(Country.class, countryRepository);
    }

    /**
     * Gets a list of all countries ordered by ascending name.
     *
     * @return List of all countries ordered by ascending name.
     */
    public List<Country> getCountries() {
        return getJpaRepository().findByOrderByName();
    }

}
