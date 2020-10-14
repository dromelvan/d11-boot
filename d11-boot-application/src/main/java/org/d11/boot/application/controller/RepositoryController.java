package org.d11.boot.application.controller;

import org.d11.boot.application.service.AbstractRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Base class for controllers using repository services and returning entity DTOs.
 *
 * @param <T> The DTO class this controller will return.
 * @param <U> The repository service class this controller will use.
 */
public class RepositoryController<T, U extends AbstractRepositoryService<?, T, ?>> extends D11BootController {

    /**
     * The repository service this controller will use.
     */
    private final U repositoryService;

    /**
     * Creates a new controller.
     *
     * @param repositoryService The repository service this
     */
    @Autowired
    public RepositoryController(final U repositoryService) {
        this.repositoryService = repositoryService;
    }

    protected U getRepositoryService() {
        return this.repositoryService;
    }

    /**
     * Finds an entity by id.
     *
     * @param id The id of the entity.
     * @return OK response with the entity with the specified id.
     */
    public ResponseEntity<T> findById(final long id) {
        final T d11ApiModelDTO = this.repositoryService.findById(id);
        return ResponseEntity.ok(d11ApiModelDTO);
    }

}
