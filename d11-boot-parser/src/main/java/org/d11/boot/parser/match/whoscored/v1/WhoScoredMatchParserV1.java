package org.d11.boot.parser.match.whoscored.v1;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.api.model.GoalBaseDTO;
import org.d11.boot.api.model.TeamBaseDTO;
import org.d11.boot.api.model.UpdateMatchDTO;
import org.d11.boot.parser.AbstractParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.match.MatchParser;
import org.d11.boot.parser.match.whoscored.v1.model.MatchCenterData;
import org.d11.boot.parser.match.whoscored.v1.model.MatchHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.regex.Matcher;

/**
 * V1 implementation of WhoScored match page parser.
 */
@Slf4j
public class WhoScoredMatchParserV1 extends AbstractParser<UpdateMatchDTO> implements MatchParser {

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    protected UpdateMatchDTO doParse(final String input) throws ParserException {
        final Document document = Jsoup.parse(input);

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
            final UpdateMatchDTO updateMatchDTO = new UpdateMatchDTO()
                    .whoscoredId(matchHeader.getMatchId())
                    .homeTeam(new TeamBaseDTO()
                            .whoscoredId(matchHeader.getHomeTeamId())
                            .name(matchHeader.getHomeTeamName()))
                    .awayTeam(new TeamBaseDTO()
                            .whoscoredId(matchHeader.getAwayTeamId())
                            .name(matchHeader.getAwayTeamName()))
                    .datetime(matchHeader.getDatetime())
                    .elapsed(matchHeader.getElapsed())
                    .status(matchHeader.getStatus());
            if(matchCenterData == null) {
                log.debug("Script element with match centre data was not found.");
            } else {
                final List<GoalBaseDTO> homeTeamGoals = matchCenterData.getHome().getGoals(matchCenterData.getPlayerIdNameDictionary());
                final List<GoalBaseDTO> awayTeamGoals = matchCenterData.getAway().getGoals(matchCenterData.getPlayerIdNameDictionary());

                updateMatchDTO.getGoals().addAll(homeTeamGoals);
                updateMatchDTO.getGoals().addAll(awayTeamGoals);
                updateMatchDTO.getPlayerMatchStats().addAll(matchCenterData.getHome().getPlayerMatchStats(homeTeamGoals.size()));
                updateMatchDTO.getPlayerMatchStats().addAll(matchCenterData.getAway().getPlayerMatchStats(awayTeamGoals.size()));
            }

            log.debug("Parsed update match DTO:\n{}", getGson().toJson(updateMatchDTO));

            return updateMatchDTO;
        }

        throw new ParserException("Script element with match header data was not found.");
    }

}
