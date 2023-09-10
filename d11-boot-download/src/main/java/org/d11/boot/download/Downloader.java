package org.d11.boot.download;

import lombok.extern.slf4j.Slf4j;
import org.d11.boot.download.browser.Browser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Base class for downloaders.
 *
 * @param <T> Browser type used by the downloader.
 */
@Slf4j
public class Downloader<T extends Browser> {

    /**
     * Registry of URLs from the url-registry.properties file.
     */
    protected static final UrlRegistry URL_REGISTRY = new UrlRegistry();

    /**
     * Callable that should produce a properly configured Browser.
     */
    private final Callable<T> callable;

    /**
     * Creates a new downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public Downloader(final Callable<T> callable) {
        this.callable = callable;
    }

    /**
     * Creates a new browser instance and returns it.
     *
     * @return A new browser instance.
     */
    @SuppressWarnings({"PMD.AvoidCatchingGenericException", "checkstyle:IllegalCatch"})
    protected T getBrowser() {
        try {
            return this.callable.call();
        } catch (final Exception e) {
            // Have to catch Exception here since that's what Callable.call() throws.
            throw new DownloadException("Could not create browser.", e);
        }
    }

    /**
     * Downloads the provided URL.
     *
     * @param url URL for the web page that will be downloaded.
     * @return Web page with title and source of the provided URL.
     */
    public WebPage download(final URL url) {
        return getBrowser().getWebPage(url);
    }

    /**
     * Downloads a provided URL as bytes.
     *
     * @param url URL for the resource that will be downloaded.
     * @return Byte array representing the data from the URL.
     */
    public byte[] downloadBytes(final URL url) {
        try (InputStream inputStream = url.openStream()) {
            return inputStream.readAllBytes();
        } catch (final IOException e) {
            throw new DownloadException("Could not download image " + url + ".", e);
        }
    }

}
