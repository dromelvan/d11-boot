package org.d11.boot.parser.team.premierleague.v1;

import org.d11.boot.jms.model.PlayerData;
import org.d11.boot.jms.model.TeamData;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.team.TeamParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V1 implementation of Premier League squad page parser.
 */
public class PremierLeagueSquadParserV1 extends AbstractJsoupParser<TeamData> implements TeamParser {

    /**
     * Premier League player link pattern for extracting player id.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("/players/(\\d*)/.*/overview");

    @Override
    @SuppressWarnings({"PMD.CyclomaticComplexity", "checkstyle:MultipleStringLiterals", "checkstyle:CyclomaticComplexity"})
    protected TeamData doParse(final Document document) throws ParserException {
        final TeamData teamData = new TeamData();

        for(final Element clubHero : document.getElementsByClass("clubHero")) {
            final int teamId = Integer.parseInt(clubHero.attr("data-id"));
            final String teamName = clubHero.getElementsByClass("team js-team").text();
            teamData.setId(teamId);
            teamData.setName(teamName);
        }

        if(teamData.getId() == null) {
            throw new ParserException("Club hero element was not found or could not be parsed.");
        }

        for(final Element playerOverviewCard : document.getElementsByClass("playerOverviewCard")) {
            final Matcher matcher = LINK_PATTERN.matcher(playerOverviewCard.attr("href"));
            if(matcher.matches()) {
                final int id = Integer.parseInt(matcher.group(1));
                final String name = playerOverviewCard.getElementsByClass("name").text();
                final String shirtNumberText = playerOverviewCard.getElementsByClass("number").text();
                final Integer shirtNumber = "-".equals(shirtNumberText) ? null : Integer.parseInt(shirtNumberText);
                final String position = playerOverviewCard.getElementsByClass("position").text();
                final String nationality = playerOverviewCard.getElementsByClass("playerCountry").text();
                final String photoId = playerOverviewCard.getElementsByClass("img statCardImg").attr("data-player");

                final PlayerData playerData = new PlayerData(id, name, shirtNumber, position, nationality, photoId, null, null);
                teamData.getPlayers().add(playerData);
            } else {
                throw new ParserException("Could not parse Premier League player link: %s.", playerOverviewCard.attr("href"));
            }
        }

        if(teamData.getPlayers().isEmpty()) {
            throw new ParserException("No players could be parsed.");
        }
        return teamData;
    }

}
