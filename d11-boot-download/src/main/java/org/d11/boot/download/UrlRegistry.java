package org.d11.boot.download;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Provides URLs from url-registry.properties.
 */
@Slf4j
public class UrlRegistry extends Properties {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new registry and loads data from /url-registry.properties.
     */
    public UrlRegistry() {
        try(InputStream inputStream = getClass().getResourceAsStream("/url-registry.properties")) {
            load(inputStream);
        } catch(IOException e) {
            log.error("URL registry loading failed: ", e);
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
     * @param whoscoredId Whoscored id for the match the URL should point to.
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
     * @param id   Id of the Premier League team.
     * @param name Name of the Premier League team.
     * @return Premier League team squad url.
     */
    public URL getPremierLeagueSquadUrl(final int id, final String name) {
        return getUrl(String.format(getProperty("url.premierleague.squad"), id, name.replace(" ", "-")));
    }

    /**
     * Gets the url.premierleague.player property.
     *
     * @param id   Id of the Premier League player.
     * @param name Name of the Premier League player.
     * @return Premier League player url.
     */
    public URL getPremierLeaguePlayerUrl(final int id, final String name) {
        return getUrl(String.format(getProperty("url.premierleague.player"), id, name.replace(" ", "-")));
    }

    /**
     * Gets the url.premierleague.playerPhoto property.
     *
     * @param photoId Id of the Premier League player photo.
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
        } catch(MalformedURLException e) {
            throw new DownloadException("Malformed URL: " + urlString, e);
        }
    }

}
