package org.d11.boot.spring.repository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.spring.model.D11Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for repository tests.
 *
 * @param <E> Entity class.
 * @param <T> Repository class.
 */
@DataJpaTest
@ActiveProfiles("test")
public class D11BootRepositoryTests<E extends D11Entity, T extends D11EntityRepository<E>> extends EasyRandomTests {

    /**
     * Repository that will be tested.
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private T repository;

    /**
     * Generated entity list.
     */
    @Getter(AccessLevel.PROTECTED)
    private final List<E> entities = new ArrayList<>();

    /**
     * Class of the entity the repository handles.
     */
    @Getter(AccessLevel.PROTECTED)
    private final Class<E> entityClass;

    /**
     * Creates new repository tests.
     *
     * @param entityClass Class of the entity the repository handles.
     */
    public D11BootRepositoryTests(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Generates a list of entities and saves them.
     */
    @BeforeEach
    @Transactional
    protected void beforeEach() {
        this.entities.addAll(generateList(this.entityClass));
        entities.forEach(entity -> {
            beforeSave(entity);
            this.repository.save(entity);
        });
    }

    /**
     * Called for each entity before saving them in beforeEach(). Override this method to, for example, set ids of
     * associated entities to null before saving.
     *
     * @param entity Entity that will be saved.
     */
    protected void beforeSave(final E entity) {
        entity.setId(null);
    }

    /**
     * Deletes the generated entities.
     */
    @AfterEach
    protected void afterEach() {
        this.repository.deleteAll();
        this.entities.clear();
    }

}
