package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Team;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Team repository tests.
 */
class TeamRepositoryTests extends AbstractRepositoryTests<Team, TeamRepository> {

    /**
     * Tests TeamRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<Team> teams = getEntities();
        teams.sort(Comparator.comparing(Team::getName));

        final List<Team> result = getRepository().findByOrderByName();

        assertNotNull(result);
        assertEquals(teams, result);
    }

}
