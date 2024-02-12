package org.d11.boot.spring.service;

import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.repository.D11MatchRepository;
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
 * D11 match service tests.
 */
class D11MatchServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked D11 match repository.
     */
    @Mock
    private D11MatchRepository d11MatchRepository;

    /**
     * D11 match service.
     */
    @InjectMocks
    private D11MatchService d11MatchService;

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

}
