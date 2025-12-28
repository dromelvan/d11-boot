package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchRepository;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Match service tests.
 */
class MatchServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked match repository.
     */
    @Mock
    private MatchRepository matchRepository;

    /**
     * Mocked match week service.
     */
    @Mock
    private MatchWeekService matchWeekService;

    /**
     * Match service.
     */
    @InjectMocks
    private MatchService matchService;

    /**
     * Tests MatchService::getByTeamIdAmdSeasonId.
     */
    @Test
    void testGetByTeamIdAmdSeasonId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String teamIdProperty = "teamId";

        final BadRequestException nullTeamIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByTeamIdAndSeasonId(null, 1L));
        assertEquals(teamIdProperty, nullTeamIdException.getParameter());

        final BadRequestException invalidTeamIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByTeamIdAndSeasonId(-1L, 1L));
        assertEquals(teamIdProperty, invalidTeamIdException.getParameter());

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByTeamIdAndSeasonId(1L, null));
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter());

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByTeamIdAndSeasonId(1L, -1L));
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final List<Match> matches = generateList(Match.class);
        when(this.matchRepository.findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(any(Long.class), any(Long.class)))
                .thenReturn(matches);

        final List<Match> result = this.matchService.getByTeamIdAndSeasonId(1L, 1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(matches, result);
    }

    /**
     * Tests MatchService::getByMatchWeekId.
     */
    @Test
    void testGetByMatchWeekId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String matchWeekIdProperty = "matchWeekId";

        final BadRequestException nullMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByMatchWeekId(null));
        assertEquals(matchWeekIdProperty, nullMatchWeekIdException.getParameter());

        final BadRequestException invalidMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByMatchWeekId(-1L));
        assertEquals(matchWeekIdProperty, invalidMatchWeekIdException.getParameter());

        // Success -----------------------------------------------------------------------------------------------------

        final List<Match> matches = generateList(Match.class);

        when(this.matchRepository.findByMatchWeekIdOrderByDatetimeAscIdAsc(any(Long.class)))
                .thenReturn(matches);

        final List<Match> result = this.matchService.getByMatchWeekId(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(matches, result);
    }

    /**
     * Tests MatchService::getCurrentMatches.
     */
    @Test
    void testGetCurrentMatches() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        final List<Match> matches = generateList(Match.class);
        final Set<Status> currentStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);

        when(this.matchWeekService.getCurrentMatchWeek()).thenReturn(matchWeek);
        when(this.matchRepository.findByMatchWeekIdOrStatusInOrderByDatetime(eq(matchWeek.getId()),
                                                                             eq(currentStatuses)))
                .thenReturn(matches);

        final List<Match> result = this.matchService.getCurrentMatches();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(matches, result);
    }

}
