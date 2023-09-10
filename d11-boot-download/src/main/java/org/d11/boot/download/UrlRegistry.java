package org.d11.boot.download;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Provides URLs from url-registry.properties.
 */
@Slf4j
public class UrlRegistry extends Properties {

    @Serial
    private static final long serialVersionUID = -5972140787978989591L;

    /**
     * Creates a new registry and loads data from /url-registry.properties.
     */
    public UrlRegistry() {
        try (InputStream inputStream = getClass().getResourceAsStream("/url-registry.properties")) {
            load(inputStream);
        } catch (final IOException e) {
            LOGGER.error("URL registry loading failed: ", e);
        }
    }

    /**
     * Gets the url.test property.
     *
     * @return Test URL.
     */
    public URL getTestUrl() {
        return getUrl(getProperty("url.test"));
    }

    /**
     * Gets the url.whoscored.match property.
     *
     * @param whoscoredId The match id on WhoScored.
     * @return Whoscored match URL.
     */
    public URL getWhoscoredMatchUrl(final long whoscoredId) {
        return getUrl(String.format(getProperty("url.whoscored.match"), whoscoredId));
    }

    /**
     * Gets the url.premierleague.clubs property.
     *
     * @return Premier League clubs URL.
     */
    public URL getPremierLeagueClubsUrl() {
        return getUrl(getProperty("url.premierleague.clubs"));
    }

    /**
     * Gets the url.premierleague.squad property.
     *
     * @param teamId The team id.
     * @param name   The team name.
     * @return Premier League team squad url.
     */
    public URL getPremierLeagueSquadUrl(final int teamId, final String name) {
        return getUrl(String.format(getProperty("url.premierleague.squad"), teamId, formatName(name)));
    }

    /**
     * Gets the url.premierleague.player property.
     *
     * @param playerId The player id.
     * @param name     The player name.
     * @return Premier League player url.
     */
    public URL getPremierLeaguePlayerUrl(final int playerId, final String name) {
        return getUrl(String.format(getProperty("url.premierleague.player"), playerId, formatName(name)));
    }

    /**
     * Gets the url.premierleague.playerPhoto property.
     *
     * @param photoId The photo id.
     * @return Premier League player photo URL.
     */
    public URL getPremierLeaguePlayerPhotoUrl(final String photoId) {
        return getUrl(String.format(getProperty("url.premierleague.playerPhoto"), photoId));
    }

    /**
     * Creates a new URL from a string.
     *
     * @param urlString String representing a URL.
     * @return URL for the provided string.
     */
    private URL getUrl(final String urlString) {
        try {
            return new URL(urlString);
        } catch (final MalformedURLException e) {
            throw new DownloadException("Malformed URL: " + urlString, e);
        }
    }

    /**
     * Replaces spaces in a name with -s.
     *
     * @param name The name.
     * @return The name with spaces replaced by -s.
     */
    private String formatName(final String name) {
        return name.replace(StringUtils.SPACE, "-");
    }

}
