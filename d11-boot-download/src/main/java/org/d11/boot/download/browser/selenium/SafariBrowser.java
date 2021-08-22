package org.d11.boot.download.browser.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Selenium browser that uses a SafariDriver.
 */
public class SafariBrowser extends AbstractSeleniumBrowser<SafariOptions> {

    /**
     * Creates a new SafariBrowser.
     */
    public SafariBrowser() {
        setCapabilities(new SafariOptions());
    }

    @Override
    protected WebDriver createWebDriver() {
        return new SafariDriver(getCapabilities());
    }

}
