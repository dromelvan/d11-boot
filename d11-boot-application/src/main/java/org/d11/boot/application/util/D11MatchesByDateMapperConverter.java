package org.d11.boot.application.util;

import org.d11.boot.application.model.D11Match;
import org.modelmapper.AbstractConverter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts a list of D11 matches to a map with match dates as key and a list of D11 matches for each
 * date as values.
 */
public class D11MatchesByDateMapperConverter extends AbstractConverter<List<D11Match>, Map<String, List<Long>>> {

    /**
     * DateTimeFormatter used to convert D11 match date to string key in the map.
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Map<String, List<Long>> convert(final List<D11Match> d11Matches) {
        final Map<String, List<Long>> d11MatchesByDate = new LinkedHashMap<>();
        for (final D11Match d11Match : d11Matches) {
            final String key = d11Match.getDate().format(DATE_TIME_FORMATTER);
            final List<Long> d11MatchIds = d11MatchesByDate.computeIfAbsent(key, k -> new ArrayList<>());
            d11MatchIds.add(d11Match.getId());
        }
        return d11MatchesByDate;
    }

}
