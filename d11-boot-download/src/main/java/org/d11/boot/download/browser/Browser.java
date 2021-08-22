package org.d11.boot.download.browser;

import org.d11.boot.download.WebPage;

import java.net.URL;

/**
 * Interface that should be implemented by browsers.
 */
public interface Browser {

    /**
     * Downloads the provided URL and produces a web page.
     *
     * @param url URL for the page that will be downloaded.
     * @return Web page with title and source of the provided URL.
     */
    WebPage getWebPage(URL url);

}
