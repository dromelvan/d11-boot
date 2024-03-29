package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.MatchWeekApi;
import org.d11.boot.api.v2.model.MatchWeekDTO;
import org.d11.boot.api.v2.model.MatchWeekResponseBodyDTO;
import org.d11.boot.api.v2.model.MatchWeeksResponseBodyDTO;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match week controller tests.
 */
class MatchWeekControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Match week repository.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Tests MatchWeekController::getMatchWeekById.
     */
    @Test
    void testGetMatchWeekById() {
        final MatchWeekApi matchWeekApi = getApi(MatchWeekApi.class);

        assertThrows(FeignException.NotFound.class, () -> matchWeekApi.getMatchWeekById(0L),
                "MatchWeekController::getMatchWeekById not found");

        final List<MatchWeek> matchWeeks = this.matchWeekRepository.findAll();

        assertFalse(matchWeeks.isEmpty(), "MatchWeekController::getMatchWeekById matchWeeks not empty");

        for (final MatchWeek matchWeek : matchWeeks) {
            final MatchWeekResponseBodyDTO result = matchWeekApi.getMatchWeekById(matchWeek.getId());
            assertNotNull(result, "MatchWeekController::getMatchWeekById not null");
            assertEquals(getMapper().map(matchWeek, MatchWeekDTO.class),
                         result.getMatchWeek(),
                         "MatchWeekController::getMatchWeekById equals");
        }
    }

    /**
     * Tests MatchWeekController::getCurrentMatchWeek.
     */
    @Test
    void testGetCurrentMatchWeek() {
        final MatchWeekApi matchWeekApi = getApi(MatchWeekApi.class);

        final Optional<MatchWeek> optional =
                this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(LocalDate.now());

        assertFalse(optional.isEmpty(), "MatchWeekController::getCurrentMatchWeek matchWeek present");

        optional.ifPresent(matchWeek -> {
            final MatchWeekResponseBodyDTO result = matchWeekApi.getCurrentMatchWeek();
            assertNotNull(result, "MatchWeekController::getCurrentMatchWeek not null");
            assertEquals(getMapper().map(matchWeek, MatchWeekDTO.class),
                         result.getMatchWeek(),
                         "MatchWeekController::getCurrentMatchWeek equals");
        });
    }

    /**
     * Tests MatchWeekController::getMatchWeeksBySeasonId.
     */
    @Test
    void testGetMatchWeeksBySeasonId() {
        final MatchWeekApi matchWeekApi = getApi(MatchWeekApi.class);

        assertThrows(FeignException.BadRequest.class, () -> matchWeekApi.getMatchWeeksBySeasonId((Long) null),
                     "MatchWeekController::getMatchWeeksBySeasonId seasonId null throws");

        final List<MatchWeek> matchWeeks = this.matchWeekRepository.findAll();
        matchWeeks.sort(Comparator.comparing(MatchWeek::getDate));

        final Set<Season> seasons = matchWeeks.stream()
                .map(MatchWeek::getSeason)
                .collect(Collectors.toSet());

        assertTrue(seasons.size() > 1, "MatchWeekController::getMatchWeeksBySeasonId seasons size > 0");

        for (final Season season : seasons) {
            final MatchWeeksResponseBodyDTO matchWeeksResponseBodyDTO =
                    matchWeekApi.getMatchWeeksBySeasonId(season.getId());
            assertNotNull(matchWeeksResponseBodyDTO, "MatchWeekController::getMatchWeeksBySeasonId response not null");

            final List<MatchWeek> expected = matchWeeks.stream()
                    .filter(matchWeek -> matchWeek.getSeason().equals(season))
                    .toList();

            assertTrue(expected.size() > 1, "MatchWeekController::getMatchWeeksBySeasonId expected size > 1");

            final List<MatchWeekDTO> result = matchWeeksResponseBodyDTO.getMatchWeeks();

            assertNotNull(result, "MatchWeekController::getMatchWeeksBySeasonId not null ");
            assertFalse(result.isEmpty(), "MatchWeekController::getMatchWeeksBySeasonId empty");
            assertEquals(map(expected, MatchWeekDTO.class), result,
                         "MatchWeekController::getMatchWeeksBySeasonId equals");
        }
    }

}
