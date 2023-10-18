package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.D11TeamApi;
import org.d11.boot.api.v2.model.D11TeamDTO;
import org.d11.boot.api.v2.model.D11TeamsResponseBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.service.D11TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * D11 team API REST controller implementation.
 */
@RestController
public class D11TeamControllerV2 extends RepositoryServiceController<D11TeamService> implements D11TeamApi {

    /**
     * Create a new controller.
     *
     * @param d11TeamService The service the controller will use.
     */
    @Autowired
    public D11TeamControllerV2(final D11TeamService d11TeamService) {
        super(d11TeamService);
    }

    @Override
    public ResponseEntity<D11TeamsResponseBodyDTO> getD11Teams() {
        final List<D11Team> d11Teams = getRepositoryService().getD11Teams();

        return ResponseEntity.ok(new D11TeamsResponseBodyDTO()
                .d11Teams(getMapper().map(d11Teams, D11TeamDTO.class)));
    }

}
