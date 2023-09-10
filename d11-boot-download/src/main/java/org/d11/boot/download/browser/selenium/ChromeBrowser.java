package org.d11.boot.download.browser.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Selenium browser that uses a ChromeDriver. User data directory needs to be set when this browser is used with a
 * standalone Selenium application. Note: Headless mode results in anti robot system messing everything up on
 * WhoScored.com when running with a standalone Selenium application. If we still want to run as headless, then
 * add getCapabilities().addArguments("--headless=new"); somewhere.
 */
public class ChromeBrowser extends AbstractSeleniumBrowser<ChromeOptions> {

    /**
     * Creates a new ChromeBrowser.
     */
    public ChromeBrowser() {
        setCapabilities(new ChromeOptions());
        final Map<String, Object> images = new ConcurrentHashMap<>();
        images.put("images", 2);
        final Map<String, Object> prefs = new ConcurrentHashMap<>();
        prefs.put("profile.default_content_setting_values", images);

        setExperimentalOption("prefs", prefs);
    }

    /**
     * Sets an experimental option in the ChromeOptions.
     *
     * @param name  Name of the value that will be set.
     * @param value The value that will be set.
     */
    public final void setExperimentalOption(final String name, final Object value) {
        getCapabilities().setExperimentalOption(name, value);
    }

    /**
     * Sets the user data dir the Chrome browser will use.
     *
     * @param userDataDir The user data dir the Chrome browser will use.
     */
    public final void setUserDataDir(final String userDataDir) {
        getCapabilities().addArguments("user-data-dir=" + userDataDir);
    }

    @Override
    protected WebDriver createWebDriver() {
        return new ChromeDriver(getCapabilities());
    }

}
