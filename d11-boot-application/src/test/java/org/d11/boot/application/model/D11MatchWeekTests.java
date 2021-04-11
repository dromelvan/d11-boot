package org.d11.boot.application.model;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.application.util.D11MatchesByDateMapperConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 match week tests.
 */
public class D11MatchWeekTests extends D11EasyRandomTests {

    /**
     * Tests D11 match week validity.
     */
    @Test
    public void isValid() {
        final D11MatchWeek d11MatchWeek = generate(D11MatchWeek.class);

        assertTrue(d11MatchWeek.isValid(), "New D11 match week should be valid.");

        d11MatchWeek.setMatchWeekNumber(0);
        assertFalse(d11MatchWeek.isValid(), "Too low match week number should not be valid.");
        d11MatchWeek.setMatchWeekNumber(MatchWeek.MAX_MATCH_WEEK_NUMBER + 1);
        assertFalse(d11MatchWeek.isValid(), "Too high match week number should not be valid.");
        d11MatchWeek.setMatchWeekNumber(1);

        d11MatchWeek.setDate(null);
        assertFalse(d11MatchWeek.isValid(), "Null date should not be valid.");
        d11MatchWeek.setDate(LocalDate.now());

        d11MatchWeek.setD11League(null);
        assertFalse(d11MatchWeek.isValid(), "Null D11 league should not be valid.");
        d11MatchWeek.setD11League(new D11League());

        d11MatchWeek.setMatchWeek(null);
        assertFalse(d11MatchWeek.isValid(), "Null match week should not be valid.");
        d11MatchWeek.setMatchWeek(new MatchWeek());

        assertTrue(d11MatchWeek.isValid(), "D11 match week should be valid.");
    }

    /**
     * Tests mapping between D11MatchWeek and D11MatchWeekDTO.
     */
    @Test
    public void map() {
        final D11MatchWeek d11MatchWeek = generate(D11MatchWeek.class);

        final D11MatchWeekDTO d11MatchWeekDTO = map(d11MatchWeek, D11MatchWeekDTO.class);
        final D11MatchWeek mappedD11MatchWeek = map(d11MatchWeekDTO, D11MatchWeek.class);

        assertEquals(d11MatchWeek, mappedD11MatchWeek, "D11 match week should equal mapped D11 match week.");

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(final D11Match d11Match : d11MatchWeek.getD11Matches()) {
            final String key = d11Match.getDate().format(dateTimeFormatter);
            assertTrue(d11MatchWeekDTO.getD11Matches().containsKey(key),
                    "D11 match week DTO D11 match map should have an entry for D11 match date.");
            assertTrue(d11MatchWeekDTO.getD11Matches().get(key).contains(d11Match.getId()),
                    "D11 match week DTO D11 match map entry for match date should contain match id.");
        }

        assertEquals(new D11MatchesByDateMapperConverter().convert(d11MatchWeek.getD11Matches()), d11MatchWeekDTO.getD11Matches(),
                "D11 match week DTO D11 matches should equal converted D11MatchWeek matches.");
    }

    /**
     * Tests the getElapsed() method.
     */
    @Test
    public void elapsed() {
        final D11MatchWeek d11MatchWeek = generate(D11MatchWeek.class);
        d11MatchWeek.setD11Matches(generate(D11Match.class, DEFAULT_GENERATED_LIST_SIZE));

        int finishedD11Matches = 0;
        for(final D11Match d11Match : d11MatchWeek.getD11Matches()) {
            if(d11Match.getStatus() == Status.FINISHED) {
                ++finishedD11Matches;
            }
        }

        assertEquals(finishedD11Matches, d11MatchWeek.getElapsed(),
                "D11 match week elapsed should equal number of finished D11 matches.");
    }

    /**
     * Tests the getLeader() method.
     */
    @Test
    public void leader() {
        final D11MatchWeek d11MatchWeek = generate(D11MatchWeek.class);
        d11MatchWeek.setD11TeamTableStats(generate(D11TeamTableStat.class, DEFAULT_GENERATED_LIST_SIZE));

        assertEquals(d11MatchWeek.getLeader(), d11MatchWeek.getD11TeamTableStats().get(0).getD11Team(),
                "D11 match week leader should equal D11 team of the first D11  match week D11 team table stat.");
    }

}
