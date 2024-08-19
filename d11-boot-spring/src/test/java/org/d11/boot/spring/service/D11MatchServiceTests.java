package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.D11MatchRepository;
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
     * D11 match service.
     */
    @InjectMocks
    private D11MatchService d11MatchService;

    /**
     * Tests D11MatchService::getByD11TeamIdAmdSeasonId.
     */
    @Test
    void testGetByD11TeamIdAmdSeasonId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String d11TeamIdProperty = "d11TeamId";

        final BadRequestException nullD11TeamIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(null, 1L),
                             "D11MatchService::getByD11TeamIdAmdSeasonId null d11TeamId throws");
        assertEquals(d11TeamIdProperty, nullD11TeamIdException.getParameter(),
                     "D11MatchService::getByD11TeamIdAmdSeasonId property equals null d11TeamId");

        final BadRequestException invalidD11TeamIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(-1L, 1L),
                             "D11MatchService::getByD11TeamIdAmdSeasonId invalid d11TeamId throws");
        assertEquals(d11TeamIdProperty, invalidD11TeamIdException.getParameter(),
                     "D11MatchService::getByD11TeamIdAmdSeasonId property equals invalid d11TeamId");

        final String seasonIdProperty = "seasonId";

        final BadRequestException nullSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(1L, null),
                             "D11MatchService::getByD11TeamIdAmdSeasonId null seasonId throws");
        assertEquals(seasonIdProperty, nullSeasonIdException.getParameter(),
                     "D11MatchService::getByD11TeamIdAmdSeasonId property equals null seasonId");

        final BadRequestException invalidSeasonIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByD11TeamIdAndSeasonId(1L, -1L),
                             "D11MatchService::getByD11TeamIdAmdSeasonId invalid seasonId throws");
        assertEquals(seasonIdProperty, invalidSeasonIdException.getParameter(),
                     "D11MatchService::getByD11TeamIdAmdSeasonId property equals invalid seasonId");

        // Success -----------------------------------------------------------------------------------------------------

        final List<D11Match> d11Matches = generateList(D11Match.class);
        when(this.d11MatchRepository
                     .findByD11TeamIdAndMatchWeekSeasonIdOrderByDatetime(any(Long.class), any(Long.class)))
                .thenReturn(d11Matches);

        final List<D11Match> result = this.d11MatchService.getByD11TeamIdAndSeasonId(1L, 1L);

        assertNotNull(result, "D11MatchService::getByD11TeamIdAmdSeasonId not null");
        assertFalse(result.isEmpty(), "D11MatchService::getByD11TeamIdAmdSeasonId isEmpty");
        assertEquals(d11Matches, result, "D11MatchService::getByD11TeamIdAmdSeasonId equals");
    }

    /**
     * Tests D11MatchService::getByMatchWeekId.
     */
    @Test
    void testGetByMatchWeekId() {
        // Validation --------------------------------------------------------------------------------------------------

        final String matchWeekIdProperty = "matchWeekId";

        final BadRequestException nullMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByMatchWeekId(null),
                        "D11MatchService::getByMatchWeekId null matchWeekId throws");
        assertEquals(matchWeekIdProperty, nullMatchWeekIdException.getParameter(),
                     "D11MatchService::getByMatchWeekId property equals null matchWeekId");

        final BadRequestException invalidMatchWeekIdException =
                assertThrows(BadRequestException.class, () -> this.d11MatchService.getByMatchWeekId(-1L),
                        "D11MatchService::getByMatchWeekId invalid matchWeekId throws");
        assertEquals(matchWeekIdProperty, invalidMatchWeekIdException.getParameter(),
                     "D11MatchService::getByMatchWeekId property equals invalid matchWeekId");

        // Success -----------------------------------------------------------------------------------------------------

        final List<D11Match> d11Matches = generateList(D11Match.class);

        when(this.d11MatchRepository.findByMatchWeekIdOrderByDatetimeAscIdAsc(any(Long.class)))
                .thenReturn(d11Matches);

        final List<D11Match> result = this.d11MatchService.getByMatchWeekId(1L);

        assertNotNull(result, "D11MatchService::getByMatchWeekId not null");
        assertFalse(result.isEmpty(), "D11MatchService::getByMatchWeekId isEmpty");
        assertEquals(d11Matches, result, "D11MatchService::getByMatchWeekId equals");
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

        assertNotNull(result, "D11MatchService::getCurrentD11Matches not null");
        assertFalse(result.isEmpty(), "D11MatchService::getCurrentD11Matches isEmpty");
        assertEquals(d11Matches, result, "D11MatchService::getCurrentD11Matches equals");
    }

}
