package org.d11.boot.parser.fixtures.whoscored.v1;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.jms.model.MatchData;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.fixtures.FixturesParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * WhoScored implementation of fixture parser.
 */
@Slf4j
public class WhoScoredFixturesParserV1 extends AbstractJsoupParser<List<MatchData>> implements FixturesParser {

    /**
     * Attribute for matching to fixture data elements.
     */
    private static final String DATA_ID = "data-id";

    @Override
    protected List<MatchData> doParse(final Document document) {
        final List<MatchData> matches = new ArrayList<>();

        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, LLL d yyyy");
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate localDate = null;

        for(final Element rowElement : document.getElementsByClass("divtable-row")) {
            try {
                localDate = LocalDate.parse(rowElement.text().trim(), dateFormatter);
            } catch(DateTimeParseException e) {
                final MatchData matchData = new MatchData();
                matchData.setWhoscoredId(Long.parseLong(rowElement.attr(DATA_ID)));
                matchData.setDatetime(localDate.atTime(LocalTime.parse(rowElement.getElementsByClass("time").first().text(), timeFormatter)));

                matchData.setHomeTeamName(rowElement.getElementsByClass("home").first().text());
                matchData.setHomeTeamWhoscoredId(Long.parseLong(rowElement.getElementsByClass("home").first().attr(DATA_ID)));

                matchData.setAwayTeamName(rowElement.getElementsByClass("away").first().text());
                matchData.setAwayTeamWhoscoredId(Long.parseLong(rowElement.getElementsByClass("away").first().attr(DATA_ID)));

                matches.add(matchData);
            }
        }
        return matches;

    }

}
