package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.D11TeamSeasonStatApi;
import org.d11.boot.api.v2.model.D11TeamSeasonStatDTO;
import org.d11.boot.api.v2.model.D11TeamSeasonStatsResponseBodyDTO;
import org.d11.boot.spring.model.D11TeamSeasonStat;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.D11TeamSeasonStatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team season stat controller tests.
 */
class D11TeamSeasonStatControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * D11 team season stat repository.
     */
    @Autowired
    private D11TeamSeasonStatRepository d11TeamSeasonStatRepository;

    /**
     * Tests D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId.
     */
    @Test
    void testGetD11TeamSeasonStatsBySeasonId() {
        final D11TeamSeasonStatApi d11TeamSeasonStatApi = getApi(D11TeamSeasonStatApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> d11TeamSeasonStatApi.getD11TeamSeasonStatsBySeasonId((Long) null),
                     "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId seasonId null throws");

        final List<D11TeamSeasonStat> d11TeamSeasonStats = this.d11TeamSeasonStatRepository.findAll();
        d11TeamSeasonStats.sort(Comparator.comparing(D11TeamSeasonStat::getRanking));

        final Set<Season> seasons = d11TeamSeasonStats.stream()
                .map(D11TeamSeasonStat::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1, "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId seasons size > 0");

        for (final Season season : seasons) {
            final D11TeamSeasonStatsResponseBodyDTO d11TeamSeasonStatsResponseBodyDTO =
                    d11TeamSeasonStatApi.getD11TeamSeasonStatsBySeasonId(season.getId());
            assertNotNull(d11TeamSeasonStatsResponseBodyDTO,
                          "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId response not null");

            final List<D11TeamSeasonStat> expected = d11TeamSeasonStats.stream()
                    .filter(d11TeamSeasonStat -> d11TeamSeasonStat.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1,
                       "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId expected size > 1");

            final List<D11TeamSeasonStatDTO> result = d11TeamSeasonStatsResponseBodyDTO.getD11TeamSeasonStats();

            assertNotNull(result, "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId not null ");
            assertFalse(result.isEmpty(), "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId empty");
            assertEquals(map(expected, D11TeamSeasonStatDTO.class), result,
                         "D11TeamSeasonStatController::getD11TeamSeasonStatsBySeasonId equals");
        }
    }

}
