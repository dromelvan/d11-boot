package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Team;
import org.d11.boot.spring.repository.TeamRepository;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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
     * Tests TeamService::getById.
     */
    @Test
    void testGetById() {
        final List<Team> teams = generateList(Team.class);
        when(this.teamRepository.findById(anyLong())).thenReturn(Optional.empty());

        for (final Team team : teams) {
            when(this.teamRepository.findById(team.getId())).thenReturn(Optional.of(team));

            final Team result = this.teamService.getById(team.getId());
            assertNotNull(result);
            assertEquals(team, result);
        }

        assertThrows(NotFoundException.class, () -> this.teamService.getById(-1L));
    }

    /**
     * Tests TeamService::getTeams.
     */
    @Test
    void testGetTeams() {
        final List<Team> teams = generateList(Team.class);
        when(this.teamRepository.findByOrderByName()).thenReturn(teams);

        final List<Team> result = this.teamService.getTeams();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(teams, result);
    }

}
