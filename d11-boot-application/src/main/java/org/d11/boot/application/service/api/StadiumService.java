package org.d11.boot.application.service.api;

import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.application.model.Stadium;
import org.d11.boot.application.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides stadium services.
 */
@Service
public class StadiumService extends ApiRepositoryService<Stadium, StadiumDTO, StadiumRepository> {

    /**
     * Creates a new service.
     *
     * @param stadiumRepository The repository this service will use.
     */
    @Autowired
    public StadiumService(final StadiumRepository stadiumRepository) {
        super(stadiumRepository);
    }

}
