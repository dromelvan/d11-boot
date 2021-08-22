package org.d11.boot.application.service.api;

import org.d11.boot.application.model.jpa.D11Entity;
import org.d11.boot.application.repository.D11EntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContextException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

/**
 * Tests the base repository service.
 */
@SuppressWarnings("unchecked")
public class RepositoryServiceTests {

    /**
     * Repository service that will not be able to generate a valid DTO class from its name.
     */
    @SuppressWarnings("PMD.CommentDefaultAccessModifier")
    private static class InvalidClassService extends ApiRepositoryService<D11Entity, Object, D11EntityRepository<D11Entity>> {
        InvalidClassService(final D11EntityRepository<D11Entity> d11EntityRepository) {
            super(d11EntityRepository);
        }
    }

    /**
     * Tests invalid DTO class name generation.
     */
    @Test
    public void invalidDTOClass() {
        final D11EntityRepository<D11Entity> d11EntityRepository = mock(D11EntityRepository.class);

        assertThrows(ApplicationContextException.class, () -> new InvalidClassService(d11EntityRepository));
    }

}
