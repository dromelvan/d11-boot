package org.d11.boot.application.model;

import org.d11.boot.api.model.MatchWeekDTO;
import org.d11.boot.application.util.MatchesByDateMapperConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match week tests.
 */
public class MatchWeekTests extends D11EasyRandomTests {

    /**
     * Tests match week validity.
     */
    @Test
    public void isValid() {
        final MatchWeek matchWeek = generate(MatchWeek.class);

        assertTrue(matchWeek.isValid(), "New match week should be valid.");

        matchWeek.setMatchWeekNumber(0);
        assertFalse(matchWeek.isValid(), "Too low match week number should not be valid.");
        matchWeek.setMatchWeekNumber(MatchWeek.MAX_MATCH_WEEK_NUMBER + 1);
        assertFalse(matchWeek.isValid(), "Too high match week number should not be valid.");
        matchWeek.setMatchWeekNumber(1);

        matchWeek.setDate(null);
        assertFalse(matchWeek.isValid(), "Null date should not be valid.");
        matchWeek.setDate(LocalDate.now());

        matchWeek.setStatus(null);
        assertFalse(matchWeek.isValid(), "Null status should not be valid.");
        matchWeek.setStatus(Status.PENDING);

        matchWeek.setPremierLeague(null);
        assertFalse(matchWeek.isValid(), "Null Premier League should not be valid.");
        matchWeek.setPremierLeague(new PremierLeague());

        assertTrue(matchWeek.isValid(), "Match week should be valid.");
    }

    /**
     * Tests mapping between Matchweek and MatchweekDTO.
     */
    @Test
    public void map() {
        final MatchWeek matchWeek = generate(MatchWeek.class);
        matchWeek.setMatches(generate(Match.class, 2));

        final MatchWeekDTO matchWeekDTO = map(matchWeek, MatchWeekDTO.class);
        final MatchWeek mappedMatchWeek = map(matchWeekDTO, MatchWeek.class);

        assertEquals(matchWeek, mappedMatchWeek, "Match week should equal mapped match week.");

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(final Match match : matchWeek.getMatches()) {
            final String key = match.getDatetime().toLocalDate().format(dateTimeFormatter);
            assertTrue(matchWeekDTO.getMatches().containsKey(key),
                    "Match week DTO match map should have an entry for match date.");
            assertTrue(matchWeekDTO.getMatches().get(key).contains(match.getId()),
                    "Match week DTO match map entry for match date should contain match id.");
        }

        assertEquals(new MatchesByDateMapperConverter().convert(matchWeek.getMatches()), matchWeekDTO.getMatches(),
                     "Match week DTO matches should equal converted MatchWeek matches.");
    }

}
