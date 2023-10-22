package org.d11.boot.spring.service;

import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Match week service tests.
 */
class MatchWeekServiceTests extends BaseD11BootServiceTests {

    /**
     * Mocked match week repository.
     */
    @Mock
    private MatchWeekRepository matchWeekRepository;

    /**
     * Match week service.
     */
    @InjectMocks
    private MatchWeekService matchWeekService;

    /**
     * Tests MatchWeekService::getBySeasonId.
     */
    @Test
    void testGetBySeasonId() {
        final List<MatchWeek> matchWeeks = generateList(MatchWeek.class);
        when(this.matchWeekRepository.findBySeasonIdOrderByDate(any(Long.class))).thenReturn(matchWeeks);

        final List<MatchWeek> result = this.matchWeekService.getBySeasonId(1L);

        assertNotNull(result, "MatchWeekService::getBySeasonId not null");
        assertFalse(result.isEmpty(), "MatchWeekService::getBySeasonId isEmpty");
        assertEquals(matchWeeks, result, "MatchWeekService::getBySeasonId equals");
    }

}
