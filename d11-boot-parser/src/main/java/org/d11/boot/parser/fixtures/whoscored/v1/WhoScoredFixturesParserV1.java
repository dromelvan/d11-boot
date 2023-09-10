package org.d11.boot.parser.fixtures.whoscored.v1;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.fixtures.FixturesParser;
import org.d11.boot.parser.model.ParsedMatchData;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * WhoScored implementation of fixture parser. The WhoScored files have to be downloaded by going to their fixtures
 * page, clicking backwards/forwards to whichever month we're interested in, inspecting, copying the top html element
 * and saving to a file.
 */
@Slf4j
public class WhoScoredFixturesParserV1 extends AbstractJsoupParser<List<ParsedMatchData>> implements FixturesParser {

    /**
     * Attribute for matching to fixture data elements.
     */
    private static final String DATA_ID = "data-id";

    @Override
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    protected List<ParsedMatchData> doParse(final Document document) throws ParserException {
        final List<ParsedMatchData> matches = new ArrayList<>();

        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, LLL d yyyy");
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate localDate = null;

        for (final Element rowElement : document.getElementsByClass("divtable-row")) {
            if (rowElement.hasAttr(DATA_ID)) {
                if (localDate == null) {
                    throw new ParserException("Date null when handling row element %s", rowElement);
                } else {
                    final ParsedMatchData parsedMatchData = new ParsedMatchData();

                    parsedMatchData.setSiteId(Long.parseLong(rowElement.attr(DATA_ID)));

                    final Element timeElement = getFirstChildElementByClass(rowElement, "time");
                    parsedMatchData.setDatetime(localDate.atTime(LocalTime.parse(timeElement.text(), timeFormatter)));

                    final Element homeElement = getFirstChildElementByClass(rowElement, "home");
                    parsedMatchData.setHomeTeamName(homeElement.text());
                    parsedMatchData.setHomeTeamSiteId(Long.parseLong(homeElement.attr(DATA_ID)));

                    final Element awayElement = getFirstChildElementByClass(rowElement, "away");
                    parsedMatchData.setAwayTeamName(awayElement.text());
                    parsedMatchData.setAwayTeamSiteId(Long.parseLong(awayElement.attr(DATA_ID)));

                    matches.add(parsedMatchData);
                }
            } else {
                localDate = LocalDate.parse(rowElement.text().trim(), dateFormatter);
            }
        }
        return matches;
    }

}
