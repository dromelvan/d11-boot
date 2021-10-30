package org.d11.boot.parser.match.whoscored.v1;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.jms.model.MatchData;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.match.MatchParser;
import org.d11.boot.parser.match.whoscored.v1.model.MatchCenterData;
import org.d11.boot.parser.match.whoscored.v1.model.MatchHeader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;

/**
 * V1 implementation of WhoScored match page parser.
 */
@Slf4j
public class WhoScoredMatchParserV1 extends AbstractJsoupParser<MatchData> implements MatchParser {

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    protected MatchData doParse(final Document document) throws ParserException {
        final Elements elements = document.getElementsByTag("script");
        log.debug("Found {} script elements.", elements.size());

        MatchHeader matchHeader = null;
        MatchCenterData matchCenterData = null;

        for(final Element element : elements) {
            log.trace("Matching element {}", element.data());

            final Matcher matchHeaderMatcher = MatchHeader.MATCH_HEADER_PATTERN.matcher(element.data());
            if(matchHeaderMatcher.matches()) {
                log.debug("Matched match header element {}", element.data());

                matchHeader = getGson().fromJson(matchHeaderMatcher.group(1), MatchHeader.class);
            }

            final Matcher matchCenterDataMatcher = MatchCenterData.MATCH_CENTRE_DATA_PATTERN.matcher(element.data());
            if(matchCenterDataMatcher.matches()) {
                log.debug("Matched match center data element {}...", element.data().substring(0, 100));

                // The match centre data is a map so we can map it to a Map to see everything in it if necessary.
                matchCenterData = getGson().fromJson(matchCenterDataMatcher.group(1), MatchCenterData.class);
            }
        }

        if(matchHeader != null) {
            final MatchData matchData = matchHeader.getMatchData();

            if(matchCenterData == null) {
                log.debug("Script element with match centre data was not found.");
            } else {
                matchData.setGoals(matchCenterData.getGoals());
                matchData.setPlayers(matchCenterData.getPlayerMatchDatas());
            }

            log.debug("Parsed match data:\n{}", getGson().toJson(matchData));

            return matchData;
        }

        throw new ParserException("Script element with match header data was not found.");
    }

}
