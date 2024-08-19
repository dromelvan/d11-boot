package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import jakarta.transaction.Transactional;
import org.d11.boot.api.v2.client.D11MatchApi;
import org.d11.boot.api.v2.model.D11MatchBaseDTO;
import org.d11.boot.api.v2.model.D11MatchDTO;
import org.d11.boot.api.v2.model.D11MatchResponseBodyDTO;
import org.d11.boot.api.v2.model.D11MatchesResponseBodyDTO;
import org.d11.boot.spring.model.D11Entity;
import org.d11.boot.spring.model.D11Match;
import org.d11.boot.spring.model.D11Team;
import org.d11.boot.spring.model.MatchWeek;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.D11MatchRepository;
import org.d11.boot.spring.repository.MatchWeekRepository;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 match controller tests.
 */
class D11MatchControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * D11 match repository.
     */
    @Autowired
    private D11MatchRepository d11MatchRepository;

    /**
     * Match week repository.
     */
    @Autowired
    private MatchWeekRepository matchWeekRepository;

    /**
     * Tests D11MatchController::getD11MatchById.
     */
    @Test
    void testGetD11MatchById() {
        final D11MatchApi d11MatchApi = getApi(D11MatchApi.class);
        final List<D11Match> d11Matches = this.d11MatchRepository.findAll();

        assertFalse(d11Matches.isEmpty(), "D11MatchController::getD11MatchById empty");

        for (final Long d11MatchId : d11Matches.stream().map(D11Entity::getId).toList()) {
            final D11MatchResponseBodyDTO result = d11MatchApi.getD11MatchById(d11MatchId);
            assertNotNull(result, "D11MatchController::getD11MatchById not null");

            // Have to do findById to get the associations needed for the mapping
            final D11Match d11Match = this.d11MatchRepository
                    .findById(d11MatchId).orElseThrow(IllegalStateException::new);

            assertEquals(getMapper().map(d11Match, D11MatchDTO.class),
                         result.getD11Match(),
                         "D11MatchController::getD11MatchById equals");
        }

        assertThrows(FeignException.NotFound.class, () -> d11MatchApi.getD11MatchById(0L),
                     "D11MatchController::getD11MatchById not found");
    }

    /**
     * Tests D11MatchController::getD11MatchesByMatchWeekId.
     */
    @Test
    @Transactional
    void testGetD11MatchesByMatchWeekId() {
        final D11MatchApi d11MatchApi = getApi(D11MatchApi.class);

        assertThrows(FeignException.BadRequest.class,
                () -> d11MatchApi.getD11MatchesByMatchWeekId((Long) null),
                "D11MatchController::getD11MatchesByMatchWeekId matchWeekId null throws");

        assertThrows(FeignException.BadRequest.class,
                () -> d11MatchApi.getD11MatchesByMatchWeekId(-1L),
                "D11MatchController::getD11MatchesByMatchWeekId matchWeekId negative throws");

        final List<MatchWeek> matchWeeks = this.matchWeekRepository.findAll().stream()
                .filter(matchWeek -> !matchWeek.getMatches().isEmpty())
                .toList();

        assertTrue(matchWeeks.size() > 1, "D11MatchController::getD11MatchesByMatchWeekId matchWeeks size > 0");

        for (final MatchWeek matchWeek : matchWeeks) {
            final D11MatchesResponseBodyDTO d11MatchesByMatchWeekId =
                    d11MatchApi.getD11MatchesByMatchWeekId(matchWeek.getId());
            assertNotNull(d11MatchesByMatchWeekId, "D11MatchController::getD11MatchesByMatchWeekId response not null");

            final List<D11MatchBaseDTO> result = d11MatchesByMatchWeekId.getD11Matches();

            assertNotNull(result, "D11MatchController::getD11MatchesByMatchWeekId not null ");
            assertFalse(result.isEmpty(), "D11MatchController::getD11MatchesByMatchWeekId empty");

            final List<D11Match> d11Matches =
                    this.d11MatchRepository.findByMatchWeekIdOrderByDatetimeAscIdAsc(matchWeek.getId());

            assertEquals(map(d11Matches, D11MatchBaseDTO.class), result,
                         "D11MatchController::getD11MatchesByMatchWeekId equals");
        }
    }

    /**
     * Tests D11MatchController::getD11MatchesByD11TeamIdAndSeasonId.
     */
    @Test
    void testGetMatchesByTeamIdAndSeasonId() {
        final D11MatchApi d11MatchApi = getApi(D11MatchApi.class);

        assertThrows(FeignException.BadRequest.class,
                     () -> d11MatchApi.getD11MatchesByD11TeamIdAndSeasonId(null, 1L),
                     "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId d11TeamId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> d11MatchApi.getD11MatchesByD11TeamIdAndSeasonId(-1L, 1L),
                     "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId d11TeamId negative throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> d11MatchApi.getD11MatchesByD11TeamIdAndSeasonId(1L, (Long) null),
                     "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId seasonId null throws");

        assertThrows(FeignException.BadRequest.class,
                     () -> d11MatchApi.getD11MatchesByD11TeamIdAndSeasonId(1L, -1L),
                     "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId seasonId negative throws");

        final List<D11Match> d11Matches = this.d11MatchRepository.findAll();
        final Set<D11Team> d11Teams = new HashSet<>();
        final Set<Season> seasons = new HashSet<>();
        for (final D11Match d11Match : d11Matches) {
            d11Teams.add(d11Match.getHomeD11Team());
            d11Teams.add(d11Match.getAwayD11Team());
            seasons.add(d11Match.getMatchWeek().getSeason());
        }

        assertTrue(d11Teams.size() > 1, "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId D11 teams size > 0");
        assertTrue(seasons.size() > 1, "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId seasons size > 0");

        for (final D11Team d11Team: d11Teams) {
            for (final Season season : seasons) {
                final D11MatchesResponseBodyDTO response =
                        d11MatchApi.getD11MatchesByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());

                assertNotNull(response, "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId not null ");
                assertFalse(response.getD11Matches().isEmpty(),
                            "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId empty");

                final List<D11Match> expected = d11Matches.stream()
                        .filter(d11Match -> (d11Match.getHomeD11Team() == d11Team
                                             || d11Match.getAwayD11Team() == d11Team)
                                         && d11Match.getMatchWeek().getSeason() == season)
                        .sorted(Comparator.comparing(D11Match::getDatetime))
                        .toList();

                assertEquals(map(expected, D11MatchBaseDTO.class), response.getD11Matches(),
                             "D11MatchController::getD11MatchesByD11TeamIdAndSeasonId equals");
            }
        }
    }

    /**
     * Tests MatchController::getCurrentD11Matches.
     */
    @Test
    void testGetCurrentD11Matches() {
        final D11MatchApi d11MatchApi = getApi(D11MatchApi.class);

        final LocalDate localDate = LocalDate.now();
        final MatchWeek currentMatchWeek =
                this.matchWeekRepository.findFirstBySeasonStatusOrderByDateAsc(Status.PENDING)
                        .or(() -> this.matchWeekRepository.findFirstByDateLessThanEqualOrderByDateDesc(localDate))
                        .orElseThrow(RuntimeException::new);
        final Set<Status> currentStatuses = Set.of(Status.ACTIVE, Status.FULL_TIME);

        final List<D11Match> expected =
                this.d11MatchRepository.findByMatchWeekIdOrStatusInOrderByDatetime(currentMatchWeek.getId(),
                                                                                   currentStatuses);

        final D11MatchesResponseBodyDTO response = d11MatchApi.getCurrentD11Matches();

        final List<D11MatchBaseDTO> result = response.getD11Matches();

        assertNotNull(result, "D11MatchController::getCurrentD11Matches not null ");
        assertFalse(result.isEmpty(), "D11MatchController::getCurrentD11Matches empty");

        assertEquals(map(expected, D11MatchBaseDTO.class), result, "D11MatchController::getCurrentD11Matches equals");
    }

}
