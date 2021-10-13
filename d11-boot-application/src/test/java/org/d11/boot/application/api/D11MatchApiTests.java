package org.d11.boot.application.api;

import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.api.model.D11MatchesByDateDTO;
import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.service.D11MatchApiService;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.repository.D11MatchRepository;
import org.d11.boot.application.service.api.MatchWeekService;
import org.d11.boot.application.util.D11MatchesByDateMapperConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 match API tests.
 */
public class D11MatchApiTests extends AbstractRepositoryApiTests<D11Match, D11MatchRepository, D11MatchApiService> {

    /**
     * Used to get current match week for findCurrentMatches test.
     */
    @Autowired
    private MatchWeekService matchWeekService;

    /**
     * Tests the findD11MatchById API operation.
     */
    @Test
    public void findD11MatchById() {
        for(final D11Match d11Match : getEntities()) {
            final D11MatchDTO result = getApiService().findD11MatchById(d11Match.getId());
            final D11MatchDTO d11MatchDTO = map(d11Match, D11MatchDTO.class);

            assertNotNull(result, "D11 Match by id should not be null.");
            assertEquals(d11MatchDTO, result, "D11 Match by id should equal D11 Match.");
        }

        assertNull(getApiService().findD11MatchById(-1L), "D11 match not found should return null.");
        assertBadRequest(get("d11-matches", "BAD_REQUEST"));
    }

    /**
     * Tests the findD11MatchByD11TeamIdAndSeasonId API operation.
     */
    @Test
    public void findD11MatchByD11TeamIdAndSeasonId() {
        final Map<D11Team, Map<Season, List<D11Match>>> d11MatchMap = new HashMap<>();
        for(final D11Match d11Match : getEntities()) {
            Map<Season, List<D11Match>> seasonMap = d11MatchMap.computeIfAbsent(d11Match.getHomeD11Team(), t -> new HashMap<>());
            List<D11Match> d11Matches = seasonMap.computeIfAbsent(d11Match.getMatchWeek().getSeason(), s -> new ArrayList<>());
            d11Matches.add(d11Match);
            seasonMap = d11MatchMap.computeIfAbsent(d11Match.getAwayD11Team(), t -> new HashMap<>());
            d11Matches = seasonMap.computeIfAbsent(d11Match.getMatchWeek().getSeason(), s -> new ArrayList<>());
            d11Matches.add(d11Match);
        }

        for(final Map.Entry<D11Team, Map<Season, List<D11Match>>> d11TeamEntry : d11MatchMap.entrySet()) {
            final D11Team d11Team = d11TeamEntry.getKey();
            for(final Map.Entry<Season, List<D11Match>> seasonEntry : d11TeamEntry.getValue().entrySet()) {
                final Season season = seasonEntry.getKey();
                final List<Long> d11MatchIds = seasonEntry.getValue().stream()
                        .sorted(Comparator.comparing(D11Match::getDatetime))
                        .map(D11Match::getId)
                        .collect(Collectors.toList());

                final List<Long> result = getApiService().findD11MatchByD11TeamIdAndSeasonId(d11Team.getId(), season.getId());

                assertNotNull(result, "Result should not be null.");
                assertEquals(d11MatchIds.size(), result.size(),
                        "D11 matches by D11 team id and season id should have the same size as D11 team D11 matches by season.");
                assertEquals(d11MatchIds, result,
                        "D11 matches by D11 team id and season id should equal D11 team D11 matches by season.");
            }
        }
    }

    /**
     * Test the findCurrentD11Matches API operation.
     */
    @Test
    public void findCurrentD11Matches() {
        final List<D11Match> d11Matches = new ArrayList<>();
        final MatchWeekDTO matchWeekDTO = this.matchWeekService.findCurrentMatchWeek();
        for(final D11Match d11Match : getEntities()) {
            if(d11Match.getMatchWeek().getId().equals(matchWeekDTO.getId())
                    || d11Match.getStatus().equals(Status.ACTIVE)
                    || d11Match.getStatus().equals(Status.FULL_TIME)) {
                d11Matches.add(d11Match);
            }
        }

        d11Matches.sort(Comparator.comparing(D11Match::getDatetime));
        final D11MatchesByDateDTO d11MatchesByDateDTO = new D11MatchesByDateDTO()
                .d11Matches(new D11MatchesByDateMapperConverter().convert(d11Matches));

        final D11MatchesByDateDTO result = getApiService().findCurrentD11Matches();
        assertEquals(d11MatchesByDateDTO, result, "Current D11 matches should equal result.");
    }

}
