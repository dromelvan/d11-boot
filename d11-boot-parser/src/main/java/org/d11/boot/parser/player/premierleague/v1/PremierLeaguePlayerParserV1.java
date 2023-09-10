package org.d11.boot.parser.player.premierleague.v1;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.parser.AbstractJsoupParser;
import org.d11.boot.parser.model.ParsedPlayerData;
import org.d11.boot.parser.player.PlayerParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * V1 implementation of Premier League player page parser.
 */
public class PremierLeaguePlayerParserV1 extends AbstractJsoupParser<ParsedPlayerData> implements PlayerParser {

    /**
     * Premier League team link pattern for extracting team id.
     */
    private static final Pattern DATE_OF_BIRTH_PATTERN = Pattern.compile("(\\d{2}/\\d{2}/\\d{4}).*");

    /**
     * Height pattern for extracting player height.
     */
    private static final Pattern HEIGHT_PATTERN = Pattern.compile("(\\d{3})cm");

    /**
     * Sibling index for the info tag that contains position information.
     */
    private static final int POSITION_ELEMENT_SIBLING_INDEX = 3;

    /**
     * Date of birth formatter for extracting player date of birth.
     */
    private static final DateTimeFormatter DATE_OF_BIRTH_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    @SuppressWarnings("PMD.CognitiveComplexity")
    protected ParsedPlayerData doParse(final Document document) {
        final ParsedPlayerData parsedPlayerData = new ParsedPlayerData();

        final String dataPlayerAttr = "data-player";

        for (final Element playerHero : document.getElementsByClass("playerHero")) {
            final String name = playerHero.getElementsByClass("name").text();
            final String shirtNumber = playerHero.getElementsByClass("number").text();
            final String photoId = playerHero.getElementsByClass("img").attr(dataPlayerAttr);

            parsedPlayerData.setName(name);
            parsedPlayerData.setShirtNumber(Integer.parseInt(shirtNumber));
            parsedPlayerData.setPhotoId(photoId);
        }

        final String infoClass = "info";

        for (final Element info : document.getElementsByClass(infoClass)) {
            if (info.elementSiblingIndex() == POSITION_ELEMENT_SIBLING_INDEX) {
                parsedPlayerData.setPosition(info.text());
            }
        }

        for (final Element playerSidebarTable : document.getElementsByClass("playerSidebarTable")) {
            final String siteId = playerSidebarTable.getElementsByTag("tbody").attr(dataPlayerAttr);
            if (!StringUtils.isBlank(siteId)) {
                parsedPlayerData.setSiteId(Long.parseLong(siteId));
            }
        }

        for (final Element personalDetails : document.getElementsByClass("personalDetails")) {
            final String nationality = personalDetails.getElementsByClass("playerCountry").text();
            parsedPlayerData.setNationality(nationality);
            for (final Element info : personalDetails.getElementsByClass(infoClass)) {
                final Matcher heightMatcher = HEIGHT_PATTERN.matcher(info.text());
                final Matcher dateOfBirthMatcher = DATE_OF_BIRTH_PATTERN.matcher(info.text());
                if (heightMatcher.matches()) {
                    parsedPlayerData.setHeight(Integer.parseInt(heightMatcher.group(1)));
                }
                if (dateOfBirthMatcher.matches()) {
                    parsedPlayerData.setDateOfBirth(LocalDate.parse(dateOfBirthMatcher.group(1),
                                                                    DATE_OF_BIRTH_FORMATTER));
                }
            }
        }
        return parsedPlayerData;
    }

}
