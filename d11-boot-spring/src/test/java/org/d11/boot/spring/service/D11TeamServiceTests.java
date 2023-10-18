package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.repository.D11TeamRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
     * Tests D11TeamService::getD11Teams.
     */
    @Test
    void testGetD11Teams() {
        final List<D11Team> d11Teams = generateList(D11Team.class);
        when(this.d11TeamRepository.findByOrderByName()).thenReturn(d11Teams);

        final List<D11Team> result = this.d11TeamService.getD11Teams();

        assertNotNull(result, "D11TeamService::getD11Teams not null");
        assertFalse(result.isEmpty(), "D11TeamService::getD11Teams isEmpty");
        assertEquals(d11Teams, result, "D11TeamService::getD11Teams");
    }

}
