package org.d11.boot.parser.team.premierleague.v1;

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
 * V1 implementation of Premier League squad page parser.
 */
public class PremierLeagueSquadParserV1 extends AbstractJsoupParser<ParsedTeamData> implements TeamParser {

    /**
     * Premier League player link pattern for extracting player id.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("/players/(\\d*)/.*/overview");

    @Override
    protected ParsedTeamData doParse(final Document document) throws ParserException {
        final ParsedTeamData parsedTeamData = new ParsedTeamData();

        for (final Element clubHero : document.getElementsByClass("clubHero")) {
            final int teamId = Integer.parseInt(clubHero.attr("data-id"));
            final String teamName = clubHero.getElementsByClass("team js-team").text();
            parsedTeamData.setSiteId(teamId);
            parsedTeamData.setName(teamName);
        }

        if (parsedTeamData.getSiteId() == null) {
            throw new ParserException("Club hero element was not found or could not be parsed.");
        }

        for (final Element playerOverviewCard : document.getElementsByClass("playerOverviewCard")) {
            final String href = playerOverviewCard.attr(HREF);
            final Matcher matcher = LINK_PATTERN.matcher(href);
            if (matcher.matches()) {
                final long siteId = Long.parseLong(matcher.group(1));
                final String name = playerOverviewCard.getElementsByClass("name").text();
                final String shirtNumberText = playerOverviewCard.getElementsByClass("number").text();
                final Integer shirtNumber = "-".equals(shirtNumberText) ? null : Integer.parseInt(shirtNumberText);
                final String position = playerOverviewCard.getElementsByClass("position").text();
                final String nationality = playerOverviewCard.getElementsByClass("playerCountry").text();
                final String photoId = playerOverviewCard.getElementsByClass("img statCardImg").attr("data-player");

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
                throw new ParserException("Could not parse Premier League player link: %s.",
                                          playerOverviewCard.attr(HREF));
            }
        }

        if (parsedTeamData.getPlayers().isEmpty()) {
            throw new ParserException("No players could be parsed.");
        }
        return parsedTeamData;
    }

}
