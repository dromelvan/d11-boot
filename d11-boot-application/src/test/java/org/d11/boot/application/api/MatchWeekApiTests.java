package org.d11.boot.application.api;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.service.MatchWeekApiService;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.util.MatchesByDateMapperConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match week API tests.
 */
public class MatchWeekApiTests extends AbstractRepositoryApiTests<MatchWeek, MatchWeekRepository, MatchWeekApiService> {

    /**
     * Tests the findMatchWeekById API operation.
     */
    @Test
    public void findMatchWeekById() {
        // Need to do getRepository().findAll() here since mapping MatchWeek requires getMatches() and the entities
        // in getEntities() are detached at this point.
        for(final MatchWeek matchWeek : getRepository().findAll()) {
            final MatchWeekDTO result = getApiService().findMatchWeekById(matchWeek.getId());
            final MatchWeekDTO matchWeekDTO = map(matchWeek, MatchWeekDTO.class);
            assertNotNull(result, "Match week by id should not be null.");
            assertEquals(matchWeekDTO, result, "Match week by id should equal MatchWeek.");
            assertFalse(result.getMatches().isEmpty(), "Match week by id should have matches.");
            assertEquals(new MatchesByDateMapperConverter().convert(matchWeek.getMatches()), result.getMatches(),
                    "Match week by id matches should equal converted MatchWeek matches.");
        }

        assertNull(getApiService().findMatchWeekById(-1L), "Match week not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findCurrentMatchWeek API operation.
     */
    @Test
    public void findCurrentMatchWeek() {
        final MatchWeekDTO result = getApiService().findCurrentMatchWeek();

        boolean currentMatchWeekFound = false;

        for(final MatchWeek matchWeek : getRepository().findAll()) {
            if(matchWeek.getDate().equals(LocalDate.now())) {
                currentMatchWeekFound = true;
                final MatchWeekDTO matchWeekDTO = map(matchWeek, MatchWeekDTO.class);

                assertNotNull(result, "Current match week should not be null.");
                assertEquals(matchWeekDTO, result, "Current match week result should equal current match week.");
                assertFalse(result.getMatches().isEmpty(), "Current match week should have matches.");
                assertEquals(new MatchesByDateMapperConverter().convert(matchWeek.getMatches()), result.getMatches(),
                        "Current match week matches should equal converted MatchWeek matches.");
                break;
            }
        }

        assertTrue(currentMatchWeekFound, "The test did not complete correctly as no current match week was found.");
    }

    /**
     * Tests the findMatchWeekBySeasonId API operation.
     */
    @Test
    public void findMatchWeekBySeasonId() {
        final Map<Season, List<MatchWeek>> matchWeekMap = new HashMap<>();
        for(final MatchWeek matchWeek : getRepository().findAll()) {
            final List<MatchWeek> matchWeeks =
                    matchWeekMap.computeIfAbsent(matchWeek.getSeason(), season -> new ArrayList<>());
            matchWeeks.add(matchWeek);
        }

        for(final Map.Entry<Season, List<MatchWeek>> entry : matchWeekMap.entrySet()) {
            final List<MatchWeek> seasonMatchWeeks = entry.getValue();

            seasonMatchWeeks.sort(Comparator.comparingInt(MatchWeek::getMatchWeekNumber));

            final List<MatchWeekDTO> matchWeeks = getApiService().findMatchWeekBySeasonId(entry.getKey().getId());

            assertEquals(seasonMatchWeeks.size(), matchWeeks.size(),
                    "Season match week and match week sizes are not equal. " +
                    "This means the team season stat test data is not set up properly.");

            assertEquals(map(seasonMatchWeeks, MatchWeekDTO.class), matchWeeks,
                    "Season match week should equal match week.");
        }
    }

}
