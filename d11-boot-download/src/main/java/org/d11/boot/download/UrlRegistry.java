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
