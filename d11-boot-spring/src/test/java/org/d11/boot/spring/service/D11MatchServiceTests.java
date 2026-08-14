package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.Goal;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.D11MatchRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * D11 match service tests.
 */
class D11MatchServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked D11 match repository.
     */
    @Mock
    private D11MatchRepository d11MatchRepository;

    /**
     * Mocked match week service.
     */
    @Mock
    private MatchWeekService matchWeekService;

    /**
     * Mocked goal service.
     */
    @Mock
    private GoalService goalService;

    /**
     * D11 match service.
     */
    @InjectMocks
    private D11MatchService d11MatchService;

    /**
     * Tests D11MatchService::getById.
     */
    @Test
    void testGetById() {
        final D11Match d11Match = generate(D11Match.class);
        final List<Goal> homeTeamGoals = generateList(Goal.class);
        final List<Goal> awayTeamGoals = generateList(Goal.class);

        when(this.d11MatchRepository.findById(eq(d11Match.getId()))).thenReturn(Optional.of(d11Match));
        when(this.goalService.getByD11MatchIdAndD11TeamId(eq(d11Match.getId()),
                                                          eq(d11Match.getHomeD11Team().getId())))
                .thenReturn(homeTeamGoals);
        when(this.goalService.getByD11MatchIdAndD11TeamId(eq(d11Match.getId()),
                                                          eq(d11Match.getAwayD11Team().getId())))
                .thenReturn(awayTeamGoals);

        final D11Match result = this.d11MatchService.getById(d11Match.getId());

        assertNotNull(result);
        assertEquals(d11Match, result);
        assertEquals(homeTeamGoals, result.getHomeTeamGoals());
        assertEquals(awayTeamGoals, result.getAwayTeamGoals());

        verify(this.goalService).getByD11MatchIdAndD11TeamId(eq(d11Match.getId()),
                                                             eq(d11Match.getHomeD11Team().getId()));
        verify(this.goalService).getByD11MatchIdAndD11TeamId(eq(d11Match.getId()),
                                                             eq(d11Match.getAwayD11Team().getId()));
    }

    /**
     * Tests D11MatchService::getByD11TeamIdAmdSeasonId.
     */
    @Test
    void testGetByD11TeamIdAmdSeasonId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String d11TeamIdProperty = "d11TeamId";

        final BadRequestException nullD11TeamIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(null, 1L));
        assertEquals(d11TeamIdProperty, nullD11TeamIdException.getParameter());

        final BadRequestException invalidD11TeamIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(-1L, 1L));
        assertEquals(d11TeamIdProperty, invalidD11TeamIdException.getParameter());

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(1L, null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(1L, -1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final List<D11Match> d11Matches = generateList(D11Match.class);
        when(this.d11MatchRepository
                     .findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(any(Long.class), any(Long.class)))
                .thenReturn(d11Matches);

        final List<D11Match> result = this.d11MatchService.getByD11TeamIdAndSeasonId(1L, 1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d11Matches, result);
    }

    /**
     * Tests D11MatchService::getByMatchWeekId.
     */
    @Test
    void testGetByMatchWeekId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String matchWeekIdProperty = "matchWeekId";

        final BadRequestException nullMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByMatchWeekId(null));
        assertEquals(matchWeekIdProperty, nullMatchWeekIdException.getParameter());

        final BadRequestException invalidMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByMatchWeekId(-1L));
        assertEquals(matchWeekIdProperty, invalidMatchWeekIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final List<D11Match> d11Matches = generateList(D11Match.class);

        when(this.d11MatchRepository.findByMatchWeekIdOrderByDatetimeAscIdAsc(any(Long.class)))
                .thenReturn(d11Matches);

        final List<D11Match> result = this.d11MatchService.getByMatchWeekId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d11Matches, result);
    }

    /**
     * Tests D11MatchService::getCurrentD11Matches.
     */
    @Test
    void testGetCurrentD11Matches() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        final List<D11Match> d11Matches = generateList(D11Match.class);
        final Set<Status> currentStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);

        when(this.matchWeekService.getCurrentMatchWeek()).thenReturn(matchWeek);
        when(this.d11MatchRepository.findByMatchWeekIdOrStatusInOrderByDatetime(eq(matchWeek.getId()),
                                                                                eq(currentStatuses)))
                .thenReturn(d11Matches);

        final List<D11Match> result = this.d11MatchService.getCurrentD11Matches();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d11Matches, result);
    }

    /**
     * Tests D11MatchService::getActiveD11Matches.
     */
    @Test
    void testGetActiveD11Matches() {
        final List<D11Match> d11Matches = generateList(D11Match.class);
        final Set<Status> activeStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);

        when(this.d11MatchRepository.findByStatusInOrderByDatetime(eq(activeStatuses))).thenReturn(d11Matches);

        final List<D11Match> result = this.d11MatchService.getActiveD11Matches();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d11Matches, result);
    }

}
