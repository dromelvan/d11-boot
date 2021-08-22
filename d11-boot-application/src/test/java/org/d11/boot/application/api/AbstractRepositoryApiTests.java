package org.d11.boot.application.api;

import lombok.Getter;
import org.d11.boot.api.service.D11ApiService;
import org.d11.boot.application.model.jpa.D11Entity;
import org.d11.boot.application.repository.D11EntityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Base class for API tests.
 *
 * @param <T> The entity class this tests is based on.
 * @param <U> The repository class for the entity class.
 * @param <V> The API service class this test will use.
 */
public abstract class AbstractRepositoryApiTests<T extends D11Entity, U extends D11EntityRepository<T>, V extends D11ApiService> extends AbstractApiTests<V> {

    /**
     * Repository for the entity class the test is going to test.
     */
    @Getter
    @Autowired
    private U repository;
    /**
     * List of entities.
     */
    @Getter
    private final List<T> entities = new ArrayList<>();

    /**
     * Sets up the entities for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(this.repository.findAll());
        assertFalse(getEntities().isEmpty(), "Entities should not be empty.");
    }

}
