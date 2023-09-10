package org.d11.boot.parser.team.premierleague.v2;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedPlayerData;
import org.d11.boot.parser.model.ParsedTeamData;
import org.d11.boot.parser.team.TeamParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V2 implementation of Premier League squad page parser.
 */
public class PremierLeagueSquadParserV2 extends AbstractJsoupParser<ParsedTeamData> implements TeamParser {

    /**
     * Premier League player link pattern for extracting player id.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("/players/(\\d*)/.*/overview");

    /**
     * Data-team attribute name.
     */
    private static final String DATA_TEAM = "data-team";

    @Override
    protected ParsedTeamData doParse(final Document document) throws ParserException {
        final ParsedTeamData parsedTeamData = new ParsedTeamData();

        for (final Element wrapper : document.getElementsByClass("wrapper")) {
            if (wrapper.hasAttr(DATA_TEAM)) {
                final int teamId = Integer.parseInt(wrapper.attr(DATA_TEAM));
                parsedTeamData.setSiteId(teamId);
            }
        }

        if (parsedTeamData.getSiteId() == 0) {
            throw new ParserException("Team id was not found or could not be parsed.");
        }

        for (final Element clubHeaderTeamName : document.getElementsByClass("club-header__team-name")) {
            parsedTeamData.setName(clubHeaderTeamName.text());
        }

        if (parsedTeamData.getName() == null) {
            throw new ParserException("Team name was not found or could not be parsed.");
        }

        for (final Element statsCard : document.getElementsByClass("stats-card")) {
            final Matcher matcher = LINK_PATTERN.matcher(statsCard.getElementsByTag("a").attr(HREF));
            if (matcher.matches()) {
                final long siteId = Long.parseLong(matcher.group(1));
                final String name = statsCard.getElementsByClass("stats-card__player-name").text();
                final String shirtNumberText =
                        statsCard.getElementsByClass("stats-card__squad-number u-show-mob-l").text();
                final Integer shirtNumber =
                        StringUtils.isBlank(shirtNumberText) ? null : Integer.parseInt(shirtNumberText);
                final String position = statsCard.getElementsByClass("stats-card__player-position").text();
                final String nationality = statsCard.getElementsByClass("stats-card__player-country").text();
                final String photoId = statsCard.getElementsByClass("statCardImg statCardPlayer").attr("data-player");

                final ParsedPlayerData parsedPlayerData = ParsedPlayerData.builder()
                        .siteId(siteId)
                        .name(name)
                        .shirtNumber(shirtNumber)
                        .position(position)
                        .nationality(nationality)
                        .photoId(photoId)
                        .build();

                parsedTeamData.getPlayers().add(parsedPlayerData);
            } else {
                throw new ParserException("Could not parse Premier League player link: %s.", statsCard.attr(HREF));
            }
        }

        if (parsedTeamData.getPlayers().isEmpty()) {
            throw new ParserException("No players could be parsed.");
        }
        return parsedTeamData;
    }

}
