package org.d11.boot.parser.match.whoscored.v1;

import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.match.MatchParser;
import org.d11.boot.parser.match.whoscored.v1.model.MatchCenterData;
import org.d11.boot.parser.match.whoscored.v1.model.MatchHeader;
import org.d11.boot.parser.model.ParsedMatchData;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;

/**
 * V1 implementation of WhoScored match page parser.
 */
public class WhoScoredMatchParserV1 extends AbstractJsoupParser<ParsedMatchData> implements MatchParser {

    @Override
    protected ParsedMatchData doParse(final Document document) throws ParserException {
        final Elements elements = document.getElementsByTag("script");

        MatchHeader matchHeader = null;
        MatchCenterData matchCenterData = null;

        for (final Element element : elements) {
            final Matcher matchHeaderMatcher = MatchHeader.MATCH_HEADER_PATTERN.matcher(element.data());
            if (matchHeaderMatcher.matches()) {
                matchHeader = deserialize(matchHeaderMatcher.group(1), MatchHeader.class);
            }

            final Matcher matchCenterDataMatcher = MatchCenterData.MATCH_CENTRE_DATA_PATTERN.matcher(element.data());
            if (matchCenterDataMatcher.matches()) {
                // The match centre data is a map, so we can also map it to a Map while debugging to see everything in
                // it if necessary.
                matchCenterData = deserialize(matchCenterDataMatcher.group(1), MatchCenterData.class);
            }
        }

        if (matchHeader == null) {
            throw new ParserException("Script element with match header data was not found.");
        }

        final ParsedMatchData parsedMatchData = matchHeader.getMatchData();

        if (matchCenterData != null) {
            parsedMatchData.setGoals(matchCenterData.getGoals());
            parsedMatchData.setPlayers(matchCenterData.getPlayerMatchDatas());
        }

        return parsedMatchData;
    }

}
