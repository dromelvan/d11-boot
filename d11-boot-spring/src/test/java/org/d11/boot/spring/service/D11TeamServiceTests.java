package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.repository.D11TeamRepository;
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
 * D11 team service tests.
 */
class D11TeamServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked D11 team repository.
     */
    @Mock
    private D11TeamRepository d11TeamRepository;

    /**
     * D11 team service.
     */
    @InjectMocks
    private D11TeamService d11TeamService;

    /**
     * Tests D11TeamService::getById.
     */
    @Test
    void testGetById() {
        final List<D11Team> d11Teams = generateList(D11Team.class);
        when(this.d11TeamRepository.findById(anyLong())).thenReturn(Optional.empty());

        for (final D11Team d11Team : d11Teams) {
            when(this.d11TeamRepository.findById(d11Team.getId())).thenReturn(Optional.of(d11Team));

            final D11Team result = this.d11TeamService.getById(d11Team.getId());
            assertNotNull(result);
            assertEquals(d11Team, result);
        }

        assertThrows(NotFoundException.class, () -> this.d11TeamService.getById(-1L));
    }

    /**
     * Tests D11TeamService::getD11Teams.
     */
    @Test
    void testGetD11Teams() {
        final List<D11Team> d11Teams = generateList(D11Team.class);
        when(this.d11TeamRepository.findByOrderByName()).thenReturn(d11Teams);

        final List<D11Team> result = this.d11TeamService.getD11Teams();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d11Teams, result);
    }

}
