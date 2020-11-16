package org.d11.boot.application.util;

import org.d11.boot.application.model.D11EasyRandomTests;
import org.d11.boot.application.model.Match;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the MatchesByDateConverter.
 */
public class MatchesByDateConverterTests extends D11EasyRandomTests {

    /**
     * Tests the convert function.
     */
    @Test
    public void convert() {
        final List<Match> matches = generate(Match.class, 10);
        final LocalDateTime localDateTime = LocalDateTime.now()
                                                .truncatedTo(ChronoUnit.HOURS)
                                                .plus(1, ChronoUnit.DAYS);

        final List<String> dates = new ArrayList<>();
        final Map<String, List<Long>> matchIdByDate = new HashMap<>();

        for(final Match match : matches) {
            match.setDatetime(localDateTime.plus(matches.indexOf(match) / 2, ChronoUnit.DAYS));
            final String date = MatchesByDateConverter.DATE_TIME_FORMATTER.format(match.getDatetime());
            if(!dates.contains(date)) {
                dates.add(date);
            }
            matchIdByDate.computeIfAbsent(date, d -> new ArrayList<>()).add(match.getId());
        }

        final Map<String, List<Long>> result = new MatchesByDateConverter().convert(matches);
        int count = 0;
        for(final Entry<String, List<Long>> entry : result.entrySet()) {
            assertEquals(dates.get(count), entry.getKey(), "Match date should equal result map date at index " + count + ".");
            ++count;
            assertEquals(matchIdByDate.get(entry.getKey()), entry.getValue(), "Match ids by date should equal result map match ids by date.");
        }
    }

}
