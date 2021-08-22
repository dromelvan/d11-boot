package org.d11.boot.application.controller;

import org.d11.boot.api.StadiaApi;
import org.d11.boot.api.model.StadiumDTO;
import org.d11.boot.application.service.api.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that implements the StadiaApi and provides stadium endpoints.
 */
@RestController
public class StadiumController extends AbstractRepositoryServiceController<StadiumDTO, StadiumService> implements StadiaApi {

    /**
     * Creates a new controller.
     *
     * @param stadiumService The repository service this controller will use.
     */
    @Autowired
    public StadiumController(final StadiumService stadiumService) {
        super(stadiumService);
    }

    @Override
    public ResponseEntity<StadiumDTO> findStadiumById(final Long stadiumId) {
        return findById(stadiumId);
    }

}
