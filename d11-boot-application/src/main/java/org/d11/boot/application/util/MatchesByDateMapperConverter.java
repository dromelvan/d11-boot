package org.d11.boot.application.util;

import org.d11.boot.application.model.Match;
import org.modelmapper.AbstractConverter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts a list of matches to a map with match dates as key and a list of matches for each
 * date as values.
 */
public class MatchesByDateMapperConverter extends AbstractConverter<List<Match>, Map<String, List<Long>>> {

    /**
     * DateTimeFormatter used to convert match datetime to string key in the map.
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Map<String, List<Long>> convert(final List<Match> matches) {
        final Map<String, List<Long>> matchesByDate = new LinkedHashMap<>();
        for (final Match match : matches) {
            final String key = match.getDatetime().toLocalDate().format(DATE_TIME_FORMATTER);
            final List<Long> matchIds = matchesByDate.computeIfAbsent(key, k -> new ArrayList<>());
            matchIds.add(match.getId());
        }
        return matchesByDate;
    }

}
