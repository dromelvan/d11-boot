package org.d11.boot.parser.team.premierleague.v1;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedTeamData;
import org.d11.boot.parser.team.TeamsParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V1 implementation of Premier League clubs page parser.
 */
@Slf4j
public class PremierLeagueClubsParserV1 extends AbstractJsoupParser<List<ParsedTeamData>> implements TeamsParser {

    /**
     * Premier League club link pattern for extracting team id.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("/clubs/(\\d*)/[A-Za-z-]*/overview");

    @Override
    protected List<ParsedTeamData> doParse(final Document document) throws ParserException {
        final Element clubIndex = getFirstChildElementByClass(document, "clubIndex");
        final Elements lis = clubIndex.getElementsByTag("li");

        final List<ParsedTeamData> parsedTeamDatas = new ArrayList<>();

        LOGGER.debug("Found {} club elements.", lis.size());

        for (final Element li : lis) {
            final Element link = li.getElementsByTag("a").first();
            final Element clubName = li.getElementsByClass("clubName").first();

            if (link == null || clubName == null) {
                throw new ParserException("Could not parse club element:\n{}", li);
            } else {
                final Matcher matcher = LINK_PATTERN.matcher(link.attr(HREF));
                if (matcher.matches()) {
                    final ParsedTeamData parsedTeamData = ParsedTeamData.builder()
                            .siteId(Integer.parseInt(matcher.group(1)))
                            .name(clubName.text())
                            .build();

                    parsedTeamDatas.add(parsedTeamData);
                    LOGGER.debug("Parsed team {} ({}).", parsedTeamData.getName(), parsedTeamData.getSiteId());
                } else {
                    throw new ParserException("Could not parse Premier League team link: %s.", link.attr(HREF));
                }
            }
        }
        return parsedTeamDatas;
    }

}
