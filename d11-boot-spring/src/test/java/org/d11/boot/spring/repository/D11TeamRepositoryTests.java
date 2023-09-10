package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.D11Team;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * D11 team repository tests.
 */
class D11TeamRepositoryTests extends D11BootRepositoryTests<D11Team, D11TeamRepository> {

    /**
     * Creates new D11 team repository tests.
     */
    D11TeamRepositoryTests() {
        super(D11Team.class);
    }

    @Override
    protected void beforeSave(final D11Team d11Team) {
        super.beforeSave(d11Team);
        d11Team.getOwner().setId(null);
        d11Team.getCoOwner().setId(null);
    }

    /**
     * Tests D11TeamRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<D11Team> d11Teams = getEntities();
        d11Teams.sort(Comparator.comparing(D11Team::getName));

        final List<D11Team> result = getRepository().findByOrderByName();

        assertNotNull(result, "D11TeamRepository::findByOrderByName not null");
        assertTrue(result.size() >= 2, "D11TeamRepository::findByOrderByName size");
        assertEquals(d11Teams, result, "D11TeamRepository::findByOrderByName");
    }

}
