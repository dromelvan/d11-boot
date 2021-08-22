package org.d11.boot.download.browser.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Selenium browser that uses a ChromeDriver. The default user data directory for this browser is .chrome.
 * Note: Headless mode results in anti robot system messing everything up on WhoScored.com.
 */
public class ChromeBrowser extends AbstractSeleniumBrowser<ChromeOptions> {

    /**
     * Creates a new ChromeBrowser.
     */
    public ChromeBrowser() {
        setCapabilities(new ChromeOptions());
        final Map<String, Object> images = new HashMap<>();
        images.put("images", 2);
        final Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values", images);

        setHeadless(false);
        setExperimentalOption("prefs", prefs);
        setUserDataDir(".chrome");
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
     * Sets the headless status in the ChromeOptions.
     *
     * @param headless The headless status that will be set.
     */
    public final void setHeadless(final boolean headless) {
        getCapabilities().setHeadless(headless);
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
