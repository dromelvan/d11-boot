package org.d11.boot.application.api;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.api.service.MatchWeekApiService;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.model.MatchWeek;
import org.d11.boot.application.model.PremierLeague;
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
     * Tests the findMatchWeekByPremierLeagueId API operation.
     */
    @Test
    public void findMatchWeekByPremierLeagueId() {
        final Map<PremierLeague, List<MatchWeek>> map = new HashMap<>();
        for(final MatchWeek matchWeek : getRepository().findAll()) {
            final List<MatchWeek> matchWeeks = map.computeIfAbsent(matchWeek.getPremierLeague(), premierLeague -> new ArrayList<>());
            matchWeeks.add(matchWeek);
        }

        for(final Map.Entry<PremierLeague, List<MatchWeek>> entry : map.entrySet()) {
            final List<MatchWeekDTO> matchWeekDTOs = map(entry.getValue(), MatchWeekDTO.class);
            matchWeekDTOs.sort(Comparator.comparing(MatchWeekDTO::getDate));

            final List<MatchWeekDTO> result = getApiService().findMatchWeekByPremierLeagueId(entry.getKey().getId());

            assertNotNull(result, "Premier League match weeks should not be null.");
            assertFalse(result.isEmpty(), "Premier League match weeks should not be empty.");
            assertEquals(matchWeekDTOs, result, "Premier League match weeks should equal match weeks.");
        }
    }

    /**
     * Tests match week match order.
     */
    @Test
    public void matchOrder() {
        for(final MatchWeek matchWeek : getRepository().findAll()) {
            assertFalse(matchWeek.getMatches().isEmpty(), "Match week matches should not be empty.");

            final List<Match> matches = new ArrayList<>(matchWeek.getMatches());
            matches.sort(Comparator.comparing(Match::getDatetime));

            assertEquals(matches, matchWeek.getMatches(), "Match order should be by datetime, ascending.");
        }
    }

}
