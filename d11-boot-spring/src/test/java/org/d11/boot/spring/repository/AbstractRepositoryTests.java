package org.d11.boot.spring.repository;

import lombok.AccessLevel;
import lombok.Getter;
import org.d11.boot.spring.model.D11Entity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Base class for repository tests.
 *
 * @param <E> Entity class.
 * @param <T> Repository class.
 */
@DataJpaTest
@ActiveProfiles("test")
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
abstract class AbstractRepositoryTests<E extends D11Entity, T extends D11EntityRepository<E>> {

    /**
     * Repository that will be tested.
     */
    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private T repository;

    /**
     * Gets all entities of the repository entity class.
     *
     * @return List of entities of the repository entity class.
     */
    protected List<E> getEntities() {
        return this.repository.findAll();
    }

    /**
     * Tests that the test data is configured correctly.
     */
    @Test
    void testGetEntities() {
        final List<E> entities = getEntities();

        assertFalse(entities.isEmpty());
    }

}
