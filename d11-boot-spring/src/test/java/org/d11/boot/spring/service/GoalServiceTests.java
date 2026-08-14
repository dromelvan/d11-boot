package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Goal;
import org.d11.boot.spring.repository.GoalRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Goal service tests.
 */
class GoalServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked goal repository.
     */
    @Mock
    private GoalRepository goalRepository;

    /**
     * Goal service.
     */
    @InjectMocks
    private GoalService goalService;

    /**
     * Tests GoalService::getByD11MatchIdAndD11TeamId.
     */
    @Test
    void testGetByD11MatchIdAndD11TeamId() {

        // Validation --------------------------------------------------------------------------------------------------

        final String d11MatchIdProperty = "d11MatchId";

        assertThrows(BadRequestException.class,
                     () -> this.goalService.getByD11MatchIdAndD11TeamId(null, 1L));
        assertEquals(d11MatchIdProperty,
                     assertThrows(BadRequestException.class,
                                  () -> this.goalService.getByD11MatchIdAndD11TeamId(null, 1L)).getParameter());

        assertEquals(d11MatchIdProperty,
                     assertThrows(BadRequestException.class,
                                  () -> this.goalService.getByD11MatchIdAndD11TeamId(-1L, 1L)).getParameter());

        final String d11TeamIdProperty = "d11TeamId";

        assertEquals(d11TeamIdProperty,
                     assertThrows(BadRequestException.class,
                                  () -> this.goalService.getByD11MatchIdAndD11TeamId(1L, null)).getParameter());

        assertEquals(d11TeamIdProperty,
                     assertThrows(BadRequestException.class,
                                  () -> this.goalService.getByD11MatchIdAndD11TeamId(1L, -1L)).getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final long d11MatchId = 1L;
        final long d11TeamId = 1L;
        final List<Goal> goals = generateList(Goal.class);

        when(this.goalRepository.findByD11MatchIdAndD11TeamId(eq(d11MatchId), eq(d11TeamId)))
                .thenReturn(goals);

        final List<Goal> result = this.goalService.getByD11MatchIdAndD11TeamId(d11MatchId, d11TeamId);

        assertEquals(goals, result);

        verify(this.goalRepository, times(1)).findByD11MatchIdAndD11TeamId(eq(d11MatchId), eq(d11TeamId));
    }

}
