package org.d11.boot.download.browser.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.d11.boot.download.DownloadException;
import org.d11.boot.download.WebPage;
import org.d11.boot.download.browser.Browser;

import java.io.IOException;
import java.net.URL;

/**
 * Browser implementation that uses HtmlUnit.
 */
public class HtmlUnitBrowser implements Browser {

    @Override
    public WebPage getWebPage(final URL url) {
        try (WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            final HtmlPage htmlPage = webClient.getPage(url);
            final WebResponse webResponse = htmlPage.getWebResponse();
            return new WebPage(htmlPage.getTitleText(), webResponse.getContentAsString());
        } catch (final IOException e) {
            throw new DownloadException("Could not download URL " + url + ".", e);
        }
    }

}
