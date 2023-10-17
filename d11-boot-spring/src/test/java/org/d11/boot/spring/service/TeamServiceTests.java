package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Team service tests.
 */
class TeamServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked team repository.
     */
    @Mock
    private TeamRepository teamRepository;

    /**
     * Team service.
     */
    @InjectMocks
    private TeamService teamService;

    /**
     * Tests TeamService::getTeams.
     */
    @Test
    void testGetTeams() {
        final List<Team> teams = generateList(Team.class);
        when(this.teamRepository.findByOrderByName()).thenReturn(teams);

        final List<Team> result = this.teamService.getTeams();

        assertNotNull(result, "TeamService::getTeams not null");
        assertFalse(result.isEmpty(), "TeamService::getTeams isEmpty");
        assertEquals(teams, result, "TeamService::getTeams");
    }

}
