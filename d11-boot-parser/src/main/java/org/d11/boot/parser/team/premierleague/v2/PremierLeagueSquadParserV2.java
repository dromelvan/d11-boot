package org.d11.boot.parser.team.premierleague.v2;

import org.apache.commons.lang3.StringUtils;
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
 * V2 implementation of Premier League squad page parser.
 */
public class PremierLeagueSquadParserV2 extends AbstractJsoupParser<TeamData> implements TeamParser {

    /**
     * Premier League player link pattern for extracting player id.
     */
    private static final Pattern LINK_PATTERN = Pattern.compile("/players/(\\d*)/.*/overview");

    @Override
    @SuppressWarnings({"PMD.CyclomaticComplexity", "checkstyle:MultipleStringLiterals", "checkstyle:CyclomaticComplexity"})
    protected TeamData doParse(final Document document) throws ParserException {
        final TeamData teamData = new TeamData();

        for(final Element wrapper : document.getElementsByClass("wrapper")) {
            if(wrapper.hasAttr("data-team")) {
                final int teamId = Integer.parseInt(wrapper.attr("data-team"));
                teamData.setId(teamId);
            }
        }

        if(teamData.getId() == 0) {
            throw new ParserException("Team id was not found or could not be parsed.");
        }

        for(final Element clubHeaderTeamName : document.getElementsByClass("club-header__team-name")) {
            teamData.setName(clubHeaderTeamName.text());
        }

        if(teamData.getName() == null) {
            throw new ParserException("Team name was not found or could not be parsed.");
        }

        for(final Element statsCard : document.getElementsByClass("stats-card")) {
            final Matcher matcher = LINK_PATTERN.matcher(statsCard.getElementsByTag("a").attr("href"));
            if (matcher.matches()) {
                final int id = Integer.parseInt(matcher.group(1));
                final String name = statsCard.getElementsByClass("stats-card__player-name").text();
                final String shirtNumberText = statsCard.getElementsByClass("stats-card__squad-number u-show-mob-l").text();
                final Integer shirtNumber = StringUtils.isBlank(shirtNumberText) ? null : Integer.parseInt(shirtNumberText);
                final String position = statsCard.getElementsByClass("stats-card__player-position").text();
                final String nationality = statsCard.getElementsByClass("stats-card__player-country").text();
                final String photoId = statsCard.getElementsByClass("statCardImg statCardPlayer").attr("data-player");

                final PlayerData playerData = new PlayerData(id, name, shirtNumber, position, nationality, photoId, null, null);
                teamData.getPlayers().add(playerData);
            } else {
                throw new ParserException("Could not parse Premier League player link: %s.", statsCard.attr("href"));
            }
        }

        if(teamData.getPlayers().isEmpty()) {
            throw new ParserException("No players could be parsed.");
        }
        return teamData;
    }

}
