package org.d11.boot.download.browser.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.net.URL;

/**
 * Selenium browser that uses a FirefoxDriver. The default user data directory for this browser is .firefox.
 * This browser does not currently work with remote driver for some yet unknown reason.
 */
public class FirefoxBrowser extends AbstractSeleniumBrowser<FirefoxOptions> {

    /**
     * Creates a new FirefoxBrowser.
     *
     * @param geckoDriverUrl URL for the driver needed for the FirefoxDriver to run.
     *                       Get the driver from here: https://github.com/mozilla/geckodriver/releases
     */
    public FirefoxBrowser(final URL geckoDriverUrl) {
        setCapabilities(new FirefoxOptions());
        addPreference("permissions.default.image", 2);
        addPreference("permissions.default.stylesheet", 2);
        setHeadless(false);

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        System.setProperty("webdriver.gecko.driver", geckoDriverUrl.getFile());

        final FirefoxProfile firefoxProfile = new FirefoxProfile(new File(".firefox"));
        getCapabilities().setProfile(firefoxProfile);
    }

    /**
     * Adds a preference to the FirefoxOptions.
     *
     * @param name  Name of the value that will be set.
     * @param value The value that will be set.
     */
    public final void addPreference(final String name, final int value) {
        getCapabilities().addPreference(name, value);
    }

    /**
     * Sets the headless status in the FirefoxOptions.
     *
     * @param headless The headless status that will be set.
     */
    public final void setHeadless(final boolean headless) {
        getCapabilities().setHeadless(headless);
    }

    @Override
    protected WebDriver createWebDriver() {
        return new FirefoxDriver(getCapabilities());
    }

}
