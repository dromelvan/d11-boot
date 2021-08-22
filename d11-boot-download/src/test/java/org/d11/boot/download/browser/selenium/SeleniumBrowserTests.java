package org.d11.boot.download.browser.selenium;

import org.d11.boot.download.TestDownloader;
import org.d11.boot.download.WebPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * These tests are supposed to be used for manual testing and not automagic.
 */
@Disabled
public class SeleniumBrowserTests {

    /**
     * URL for the remote Selenium driver.
     */
    private static final String REMOTE_DRIVER_URL = "http://localhost:4444/wd/hub";

    /**
     * Tests download with a ChromeBrowser.
     */
    @Test
    public void chromeBrowser() {
        final Callable<ChromeBrowser> callable = () -> {
            final ChromeBrowser chromeBrowser = new ChromeBrowser();
            chromeBrowser.setAutoQuit(true);
            return chromeBrowser;
        };
        final TestDownloader<ChromeBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "Test webpage with ChromeBrowser should not be null.");
    }

    /**
     * Tests remote download with a ChromeBrowser.
     */
    @Test
    public void chromeBrowserRemote() {
        final Callable<ChromeBrowser> callable = () -> {
            final ChromeBrowser chromeBrowser = new ChromeBrowser();
            chromeBrowser.setRemoteDriverUrl(new URL(REMOTE_DRIVER_URL));
            chromeBrowser.setAutoQuit(true);
            return chromeBrowser;
        };
        final TestDownloader<ChromeBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "Test webpage with ChromeBrowser remotely downloaded should not be null.");
    }

    /**
     * Tests download with a FirefoxBrowser.
     */
    @Test
    public void firefoxBrowser() {
        final URL geckoDriverUrl = getClass().getResource("/geckodriver/geckodriver-v0.29.1-macos");
        final Callable<FirefoxBrowser> callable = () -> {
            final FirefoxBrowser firefoxBrowser = new FirefoxBrowser(geckoDriverUrl);
            firefoxBrowser.setAutoQuit(false);
            return firefoxBrowser;
        };
        final TestDownloader<FirefoxBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "Test webpage with FirefoxBrowser should not be null.");
    }

    /**
     * Tests download with a SafariBrowser.
     */
    @Test
    public void safariBrowser() {
        final Callable<SafariBrowser> callable = () -> {
            final SafariBrowser safariBrowser = new SafariBrowser();
            safariBrowser.setAutoQuit(true);
            return safariBrowser;
        };
        final TestDownloader<SafariBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "Test webpage with SafariBrowser should not be null.");
    }

    /**
     * Tests download with a SafariBrowser.
     */
    @Test
    public void safariBrowserRemote() {
        final Callable<SafariBrowser> callable = () -> {
            final SafariBrowser safariBrowser = new SafariBrowser();
            safariBrowser.setRemoteDriverUrl(new URL(REMOTE_DRIVER_URL));
            safariBrowser.setAutoQuit(true);
            return safariBrowser;
        };
        final TestDownloader<SafariBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage, "Test webpage with SafariBrowser remotely downloaded should not be null.");
    }

}
