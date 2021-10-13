package org.d11.boot.application.util;

import org.d11.boot.application.model.D11EasyRandomTests;
import org.d11.boot.application.model.D11Match;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the D11MatchesByDateConverter.
 */
public class D11MatchesByDateMapperConverterTests extends D11EasyRandomTests {

    /**
     * Tests the convert function.
     */
    @Test
    public void convert() {
        final List<D11Match> d11Matches = generate(D11Match.class, 10);
        Collections.sort(d11Matches);

        final LocalDateTime localDateTime = LocalDateTime.now()
                                                .truncatedTo(ChronoUnit.HOURS)
                                                .plus(1, ChronoUnit.DAYS);

        final List<String> dates = new ArrayList<>();
        final Map<String, List<Long>> d11MatchIdByDate = new HashMap<>();

        for(final D11Match d11Match : d11Matches) {
            d11Match.setDatetime(localDateTime.plus(d11Matches.indexOf(d11Match) / 2, ChronoUnit.DAYS));
            final String date = D11MatchesByDateMapperConverter.DATE_TIME_FORMATTER.format(d11Match.getDatetime());
            if(!dates.contains(date)) {
                dates.add(date);
            }
            d11MatchIdByDate.computeIfAbsent(date, d -> new ArrayList<>()).add(d11Match.getId());
        }

        final Map<String, List<Long>> result = new D11MatchesByDateMapperConverter().convert(d11Matches);
        int count = 0;
        for(final Entry<String, List<Long>> entry : result.entrySet()) {
            assertEquals(dates.get(count), entry.getKey(), "D11 match date should equal result map date at index " + count + ".");
            ++count;
            assertEquals(d11MatchIdByDate.get(entry.getKey()), entry.getValue(),
                    "D11 match ids by date should equal result map D11 match ids by date.");
        }
    }

}
