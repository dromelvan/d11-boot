package org.d11.boot.spring.repository;

import org.d11.boot.spring.model.Team;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Team repository tests.
 */
class TeamRepositoryTests extends D11BootRepositoryTests<Team, TeamRepository> {

    /**
     * Creates new Team repository tests.
     */
    TeamRepositoryTests() {
        super(Team.class);
    }

    @Override
    protected void beforeSave(final Team team) {
        super.beforeSave(team);
        team.getStadium().setId(null);
    }

    /**
     * Tests TeamRepository::findByOrderByName.
     */
    @Test
    void testFindByOrderByName() {
        final List<Team> teams = getEntities();
        teams.sort(Comparator.comparing(Team::getName));

        final List<Team> result = getRepository().findByOrderByName();

        assertNotNull(result, "TeamRepository::findByOrderByName not null");
        assertTrue(result.size() >= 2, "TeamRepository::findByOrderByName size");
        assertEquals(teams, result, "TeamRepository::findByOrderByName");
    }

}
