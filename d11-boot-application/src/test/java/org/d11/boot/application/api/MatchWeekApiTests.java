package org.d11.boot.application.api;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.service.MatchWeekApiService;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.repository.MatchWeekRepository;
import org.d11.boot.application.util.MatchesByDateConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match week API tests.
 */
public class MatchWeekApiTests extends AbstractApiTests<MatchWeek, MatchWeekRepository, MatchWeekApiService> {

    /**
     * Sets up match weeks for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        final List<MatchWeek> matchWeeks = getRepository().findAll();
        // Start with yesterday and set the date of each match week one week forward from the previous.
        // This means the 'current' match week should be the second one.
        LocalDate localDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
        for(final MatchWeek matchWeek : matchWeeks) {
            matchWeek.setDate(localDate);
            localDate = localDate.plus(1, ChronoUnit.DAYS);
        }
        getEntities().addAll(getRepository().saveAll(matchWeeks));
        assertFalse(getEntities().isEmpty(), "Match weeks should not be empty.");
    }

    /**
     * Tests the findMatchWeekById API operation.
     */
    @Test
    public void findMatchWeekById() {
        for(final MatchWeek matchWeek : getEntities()) {
            final MatchWeekDTO result = getApiService().findMatchWeekById(matchWeek.getId());
            final MatchWeekDTO matchWeekDTO = map(matchWeek, MatchWeekDTO.class);
            assertNotNull(result, "Match week by id should not be null.");
            assertEquals(matchWeekDTO, result, "Match week by id should equal MatchWeek.");
            assertEquals(new MatchesByDateConverter().convert(matchWeek.getMatches()), result.getMatches(),
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

        for(final MatchWeek matchWeek : getEntities()) {
            if(matchWeek.getDate().equals(LocalDate.now())) {
                currentMatchWeekFound = true;
                final MatchWeekDTO matchWeekDTO = map(matchWeek, MatchWeekDTO.class);

                assertNotNull(result, "Current match week should not be null.");
                assertEquals(matchWeekDTO, result, "Current match week result should equal current match week.");
                assertEquals(new MatchesByDateConverter().convert(matchWeek.getMatches()), result.getMatches(),
                             "Current match week matches should equal converted MatchWeek matches.");
                break;
            }
        }

        assertTrue(currentMatchWeekFound, "The test did not complete correctly as no current match week was found.");
    }

    /**
     * Tests match week match order.
     */
    @Test
    public void matchOrder() {
        for(final MatchWeek matchWeek : getEntities()) {
            assertFalse(matchWeek.getMatches().isEmpty(), "Match week matches should not be empty.");

            final List<Match> matches = new ArrayList<>(matchWeek.getMatches());
            matches.sort(Comparator.comparing(Match::getDatetime));

            assertEquals(matches, matchWeek.getMatches(), "Match order should be by datetime, ascending.");
        }
    }

}
