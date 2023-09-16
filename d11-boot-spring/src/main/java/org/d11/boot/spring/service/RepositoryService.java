package org.d11.boot.spring.service;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.repository.D11EntityRepository;
import org.d11.boot.util.exception.NotFoundException;

/**
 * Base class for services that use a specific repository.
 *
 * @param <T> Class of the entities the service will handle.
 * @param <U> Class of the repository the service will use.
 */
public class RepositoryService<T extends D11Entity, U extends D11EntityRepository<T>> extends D11BootService {

    /**
     * The JPA repository the service will use.
     */
    @Getter(AccessLevel.PROTECTED)
    private final U jpaRepository;

    /**
     * Class of the entity the service will handle.
     */
    @Getter(AccessLevel.PROTECTED)
    private final Class<T> entityClass;

    /**
     * Creates a new repository service.
     *
     * @param entityClass   Class of the entity the service will handle.
     * @param jpaRepository The JPA repository the service will use.
     */
    public RepositoryService(final Class<T> entityClass, final U jpaRepository) {
        this.entityClass = entityClass;
        this.jpaRepository = jpaRepository;
    }

    /**
     * Gets an entity by id.
     *
     * @param id Id of the entity.
     * @return Entity with the provided id.
     */
    public T getById(final long id) {
        return this.jpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, this.entityClass));
    }

    /**
     * Saves an entity.
     *
     * @param entity The entity.
     * @return The saved entity.
     */
    public T save(final T entity) {
        return this.jpaRepository.save(entity);
    }

}
