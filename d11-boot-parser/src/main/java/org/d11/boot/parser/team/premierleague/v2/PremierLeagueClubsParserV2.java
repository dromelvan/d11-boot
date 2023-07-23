package org.d11.boot.parser.team.premierleague.v2;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.jms.model.TeamData;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.team.TeamsParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V2 implementation of Premier League clubs page parser.
 */
@Slf4j
public class PremierLeagueClubsParserV2 extends AbstractJsoupParser<List<TeamData>> implements TeamsParser {

    /**
     * Premier League club link pattern for extracting team id.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("/clubs/(\\d*)/([A-Za-z-]*)/overview");

    @Override
    @SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:NestedIfDepth"})
    protected List<TeamData> doParse(final Document document) throws ParserException {
        final Element clubIndex = document.getElementsByClass("clubIndex").first();
        if(clubIndex != null) {
            final Elements lis = clubIndex.getElementsByTag("li");

            final List<TeamData> teamDatas = new ArrayList<>();

            log.debug("Found {} club elements.", lis.size());

            for(final Element li : lis) {
                final Element link = li.getElementsByTag("a").first();
                final Element clubCard = li.getElementsByClass("club-card").first();

                if(link == null || clubCard == null) {
                    throw new ParserException("Could not parse club element:\n{}", li);
                } else {
                    final Matcher matcher = LINK_PATTERN.matcher(link.attr("href"));
                    if(matcher.matches()) {
                        final TeamData teamData = new TeamData(Integer.parseInt(matcher.group(1)), matcher.group(2));
                        teamDatas.add(teamData);
                        log.debug("Parsed team {} ({}).", teamData.getName(), teamData.getId());
                    } else {
                        throw new ParserException("Could not parse Premier League team link: %s.", link.attr("href"));
                    }
                }
            }
            return teamDatas;
        }
        throw new ParserException("Club index element not found.");
    }

}
