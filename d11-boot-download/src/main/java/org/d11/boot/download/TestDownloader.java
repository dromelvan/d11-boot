package org.d11.boot.download;

import org.d11.boot.download.browser.Browser;

import java.util.concurrent.Callable;

/**
 * Downloader that downloads a test url.
 *
 * @param <T> Browser type used by the downloader.
 */
public class TestDownloader<T extends Browser> extends Downloader<T> {

    /**
     * Creates a new test downloader.
     *
     * @param callable Callable that should produce a properly configured Browser.
     */
    public TestDownloader(final Callable<T> callable) {
        super(callable);
    }

    /**
     * Downloads the test URL.
     *
     * @return Web page with title and source of the test URL.
     */
    public WebPage download() {
        return download(URL_REGISTRY.getTestUrl());
    }

}
