package org.d11.boot.application.service;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.DTOClassMapper;
import org.d11.boot.application.model.D11Entity;
import org.d11.boot.application.repository.D11EntityRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Abstract base class for repositry services.
 *
 * @param <T> Database entity class that this service will handle.
 * @param <U> DTO class that this service will handle.
 * @param <V> Repository class that this service will handle.
 */
@Slf4j
@Transactional
public abstract class AbstractRepositoryService<T extends D11Entity, U, V extends D11EntityRepository<T>> extends D11BootService {

    /**
     * The repository this service will use.
     */
    private final V jpaRepository;
    /**
     * The DTO class this service will produce.
     */
    private final Class<U> dtoClass;

    /**
     * Creates a new service.
     *
     * @param jpaRepository The repository this service will use.
     */
    @Autowired
    @SuppressWarnings("unchecked")
    public AbstractRepositoryService(final V jpaRepository) {
        this.jpaRepository = jpaRepository;

        try {
            // Really convoluted way of avoiding having to provide DTO class in every subclass.
            final Package entityPackage = D11Entity.class.getPackage();
            String entityClassName = getClass().getSimpleName();
            entityClassName = entityPackage.getName() + "." + entityClassName.substring(0, entityClassName.indexOf("Service"));
            final DTOClassMapper dtoClassMapper = new DTOClassMapper();
            this.dtoClass = (Class<U>) dtoClassMapper.getDTOClass(Class.forName(entityClassName));
        } catch(ClassNotFoundException e) {
            throw new ApplicationContextException("Could not map entity class to DTO class.", e);
        }
    }

    protected V getJpaRepository() {
        return this.jpaRepository;
    }

    /**
     * Finds a resource by id.
     *
     * @param id The id of the resource.
     * @return DTO of the resource with the specified id or throws NotFoundException if no such resource exists.
     */
    public U findById(final long id) {
        final Optional<T> optional = this.jpaRepository.findById(id);
        return find(optional);
    }

    /**
     * Converts an optional into a DTO.
     *
     * @param optional With an entity.
     * @return A DTO representing the entity in the optional. Throws NotFoundException if the optional is empty.
     */
    protected U find(final Optional<T> optional) {
        if(optional.isPresent()) {
            return map(optional.get());
        }
        throw new NotFoundException();
    }

    /**
     * Maps an entity to its corresponding DTO class.
     *
     * @param entity The entity to map.
     * @return DTO representation of the entity.
     */
    protected U map(final T entity) {
        return map(entity, this.dtoClass);
    }

    /**
     * Maps a list of entities to their corresponding DTO class.
     *
     * @param entities The list of entities to map.
     * @return List of DTO representations of the entities.
     */
    protected List<U> map(final List<T> entities) {
        return map(entities, this.dtoClass);
    }

}
