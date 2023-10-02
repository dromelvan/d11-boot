package org.d11.boot.interfaces.rest;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.spring.service.RepositoryService;

/**
 * Base class for controllers that use a specific service.
 *
 * @param <T> Class of the service the controller will use.
 */
@SuppressFBWarnings(value = "EI_EXPOSE_REP2",
                    justification = "This comes from subclasses")
public class RepositoryServiceController<T extends RepositoryService<?, ?>> extends D11BootRestController {

    /**
     * The service the controller will use.
     */
    @Getter(AccessLevel.PROTECTED)
    private final T repositoryService;

    /**
     * Create a new controller.
     *
     * @param repositoryService The service the controller will use.
     */
    public RepositoryServiceController(final T repositoryService) {
        this.repositoryService = repositoryService;
    }

}
