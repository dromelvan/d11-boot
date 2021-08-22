package org.d11.boot.download.browser.selenium;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.d11.boot.download.WebPage;
import org.d11.boot.download.browser.Browser;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Base class for Selenium browsers.
 *
 * @param <T> Capabilities type used by the browser.
 */
@Data
public abstract class AbstractSeleniumBrowser<T extends Capabilities> implements Browser {

    /**
     * Default timeout value in seconds used by Selenium browsers.
     */
    public static final int DEFAULT_TIMEOUT = 45;

    /**
     * Capabilities used by the browser.
     */
    private T capabilities;
    /**
     * Web driver used by the browsers.
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private WebDriver webDriver;
    /**
     * URL for a remote Selenium server. If this is not null then a remote web driver will be used.
     */
    private URL remoteDriverUrl;
    /**
     * Timeout set for the browser web driver.
     */
    private int timeout = DEFAULT_TIMEOUT;
    /**
     * Do/do not auto quit the web driver after the download is complete.
     */
    private boolean autoQuit;

    @Override
    public WebPage getWebPage(final URL url) {
        getWebDriver().get(url.toString());
        final WebPage webPage = new WebPage(getWebDriver().getTitle(), getWebDriver().getPageSource());
        if(isAutoQuit()) {
            quit();
        }
        return webPage;
    }

    /**
     * Implement in subclasses to create a web driver compatible with the capabilities type.
     *
     * @return Properly configured web driver.
     */
    protected abstract WebDriver createWebDriver();

    /**
     * Quits the current web driver and sets the current web driver to null.
     */
    @SuppressWarnings("PMD.NullAssignment")
    protected void quit() {
        if(this.webDriver != null) {
            this.webDriver.quit();
            this.webDriver = null;
        }
    }

    /**
     * Creates a remote web driver if the remote driver URL is not null or one compatible with the capabilities type
     * if the remove web driver URL is null.
     *
     * @return Web driver that will be used by the browser until it is quit.
     */
    private WebDriver getWebDriver() {
        if(this.webDriver == null) {
            if(this.remoteDriverUrl == null) {
                this.webDriver = createWebDriver();
            } else {
                this.webDriver = new RemoteWebDriver(this.remoteDriverUrl, getCapabilities());
            }
            this.webDriver.manage().timeouts().pageLoadTimeout(this.timeout, TimeUnit.SECONDS);
        }
        return this.webDriver;
    }

}
