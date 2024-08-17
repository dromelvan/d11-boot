package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.MatchApi;
import org.d11.boot.api.v2.model.MatchBaseDTO;
import org.d11.boot.api.v2.model.MatchDTO;
import org.d11.boot.api.v2.model.MatchResponseBodyDTO;
import org.d11.boot.api.v2.model.MatchesResponseBodyDTO;
import org.d11.boot.api.v2.model.StadiumDTO;
import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.model.Match;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.repository.MatchRepository;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match controller tests.
 */
class MatchControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Match repository.
     */
    @Autowired
    private MatchRepository matchRepository;

    /**
     * Match week repository.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Tests MatchController::getMatchById.
     */
    @Test
    void testGetMatchById() {
        final MatchApi matchApi = getApi(MatchApi.class);
        final List<Match> matches = this.matchRepository.findAll();

        assertFalse(matches.isEmpty(), "MatchController::getMatchById empty");

        for (final Long matchId : matches.stream().map(D11Entity::getId).toList()) {
            final MatchResponseBodyDTO result = matchApi.getMatchById(matchId);
            assertNotNull(result, "MatchController::getMatchById not null");

            // Have to do findById to get the associations needed for the mapping
            final Match match = this.matchRepository.findById(matchId).orElseThrow(IllegalStateException::new);

            assertEquals(getMapper().map(match, MatchDTO.class),
                         result.getMatch(),
                         "MatchController::getMatchById equals");
            assertEquals(getMapper().map(match.getStadium(), StadiumDTO.class),
                         result.getStadium(),
                         "MatchController::getMatchById stadium equals");
        }

        assertThrows(FeignException.NotFound.class, () -> matchApi.getMatchById(0L),
                     "MatchController::getMatchById not found");
    }

    /**
     * Tests MatchController::getMatchesByMatchWeekId.
     */
    @Test
    @Transactional
    void testGetMatchesByMatchWeekId() {
        final MatchApi matchApi = getApi(MatchApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> matchApi.getMatchesByMatchWeekId((Long) null),
                     "MatchController::getMatchesByMatchWeekId matchWeekId null throws");

        assertThrows(FeignException.BadRequest.class,
                    () -> matchApi.getMatchesByMatchWeekId(-1L),
                    "MatchController::getMatchesByMatchWeekId matchWeekId negative throws");

        final List<MatchWeek> matchWeeks = this.matchWeekRepository.findAll().stream()
                .filter(matchWeek -> !matchWeek.getMatches().isEmpty())
                .toList();

        assertTrue(matchWeeks.size() > 1, "MatchController::getMatchesByMatchWeekId matchWeeks size > 0");

        for (final MatchWeek matchWeek : matchWeeks) {
            final MatchesResponseBodyDTO matchesByMatchWeekId = matchApi.getMatchesByMatchWeekId(matchWeek.getId());
            assertNotNull(matchesByMatchWeekId, "MatchController::getMatchesByMatchWeekId response not null");

            final List<MatchBaseDTO> result = matchesByMatchWeekId.getMatches();

            assertNotNull(result, "MatchController::getMatchesByMatchWeekId not null ");
            assertFalse(result.isEmpty(), "MatchController::getMatchesByMatchWeekId empty");

            final List<Match> matches =
                    this.matchRepository.findByMatchWeekIdOrderByDatetimeAscIdAsc(matchWeek.getId());

            assertEquals(map(matches, MatchBaseDTO.class), result, "MatchController::getMatchesByMatchWeekId equals");
        }
    }

    /**
     * Tests MatchController::getCurrentMatches.
     */
    @Test
    void testGetCurrentMatches() {
        final MatchApi matchApi = getApi(MatchApi.class);

        final LocalDate localDate = LocalDate.now();
        final MatchWeek currentMatchWeek =
                this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(Status.PENDING)
                        .or(() -> this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(localDate))
                        .orElseThrow(RuntimeException::new);
        final Set<Status> currentStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);

        final List<Match> expected =
                this.matchRepository.findByMatchWeekIdOrStatusInOrderByDatetime(currentMatchWeek.getId(),
                                                                                currentStatuses);

        final MatchesResponseBodyDTO response = matchApi.getCurrentMatches();

        final List<MatchBaseDTO> result = response.getMatches();

        assertNotNull(result, "MatchController::getCurrentMatches not null ");
        assertFalse(result.isEmpty(), "MatchController::getCurrentMatches empty");

        assertEquals(map(expected, MatchBaseDTO.class), result, "MatchController::getCurrentMatches equals");
    }

}
