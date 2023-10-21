package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * D11 team repository tests.
 */
class D11TeamRepositoryTests extends AbstractRepositoryTests<D11Team, D11TeamRepository> {

    /**
     * Tests D11TeamRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<D11Team> d11Teams = getEntities();
        d11Teams.sort(Comparator.comparing(D11Team::getName));

        final List<D11Team> result = getRepository().findByOrderByName();

        assertNotNull(result, "D11TeamRepository::findByOrderByName not null");
        assertEquals(d11Teams, result, "D11TeamRepository::findByOrderByName");
    }

}
