package org.d11.boot.parser.player.premierleague.v2;

import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.ParserException;
import org.d11.boot.parser.model.ParsedPlayerData;
import org.d11.boot.parser.player.PlayerParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V2 implementation of Premier League player page parser.
 */
public class PremierLeaguePlayerParserV2 extends AbstractJsoupParser<ParsedPlayerData> implements PlayerParser {

    /**
     * Premier League player position name for matching position.
     */
    private static final Pattern POSITION_PATTERN = Pattern.compile("Goalkeeper|Defender|Midfielder|Forward");

    /**
     * Premier League team link pattern for extracting team id.
     */
    private static final Pattern DATE_OF_BIRTH_PATTERN = Pattern.compile("(\\d{2}/\\d{2}/\\d{4}).*");

    /**
     * Height pattern for extracting player height.
     */
    private static final Pattern HEIGHT_PATTERN = Pattern.compile("(\\d{3})cm");

    /**
     * Date of birth formatter for extracting player date of birth.
     */
    private static final DateTimeFormatter DATE_OF_BIRTH_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    protected ParsedPlayerData doParse(final Document document) throws ParserException {
        final ParsedPlayerData parsedPlayerData = new ParsedPlayerData();
        final String dataPlayerAttr = "data-player";

        for (final Element div : document.getElementsByTag("div")) {
            if (div.hasAttr(dataPlayerAttr)) {
                parsedPlayerData.setSiteId(Long.parseLong(div.attr(dataPlayerAttr)));
            }
        }

        for (final Element playerHeader : document.getElementsByClass("player-header")) {
            final String name = playerHeader.getElementsByClass("player-header__name").text();
            final String shirtNumber = playerHeader
                    .getElementsByClass("player-header__player-number player-header__player-number--small").text();
            final String photoId = playerHeader.getElementsByClass("img").attr(dataPlayerAttr);

            parsedPlayerData.setName(name);
            parsedPlayerData.setShirtNumber(Integer.parseInt(shirtNumber));
            parsedPlayerData.setPhotoId(photoId);
        }

        for (final Element playerOverviewInfo : document.getElementsByClass("player-overview__info")) {
            final Matcher positionMatcher = POSITION_PATTERN.matcher(playerOverviewInfo.text());
            if (positionMatcher.matches()) {
                parsedPlayerData.setPosition(playerOverviewInfo.text());
            }
        }

        final Element playerCountryElement = getFirstChildElementByClass(document, "player-info__player-country");
        parsedPlayerData.setNationality(playerCountryElement.text());

        for (final Element playerInfoInfo : document.getElementsByClass("player-info__info")) {
            final Matcher dateOfBirthMatcher = DATE_OF_BIRTH_PATTERN.matcher(playerInfoInfo.text());
            if (dateOfBirthMatcher.matches()) {
                parsedPlayerData.setDateOfBirth(LocalDate.parse(dateOfBirthMatcher.group(1),
                                                                DATE_OF_BIRTH_FORMATTER));
            } else {
                final Matcher heightMatcher = HEIGHT_PATTERN.matcher(playerInfoInfo.text());
                if (heightMatcher.matches()) {
                    parsedPlayerData.setHeight(Integer.parseInt(heightMatcher.group(1)));
                }
            }
        }

        return parsedPlayerData;
    }

}
