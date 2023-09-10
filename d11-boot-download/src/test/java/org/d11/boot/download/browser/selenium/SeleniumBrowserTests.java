package org.d11.boot.download.browser.selenium;

import org.d11.boot.download.TestDownloader;
import org.d11.boot.download.WebPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * These tests are supposed to be used for manual testing and not for automagic testing.
 */
@Disabled
class SeleniumBrowserTests {

    /**
     * URL for the remote Selenium driver when Selenium is run as a standalone application.
     */
    private static final String STANDALONE_REMOTE_DRIVER_URL = "http://localhost:4444/wd/hub";

    /**
     * URL for the remote Selenium driver when Selenium is run in Docker.
     */
    private static final String DOCKER_REMOTE_DRIVER_URL = "http://localhost:4444";

    /**
     * Tests remote download with a ChromeBrowser against a Selenium running as a standalone application.
     */
    @Test
    void chromeBrowserStandaloneSelenium() {
        final Callable<ChromeBrowser> callable = () -> {
            final ChromeBrowser chromeBrowser = new ChromeBrowser();
            chromeBrowser.setRemoteDriverUrl(new URL(STANDALONE_REMOTE_DRIVER_URL));
            chromeBrowser.setAutoQuit(true);
            // User data dir is needed for standalone Selenium
            chromeBrowser.setUserDataDir(".chrome");
            return chromeBrowser;
        };
        final TestDownloader<ChromeBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "TestDownloader::download Selenium standalone is null");
    }

    /**
     * Tests remote download with a ChromeBrowser against a Selenium running in Docker.
     */
    @Test
    void chromeBrowserDockerSelenium() {
        final Callable<ChromeBrowser> callable = () -> {
            final ChromeBrowser chromeBrowser = new ChromeBrowser();
            chromeBrowser.setRemoteDriverUrl(new URL(DOCKER_REMOTE_DRIVER_URL));
            chromeBrowser.setAutoQuit(true);
            return chromeBrowser;
        };
        final TestDownloader<ChromeBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "TestDownloader::download Selenium Docker is null");
    }

}
