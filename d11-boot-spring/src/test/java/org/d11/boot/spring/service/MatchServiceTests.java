package org.d11.boot.spring.service;

import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.repository.MatchRepository;
import org.d11.boot.util.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
     * Match service.
     */
    @InjectMocks
    private MatchService matchService;

    /**
     * Tests MatchService::getByTeamIdAmdSeasonId.
     */
    @Test
    void testGetByTeamIdAmdSeasonId() {
        final List<Match> matches = generateList(Match.class);
        when(this.matchRepository.findByTeamIdAndMatchWeekSeasonIdOrderByDatetime(any(Long.class), any(Long.class)))
                .thenReturn(matches);

        final List<Match> result = this.matchService.getByTeamIdAndSeasonId(1L, 1L);

        assertNotNull(result, "MatchService::getByTeamIdAmdSeasonId not null");
        assertFalse(result.isEmpty(), "MatchService::getByTeamIdAmdSeasonId isEmpty");
        assertEquals(matches, result, "MatchService::getByTeamIdAmdSeasonId equals");
    }

    /**
     * Tests MatchService::getByMatchWeekId.
     */
    @Test
    void testGetByMatchWeekId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String matchWeekIdProperty = "matchWeekId";

        final BadRequestException nullMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByMatchWeekId(null),
                        "MatchService::getByMatchWeekId null matchWeekId throws");
        assertEquals(matchWeekIdProperty, nullMatchWeekIdException.getParameter(),
                "MatchService::getByMatchWeekId property equals null matchWeekId");

        final BadRequestException invalidMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.matchService.getByMatchWeekId(-1L),
                        "MatchService::getByMatchWeekId invalid matchWeekId throws");
        assertEquals(matchWeekIdProperty, invalidMatchWeekIdException.getParameter(),
                "MatchService::getByMatchWeekId property equals invalid matchWeekId");

        // Success -----------------------------------------------------------------------------------------------------

        final List<Match> matches = generateList(Match.class);

        when(this.matchRepository.findByMatchWeekIdOrderByDatetimeAscIdAsc(any(Long.class)))
                .thenReturn(matches);

        final List<Match> result = this.matchService.getByMatchWeekId(1L);

        assertNotNull(result, "MatchService::getByMatchWeekId not null");
        assertFalse(result.isEmpty(), "MatchService::getByMatchWeekId isEmpty");
        assertEquals(matches, result, "MatchService::getByMatchWeekId equals");
    }

}
