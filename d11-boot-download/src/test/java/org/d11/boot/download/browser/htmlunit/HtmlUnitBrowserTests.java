package org.d11.boot.download.browser.htmlunit;

import org.d11.boot.download.TestDownloader;
import org.d11.boot.download.WebPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * These tests are supposed to be used for manual testing and not automagic.
 */
@Disabled
class HtmlUnitBrowserTests {

    /**
     * Tests download with a HtmlUnitBrowser.
     */
    @Test
    void htmlUnitBrowser() {
        final Callable<HtmlUnitBrowser> callable = HtmlUnitBrowser::new;

        final TestDownloader<HtmlUnitBrowser> testDownloader = new TestDownloader<>(callable);
        final WebPage webPage = testDownloader.download();

        assertNotNull(webPage);
    }

}
